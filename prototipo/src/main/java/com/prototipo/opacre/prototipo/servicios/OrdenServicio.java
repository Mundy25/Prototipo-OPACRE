package com.prototipo.opacre.prototipo.servicios;

import com.prototipo.opacre.prototipo.clientes.Orden;

import java.util.List;

public interface OrdenServicio {

    public Orden guardar(Orden orden);
    public List<Orden> findAll();
}
