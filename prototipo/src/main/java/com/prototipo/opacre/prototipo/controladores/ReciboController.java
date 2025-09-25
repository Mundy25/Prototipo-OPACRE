package com.prototipo.opacre.prototipo.controladores;

import com.prototipo.opacre.prototipo.clientes.Recibo;
import com.prototipo.opacre.prototipo.servicios.ReciboServicio;
import com.prototipo.opacre.prototipo.servicios.ReciboServicioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/recibo")

public class ReciboController {

    @Autowired
    private ReciboServicio reciboServicio;


    @GetMapping
    private List<Recibo> findAll(){ return reciboServicio.findAll();

    }
}
