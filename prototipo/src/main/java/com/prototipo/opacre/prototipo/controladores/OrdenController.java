package com.prototipo.opacre.prototipo.controladores;

import com.prototipo.opacre.prototipo.clientes.Orden;
import com.prototipo.opacre.prototipo.servicios.OrdenServicio;
import com.prototipo.opacre.prototipo.servicios.OrdenServicioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping ("/api/orden")

public class OrdenController {
    @Autowired
    private OrdenServicio ordenServicio;

    @GetMapping
    public List<Orden> findAll(){return ordenServicio.findAll();}

}
