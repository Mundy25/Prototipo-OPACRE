package com.prototipo.opacre.prototipo.servicios;

import com.prototipo.opacre.prototipo.clientes.Recibo;

import java.util.List;

public interface ReciboServicio {
    public Recibo guardar(Recibo recibo);
    public List<Recibo> findAll();
}
