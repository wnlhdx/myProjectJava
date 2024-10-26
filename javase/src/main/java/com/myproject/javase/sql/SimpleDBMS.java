package com.myproject.javase.sql;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// 数据库中的一条记录
class Record {
    private int id;
    private Map<String, Object> fields;

    public Record(int id) {
        this.id = id;
        this.fields = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public Map<String, Object> getFields() {
        return fields;
    }

    public void setField(String key, Object value) {
        fields.put(key, value);
    }

    @Override
    public String toString() {
        return "Record{" + "id=" + id + ", fields=" + fields + '}';
    }
}

// 简单的表结构
class SimpleTable {
    private List<Record> records;
    private int idCounter = 1;

    public SimpleTable() {
        this.records = new ArrayList<>();
    }

    // 创建记录
    public Record createRecord() {
        Record record = new Record(idCounter++);
        records.add(record);
        return record;
    }

    // 通过ID查询记录
    public Record getRecordById(int id) {
        for (Record record : records) {
            if (record.getId() == id) {
                return record;
            }
        }
        return null;
    }

    // 更新记录
    public void updateRecord(int id, String key, Object value) {
        Record record = getRecordById(id);
        if (record != null) {
            record.setField(key, value);
        }
    }

    // 删除记录
    public boolean deleteRecord(int id) {
        return records.removeIf(record -> record.getId() == id);
    }

    // 获取所有记录
    public List<Record> getAllRecords() {
        return records;
    }
}

// 简单DBMS的主类
public class SimpleDBMS {
    public static void main(String[] args) {
        SimpleTable table = new SimpleTable();

        // 创建记录
        Record record1 = table.createRecord();
        record1.setField("name", "Alice");
        record1.setField("age", 25);

        Record record2 = table.createRecord();
        record2.setField("name", "Bob");
        record2.setField("age", 30);

        // 输出所有记录
        System.out.println("所有记录:");
        for (Record record : table.getAllRecords()) {
            System.out.println(record);
        }

        // 更新记录
        System.out.println("\n更新ID为1的记录:");
        table.updateRecord(1, "age", 26);
        System.out.println(table.getRecordById(1));

        // 删除记录
        System.out.println("\n删除ID为2的记录:");
        table.deleteRecord(2);

        // 输出所有记录
        System.out.println("\n所有记录:");
        for (Record record : table.getAllRecords()) {
            System.out.println(record);
        }
    }
}

