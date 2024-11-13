package com.task.bigxyt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class StockExchangeOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String orderId;

    private String orderType;

    private String operationType;

    private Double price;

    private Integer quantity;

    // Constructors for new StockExchangeOrder objects - first one for add operations, second for remove operations
    public StockExchangeOrder(final String operationType) {
        this.operationType = operationType;
    }

    public StockExchangeOrder(final String orderId, final String orderType, final String operationType, final Double price) {
        this.orderId = orderId;
        this.orderType = orderType;
        this.operationType = operationType;
        this.price = price;
    }
}
