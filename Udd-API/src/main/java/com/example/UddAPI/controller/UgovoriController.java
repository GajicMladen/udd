package com.example.UddAPI.controller;

import com.example.UddAPI.service.UgovorSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ugovori")
public class UgovoriController {

    @Autowired
    private UgovorSearchService ugovorSearchService;

    @GetMapping("/test")
    public ResponseEntity<?> testEndpoint(){
        return ResponseEntity.ok().body("");
    }
}
