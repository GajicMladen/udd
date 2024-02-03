package com.example.UddAPI.repository;

import com.example.UddAPI.index.ContractIndex;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends ElasticsearchRepository<ContractIndex,String> {

    List<ContractIndex> findByNaslov(String naslov);
}
