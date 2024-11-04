package com.example.examenud4.model.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

//usuario
@Entity
public class Huesped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idHuesped;
    @Column(length = 100)
    private String nombre;
    private double edad;
    @Column(length = 100, unique = true, nullable = false)
    private String email;
    @ManyToMany(mappedBy = "huespeds", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Casa> casas = new HashSet<Casa>();

    public Huesped(String nombre, double edad, String email) {
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
    }

    public Huesped(){}

    @Override
    public String toString() {
        return "Huesped [idHuesped=" + idHuesped + ", nombre=" + nombre + ", edad=" + edad + ", email=" + email + "]";
    }

    public int getIdHuesped() {
        return idHuesped;
    }

    public void setIdHuesped(int idHuesped) {
        this.idHuesped = idHuesped;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getEdad() {
        return edad;
    }

    public void setEdad(double edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        Huesped huesped = (Huesped) obj;
        return idHuesped == huesped.idHuesped;
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(idHuesped);
    }
}
