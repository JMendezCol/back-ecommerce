package com.prueba.back.controller;

import com.prueba.back.model.Product;
import com.prueba.back.service.ProductSyncService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SyncController {
    private final ProductSyncService syncService;

    public SyncController(ProductSyncService syncService) {
        this.syncService = syncService;
    }

    @GetMapping("/sync-products")
    public ResponseEntity<List<Product>> sync() {
        List<Product> synced = syncService.syncProducts();
        return ResponseEntity.ok(synced);
    }
}