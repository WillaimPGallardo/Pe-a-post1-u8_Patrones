package com.example.inventariocqrs.command.handler;

import com.example.inventariocqrs.command.ActualizarStockCommand;
import com.example.inventariocqrs.command.repository.ProductoWriteRepository;
import com.example.inventariocqrs.domain.ProductoId;

import org.springframework.stereotype.Component;

@Component
public class ActualizarStockHandler {

    private final ProductoWriteRepository repo;

    public ActualizarStockHandler(ProductoWriteRepository repo) {
        this.repo = repo;
    }

    public void handle(ActualizarStockCommand cmd) {
        var producto = repo.buscarPorId(new ProductoId(cmd.productoId()))
                .orElseThrow();

        producto.reducirStock(cmd.cantidad());
        repo.guardar(producto);
    }
}