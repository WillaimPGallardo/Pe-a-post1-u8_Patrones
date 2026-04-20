package com.example.inventariocqrs.adapter.web;

import com.example.inventariocqrs.command.AgregarProductoCommand;
import com.example.inventariocqrs.command.handler.AgregarProductoHandler;
import com.example.inventariocqrs.query.handler.ListarProductosQueryHandler;
import com.example.inventariocqrs.query.model.ProductoView;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/inventario")
public class ProductoController {

    private final AgregarProductoHandler agregar;
    private final ListarProductosQueryHandler listar;

    public ProductoController(AgregarProductoHandler agregar,
                              ListarProductosQueryHandler listar) {
        this.agregar = agregar;
        this.listar = listar;
    }

    @PostMapping("/productos")
    public Map<String, String> crear(@RequestBody AgregarProductoCommand cmd) {
        return Map.of("id", agregar.handle(cmd).toString());
    }

    @GetMapping("/productos")
    public List<ProductoView> listar() {
        return listar.handle();
    }
}