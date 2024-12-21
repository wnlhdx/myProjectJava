package com.myproject.javase.sql;

import org.apache.calcite.sql.SqlNode;
import org.apache.calcite.sql.parser.SqlParseException;
import org.apache.calcite.sql.parser.SqlParser;
import org.apache.calcite.sql.SqlKind;

public class SimpleDBMSWithSQL extends SimpleDBMS {
    private SimpleTable table;

    public SimpleDBMSWithSQL() {
        this.table = new SimpleTable();
    }

    // SQL 执行方法
    public void executeSQL(String sql) throws SqlParseException {
        SqlParser parser = SqlParser.create(sql);
        SqlNode sqlNode = parser.parseStmt();

        if (sqlNode.getKind() == SqlKind.SELECT) {
            handleSelect(sqlNode);
        } else if (sqlNode.getKind() == SqlKind.INSERT) {
            handleInsert(sqlNode);
        } else if (sqlNode.getKind() == SqlKind.UPDATE) {
            handleUpdate(sqlNode);
        } else if (sqlNode.getKind() == SqlKind.DELETE) {
            handleDelete(sqlNode);
        } else {
            System.out.println("Unsupported SQL operation.");
        }
    }

    // 处理 SELECT 查询
    private void handleSelect(SqlNode sqlNode) {
        System.out.println("Processing SELECT query...");
        // 添加查询逻辑, 遍历并打印所有记录
        for (Record record : table.getAllRecords()) {
            System.out.println(record);
        }
    }

    // 处理 INSERT 查询
    private void handleInsert(SqlNode sqlNode) {
        System.out.println("Processing INSERT query...");
        Record newRecord = table.createRecord();
        // 添加插入逻辑, 例如设置字段值
        newRecord.setField("name", "ExampleName"); // 示例字段
        System.out.println("Inserted record: " + newRecord);
    }

    // 处理 UPDATE 查询
    private void handleUpdate(SqlNode sqlNode) {
        System.out.println("Processing UPDATE query...");
        // 添加更新逻辑，选择并修改记录
    }

    // 处理 DELETE 查询
    private void handleDelete(SqlNode sqlNode) {
        System.out.println("Processing DELETE query...");
        // 添加删除逻辑，选择并删除记录
    }

//    public static void main(String[] args) {
//        SimpleDBMSWithSQL dbms = new SimpleDBMSWithSQL();
//        dbms.executeSQL("SELECT * FROM SimpleTable");
//        dbms.executeSQL("INSERT INTO SimpleTable (name) VALUES ('Alice')");
//    }
}