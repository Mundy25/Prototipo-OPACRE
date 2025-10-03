package com.prototipo.opacre.prototipo.clientes;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "materiaprima")

public class MateriaPrima {
    @Id
    private String id;
    private String nombre;
    private String cantidad;
    private String codigo;
    private String valor;

    public MateriaPrima(String id, String nombre, String cantidad, String codigo, String valor) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.codigo = codigo;
        this.valor = valor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }
}


 //incluir automatizaciones
 //ventas por whatsapp
 // enum de productos