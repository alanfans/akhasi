package com.luosl.akhasi.lucence;

import com.luosl.akhasi.lucence.ik.IKAnalyzer5x;
import org.apache.lucene.analysis.Analyzer;

/**
 * Created by Administrator on 2016/5/5.
 */
public class Analyzers {

    public static final Analyzer ANALYZER;

    static {
        ANALYZER = genAnalyzer();
    }

    public static Analyzer genAnalyzer(){
        return new IKAnalyzer5x(true);
    }
}
