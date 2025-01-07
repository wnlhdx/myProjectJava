package com.myproject.datastorage.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.client.ElasticsearchRestTemplate;

@Configuration
public class ElasticsearchConfig {

    @Bean
    public ElasticsearchRestTemplate elasticsearchRestTemplate() {
        return new ElasticsearchRestTemplate(restClient());
    }

    @Bean
    public RestClient restClient() {
        // 创建 RestClient 配置
        RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200, "http"));

        // 配置连接超时和读取超时
        builder.setRequestConfigCallback(requestConfigBuilder ->
                requestConfigBuilder.setConnectTimeout(1000)
                        .setSocketTimeout(3000)
        );

        // 添加认证信息（如果需要）
        builder.setDefaultHeaders(new org.apache.http.Header[]{
                new org.apache.http.message.BasicHeader("Authorization", "Bearer my-token")
        });

        return builder.build();
    }
}
