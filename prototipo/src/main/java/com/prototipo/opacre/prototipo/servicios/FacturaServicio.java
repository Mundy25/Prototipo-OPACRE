package com.prototipo.opacre.prototipo.servicios;

import com.prototipo.opacre.prototipo.clientes.Factura;

import java.util.List;
import java.util.Optional;
public interface FacturaServicio {
    //public Factura guardar(Factura factura);
    public List<Factura> findAll();
    Optional<Factura> findById(String id);
    List<Factura> findByClienteId(String clienteId);
    List<Factura> findByClienteUsername(String clienteUsername);
    Factura save(Factura factura);
    void delete(String id);
}

