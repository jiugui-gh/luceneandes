package chapter2.test;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Terms;
import org.apache.lucene.index.TermsEnum;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.BytesRef;

public class GetTopTerms {

    public static void main(String[] args) throws IOException {
        
        FSDirectory dir = FSDirectory.open(Paths.get("indexdir"));
        DirectoryReader reader = DirectoryReader.open(dir);
        // 通过getTermVector据哦去content字段的词项
        Terms terms = reader.getTermVector(3, "content");
        
        // 遍历词项
        TermsEnum termsEnum = terms.iterator();
        BytesRef thisTerm = null;
        Map<String, Integer> map = new HashMap<String, Integer>();
        while((thisTerm = termsEnum.next()) != null) {
            // 词项
            String termText = thisTerm.utf8ToString();
            // 通过totaltFFreq()
            long num = termsEnum.totalTermFreq();
            map.put(termText, (int)num);
        }
        
        ArrayList<Entry<String, Integer>> sortedMap = new ArrayList<Map.Entry<String, Integer>>(map.entrySet());
        Collections.sort(sortedMap, new Comparator<Map.Entry<String, Integer>>() {

            @Override
            public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
                // TODO Auto-generated method stub
                return o2.getValue() - o1.getValue();
            }
        
        });
        
        getTopN(sortedMap,10);
    }

    private static void getTopN(ArrayList<Entry<String, Integer>> sortedMap, int n) {
        // TODO Auto-generated method stub
        for(int i = 0; i < n; i++) {
            System.out.println(sortedMap.get(i).getKey());
        }
    }
}
