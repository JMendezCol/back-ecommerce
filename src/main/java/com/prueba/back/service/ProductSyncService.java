package com.prueba.back.service;

import com.prueba.back.dto.ExternalProduct;
import com.prueba.back.model.Product;
import com.prueba.back.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Service
public class ProductSyncService {

    private final ProductRepository repository;
    private final RestTemplate restTemplate = new RestTemplate();

    public ProductSyncService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> syncProducts() {
        String url = "https://fakestoreapi.com/products";
        ExternalProduct[] external = restTemplate.getForObject(url, ExternalProduct[].class);

        if (external == null) return List.of();

        List<Product> products = Arrays.stream(external).map(e -> {
            Product p = new Product();
            p.setNombre(e.getTitle());
            p.setCategoria(e.getCategory());
            p.setProveedor("FakeStore");
            p.setPrecio(BigDecimal.valueOf(e.getPrice()));
            p.setStock(10);
            p.setFechaRegistro(LocalDateTime.now());
            return p;
        }).toList();

        return repository.saveAll(products);
    }

}