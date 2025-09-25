package com.prototipo.opacre.prototipo.servicios;
import com.prototipo.opacre.prototipo.clientes.Producto;
import com.prototipo.opacre.prototipo.repositorio.RepositorioProducto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class ProductoServicioImp implements ProductoServicio{

    //@Autowired
    private final RepositorioProducto repoProducto;

    public ProductoServicioImp(RepositorioProducto repoProducto) {
        this.repoProducto = repoProducto;
    }

//

    @Override
    public Producto guardar(Producto producto) {
        return repoProducto.save(producto);
    }

    @Override
    public List<Producto> findAll() {
        return repoProducto.findAll();
    }
}
