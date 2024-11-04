package com.example.examenud4.model.entity;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//cuenta creadora
@Entity
public class Localidad {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLocalidad;
    @Column(length = 100)
    private String ciudad;
    @Column(unique = true, nullable = false)
    private double codigoPostal;

    @OneToMany(mappedBy = "idCasa")
    private Set<Casa> casas = new HashSet<Casa>();

    public Localidad(String ciudad, double codigoPostal) {
        this.ciudad = ciudad;
        this.codigoPostal = codigoPostal;
    }

    public Localidad(){}

    @Override
    public String toString() {
        return "Localidad [idLocalidad=" + idLocalidad + ", ciudad=" + ciudad + ", codigoPostal=" + codigoPostal + "]";
    }

    public int getIdLocalidad() {
        return idLocalidad;
    }

    public void setIdLocalidad(int idLocalidad) {
        this.idLocalidad = idLocalidad;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public double getCodigoPostal() {
        return codigoPostal;
    }

    public void setCodigoPostal(double codigoPostal) {
        this.codigoPostal = codigoPostal;
    }

    public Set<Casa> getCasas() {
        return casas;
    }

    public void setCasas(Set<Casa> casas) {
        this.casas = casas;
    }

    public void setCasa(Casa casa) {
        casas.add(casa);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Localidad that = (Localidad) obj;
        return idLocalidad == that.idLocalidad;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idLocalidad);
    }

}
