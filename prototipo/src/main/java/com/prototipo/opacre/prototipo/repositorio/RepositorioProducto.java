package com.prototipo.opacre.prototipo.repositorio;

import com.prototipo.opacre.prototipo.clientes.Producto;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface RepositorioProducto  extends MongoRepository<Producto,String> {
}
