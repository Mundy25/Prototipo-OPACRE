package com.prototipo.opacre.prototipo.controladores;

import com.prototipo.opacre.prototipo.clientes.Producto;
import com.prototipo.opacre.prototipo.servicios.ProductoServicio;
import com.prototipo.opacre.prototipo.servicios.ProductoServicioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/producto")
public class ProductoController {

    private final ProductoServicio productoServicio;

    // Inyección por constructor (recomendada)
    @Autowired
    public ProductoController(ProductoServicioImp productoServicio) {
        this.productoServicio = productoServicio;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> findAll() {
        List<Producto> productos = productoServicio.findAll();
        return ResponseEntity.ok(productos);
    }
}
