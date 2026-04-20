package com.example.inventariocqrs.command.repository;

import com.example.inventariocqrs.domain.Producto;
import com.example.inventariocqrs.domain.ProductoId;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class ProductoWriteRepositoryImpl implements ProductoWriteRepository {

    private final Map<String, Producto> db = new HashMap<>();

    @Override
    public void guardar(Producto producto) {
        db.put(producto.getId().toString(), producto);
    }

    @Override
    public Optional<Producto> buscarPorId(ProductoId id) {
        return Optional.ofNullable(db.get(id.toString()));
    }

    @Override
    public void eliminar(ProductoId id) {
        db.remove(id.toString());
    }

    public Collection<Producto> obtenerTodos() {
        return db.values();
    }
}