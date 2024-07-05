package com.myproject.springboot.tools;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static com.myproject.springboot.tools.Index.indexPath;

public class Query {


    public static IndexReader reader;
    public static IndexSearcher searcher;
    public static QueryParser queryContent;
    public static QueryParser queryPath;
    public static Analyzer searchanalyzer;

    public   Query() throws IOException {
        Directory index = FSDirectory.open(Paths.get(indexPath));
        searchanalyzer=new StandardAnalyzer();
        reader = DirectoryReader.open(index);
        searcher= new IndexSearcher(reader);
        queryContent = new QueryParser("content",searchanalyzer);
        queryPath = new QueryParser("fullpath",searchanalyzer);
    }




    public void closeRead() throws IOException {
        reader.close();
    }


    public List<String> query(String queryString) throws Exception {
        // 搜索索引
        List<String> res = new ArrayList<>();
        org.apache.lucene.search.Query query = queryContent.parse(queryString);
        ScoreDoc[] hits = searcher.search(query,1000).scoreDocs;
        // 输出搜索结果
        for (ScoreDoc hit : hits) {
            Document hitDoc = searcher.doc(hit.doc);
            res.add(hitDoc.toString());
        }
        return res;
        // 关闭资源
    }
}