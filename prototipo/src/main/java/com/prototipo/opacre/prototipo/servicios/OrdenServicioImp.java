package com.prototipo.opacre.prototipo.servicios;

import com.prototipo.opacre.prototipo.clientes.Orden;
import com.prototipo.opacre.prototipo.repositorio.RepositorioOrden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrdenServicioImp implements OrdenServicio {

    @Autowired
    private RepositorioOrden ordenRepository;

    @Override
    public List<Orden> findAll() {
        return ordenRepository.findAll();
    }

    @Override
    public Optional<Orden> findById(String id) {
        return ordenRepository.findById(id);
    }

    @Override
    public List<Orden> findByClienteId(String clienteId) {
        return ordenRepository.findByClienteId(clienteId);
    }

    @Override
    public List<Orden> findByClienteUsername(String clienteUsername) {
        return ordenRepository.findByClienteUsername(clienteUsername);
    }

    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public void delete(String id) {
        ordenRepository.deleteById(id);
    }
}