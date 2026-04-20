package com.example.inventariocqrs.query.handler;

import com.example.inventariocqrs.query.model.ProductoView;
import com.example.inventariocqrs.query.repository.ProductoReadRepository;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ListarProductosQueryHandler {

    private final ProductoReadRepository repo;

    public ListarProductosQueryHandler(ProductoReadRepository repo) {
        this.repo = repo;
    }

    public List<ProductoView> handle() {
        return repo.buscarTodos();
    }
}