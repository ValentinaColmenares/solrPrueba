package com.example.solrtest_backend.service;

import java.util.Map;

public interface SolrService {
  String agregarDocumentos(String core, Object documents, String solrBaseUrl);
  
  String consultarDocumentos(String core, Map<String, String> queryParams, String solrBaseUrl);
  
  String getSolrUrlByCliente(String clientName);
}
