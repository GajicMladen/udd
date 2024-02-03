package com.example.UddAPI.service;

import com.example.UddAPI.index.ContractIndex;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class ContractSearchService {

    private static final String UGOVOR_INDEX = "index_ugovora";

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    public String createUgovorIndex(ContractIndex contractIndex) {

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(contractIndex.getId())
                .withObject(contractIndex).build();

        return elasticsearchOperations.index(indexQuery, IndexCoordinates.of(UGOVOR_INDEX));
    }
    public List<ContractIndex> findContractsByTitle(final String name) {
        Criteria criteria = new Criteria("title").contains(name);

        Query searchQuery = new CriteriaQuery(criteria);

        SearchHits<ContractIndex> ugovoriHits = elasticsearchOperations
                .search(searchQuery,
                        ContractIndex.class,
                        IndexCoordinates.of(UGOVOR_INDEX));

        List<ContractIndex> ugovori = new ArrayList<ContractIndex>();
        ugovoriHits.forEach( ugovoriHit -> {
            ugovori.add(ugovoriHit.getContent());
        });
        return ugovori;
    }
}
