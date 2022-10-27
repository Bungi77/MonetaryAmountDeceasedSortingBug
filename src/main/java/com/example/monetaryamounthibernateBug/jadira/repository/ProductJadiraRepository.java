package com.example.monetaryamounthibernateBug.jadira.repository;

import com.example.monetaryamounthibernateBug.jadira.models.PricingJadira;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductJadiraRepository extends PagingAndSortingRepository<PricingJadira, Integer> {
}
