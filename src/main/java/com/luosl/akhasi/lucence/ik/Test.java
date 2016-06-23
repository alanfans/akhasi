package com.luosl.akhasi.lucence.ik;

import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;

import java.io.IOException;

/**
 * Created by Administrator on 2016/4/8.
 * alan
 */
public class Test {
    public static void main(String[] args) throws IOException {
        IKAnalyzer5x ik = new IKAnalyzer5x();
        TokenStream ts = ik.tokenStream("name", "春的森林");
        ts.reset();
        CharTermAttribute term=ts.getAttribute(CharTermAttribute.class);
        while(ts.incrementToken()){
            System.out.println(term.toString());
        }
    }
}
