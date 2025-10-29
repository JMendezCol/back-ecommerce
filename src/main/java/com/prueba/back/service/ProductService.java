package com.prueba.back.service;

import com.prueba.back.model.Product;
import com.prueba.back.repository.ProductRepository;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> listar() {
        return repository.findAll();
    }

    public Product crear(Product p) {
        return repository.save(p);
    }

    public Product editar(Long id, Product datos) {
        Product existente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        existente.setNombre(datos.getNombre());
        existente.setCategoria(datos.getCategoria());
        existente.setProveedor(datos.getProveedor());
        existente.setPrecio(datos.getPrecio());
        existente.setStock(datos.getStock());
        return repository.save(existente);
    }

    public void eliminar(Long id) {
        repository.deleteById(id);
    }

    public Page<Product> listarConFiltros(
            int page,
            int size,
            String search,
            String categoria,
            String proveedor,
            Double minPrecio,
            Double maxPrecio
    ) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        List<Product> todos = repository.findAll();

        List<Product> filtrados = todos.stream()
                .filter(p -> search == null || p.getNombre().toLowerCase().contains(search.toLowerCase()))
                .filter(p -> categoria == null || p.getCategoria().equalsIgnoreCase(categoria))
                .filter(p -> proveedor == null || p.getProveedor().equalsIgnoreCase(proveedor))
                .filter(p -> minPrecio == null || p.getPrecio().compareTo(BigDecimal.valueOf(minPrecio)) >= 0)
                .filter(p -> maxPrecio == null || p.getPrecio().compareTo(BigDecimal.valueOf(maxPrecio)) <= 0)
                .collect(Collectors.toList());

        int start = (int) pageable.getOffset();
        int end = Math.min(start + pageable.getPageSize(), filtrados.size());

        return new PageImpl<>(filtrados.subList(start, end), pageable, filtrados.size());
    }
}