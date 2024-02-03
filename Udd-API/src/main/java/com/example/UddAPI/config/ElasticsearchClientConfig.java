package com.example.UddAPI.config;

import co.elastic.clients.elasticsearch.nodes.Http;
import org.apache.http.client.methods.HttpHead;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.config.ElasticsearchConfigurationSupport;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;
import org.springframework.data.elasticsearch.support.HttpHeaders;


@Configuration
@EnableElasticsearchRepositories(basePackages = "com.example.UddAPI.repository")
public class ElasticsearchClientConfig extends ElasticsearchConfiguration {

    @Override
    public ClientConfiguration clientConfiguration() {
        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.add("Content-type","application/vnd.elasticsearch+json; compatible-with=7'");

        return ClientConfiguration.builder()
                .connectedTo("localhost:9200")
                .withDefaultHeaders(httpHeaders)
                .build();
    }
}