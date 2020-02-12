package chapter2.query;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

public class BooleanQueryTest {

    public static void main(String[] args) throws Exception {
        Path indexPath = Paths.get("indexdir");
        FSDirectory dir = FSDirectory.open(indexPath);
        DirectoryReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        
        TermQuery query1 = new TermQuery(new Term("title","美国"));
        TermQuery query2 = new TermQuery(new Term("content","日本"));
        
        BooleanClause bc1 = new BooleanClause(query1, Occur.MUST);
        BooleanClause bc2 = new BooleanClause(query2,Occur.MUST_NOT);
       
        BooleanQuery boolQuery = new BooleanQuery.Builder().add(bc1).add(bc2).build();
        
        System.out.println("Query : " + boolQuery);
        
        // 返回前10条
        TopDocs tds = searcher.search(boolQuery, 10);
        for(ScoreDoc sd : tds.scoreDocs) {
            Document doc = searcher.doc(sd.doc);
            System.out.println("DocID:" + sd.doc);
            System.out.println("id:" + doc.get("id"));
            System.out.println("title:" + doc.get("title"));
            System.out.println("content:" + doc.get("content"));
            System.out.println("文档评分:" + sd.score);
        }
        
        dir.close();
        reader.close();
    }
    
}
