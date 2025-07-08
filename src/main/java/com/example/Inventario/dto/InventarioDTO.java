package com.example.Inventario.dto;

import lombok.Data;
import org.springframework.hateoas.RepresentationModel;

@Data
public class InventarioDTO extends RepresentationModel<InventarioDTO> {
    private Integer idInventario;
    private Integer idProducto;
    private Integer stockDisponible;
    private String ubicacionBodega;
}
