package com.example.solrtest_backend.service;

import java.util.Map;

public interface SolrService {
  String agregarDocumentos(String core, Object documents);
  
  String consultarDocumentos(String core, Map<String, String> queryParams);
  
}
