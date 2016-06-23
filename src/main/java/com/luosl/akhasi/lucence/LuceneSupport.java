package com.luosl.akhasi.lucence;

import com.luosl.akhasi.domain.base.Pager;
import com.luosl.akhasi.lucence.ik.IKAnalyzer5x;
import com.luosl.akhasi.timer.DocIndexTimer;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.highlight.*;
import org.apache.lucene.store.FSDirectory;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * Created by Administrator on 2016/3/4.
 */
public class LuceneSupport {

    private org.slf4j.Logger logger = LoggerFactory.getLogger(DocIndexTimer.class);
    private String indexDir;
    private IndexWriter indexWriter;
    private DirectoryReader indexReader;
    private Analyzer analyzer = Analyzers.ANALYZER;

    @PostConstruct
    private void init() throws IOException {
        logger.info("---初始化lucene,打开FSDirectory:"+new File(indexDir).getAbsolutePath()+"---");
        FSDirectory dir = FSDirectory.open(Paths.get(indexDir));
        Analyzer analyzer = new IKAnalyzer5x();
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        this.indexWriter = new IndexWriter(dir, iwc);
        this.indexReader = DirectoryReader.open(indexWriter);
        logger.info("---初始化lucene完成---");
    }

    /**
     *如果有修改重新打开reader
     * @throws IOException
     */
    public void reopenIndexReader() throws IOException {
        DirectoryReader newReader = DirectoryReader.openIfChanged(this.indexReader, this.indexWriter, true);
        if(null!=newReader) this.indexReader = newReader;
    }
    /**
     * 获取searcher
     * @return
     * @throws IOException
     */
    public IndexSearcher getSearcher() throws IOException {
        reopenIndexReader();
        return new IndexSearcher(this.indexReader);
    }

    public String highlighter(String field, String text, String kw)
            throws ParseException, IOException, InvalidTokenOffsetsException {
        QueryParser qp = new QueryParser(field,this.analyzer);
        Query query = qp.parse(kw);
        return highlighter(query,field,text);
    }
    /**
     * 高亮
     * @param query
     * @param text
     * @return
     * @throws Exception
     */
    public String highlighter(Query query, String field,String text) throws IOException, InvalidTokenOffsetsException {
        QueryScorer queryScorer = new QueryScorer(query);//如果有需要，可以传入评分
        //设置高亮标签
        Formatter formatter = new SimpleHTMLFormatter("<span style='color:red;'>", "</span>");
        //高亮分析器
        Highlighter hl = new Highlighter(formatter, queryScorer);

        Fragmenter fragmenter = new SimpleSpanFragmenter(queryScorer);
        hl.setTextFragmenter(fragmenter);
        //获取返回结果
        return hl.getBestFragment(new IKAnalyzer5x(), field,text);
    }

    public void commit() throws IOException {
        this.indexWriter.commit();
    }

    public void index(Document doc) throws IOException {
        this.indexWriter.addDocument(doc);
    }

    public void index(Collection<Document> docs) throws IOException {
        for(Document doc:docs){
            index(doc);
        }
    }

    public void deleteIntField(String field,String strVal) throws IOException {
        Term term = new Term(field,strVal);
        indexWriter.deleteDocuments(term);
    }

    public void deleteAll() throws IOException {
        indexWriter.deleteAll();
    }

    public Pager<Document> page(int pageSize, int currentPage, String kw,Sort sort, String ...field)
            throws IOException, ParseException {
        MultiFieldQueryParser queryParser = new MultiFieldQueryParser(field,analyzer);
        Query query = queryParser.parse(kw);
        return page(pageSize,currentPage,query,sort);
    }

    public Pager<Document> page(int pageSize, int currentPage, String kw,String ...field)
            throws IOException, ParseException {
        return page(pageSize,currentPage,kw,null,field);
    }

    public Pager<Document> page(int pageSize, int currentPage, String field, String kw)
            throws IOException, ParseException {
        return page(pageSize,currentPage,field,kw,null);
    }

    public Pager<Document> page(int pageSize, int currentPage,String field,String kw,Sort sort)
            throws ParseException, IOException {
        QueryParser parser = new QueryParser(field,analyzer);
        return page(pageSize,currentPage,parser.parse(kw),sort);
    }

    public Pager<Document> page(int pageSize, int currentPage, Query query,Sort sort)
            throws IOException, ParseException {
        IndexSearcher searcher = getSearcher();
        List<Document> dataList = new ArrayList<Document>();
        ScoreDoc[] scoreDocs = search(searcher,query,999,sort);
        //查询起始记录位置
        int begin = pageSize * (currentPage - 1);
        //查询终止记录位置
        int end = Math.min(begin + pageSize, scoreDocs.length);
        for(int i=begin;i<end;i++) {
            int docID = scoreDocs[i].doc;
            logger.info("doc得分:"+scoreDocs[i].score);
            dataList.add(searcher.doc(docID));
        }
        Pager<Document> pager = new Pager<Document>();
        pager.setDataList(dataList);
        pager.setPageSize(10);
        pager.setTotalCount(scoreDocs.length);
        pager.setCurrentPage(currentPage);
        return pager;
    }

    public ScoreDoc[] search(IndexSearcher searcher,Query query, int resultSize, Sort sort) throws IOException {
        TopDocs topDocs = null;
        if(null==sort) {
            topDocs = searcher.search(query,resultSize,new Sort(),true,true);
        }else {
            topDocs = searcher.search(query,resultSize,sort,true,true);
        }
        return topDocs.scoreDocs;
    }

    public String getIndexDir() {
        return indexDir;
    }

    public void setIndexDir(String indexDir) {
        this.indexDir = indexDir;
    }
}
