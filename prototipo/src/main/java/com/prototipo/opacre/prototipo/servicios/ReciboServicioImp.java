package com.prototipo.opacre.prototipo.servicios;

import com.prototipo.opacre.prototipo.clientes.Recibo;
import com.prototipo.opacre.prototipo.repositorio.RepositorioRecibo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class ReciboServicioImp implements ReciboServicio {

    //@Autowired
    private final RepositorioRecibo repoRecibo;

    public ReciboServicioImp(RepositorioRecibo repoRecibo) {
        this.repoRecibo = repoRecibo;
    }

//    public Recibo guardar(Recibo recibo){
//        return repoRecibo.save(recibo);
//    }
//
//    public List<Recibo> findAll(){ return repoRecibo.findAll();}


    @Override
    public Recibo guardar(Recibo recibo) {
        return repoRecibo.save(recibo);
    }

    @Override
    public List<Recibo> findAll() {
        return repoRecibo.findAll();
    }
}
