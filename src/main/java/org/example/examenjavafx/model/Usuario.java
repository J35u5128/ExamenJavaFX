package org.example.examenjavafx.model;

import javafx.beans.value.ObservableValue;

import java.io.Serializable;

public class Usuario implements Serializable {
    private Integer id;
    private String email;
    private String plataforma;
    private String version;
    private String fecha;
    private Boolean is_admin;

    public Usuario(Integer id, String email, String plataforma, String version, String fecha, Boolean is_admin) {
        this.id = id;
        this.email = email;
        this.plataforma = plataforma;
        this.version = version;
        this.fecha = fecha;
        this.is_admin = is_admin;
    }

    public Usuario() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPlataforma() {
        return plataforma;
    }

    public void setPlataforma(String plataforma) {
        this.plataforma = plataforma;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Boolean getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(Boolean is_admin) {
        this.is_admin = is_admin;
    }
}
