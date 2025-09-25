package com.prototipo.opacre.prototipo.controladores;

import com.prototipo.opacre.prototipo.clientes.Factura;
import com.prototipo.opacre.prototipo.servicios.FacturaServicio;
import com.prototipo.opacre.prototipo.servicios.FacturaServicioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping ("/api/factura")

public class FacturaController {

    @Autowired
    private FacturaServicio facturaServicio;

    @GetMapping
    public List<Factura> findAll(){return facturaServicio.findAll();}

}
