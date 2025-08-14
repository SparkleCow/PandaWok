package com.sparklecow.pandawok.order.model;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public enum ProductType {

    THREE_DELIGHT_RICE("Rice with three kinds of meat", BigDecimal.valueOf(14000));

    private final String description;
    private final BigDecimal price;

    ProductType(String description, BigDecimal price) {
        this.description = description;
        this.price = price;
    }
}