package com.prototipo.opacre.prototipo.controladores;

import com.prototipo.opacre.prototipo.clientes.MateriaPrima;
import com.prototipo.opacre.prototipo.servicios.MateriaPrimaServicio;
import com.prototipo.opacre.prototipo.servicios.MateriaPrimaServicioImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/materiaPrima"
)
public class MateriaPrimaController {
    @Autowired
    private MateriaPrimaServicio materiaPrimaServicio;

    @GetMapping
    public List<MateriaPrima> findAll(){return materiaPrimaServicio.findAll();}



}
