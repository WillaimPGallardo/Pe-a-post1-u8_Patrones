package com.example.inventariocqrs.query.repository;

import com.example.inventariocqrs.command.repository.ProductoWriteRepositoryImpl;
import com.example.inventariocqrs.query.model.ProductoView;

import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ProductoReadRepositoryImpl implements ProductoReadRepository {

    private final ProductoWriteRepositoryImpl repo;

    public ProductoReadRepositoryImpl(ProductoWriteRepositoryImpl repo) {
        this.repo = repo;
    }

    @Override
    public List<ProductoView> buscarTodos() {
        return repo.obtenerTodos().stream()
                .map(p -> new ProductoView(
                        p.getId().toString(),
                        p.getNombre(),
                        p.getCategoria(),
                        p.getPrecioUnitario(),
                        p.getStockDisponible(),
                        calcularEstado(p.getStockDisponible())
                ))
                .collect(Collectors.toList());
    }

    private String calcularEstado(int stock) {
        if (stock == 0) return "AGOTADO";
        if (stock < 5) return "BAJO";
        return "DISPONIBLE";
    }
}