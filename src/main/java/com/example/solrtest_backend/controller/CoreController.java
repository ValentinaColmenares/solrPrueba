package com.example.solrtest_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.solrtest_backend.service.SolrService;
import lombok.RequiredArgsConstructor;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CoreController {
 
  private final SolrService solrTemplate;
  
  // Build Post Solr REST API
  @PostMapping("{cliente}/{core}")
  public ResponseEntity<String> agregarSolr(@PathVariable String cliente,
                                            @PathVariable String core,
                                            @RequestBody Object documents) {
    String solrUrl = solrTemplate.getSolrUrlByCliente(cliente);
    String body =  solrTemplate.agregarDocumentos(core, documents, solrUrl);
    return ResponseEntity.ok(body);
  }
  
  // Build Get Solr REST API
  @GetMapping("{cliente}/{core}")
  public ResponseEntity<String> consultarSolr(@PathVariable String cliente,
                                              @PathVariable String core,
                                              @RequestParam Map<String, String> queryParams) {
    String solrUrl = solrTemplate.getSolrUrlByCliente(cliente); 
    String body = solrTemplate.consultarDocumentos(core, queryParams, solrUrl);
    return ResponseEntity.ok(body);
  }
  
}