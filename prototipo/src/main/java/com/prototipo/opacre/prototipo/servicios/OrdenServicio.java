package com.prototipo.opacre.prototipo.servicios;

import com.prototipo.opacre.prototipo.clientes.Orden;
import java.util.List;
import java.util.Optional;

public interface OrdenServicio {
    List<Orden> findAll();
    Optional<Orden> findById(String id);
    List<Orden> findByClienteId(String clienteId);
    List<Orden> findByClienteUsername(String clienteUsername);
    Orden save(Orden orden);
    void delete(String id);
}

