package com.example.solrtest_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;

import java.net.URI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class CoreController {
 
  @Value("${solr.base.url}")
  private String solrBaseUrl;

  private final RestTemplate restTemplate;
 
  public CoreController() {
    this.restTemplate = new RestTemplate();
  }
  
  // Build Get Document REST API
  @GetMapping("{core}/{id}")
  public ResponseEntity<String> buscarDocumento(@PathVariable("core") String core,
                                @PathVariable("id") String numeroUpz) { 
    URI uri = UriComponentsBuilder
            .fromUriString(solrBaseUrl + core + "/select")
            .queryParam("q", "*:*")
            .queryParam("fq", "Numero_UPZ:" + numeroUpz)
            .queryParam("wt", "json")
            .build()
            .encode()
            .toUri();
    
    ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
    return ResponseEntity.ok(response.getBody());
  }
 
  // Build Get All Documents REST API
  @GetMapping("{core}")
  public String getAllDocuments(@PathVariable("core") String core) { 
    String coreUrl = solrBaseUrl + core;
    ResponseEntity<String> response = restTemplate.getForEntity(coreUrl + "/select?q=*:*", String.class);
    return response.getBody();
  }
  
}