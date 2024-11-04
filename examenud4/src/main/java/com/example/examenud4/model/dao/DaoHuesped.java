package com.example.examenud4.model.dao;

import com.example.examenud4.model.entity.Casa;
import com.example.examenud4.model.entity.Huesped;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;
public class DaoHuesped {
    public void insertar(Huesped huesped, Session session) {
        Transaction transaction = null;
        // Obtener la sesión de Hibernate
        transaction = session.beginTransaction(); // Iniciar la transacción
        session.persist(huesped); // Guardar el objeto huesped
        transaction.commit(); // Confirmar la transacción
    }

    public List<Huesped> obtenerTodos(Session session) {
        String hql = "FROM Huesped";
        Query<Huesped> query = session.createQuery(hql, Huesped.class);
        return query.getResultList();
    }

    public Huesped obtenerHuespedPorEmail(String email, Session session) {
        String hql = "FROM Huesped WHERE email = :email";
        Query<Huesped> query = session.createQuery(hql, Huesped.class);
        query.setParameter("email", email);
        return query.uniqueResult(); // Devuelve null si no existe
    }

    public void aniadirCasa(Casa casa, Huesped huesped, Session session) {
        Transaction transaction = session.beginTransaction();
        huesped.setCasa(casa);
        session.merge(huesped);
        transaction.commit();
    }
}
