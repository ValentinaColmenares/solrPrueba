package com.example.solrtest_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
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

  private final RestTemplate restTemplate;
  
  // Build Post Solr REST API
  @PostMapping("{core}")
  public ResponseEntity<String> agregarSolr(@PathVariable("core") String core,
                                            @RequestBody Object documents) {
      String url = solrBaseUrl + core + "/update?commit=true";
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_JSON);
      HttpEntity<Object> request = new HttpEntity<>(documents, headers);
      ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
      return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
  }
  
  
  
  // Build Get Solr REST API
  @GetMapping("{core}")
  public ResponseEntity<String> consultarSolr(@PathVariable("core") String core,
                                              @RequestParam Map<String, String> queryParams) { 
    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(solrBaseUrl + core + "/select");
    queryParams.forEach(builder::queryParam);
    URI uri = builder.build().encode().toUri();
    ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
    return ResponseEntity.ok(response.getBody());
  }
  
}