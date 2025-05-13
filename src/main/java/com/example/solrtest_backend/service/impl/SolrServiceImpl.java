package com.example.solrtest_backend.service.impl;

import java.net.URI;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.solrtest_backend.service.SolrService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SolrServiceImpl implements SolrService{

  @Value("${solr.base.url}")
  private String solrBaseUrl;

  private final RestTemplate restTemplate;

  public String agregarDocumentos(String core, Object documents) {
    String url = solrBaseUrl + core + "/update?commit=true";
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity<Object> request = new HttpEntity<>(documents, headers);
    ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
    return response.getBody();
  }

  @Override
  public String consultarDocumentos(String core, Map<String, String> queryParams) {
    UriComponentsBuilder builder = UriComponentsBuilder.fromUriString(solrBaseUrl + core + "/select");
    queryParams.forEach(builder::queryParam);
    URI uri = builder.build().encode().toUri();
    ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
    return response.getBody();
  }

}
