package com.prueba.back.service;

import com.prueba.back.model.Movimiento;
import com.prueba.back.model.Product;
import com.prueba.back.model.TipoMovimiento;
import com.prueba.back.repository.MovimientoRepository;
import com.prueba.back.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MovimientoService {

    private final MovimientoRepository movimientoRepository;
    private final ProductRepository productoRepository;

    public MovimientoService(MovimientoRepository movimientoRepository, ProductRepository productoRepository) {
        this.movimientoRepository = movimientoRepository;
        this.productoRepository = productoRepository;
    }

    @Transactional(readOnly = true)
    public List<Movimiento> listarMovimientos(Long productoId, TipoMovimiento tipo) {
        if (productoId != null && tipo != null) {
            return movimientoRepository.findByProductoIdAndTipo(productoId, tipo);
        } else if (productoId != null) {
            return movimientoRepository.findByProductoId(productoId);
        } else if (tipo != null) {
            return movimientoRepository.findByTipo(tipo);
        } else {
            return movimientoRepository.findAll();
        }
    }


    @Transactional
    public Movimiento registrarMovimiento(Long productoId, TipoMovimiento tipo, int cantidad) {
        Product producto = productoRepository.findById(productoId)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        if (tipo == TipoMovimiento.ENTRADA && cantidad == producto.getStock()) {
        } else if (tipo == TipoMovimiento.ENTRADA) {
            producto.setStock(producto.getStock() + cantidad);
        } else if (tipo == TipoMovimiento.SALIDA) {
            if (producto.getStock() < cantidad) {
                throw new RuntimeException("Stock insuficiente para salida");
            }
            producto.setStock(producto.getStock() - cantidad);
        }
        productoRepository.save(producto);
        Movimiento movimiento = new Movimiento();
        movimiento.setProducto(producto);
        movimiento.setTipo(tipo);
        movimiento.setCantidad(cantidad);

        return movimientoRepository.save(movimiento);
    }

}