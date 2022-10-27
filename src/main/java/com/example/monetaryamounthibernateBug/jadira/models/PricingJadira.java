package com.example.monetaryamounthibernateBug.jadira.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.Type;

import javax.money.MonetaryAmount;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class PricingJadira {
    @Id
    private long id;

    @Columns(columns = { @Column(name = "MY_CURRENCY"), @Column(name = "MY_AMOUNT") })
    @Type(type = "org.jadira.usertype.moneyandcurrency.moneta.PersistentMoneyAmountAndCurrency")
    private MonetaryAmount price;

    @Override
    public boolean equals(Object o) {
        if (o instanceof PricingJadira pricingJadira) {
            return pricingJadira.getPrice().isEqualTo(price);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return 2;
    }
}