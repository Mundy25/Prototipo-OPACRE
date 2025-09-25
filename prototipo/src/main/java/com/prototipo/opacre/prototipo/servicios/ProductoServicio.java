package com.prototipo.opacre.prototipo.servicios;

import com.prototipo.opacre.prototipo.clientes.Producto;

import java.util.List;

public interface ProductoServicio {

    public Producto guardar(Producto producto);

    public List<Producto> findAll();


}
