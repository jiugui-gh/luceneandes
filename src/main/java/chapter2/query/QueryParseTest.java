package chapter2.query;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.queryparser.classic.QueryParser.Operator;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import chapter2.ik.IKAnalyzer6x;

public class QueryParseTest {

    public static void main(String[] args) throws Exception {
        String field = "title";
        Path indexPath = Paths.get("indexdir");
        Directory dir = FSDirectory.open(indexPath);
        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);
        IKAnalyzer6x analyzer = new IKAnalyzer6x(true);
        QueryParser parser = new QueryParser(field, analyzer);
        parser.setDefaultOperator(Operator.OR);
        
        // 查询条件
        Query query = parser.parse("特朗普");
        System.out.println("Query:" + query.toString());
        
        TopDocs tds = searcher.search(query, 10);
        System.out.println(tds.scoreDocs.length);
        for (ScoreDoc sd : tds.scoreDocs) {
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
