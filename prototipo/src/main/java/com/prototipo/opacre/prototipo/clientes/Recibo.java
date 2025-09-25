package com.prototipo.opacre.prototipo.clientes;

import org.springframework.data.annotation.Id;

public class Recibo {
    @Id
    private String id;
    private String codigo;
    private String cantidad;
    private String factura;

    public Recibo(String id, String codigo, String cantidad, String factura) {
        this.id = id;
        this.codigo = codigo;
        this.cantidad = cantidad;
        this.factura = factura;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public String getFactura() {
        return factura;
    }

    public void setFactura(String factura) {
        this.factura = factura;
    }
}
