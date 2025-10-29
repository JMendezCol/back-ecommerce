package com.prueba.back.repository;

import com.prueba.back.model.Movimiento;
import com.prueba.back.model.TipoMovimiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovimientoRepository extends JpaRepository<Movimiento, Long> {
    List<Movimiento> findByProductoId(Long productoId);
    List<Movimiento> findByTipo(TipoMovimiento tipo);
    List<Movimiento> findByProductoIdAndTipo(Long productoId, TipoMovimiento tipo);
}