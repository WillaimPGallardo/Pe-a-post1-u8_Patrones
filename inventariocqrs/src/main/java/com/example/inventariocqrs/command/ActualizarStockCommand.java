package com.example.inventariocqrs.command;

public record ActualizarStockCommand(
        String productoId,
        int cantidad
) {}