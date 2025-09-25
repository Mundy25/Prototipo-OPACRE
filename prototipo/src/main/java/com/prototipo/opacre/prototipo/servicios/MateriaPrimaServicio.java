package com.prototipo.opacre.prototipo.servicios;

import com.prototipo.opacre.prototipo.clientes.MateriaPrima;

import java.util.List;

public interface MateriaPrimaServicio {

    public MateriaPrima guardar(MateriaPrima materiaPrima);

    public List<MateriaPrima> findAll();
}
