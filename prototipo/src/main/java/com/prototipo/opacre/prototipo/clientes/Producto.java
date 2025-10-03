//package com.prototipo.opacre.prototipo.clientes;
//
//import org.springframework.data.annotation.Id;
//import org.springframework.data.mongodb.core.mapping.Document;
//
//@Document(collection = "producto")
//
//
//public class Producto {
//    @Id
//    private String id;
//    private String nombre;
//    private int cantidad;
//    private String codigo;
//    private TipoProductoEnum tipo;
//
//    public Producto(String id, String nombre, int cantidad, String codigo) {
//        this.id = id;
//        this.nombre = nombre;
//        this.cantidad = cantidad;
//        this.codigo = codigo;
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
//
//    public int getCantidad() {
//        return cantidad;
//    }
//
//    public void setCantidad(int cantidad) {
//        this.cantidad = cantidad;
//    }
//
//    public String getCodigo() {
//        return codigo;
//    }
//
//    public void setCodigo(String codigo) {
//        this.codigo = codigo;
//    }
//}
//microservicio cliente mongo
//micrservicio compra mongo

//trasnformador bases de datos
//apache kafka *****



package com.prototipo.opacre.prototipo.clientes;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "producto")
public class Producto {
    @Id
    private String id;
    private String nombre;
    private int cantidad;
    private String codigo;
    private TipoProductoEnum tipo;
    private double precio;  // ← NUEVO CAMPO

    public Producto() {
    }

    public Producto(String id, String nombre, int cantidad, String codigo) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.codigo = codigo;
    }

    public Producto(String id, String nombre, int cantidad, String codigo, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.codigo = codigo;
        this.precio = precio;
    }

    // Getters y Setters existentes
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

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public TipoProductoEnum getTipo() {
        return tipo;
    }

    public void setTipo(TipoProductoEnum tipo) {
        this.tipo = tipo;
    }

    // Getter y Setter NUEVOS para precio
    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}