package com.prototipo.opacre.prototipo.repositorio;

import com.prototipo.opacre.prototipo.clientes.Factura;
import com.prototipo.opacre.prototipo.clientes.Recibo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RepositorioRecibo extends MongoRepository<Recibo,String> {
}
