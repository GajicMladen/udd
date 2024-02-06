package com.example.UddAPI.service;

import com.example.UddAPI.index.ContractIndex;
import com.example.UddAPI.index.LawIndex;
import com.example.UddAPI.repository.LawRepository;
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
public class LawSearchService {

    private static final String LAW_INDEX = "law_index";

    @Autowired
    private ElasticsearchOperations elasticsearchOperations;

    @Autowired
    private LawRepository lawRepository;

    public String createLawIndex(LawIndex lawIndex) {

        IndexQuery indexQuery = new IndexQueryBuilder()
                .withId(lawIndex.getId())
                .withObject(lawIndex).build();

        return elasticsearchOperations.index(indexQuery, IndexCoordinates.of(LAW_INDEX));
    }

    public List<LawIndex> findLawsByContent(String content) {

        SearchHits<LawIndex> contractHits = lawRepository.findByContentContaining(content);

        return getListOfContracts(contractHits,"content");
    }
    private List<LawIndex> getListOfContracts(SearchHits<LawIndex> searchHits,String fieldName){

        List<LawIndex> contracts = new ArrayList<>();
        searchHits.forEach( contractHit -> {
            LawIndex contractIndex = contractHit.getContent();
            if(contractHit.getHighlightField(fieldName).size()>0)
                contractIndex.setHighlight(contractHit.getHighlightField(fieldName).get(0));
            contracts.add(contractHit.getContent());
        });
        return contracts;
    }
}
