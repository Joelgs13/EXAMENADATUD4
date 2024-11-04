package com.example.examenud4.model.dao;

import com.example.examenud4.model.entity.Casa;
import com.example.examenud4.model.entity.Huesped;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import java.util.List;


public class DaoCasa {

    public void insertar(Casa casa, Session session) {
        Transaction transaction = null;
        // Obtener la sesión de Hibernate
        transaction = session.beginTransaction(); // Iniciar la transacción
        session.persist(casa); // Guardar el objeto casa
        transaction.commit(); // Confirmar la transacción
    }

    public List<Casa> obtenerCasaPorHuesped(Huesped huesped, Session session) {
        String sql = "FROM Casa c JOIN c.huespeds h WHERE h.idHuesped = :idHuesped";
        List<Casa> casas = session.createSelectionQuery(sql, Casa.class)
                .setParameter("idHuesped", huesped.getIdHuesped())
                .getResultList();
        return casas;
    }

    public Casa obtenerCasaPorDireccion(String direccion, Session session) {
        String sql = "FROM Casa WHERE direccion = :direccion";
        Query<Casa> query = session.createQuery(sql, Casa.class);
        query.setParameter("direccion", direccion);
        return query.uniqueResult(); // Devuelve null si no existe
    }

    public void insertartEstancia(Huesped huesped, Casa casa, Session session) {
        Transaction transaction = session.beginTransaction();
        huesped.setCasa(casa);
        casa.setHuesped(huesped);
        session.persist(casa);
        transaction.commit();
    }

    public List<Casa> findAll(Session session) {
        // Consulta JPQL para obtener todos los contenidos
        return session.createSelectionQuery("SELECT c FROM Casa c", Casa.class).getResultList();
    }
}
