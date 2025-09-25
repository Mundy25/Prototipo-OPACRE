package com.prototipo.opacre.prototipo.servicios;


import com.prototipo.opacre.prototipo.clientes.Cliente;
import com.prototipo.opacre.prototipo.repositorio.RepositorioCliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteServicioImp implements ClienteServicio{

    //@Autowired
    private final RepositorioCliente repoServicio;

    public ClienteServicioImp(RepositorioCliente repoServicio) {
        this.repoServicio = repoServicio;
    }

//    public Cliente guardar(Cliente cliente) {
//        return repoServicio.save(cliente);
//    }
//
//    public List<Cliente> findAll() {
//        return repoServicio.findAll();
//    }


    @Override
    public Cliente guardar(Cliente cliente) {
        return repoServicio.save(cliente);
    }

    @Override
    public List<Cliente> findAll() {
        return repoServicio.findAll();
    }
}
