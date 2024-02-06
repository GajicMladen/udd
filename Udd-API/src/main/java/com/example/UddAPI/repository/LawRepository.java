package com.example.UddAPI.repository;

import com.example.UddAPI.index.ContractIndex;
import com.example.UddAPI.index.LawIndex;
import org.springframework.data.elasticsearch.annotations.Highlight;
import org.springframework.data.elasticsearch.annotations.HighlightField;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LawRepository extends ElasticsearchRepository<LawIndex,String> {


    @Highlight(fields = {@HighlightField(name = "content_sr")})
    SearchHits<LawIndex> findByContentContaining(String content);
}
