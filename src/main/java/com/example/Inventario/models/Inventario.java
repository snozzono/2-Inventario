package com.example.Inventario.models;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "inventario")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_inventario")
    private Integer idInventario;

    @Column(name = "id_producto")
    private Integer idProducto;

    @Column(name = "stock_disponible")
    private Integer stockDisponible;

    @Column(name = "ubicacion_bodega")
    private String ubicacionBodega;
}

