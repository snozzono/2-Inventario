package com.example.Inventario.models;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "movimientos_inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovimientoInventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "id_producto")
    private Integer idProducto;
    private Integer cantidad;
    @Column (name= "tipo_movimiento")
    private String tipoMovimiento; // EJ: "ENTRADA", "SALIDA"
    private LocalDateTime fecha;
    private String descripcion;
}