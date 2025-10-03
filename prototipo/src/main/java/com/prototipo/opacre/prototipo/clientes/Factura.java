package com.prototipo.opacre.prototipo.clientes;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "factura")
public class Factura {
    @Id
    private String id;
    private String nombre;
    private String mesa;
    private String valor;
    private List<Producto> producto;
    private String cantidad;

//
private String clienteId;          // ID del cliente que hizo la compra
    private String clienteUsername;    // Username del cliente autenticado
    private String clienteNombre;      // Nombre completo del cliente
    private LocalDateTime fechaCreacion;
    private String estado;             // pendiente, pagada, cancelada

    public Factura() {
        this.fechaCreacion = LocalDateTime.now();
        this.estado = "pendiente";
    }

    public Factura(String id, String nombre, String mesa, String valor, String cantidad) {
        this();
        this.id = id;
        this.nombre = nombre;
        this.mesa = mesa;
        this.valor = valor;
        this.cantidad = cantidad;
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

    public String getMesa() {
        return mesa;
    }

    public void setMesa(String mesa) {
        this.mesa = mesa;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
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

    // Getters y Setters NUEVOS para JWT
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

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }


}
