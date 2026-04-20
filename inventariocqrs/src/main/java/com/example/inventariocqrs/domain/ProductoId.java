package com.example.inventariocqrs.domain;

import java.util.UUID;

public record ProductoId(String valor) {

    public static ProductoId nuevo() {
        return new ProductoId(UUID.randomUUID().toString());
    }

    @Override
    public String toString() {
        return valor;
    }
}