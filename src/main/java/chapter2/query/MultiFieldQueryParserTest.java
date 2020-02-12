package chapter2.query;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.QueryParser.Operator;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import chapter2.ik.IKAnalyzer6x;

public class MultiFieldQueryParserTest {

    public static void main(String[] args) throws Exception {
        String []fields = {"title","content"};
        Path indexPath = Paths.get("indexdir");
        Directory dir = FSDirectory.open(indexPath);
        DirectoryReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        IKAnalyzer6x analyzer = new IKAnalyzer6x(true);
        MultiFieldQueryParser parser = new MultiFieldQueryParser(fields, analyzer);
        parser.setDefaultOperator(Operator.AND);
        Query multiFieldQuery = parser.parse("特朗普");
        System.out.println("query:" + multiFieldQuery);
        
        TopDocs tds = searcher.search(multiFieldQuery, 10);
    
        for(ScoreDoc sd : tds.scoreDocs) {
            Document doc = searcher.doc(sd.doc);
            System.out.println("DOCID :" + sd.doc);
            System.out.println("id :" + doc.get("id"));
            System.out.println("title:" + doc.get("title"));
            System.out.println("content: " + doc.get("content"));
            System.out.println("文档频分: " + sd.score);
        }
        dir.close();
        reader.close();
    }
}
