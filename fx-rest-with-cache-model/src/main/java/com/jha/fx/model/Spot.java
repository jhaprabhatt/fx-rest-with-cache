package com.jha.fx.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "SPOT")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Spot implements Serializable {

    private static final long serialVersionUID = -4575587956960962769L;

    @Column(name = "ID")
    @Id
    private Integer id;

    @Column(name = "CURRENCY_PAIR")
    private String currencyPair;

    @Column(name = "PRICE_ON")
    private Date priceOn;

    @Column(name = "BUY")
    private BigDecimal buy;

    @Column(name = "SELL")
    private BigDecimal sell;
}
