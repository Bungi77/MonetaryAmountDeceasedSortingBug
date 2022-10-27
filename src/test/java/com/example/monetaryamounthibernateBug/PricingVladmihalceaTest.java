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

    private List<MonetaryAmount> sortedDeceased;

    @Test
    void testSortingVladmihalceaDeceased(){
        createVladmihalceaTestData();
        createSolutionForSortedDeceased();

        var vladmihalceaService = new PriceVladmihalceaService(vladmihalceaRepository);
        var vladmihalceaRes = new ArrayList<MonetaryAmount>();

        Iterable<PricingVladmihalcea> pricingsVladmihalcea = vladmihalceaService.sortedWithMonetaryAmountFieldProductPricingList();
        pricingsVladmihalcea.forEach(e-> vladmihalceaRes.add(e.getPrice()));

        assertThat(vladmihalceaRes.equals(sortedDeceased)).isTrue();
    }

    @Test
    void testSortingJadiraDeceased(){
        createSolutionForSortedDeceased();
        createJadiraTestData();

        var jadiraService = new PriceJadiraService(jadiraRepository);
        var jadiraRes = new ArrayList<MonetaryAmount>();

        Iterable<PricingJadira> pricingsJadira = jadiraService.sortedWithMonetaryAmountFieldProductPricingList();
        pricingsJadira.forEach(e-> jadiraRes.add(e.getPrice()));

        assertThat(jadiraRes.equals(sortedDeceased)).isTrue();
    }

    private void createVladmihalceaTestData(){
        PricingVladmihalcea firstV = new PricingVladmihalcea(1L, Money.of(new BigDecimal("12.7"), "EUR"));
        PricingVladmihalcea secondV = new PricingVladmihalcea(2L, Money.of(new BigDecimal("30.2"), "EUR"));
        PricingVladmihalcea thirdV = new PricingVladmihalcea(3L, Money.of(new BigDecimal("29.6"), "EUR"));
        entityManager.persist(firstV);
        entityManager.flush();
        entityManager.persist(secondV);
        entityManager.flush();
        entityManager.persist(thirdV);
    }

    private void createJadiraTestData(){
        PricingJadira firstJ = new PricingJadira(1L, Money.of(new BigDecimal("12.7"), "EUR"));
        PricingJadira secondJ = new PricingJadira(2L, Money.of(new BigDecimal("30.2"), "EUR"));
        PricingJadira thirdJ = new PricingJadira(3L, Money.of(new BigDecimal("29.6"), "EUR"));
        entityManager.persist(firstJ);
        entityManager.flush();
        entityManager.persist(secondJ);
        entityManager.flush();
        entityManager.persist(thirdJ);
    }

    private void createSolutionForSortedDeceased(){
        sortedDeceased = new ArrayList<>();
        sortedDeceased.add(Money.of(new BigDecimal("30.2"), "EUR"));
        sortedDeceased.add(Money.of(new BigDecimal("29.6"), "EUR"));
        sortedDeceased.add(Money.of(new BigDecimal("12.7"), "EUR"));
    }
}
