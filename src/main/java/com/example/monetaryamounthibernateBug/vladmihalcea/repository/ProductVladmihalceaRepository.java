package com.example.monetaryamounthibernateBug.vladmihalcea.repository;

import com.example.monetaryamounthibernateBug.vladmihalcea.models.PricingVladmihalcea;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductVladmihalceaRepository extends PagingAndSortingRepository<PricingVladmihalcea, Integer> {
}
