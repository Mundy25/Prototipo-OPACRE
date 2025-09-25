package com.prototipo.opacre.prototipo.servicios;

import com.prototipo.opacre.prototipo.clientes.Proveedor;
import com.prototipo.opacre.prototipo.repositorio.RepositorioProveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProveedorServicioImp  implements  ProveedorServicio{
    //@Autowired
    private final RepositorioProveedor repoProveedor;

    public ProveedorServicioImp(RepositorioProveedor repoProveedor) {
        this.repoProveedor = repoProveedor;
    }

//    public Proveedor guardar(Proveedor proveedor){
//        return repoProveedor.save(proveedor);
//    }
//
//    public List<Proveedor> findAll(){
//        return repoProveedor.findAll();
//    }

    @Override
    public Proveedor guardar(Proveedor proveedor) {
        return repoProveedor.save(proveedor);
    }

    @Override
    public List<Proveedor> findAll() {
        return repoProveedor.findAll();
    }
}
