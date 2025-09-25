package com.prototipo.opacre.prototipo.repositorio;

import com.prototipo.opacre.prototipo.clientes.Proveedor;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;


public interface RepositorioProveedor  extends MongoRepository<Proveedor,String> {
}

