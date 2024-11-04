package com.example.examenud4.model.dao;

import com.example.examenud4.model.entity.Localidad;
import com.example.examenud4.model.entity.Huesped;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;

public class DaoLocalidad {
    public void insertar(Localidad localidad, Session session) {
        Transaction transaction = null;
        // Obtener la sesión de Hibernate
        transaction = session.beginTransaction(); // Iniciar la transacción
        session.persist(localidad); // Guardar el objeto Olimpiada
        transaction.commit(); // Confirmar la transacción
    }

    public Localidad obtenerLocalidadPorCP(double codigoPostal, Session session) {
        String hql = "FROM Localidad WHERE codigoPostal = :codigoPostal";
        Query<Localidad> query = session.createQuery(hql, Localidad.class);
        query.setParameter("codigoPostal", codigoPostal);
        return query.uniqueResult(); // Devuelve null si no existe
    }

    //!!!!!!
    public List<Localidad> obtenerLocalidadPorHuesped(Huesped huesped, Session session) {
        String hql = "FROM Localidad cc JOIN cc.casas c JOIN c.huespeds u WHERE cc.idLocalidad = c.idLocalidad AND u.idHuesped = :idHuesped";
        List<Localidad> localidades = session.createSelectionQuery(hql, Localidad.class)
                .setParameter("idHuesped", huesped.getIdHuesped())
                .getResultList();
        return localidades;
    }
}