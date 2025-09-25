package com.prototipo.opacre.prototipo.servicios;

import com.prototipo.opacre.prototipo.clientes.Factura;

import java.util.List;

public interface FacturaServicio {
    public Factura guardar(Factura factura);
    public List<Factura> findAll();
}
