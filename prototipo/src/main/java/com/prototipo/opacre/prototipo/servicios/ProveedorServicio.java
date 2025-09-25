package com.prototipo.opacre.prototipo.servicios;

import com.prototipo.opacre.prototipo.clientes.Proveedor;

import java.util.List;

public interface ProveedorServicio {

    public Proveedor guardar(Proveedor proveedor);

    public List<Proveedor> findAll();
}
