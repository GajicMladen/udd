package com.example.UddAPI.service;

import com.example.UddAPI.index.IndexUgovora;
import com.example.UddAPI.index.IndexZakona;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UgovorSearchService {

    private static final String UGOVOR_INDEX = "index_ugovora";

    private ElasticsearchOperations elasticsearchOperations;

    public String createUgovorIndex(IndexUgovora indexUgovora) {

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(indexUgovora.getId().toString())
                .withObject(indexUgovora).build();

        String documentId = elasticsearchOperations
                .index(indexQuery, IndexCoordinates.of(UGOVOR_INDEX));

        return documentId;
    }
    public List<IndexUgovora> findUgovorByName(final String name) {
        Criteria criteria = new Criteria("ime").contains(name);

        Query searchQuery = new CriteriaQuery(criteria);

        SearchHits<IndexUgovora> ugovoriHits = elasticsearchOperations
                .search(searchQuery,
                        IndexUgovora.class,
                        IndexCoordinates.of(UGOVOR_INDEX));

        List<IndexUgovora> ugovori = new ArrayList<IndexUgovora>();
        ugovoriHits.forEach( ugovoriHit -> {
            ugovori.add(ugovoriHit.getContent());
        });
        return ugovori;
    }
}
