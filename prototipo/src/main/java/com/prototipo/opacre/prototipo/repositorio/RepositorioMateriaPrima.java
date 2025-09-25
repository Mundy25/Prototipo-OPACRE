package com.prototipo.opacre.prototipo.repositorio;

import com.prototipo.opacre.prototipo.clientes.MateriaPrima;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioMateriaPrima extends MongoRepository<MateriaPrima,String> {
    
}
