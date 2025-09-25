package com.prototipo.opacre.prototipo.servicios;

import com.prototipo.opacre.prototipo.clientes.MateriaPrima;
import com.prototipo.opacre.prototipo.repositorio.RepositorioMateriaPrima;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import  java.util.List;
@Service

public class MateriaPrimaServicioImp implements MateriaPrimaServicio {
    //@Autowired
    private final RepositorioMateriaPrima repoMateriaPrima;

    public MateriaPrimaServicioImp(RepositorioMateriaPrima repoMateriaPrima) {
        this.repoMateriaPrima = repoMateriaPrima;
    }

//    public MateriaPrima guardar(MateriaPrima materiaPrima){
//        return repoMateriaPrima.save(materiaPrima);
//    }
//
//    public List<MateriaPrima> findAll(){
//        return repoMateriaPrima.findAll();
//    }


    @Override
    public MateriaPrima guardar(MateriaPrima materiaPrima) {
        return repoMateriaPrima.save(materiaPrima);
    }

    @Override
    public List<MateriaPrima> findAll() {
        return repoMateriaPrima.findAll();
    }
}