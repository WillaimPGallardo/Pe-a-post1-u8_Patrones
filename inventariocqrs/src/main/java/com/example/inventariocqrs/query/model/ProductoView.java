package com.example.inventariocqrs.query.model;

import java.math.BigDecimal;

public record ProductoView(
        String id,
        String nombre,
        String categoria,
        BigDecimal precioUnitario,
        int stockDisponible,
        String estadoStock
) {}