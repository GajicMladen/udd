package com.example.UddAPI.service;

import com.example.UddAPI.index.IndexUgovora;
import com.example.UddAPI.repository.UgovorRepository;
import org.springframework.stereotype.Service;

@Service
public class UgovorService {
    private UgovorRepository ugovorRepository;

    public void createUgovorIndex(IndexUgovora indexUgovora){
        ugovorRepository.save(indexUgovora);
    }

}
