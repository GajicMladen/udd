package com.example.UddAPI.config;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.json.jackson.JacksonJsonpMapper;
import co.elastic.clients.transport.rest_client.RestClientTransport;
import org.apache.http.Header;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpHost;
import org.apache.http.message.BasicHeader;
import org.elasticsearch.client.RestClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


@Configuration
@EnableElasticsearchRepositories(basePackages= "com.example.UddAPI.repository")
public class ElasticsearchClientConfig {

    @Bean
    public ElasticsearchClient elasticsearchClient() {
        var client = RestClient.builder(new HttpHost("localhost", 9200))
                .setDefaultHeaders(compatibilityHeaders())
                .build();

        var transport = new RestClientTransport(client, new JacksonJsonpMapper());

        return new ElasticsearchClient(transport);
    }

    private Header[] compatibilityHeaders() {
        return new Header[]{new BasicHeader(HttpHeaders.ACCEPT, "application/vnd.elasticsearch+json;compatible-with=7"), new BasicHeader("X-Elastic-Product", "Elasticsearch"), new BasicHeader(HttpHeaders.CONTENT_TYPE, "application/vnd.elasticsearch+json;compatible-with=7")};
    }
}