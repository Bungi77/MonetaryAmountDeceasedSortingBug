package com.example.monetaryamounthibernateBug.jadira.service;

import com.example.monetaryamounthibernateBug.jadira.models.PricingJadira;
import com.example.monetaryamounthibernateBug.jadira.repository.ProductJadiraRepository;
import org.springframework.data.domain.Sort;

public class PriceJadiraService {
    ProductJadiraRepository repository;

    public PriceJadiraService(ProductJadiraRepository repository) {
        this.repository = repository;
    }

    public Iterable<PricingJadira> sortedWithMonetaryAmountFieldProductPricingList(){
        return repository.findAll(Sort.by(Sort.Direction.DESC,"price"));
    }
}
