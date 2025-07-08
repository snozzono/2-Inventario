package com.example.Inventario.services;


import com.example.Inventario.models.Inventario;
import com.example.Inventario.dto.InventarioDTO;
import com.example.Inventario.models.MovimientoInventario;
import com.example.Inventario.repository.InventarioRepository;
import com.example.Inventario.repository.MovimientoInventarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class InventarioService {

    private final InventarioRepository inventarioRepo;
    private final MovimientoInventarioRepository movimientoRepo;

    public Optional<Inventario> obtenerPorProducto(Integer idProducto) {
        return inventarioRepo.findByIdProducto(idProducto);
    }

    public Inventario ajustarStock(Integer idProducto, Integer nuevoStock) {
        Inventario inv = inventarioRepo.findByIdProducto(idProducto)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));
        inv.setStockDisponible(nuevoStock);
        return inventarioRepo.save(inv);
    }

    public MovimientoInventario registrarMovimiento(MovimientoInventario movimiento) {
        movimiento.setFecha(LocalDateTime.now());
        return movimientoRepo.save(movimiento);
    }

    public Inventario crearInventario(Inventario inventario) {
    return inventarioRepo.save(inventario);
    }

public InventarioDTO convertirAInventarioDTO(Inventario inventario) {
    InventarioDTO dto = new InventarioDTO();
    dto.setIdInventario(inventario.getIdInventario());
    dto.setIdProducto(inventario.getIdProducto());
    dto.setStockDisponible(inventario.getStockDisponible());
    dto.setUbicacionBodega(inventario.getUbicacionBodega());
    return dto;
}

    
}
