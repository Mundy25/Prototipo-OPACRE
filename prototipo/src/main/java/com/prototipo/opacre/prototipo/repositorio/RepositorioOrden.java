package com.prototipo.opacre.prototipo.repositorio;

import com.prototipo.opacre.prototipo.clientes.Orden;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioOrden  extends MongoRepository<Orden,String> {
}
