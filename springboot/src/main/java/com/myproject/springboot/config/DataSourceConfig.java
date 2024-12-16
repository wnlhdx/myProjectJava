package com.myproject.springboot.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DataSourceConfig {

    @Primary
    @Bean(name = "primaryDataSource")
    public DataSource primaryDataSource(
            @Value("${spring.datasource.primary.url}") String primaryUrl,
            @Value("${spring.datasource.primary.username}") String username,
            @Value("${spring.datasource.primary.password}") String password) {
        return DataSourceBuilder.create()
                .url(primaryUrl)
                .username(username)
                .password(password)
                .driverClassName("org.postgresql.Driver")
                .build();
    }

    @Bean(name = "replicaDataSource")
    public DataSource replicaDataSource(
            @Value("${spring.datasource.replica.url}") String sqliteUrl) {  // 动态获取SQLite路径
        return DataSourceBuilder.create()
                .url(sqliteUrl)
                .driverClassName("org.sqlite.JDBC")
                .build();
    }

    @Bean(name = "dataSource")
    public DataSource dataSource(@Qualifier("primaryDataSource") DataSource primaryDataSource,
                                 @Qualifier("replicaDataSource") DataSource replicaDataSource) {
        DynamicDataSource dynamicDataSource = new DynamicDataSource();
        dynamicDataSource.setDefaultTargetDataSource(primaryDataSource);
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DataSourceType.MASTER, primaryDataSource);
        targetDataSources.put(DataSourceType.SLAVE, replicaDataSource);
        dynamicDataSource.setTargetDataSources(targetDataSources);
        return dynamicDataSource;
    }
}