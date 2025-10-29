package com.prueba.back.controller;


import com.prueba.back.model.Product;
import com.prueba.back.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }


    @GetMapping
    public Page<Product> listarProductos(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String search,
            @RequestParam(required = false) String categoria,
            @RequestParam(required = false) String proveedor,
            @RequestParam(required = false) Double minPrecio,
            @RequestParam(required = false) Double maxPrecio
    ) {
        return service.listarConFiltros(page, size, search, categoria, proveedor, minPrecio, maxPrecio);
    }

    @PostMapping
    public ResponseEntity<Product> crear(@Valid @RequestBody Product p) {
        return ResponseEntity.ok(service.crear(p));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> editar(@PathVariable Long id, @Valid @RequestBody Product p) {
        return ResponseEntity.ok(service.editar(id, p));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}