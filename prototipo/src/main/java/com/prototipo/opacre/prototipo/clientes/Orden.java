package com.prototipo.opacre.prototipo.clientes;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

import java.util.List;

@Document(collection = "orden")

public class Orden {
//    @Id
//    private  String id;
//    private Cliente cliente;
//
//    private String nombre;
//    private List<Producto> producto;
//    private String cantidad;
//    private String mesa;
//
//    public Orden(String id, String nombre, String producto, String cantidad, String mesa) {
//        this.id = id;
//        this.nombre = nombre;
////        this.producto = producto;
//        this.cantidad = cantidad;
//        this.mesa = mesa;
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
////    public String getProducto() {
////        return producto;
////    }
////
////    public void setProducto(String producto) {
////        this.producto = producto;
////    }
//
//    public String getCantidad() {
//        return cantidad;
//    }
//
//    public void setCantidad(String cantidad) {
//        this.cantidad = cantidad;
//    }
//
//    public String getMesa() {
//        return mesa;
//    }
//
//    public void setMesa(String mesa) {
//        this.mesa = mesa;
//    }
@Id
private String id;

    // Referencia al cliente (MEJORADA)
    private String clienteId;          // ID del cliente autenticado
    private String clienteUsername;    // Username del cliente
    private String clienteNombre;      // Nombre completo del cliente
    private String clienteEmail;       // Email del cliente
    private String clienteTelefono;    // Teléfono del cliente

    // Datos de la orden original
    private String nombre;
    private List<Producto> producto;
    private String cantidad;
    private String mesa;

    // NUEVOS CAMPOS útiles
    private String estado;             // pendiente, en_proceso, completada, cancelada
    private Double total;              // Total de la orden
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
    private String notas;              // Notas adicionales

    public Orden() {
        this.fechaCreacion = LocalDateTime.now();
        this.estado = "pendiente";
    }

    public Orden(String id, String nombre, String cantidad, String mesa) {
        this();
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.mesa = mesa;
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

    public List<Producto> getProducto() {
        return producto;
    }

    public void setProducto(List<Producto> producto) {
        this.producto = producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    // Getters y Setters NUEVOS para JWT y mejoras
    public String getClienteId() {
        return clienteId;
    }

    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public String getClienteUsername() {
        return clienteUsername;
    }

    public void setClienteUsername(String clienteUsername) {
        this.clienteUsername = clienteUsername;
    }

    public String getClienteNombre() {
        return clienteNombre;
    }

    public void setClienteNombre(String clienteNombre) {
        this.clienteNombre = clienteNombre;
    }

    public String getClienteEmail() {
        return clienteEmail;
    }

    public void setClienteEmail(String clienteEmail) {
        this.clienteEmail = clienteEmail;
    }

    public String getClienteTelefono() {
        return clienteTelefono;
    }

    public void setClienteTelefono(String clienteTelefono) {
        this.clienteTelefono = clienteTelefono;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public String getNotas() {
        return notas;
    }

    public void setNotas(String notas) {
        this.notas = notas;
    }

}