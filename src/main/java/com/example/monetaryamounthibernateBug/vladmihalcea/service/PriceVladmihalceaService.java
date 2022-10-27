package com.example.monetaryamounthibernateBug.vladmihalcea.service;

import com.example.monetaryamounthibernateBug.vladmihalcea.models.PricingVladmihalcea;
import com.example.monetaryamounthibernateBug.vladmihalcea.repository.ProductVladmihalceaRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class PriceVladmihalceaService {

    private ProductVladmihalceaRepository repository;

    public PriceVladmihalceaService(ProductVladmihalceaRepository repository) {
        this.repository = repository;
    }

    public Iterable<PricingVladmihalcea> sortedWithMonetaryAmountFieldProductPricingList(){
        return repository.findAll(Sort.by(Sort.Direction.DESC,"price"));
    }
}
