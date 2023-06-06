package com.example.paytrix_demo;

import java.math.BigDecimal;

public class RequestData {
    private String source;
    private String destination;
    private BigDecimal amount;

    public RequestData() {
    }

    public RequestData(String source, String destination, BigDecimal amount) {
        this.source = source;
        this.destination = destination;
        this.amount = amount;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
