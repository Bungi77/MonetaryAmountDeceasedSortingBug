package com.example.monetaryamounthibernateBug;

import com.example.monetaryamounthibernateBug.jadira.service.PriceJadiraService;
import com.example.monetaryamounthibernateBug.vladmihalcea.service.PriceVladmihalceaService;
import com.example.monetaryamounthibernateBug.jadira.models.PricingJadira;
import com.example.monetaryamounthibernateBug.vladmihalcea.models.PricingVladmihalcea;
import com.example.monetaryamounthibernateBug.jadira.repository.ProductJadiraRepository;
import com.example.monetaryamounthibernateBug.vladmihalcea.repository.ProductVladmihalceaRepository;
import org.javamoney.moneta.Money;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import javax.money.MonetaryAmount;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@DataJpaTest
class PricingVladmihalceaTest {

    @Autowired
    private TestEntityManager entityManager;
    @Autowired
    private ProductVladmihalceaRepository vladmihalceaRepository;
    @Autowired
    private ProductJadiraRepository jadiraRepository;

    @Test
    void testSortingVladmihalceaDeceased(){
        var vladmihalceaService = new PriceVladmihalceaService(vladmihalceaRepository);
        var sortedDeceased = createVladmihalceaTestData();

        Iterable<PricingVladmihalcea> pricingsVladmihalcea = vladmihalceaService.sortedWithMonetaryAmountFieldProductPricingList();

        assertThat(pricingsVladmihalcea).containsExactly(sortedDeceased);
    }

    @Test
    void testSortingJadiraDeceased(){
        var jadiraService = new PriceJadiraService(jadiraRepository);
        var sortedDeceased = createJadiraTestData();

        Iterable<PricingJadira> pricingsJadira = jadiraService.sortedWithMonetaryAmountFieldProductPricingList();

        assertThat(pricingsJadira).containsExactly(sortedDeceased);
    }

    private PricingVladmihalcea[] createVladmihalceaTestData(){
        List<PricingVladmihalcea> pricings = new ArrayList<>();
        PricingVladmihalcea first = new PricingVladmihalcea(1L, Money.of(new BigDecimal("12.7"), "EUR"));
        PricingVladmihalcea second = new PricingVladmihalcea(2L, Money.of(new BigDecimal("30.2"), "EUR"));
        PricingVladmihalcea third = new PricingVladmihalcea(3L, Money.of(new BigDecimal("29.6"), "EUR"));
        pricings.add(first);
        pricings.add(second);
        pricings.add(third);
        pricings.forEach(e-> entityManager.persistAndFlush(e));
        PricingVladmihalcea[] sortedDeceased = {pricings.get(1), pricings.get(2), pricings.get(0)};
        return sortedDeceased;

    }

    private PricingJadira[] createJadiraTestData(){
        List<PricingJadira> pricings = new ArrayList<>();
        PricingJadira first = new PricingJadira(1L, Money.of(new BigDecimal("12.7"), "EUR"));
        PricingJadira second = new PricingJadira(2L, Money.of(new BigDecimal("30.2"), "EUR"));
        PricingJadira third = new PricingJadira(3L, Money.of(new BigDecimal("29.6"), "EUR"));
        pricings.add(first);
        pricings.add(second);
        pricings.add(third);
        pricings.forEach(e-> entityManager.persistAndFlush(e));
        PricingJadira[] sortedDeceased = {pricings.get(1), pricings.get(2), pricings.get(0)};
        return sortedDeceased;
    }
}
