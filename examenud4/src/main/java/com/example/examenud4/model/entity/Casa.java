package com.example.examenud4.model.entity;


import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;



//contenido
@Entity
public class Casa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCasa;
    @Column(length = 100, unique = true, nullable = false)
    private String direccion;
    private double numHabitaciones;
    private double numPersonas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Localidad localidad;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "estancia",
    joinColumns = @JoinColumn(name = "idCasa", referencedColumnName = "idCasa"),
    inverseJoinColumns = @JoinColumn(name = "idHuesped", referencedColumnName = "idHuesped"))
    private Set<Huesped> huespeds = new HashSet<Huesped>();

    public Casa (String direccion, double numHabitaciones, double numPersonas, Localidad localidad) {
        this.direccion=direccion;
        this.numHabitaciones=numHabitaciones;
        this.numPersonas=numPersonas;
        this.localidad=localidad;
    }

    public Casa(){

    }

    @Override
    public String toString() {
        return "Casa [idCasa=" + idCasa + ", direccion=" + direccion + ", numHabitaciones=" + numHabitaciones
                + ", numPersonas=" + numPersonas + "]";
    }

    public int getIdCasa() {
        return idCasa;
    }

    public void setIdCasa(int idCasa) {
        this.idCasa = idCasa;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public double getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(double numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public double getNumPersonas() {
        return numPersonas;
    }

    public void setNumPersonas(double numPersonas) {
        this.numPersonas = numPersonas;
    }

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public Set<Huesped> getHuespeds() {
        return huespeds;
    }

    public void setHuespeds(Set<Huesped> huespeds) {
        this.huespeds = huespeds;
    }

    public void setHuesped(Huesped huesped) {
        huespeds.add(huesped);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Casa casa = (Casa) o;
        return idCasa == casa.idCasa;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(idCasa);
    }

    
}
