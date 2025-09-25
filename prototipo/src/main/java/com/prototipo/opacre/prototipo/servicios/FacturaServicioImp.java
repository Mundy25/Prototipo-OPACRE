package com.prototipo.opacre.prototipo.servicios;

import com.prototipo.opacre.prototipo.clientes.Factura;
import com.prototipo.opacre.prototipo.repositorio.RepositorioFactura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FacturaServicioImp implements FacturaServicio {

   // @Autowired
    private final RepositorioFactura repoFactura;

    public FacturaServicioImp(RepositorioFactura repoFactura) {
        this.repoFactura = repoFactura;
    }

//    public Factura guardar(Factura factura){
//        return repoFactura.save(factura);
//    }
//    public List<Factura> findAll(){
//        return repoFactura.findAll();
//    }


    @Override
    public Factura guardar(Factura factura) {
        return repoFactura.save(factura);
    }

    @Override
    public List<Factura> findAll() {
        return repoFactura.findAll();
    }
}
