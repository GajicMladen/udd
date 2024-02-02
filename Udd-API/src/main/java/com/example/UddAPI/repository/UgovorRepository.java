package com.example.UddAPI.repository;

import com.example.UddAPI.index.IndexUgovora;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UgovorRepository extends ElasticsearchRepository<IndexUgovora,String> {

    List<IndexUgovora> findByIme( String ime);
}
