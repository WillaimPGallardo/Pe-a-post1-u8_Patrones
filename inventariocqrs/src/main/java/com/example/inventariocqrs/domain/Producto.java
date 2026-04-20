package com.example.inventariocqrs.domain;

import java.math.BigDecimal;

public class Producto {

    private final ProductoId id;
    private String nombre;
    private String categoria;
    private BigDecimal precioUnitario;
    private int stockDisponible;

    public Producto(ProductoId id, String nombre, String categoria,
                    BigDecimal precio, int stockInicial) {

        if (nombre == null || nombre.isBlank())
            throw new IllegalArgumentException("Nombre obligatorio");

        if (precio.compareTo(BigDecimal.ZERO) <= 0)
            throw new IllegalArgumentException("Precio inválido");

        this.id = id;
        this.nombre = nombre;
        this.categoria = categoria;
        this.precioUnitario = precio;
        this.stockDisponible = Math.max(0, stockInicial);
    }

    public void incrementarStock(int cantidad) {
        if (cantidad <= 0)
            throw new IllegalArgumentException("Cantidad inválida");
        stockDisponible += cantidad;
    }

    public void reducirStock(int cantidad) {
        if (cantidad > stockDisponible)
            throw new StockInsuficienteException(id.toString());
        stockDisponible -= cantidad;
    }

    public ProductoId getId() { return id; }
    public int getStockDisponible() { return stockDisponible; }
    public String getNombre() { return nombre; }
    public String getCategoria() { return categoria; }
    public BigDecimal getPrecioUnitario() { return precioUnitario; }
}