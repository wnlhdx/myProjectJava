package com.myproject.springboot.tools;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Objects;

import static org.elasticsearch.action.admin.indices.stats.CommonStatsFlags.Flag.Store;
import static org.elasticsearch.script.Metadata.LongField;

public class Indexing {
    public Indexing() throws IOException {
        // 创建内存中的索引存储
        //指定索引库存放路径   D:\\Program\\Java\\index
        String path = "D:\\Program\\Java\\index\\docuindex";
        Directory directory = FSDirectory.open(Paths.get(path));
        //索引库还可以存放在内存中
        //Directory directory = new RAMDirectory();

        //创建一个标准分析器
        Analyzer analyzer = new StandardAnalyzer();

        //创建 indexwriterCofig对象
        //第一个参数： Lucene的版本信息，可以选择对应的lucene版本也可以使用LATEST
        //第二根参数：分析器对象
        IndexWriterConfig config = new IndexWriterConfig(analyzer);

        //创建 indexwriter 对象
        IndexWriter indexWriter = new IndexWriter(directory, config);

        //原始文档路径：D:\\Program\\Java\\docu
        File dirs = new File("D:\\Program\\Java\\docu");
        for (File f : Objects.requireNonNull(dirs.listFiles()))
        {
            //文件名
            String fileName = f.getName();
            //文件内容
            String fileContent = FileUtils.readFileToString(f);
            //文件路径
            String filePath = f.getPath();
            //文件大小
            long fileSize = f.getTotalSpace();

            //创建文件名域
            //第一个参数：域的名称
            //第二个参数：域的内容
            //第三个参数：是否存储
            Field fileNameField = new TextField("filename", fileName, Store.YES);

            //文件内容域
            Field fileContentField = new TextField("content", fileContent, Store.YES);
            //文件路径域（不分析，不索引，不存储）
            Field filePathField = new StoredField("path", filePath);
            //文件大小域
            Field fileSizeField = new LongField("size", fileSize, Store.YES);

            //创建 document 对象
            Document document = new Document();
            document.add(fileNameField);
            document.add(fileContentField);
            document.add(filePathField);
            document.add(fileSizeField);

            //创建索引，并写入索引库
            indexWriter.addDocument(document);
        }
        System.out.println("创建index成功");
        indexWriter.close();
    }

    }
}
