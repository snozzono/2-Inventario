package com.example.Inventario.controllers;

import com.example.Inventario.models.Inventario;
import com.example.Inventario.models.MovimientoInventario;
import com.example.Inventario.services.InventarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.Inventario.dto.InventarioDTO;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;



import java.util.Map;

@RestController
@RequestMapping("/api/inventario")
@RequiredArgsConstructor
public class InventarioController {

    private final InventarioService service;

    @GetMapping("/producto/{id}")
    public ResponseEntity<?> getStockByProducto(@PathVariable Integer id) {
        return service.obtenerPorProducto(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/ajuste")
    public ResponseEntity<Inventario> ajustar(@RequestBody Map<String, Object> data) {
        Integer idProducto = (Integer) data.get("idProducto");
        Integer nuevoStock = (Integer) data.get("nuevoStock");
        return ResponseEntity.ok(service.ajustarStock(idProducto, nuevoStock));
    }

    @PostMapping("/movimiento")
    public ResponseEntity<MovimientoInventario> movimiento(@RequestBody MovimientoInventario mov) {
        return ResponseEntity.ok(service.registrarMovimiento(mov));
    }

    @PostMapping
    public ResponseEntity<Object> crear(@RequestBody Inventario inventario) {
    return ResponseEntity.ok(service.crearInventario(inventario));
    }
@GetMapping("/hateoas/producto/{id}")
public ResponseEntity<InventarioDTO> obtenerInventarioHateoas(@PathVariable Integer id) {
    return service.obtenerPorProducto(id)
            .map(inv -> {
                InventarioDTO dto = service.convertirAInventarioDTO(inv);
                dto.add(linkTo(methodOn(InventarioController.class).obtenerInventarioHateoas(id)).withSelfRel());
                dto.add(linkTo(methodOn(InventarioController.class).ajustar(null)).withRel("ajustar")); // opcional
                dto.add(linkTo(methodOn(InventarioController.class).movimiento(null)).withRel("registrar-movimiento")); // opcional
                return ResponseEntity.ok(dto);
            })
            .orElse(ResponseEntity.notFound().build());
}


}