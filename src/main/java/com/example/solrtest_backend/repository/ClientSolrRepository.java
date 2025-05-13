package com.example.solrtest_backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.solrtest_backend.entity.ClientSolr;
import java.util.Optional;


public interface ClientSolrRepository extends JpaRepository<ClientSolr, Long>{
  Optional<ClientSolr> findByName(String name);
}
