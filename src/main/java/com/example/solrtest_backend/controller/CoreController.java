package com.example.solrtest_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.solrtest_backend.service.SolrService;

import lombok.RequiredArgsConstructor;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CoreController {
 
  @Value("${solr.base.url}")
  private String solrBaseUrl;

  private final SolrService solrTemplate;
  
  // Build Post Solr REST API
  @PostMapping("{core}")
  public ResponseEntity<String> agregarSolr(@PathVariable("core") String core,
                                            @RequestBody Object documents) {
    String body =  solrTemplate.agregarDocumentos(core, documents);
    return ResponseEntity.ok(body);
  }
  
  // Build Get Solr REST API
  @GetMapping("{core}")
  public ResponseEntity<String> consultarSolr(@PathVariable("core") String core,
                                              @RequestParam Map<String, String> queryParams) { 
    String body = solrTemplate.consultarDocumentos(core, queryParams);
    return ResponseEntity.ok(body);
  }
  
}