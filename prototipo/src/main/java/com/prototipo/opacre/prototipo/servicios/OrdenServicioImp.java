package com.prototipo.opacre.prototipo.servicios;

import com.prototipo.opacre.prototipo.clientes.Orden;
import com.prototipo.opacre.prototipo.repositorio.RepositorioOrden;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class OrdenServicioImp  implements OrdenServicio{

    //@Autowired
    private final RepositorioOrden repoOrden;

    public OrdenServicioImp(RepositorioOrden repoOrden) {
        this.repoOrden = repoOrden;
    }

//    public Orden guardar(Orden orden){
//        return repoOrden.save(orden);
//    }
//    public  List<Orden> findAll(){
//        return repoOrden.findAll();
//    }

    @Override
    public Orden guardar(Orden orden) {
        return repoOrden.save(orden);
    }

    @Override
    public List<Orden> findAll() {
        return repoOrden.findAll();
    }
}
