package chapter2.analyzer;

import java.io.IOException;
import java.io.StringReader;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.cjk.CJKAnalyzer;
import org.apache.lucene.analysis.cn.smart.SmartChineseAnalyzer;
import org.apache.lucene.analysis.core.KeywordAnalyzer;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.analysis.core.StopAnalyzer;
import org.apache.lucene.analysis.core.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

public class StdAnalyzer {

    private static String strCh = "中华人民共和国简称中国, 是一个有13亿人口的国家";
    private static String strEn = "Dogs can not achieve a place,eyes can reach; ";
    
    public static void main(String[] args) throws IOException {
        System.out.println("StandardAnalyzer对中文分词:");
        stdAnalyzer(strCh);
        System.out.println("StandardAnalyzer对英文分词:");
        stdAnalyzer(strEn);
    }

    private static void stdAnalyzer(String str) throws IOException {
        // TODO Auto-generated method stub
        Analyzer analyzer = null;
        analyzer = new StandardAnalyzer();
       // analyzer = new KeywordAnalyzer();
       // analyzer = new SimpleAnalyzer();
       // analyzer = new CJKAnalyzer();
       // analyzer = new WhitespaceAnalyzer();
        analyzer = new SmartChineseAnalyzer();
        //analyzer = new StopAnalyzer(stopwordsFile)
        StringReader reader = new StringReader(str);
        TokenStream toStream = analyzer.tokenStream(str, reader);
        toStream.reset();
        CharTermAttribute teAttribute = toStream.getAttribute(CharTermAttribute.class);
        System.out.println("分词结果");
        while(toStream.incrementToken()) {
            System.out.println(teAttribute.toString() + "|");
        }
        System.out.println();
        
        analyzer.close();
    }
}
