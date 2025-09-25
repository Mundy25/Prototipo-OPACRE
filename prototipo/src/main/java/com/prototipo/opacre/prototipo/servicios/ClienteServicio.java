package com.prototipo.opacre.prototipo.servicios;

import com.prototipo.opacre.prototipo.clientes.Cliente;

import java.util.List;

public interface ClienteServicio {

    public Cliente guardar(Cliente cliente) ;

    public List<Cliente> findAll();
}
