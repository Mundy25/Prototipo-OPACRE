package com.prototipo.opacre.prototipo.servicios;

import com.prototipo.opacre.prototipo.clientes.Factura;
import com.prototipo.opacre.prototipo.repositorio.RepositorioFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prototipo.opacre.prototipo.clientes.Factura;
import com.prototipo.opacre.prototipo.repositorio.RepositorioFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


import java.util.List;
@Service
public class FacturaServicioImp implements FacturaServicio {




@Autowired
private RepositorioFactura facturaRepository;

    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Optional<Factura> findById(String id) {
        return facturaRepository.findById(id);
    }

    @Override
    public List<Factura> findByClienteId(String clienteId) {
        return facturaRepository.findByClienteId(clienteId);
    }

    @Override
    public List<Factura> findByClienteUsername(String clienteUsername) {
        return facturaRepository.findByClienteUsername(clienteUsername);
    }

    @Override
    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public void delete(String id) {
        facturaRepository.deleteById(id);
    }
}