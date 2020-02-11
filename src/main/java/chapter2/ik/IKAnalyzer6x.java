package chapter2.ik;

import org.apache.lucene.analysis.Analyzer;

public class IKAnalyzer6x extends Analyzer{

    
    private boolean useSmart;
    
    
    public IKAnalyzer6x(boolean useSmart) {
        this.useSmart = useSmart;
    }
    
    // IK分词器Lucene Analyzer接口实现类,默认细粒度切分算法
    public IKAnalyzer6x() {
        this(false);
    }
    
    
    
    public boolean isUseSmart() {
        return useSmart;
    }

    public void setUseSmart(boolean useSmart) {
        this.useSmart = useSmart;
    }

    // 重写最新版本的createComponents;重载Analyzer接口,构造分词器组件
    @Override
    protected TokenStreamComponents createComponents(String fieldName) {
        // TODO Auto-generated method stub
        IKTokenizer6x _IKTokenizer = new IKTokenizer6x(this.useSmart);
        return new TokenStreamComponents(_IKTokenizer);
    }

}
