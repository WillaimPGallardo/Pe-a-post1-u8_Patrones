package com.example.inventariocqrs.domain;

public class StockInsuficienteException extends RuntimeException {
    public StockInsuficienteException(String id) {
        super("Stock insuficiente para producto " + id);
    }
}