package com.prueba.back.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Column(nullable = false)
    private String nombre;

    @NotBlank(message = "La categoría es obligatoria")
    @Column(nullable = false)
    private String categoria;

    @NotBlank(message = "El proveedor es obligatorio")
    @Column(nullable = false)
    private String proveedor;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0")
    @Column(nullable = false)
    private BigDecimal precio;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    @Column(nullable = false)
    private Integer stock;

    @Column(name = "fecha_registro", nullable = false)
    private LocalDateTime fechaRegistro = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "El nombre es obligatorio") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "El nombre es obligatorio") String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank(message = "La categoría es obligatoria") String getCategoria() {
        return categoria;
    }

    public void setCategoria(@NotBlank(message = "La categoría es obligatoria") String categoria) {
        this.categoria = categoria;
    }

    public @NotBlank(message = "El proveedor es obligatorio") String getProveedor() {
        return proveedor;
    }

    public void setProveedor(@NotBlank(message = "El proveedor es obligatorio") String proveedor) {
        this.proveedor = proveedor;
    }

    public @NotNull(message = "El precio es obligatorio") @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0") BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(@NotNull(message = "El precio es obligatorio") @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor que 0") BigDecimal precio) {
        this.precio = precio;
    }

    public @NotNull(message = "El stock es obligatorio") @Min(value = 0, message = "El stock no puede ser negativo") Integer getStock() {
        return stock;
    }

    public void setStock(@NotNull(message = "El stock es obligatorio") @Min(value = 0, message = "El stock no puede ser negativo") Integer stock) {
        this.stock = stock;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}