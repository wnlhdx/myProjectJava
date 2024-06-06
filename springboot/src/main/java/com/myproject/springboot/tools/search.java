package com.myproject.springboot.tools;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.nio.file.Paths;

public class search {

    public search() throws Exception {
        // 创建索引
        Directory index = FSDirectory.open(Paths.get("/"));
        IndexWriter writer = new IndexWriter(index, new IndexWriterConfig( new StandardAnalyzer()));
        Document doc = new Document();
        doc.add(new TextField("content", "Lucene is a Java full-text search engine", Field.Store.YES));
        writer.addDocument(doc);
        writer.close();

        // 搜索索引
        IndexReader reader = DirectoryReader.open(index);
        IndexSearcher searcher = new IndexSearcher(reader);
        QueryParser parser = new QueryParser("content", new StandardAnalyzer());
        Query query = parser.parse("Lucene");
        ScoreDoc[] hits = searcher.search(query,1000,null ).scoreDocs;

        // 输出搜索结果
        for (ScoreDoc hit : hits) {
            Document hitDoc = searcher.doc(hit.doc);
            System.out.println(hitDoc.toString());
        }

        // 关闭资源
        reader.close();
    }
}