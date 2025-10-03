package com.prototipo.opacre.prototipo.clientes;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Document(collection = "cliente")
public class Cliente {
    @Id
    private String id;
    private String nombre;
    private String direccion;
    private String email;
    private String telefono;


    @Indexed(unique = true)
    private String username;
    private String password; // Almacenada hasheada con BCrypt
    private List<String> roles;
    private LocalDateTime fechaRegistro;
    private boolean activo;

    public Cliente() {
        this.roles = new ArrayList<>();
        this.roles.add("CLIENTE");
        this.fechaRegistro = LocalDateTime.now();
        this.activo = true;
    }

    public Cliente(String nombre, String direccion, String email, String telefono,
                   String username, String password) {
        this();
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
        this.username = username;
        this.password = password;
    }

    public Cliente(String id, String nombre, String direccion, String email, String telefono) {
        this();
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
        this.email = email;
        this.telefono = telefono;
    }

    //    public Cliente(String id, String nombre, String direccion, String email, String telefono) {
//        this.id = id;
//        this.nombre = nombre;
//        this.direccion = direccion;
//        this.email = email;
//        this.telefono = telefono;
//    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}


