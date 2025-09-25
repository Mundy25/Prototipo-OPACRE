package com.prototipo.opacre.prototipo.repositorio;

import com.prototipo.opacre.prototipo.clientes.Factura;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioFactura  extends MongoRepository<Factura,String> {
}
