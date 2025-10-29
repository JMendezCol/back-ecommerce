package com.prueba.back.controller;

import com.prueba.back.model.Movimiento;
import com.prueba.back.model.TipoMovimiento;
import com.prueba.back.service.MovimientoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movimientos")
@CrossOrigin(origins = "*")
public class MovimientoController {
    private final MovimientoService movimientoService;

    public MovimientoController(MovimientoService movimientoService) {
        this.movimientoService = movimientoService;
    }

    @GetMapping
    public List<Movimiento> listarMovimientos(
            @RequestParam(required = false) Long productoId,
            @RequestParam(required = false) TipoMovimiento tipo
    ) {
        return movimientoService.listarMovimientos(productoId, tipo);
    }


    @PostMapping
    public Movimiento registrarMovimiento(
            @RequestParam Long productoId,
            @RequestParam TipoMovimiento tipo,
            @RequestParam int cantidad
    ) {
        return movimientoService.registrarMovimiento(productoId, tipo, cantidad);
    }
}