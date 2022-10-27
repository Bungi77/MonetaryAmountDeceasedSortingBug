package com.example.monetaryamounthibernateBug.vladmihalcea.models;

import com.vladmihalcea.hibernate.type.money.MonetaryAmountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Columns;
import org.hibernate.annotations.TypeDef;

import javax.money.MonetaryAmount;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@TypeDef(typeClass = MonetaryAmountType.class,
        defaultForType = MonetaryAmount.class)
public class PricingVladmihalcea {
    @Id
    private long id;

    @Columns(columns = {
            @Column(name = "tarifbasiswert_betrag", updatable = false, nullable = false),
            @Column(name = "tarifbasiswert_waehrung", updatable = false, nullable = false)
    })
    private MonetaryAmount price;

    @Override
    public boolean equals(Object o) {
        if (o instanceof PricingVladmihalcea pricingVladmihalcea) {
            return pricingVladmihalcea.getPrice().isEqualTo(price);
        }
        return false;
    }

    @Override
    public int hashCode(){
        return 1;
    }
}
