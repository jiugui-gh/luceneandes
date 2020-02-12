package chapter2.query;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.IntPoint;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;

public class RangeQueryTest {

    public static void main(String[] args) throws Exception {
        
        Path indexPath = Paths.get("indexdir");
        FSDirectory dir = FSDirectory.open(indexPath);
        DirectoryReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
         
        Query rangeQuery = IntPoint.newRangeQuery("reply", 500, 1000);
        System.out.println("Quert: " + rangeQuery);
        
        TopDocs tds = searcher.search(rangeQuery, 10);
        for (ScoreDoc sd : tds.scoreDocs) {
            // Explanation explanation = searcher.explain(query, sd.doc);
            // System.out.println("explain:" + explanation + "\n");
            Document doc = searcher.doc(sd.doc);
            System.out.println("DocID:" + sd.doc);
            System.out.println("id:" + doc.get("id"));
            System.out.println("title:" + doc.get("title"));
            System.out.println("Reply:" + doc.get("reply_display"));
            System.out.println("文档评分:" + sd.score);
        }
        dir.close();
        reader.close();
    }
}
