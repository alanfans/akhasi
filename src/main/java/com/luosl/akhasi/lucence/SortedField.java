package com.luosl.akhasi.lucence;

import org.apache.lucene.document.FieldType;
import org.apache.lucene.index.DocValuesType;
import org.apache.lucene.index.IndexOptions;

/**
 * Created by Administrator on 2016/5/18.
 */
public class SortedField {

    public static final FieldType LONG_SORTED_FIELD = new FieldType();
    static {
        LONG_SORTED_FIELD.setNumericType(FieldType.NumericType.LONG);
        LONG_SORTED_FIELD.setDocValuesType(DocValuesType.NUMERIC);
        LONG_SORTED_FIELD.setStored(false);
        LONG_SORTED_FIELD.setOmitNorms(true);
        LONG_SORTED_FIELD.setIndexOptions(IndexOptions.DOCS);
        LONG_SORTED_FIELD.setTokenized(false);
        LONG_SORTED_FIELD.freeze();
    }

}
