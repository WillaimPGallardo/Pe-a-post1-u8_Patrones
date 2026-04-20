package com.example.inventariocqrs.command.handler;

import com.example.inventariocqrs.command.EliminarProductoCommand;
import com.example.inventariocqrs.command.repository.ProductoWriteRepository;
import com.example.inventariocqrs.domain.ProductoId;

import org.springframework.stereotype.Component;

@Component
public class EliminarProductoHandler {

    private final ProductoWriteRepository repo;

    public EliminarProductoHandler(ProductoWriteRepository repo) {
        this.repo = repo;
    }

    public void handle(EliminarProductoCommand cmd) {
        repo.eliminar(new ProductoId(cmd.productoId()));
    }
}