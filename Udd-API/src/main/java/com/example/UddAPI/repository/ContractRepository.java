package com.example.UddAPI.repository;

import com.example.UddAPI.index.ContractIndex;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends ElasticsearchRepository<ContractIndex,String> {

    List<ContractIndex> findByTitle(String title);
    @Highlight(fields = {@HighlightField(name = "content")})
    SearchHits<ContractIndex> findByContentContaining(String content);

    @Highlight(fields = {@HighlightField(name = "nameSignAgency"),@HighlightField(name = "lastnameSignAgency")})
    SearchHits<ContractIndex> findByNameSignAgencyContainingAndLastnameSignAgencyContaining(String name,String lastname);
    @Highlight(fields = {@HighlightField(name = "govName"),@HighlightField(name = "govLevel")})
    SearchHits<ContractIndex> findByGovNameContainingAndGovLevelContaining(String name,String level);
}
