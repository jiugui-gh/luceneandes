package chapter2.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.IndexOptions;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.IndexWriterConfig.OpenMode;
import org.apache.lucene.store.FSDirectory;

import chapter2.ik.IKAnalyzer6x;

public class IndexDocs {

    public static void main(String[] args) throws Exception {
        String words = getWords();
        IKAnalyzer6x analyzer = new IKAnalyzer6x(true);
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        config.setOpenMode(OpenMode.CREATE_OR_APPEND);
        Path indexPath = Paths.get("indexdir");
        FSDirectory dir = FSDirectory.open(indexPath);
        IndexWriter indexWriter = new IndexWriter(dir,config);
        
        FieldType fieldType = new FieldType();
        fieldType.setIndexOptions(IndexOptions.DOCS_AND_FREQS_AND_POSITIONS_AND_OFFSETS);
        fieldType.setStored(true);
        fieldType.setTokenized(true);
        fieldType.setStoreTermVectors(true);
        
        
        
        Document doc = new Document();
        doc.add(new Field("content",words,fieldType));
        
        indexWriter.addDocument(doc);
        
        indexWriter.commit();
        indexWriter.close();
       
    }

    private static String getWords() throws IOException {
        InputStream in = IndexDocs.class.getClassLoader().getResourceAsStream("testfile/news.txt");
        
        InputStreamReader isr = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(isr);
        String line = null;
        StringBuilder sb = new StringBuilder();
        while((line = br.readLine()) != null) {
            sb.append(line);
        }
        return sb.toString();
    }
}
