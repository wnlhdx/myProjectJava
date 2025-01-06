package com.myproject.datastorage.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
public class ElasticsearchConfig extends ElasticsearchConfiguration {

    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        return RestHighLevelClient.builder()
                .setDefaultHeaders(headers -> headers.add("Authorization", "Bearer my-token"))
                .build();
    }
}
