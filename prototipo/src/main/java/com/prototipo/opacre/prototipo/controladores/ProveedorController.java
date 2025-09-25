package com.prototipo.opacre.prototipo.controladores;

import com.prototipo.opacre.prototipo.clientes.Proveedor;
import com.prototipo.opacre.prototipo.servicios.ProveedorServicio;
import com.prototipo.opacre.prototipo.servicios.ProveedorServicioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/proveedor")

public class ProveedorController {

    @Autowired
    private ProveedorServicio proveedorServicio;

    @GetMapping
    private List<Proveedor> findAll() {return proveedorServicio.findAll();}




}
