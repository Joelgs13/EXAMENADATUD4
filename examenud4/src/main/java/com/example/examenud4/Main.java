package com.example.examenud4;

import com.example.examenud4.model.entity.*;
import com.example.examenud4.model.dao.*;
import org.hibernate.Session;

import java.io.*;
import java.util.List;

public class Main {
    private static final String FICHERO = "C:\\Users\\HP\\Desktop\\DM2\\ADAT\\EXAMENES\\T4\\examenud4\\ficheros\\airbnb_data_coherent.csv";

    private static double validateDouble(String value) {
        if (value.isEmpty()) {
            return 0;
        }
        return Double.parseDouble(value);
    }

    private static String validateString(String value) {
        if (value.isEmpty()) {
            return "N/A";
        }
        return value.strip();
    }

    public static void cargarCsv() {
        // nombre,mail,edad,tag,tiempo,cuenta_instagram,año_creación,ubicacion,nombre_publi
        //direccion_casa,numero_habitaciones,numero_personas,ciudad,codigo_postal,nombre_persona,edad_persona,email_persona
        File f = new File(FICHERO);
        Session session = HibernateUtil.getSessionFactory().openSession();
        DaoHuesped daoHuesped = new DaoHuesped();
        DaoCasa daoCasa = new DaoCasa();
        DaoLocalidad daoLocalidad = new DaoLocalidad();
        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea = br.readLine();
            linea = br.readLine();
            while (linea!=null && !linea.isEmpty()) {
                String[] partes = linea.split(",");

                String direccion_casa = validateString(partes[0]);
                double numero_habitaciones = validateDouble(partes[1]);
                double numero_personas = validateDouble(partes[2]);
                String ciudad = validateString(partes[3]);
                double codigo_postal = validateDouble(partes[4]);
                String nombre_persona = validateString(partes[5]);
                double edad_persona = validateDouble(partes[6]);
                String email_persona = validateString(partes[7]);
                
                Huesped huesped = daoHuesped.obtenerHuespedPorEmail(email_persona, session);
                if (huesped == null) {
                    huesped = new Huesped(nombre_persona, edad_persona, email_persona);
                    daoHuesped.insertar(huesped, session);
                    huesped = daoHuesped.obtenerHuespedPorEmail(email_persona, session);
                }
                Localidad localidad = daoLocalidad.obtenerLocalidadPorCP(codigo_postal, session);
                if (localidad == null) {
                    localidad = new Localidad(ciudad,codigo_postal);
                    daoLocalidad.insertar(localidad, session);
                    localidad = daoLocalidad.obtenerLocalidadPorCP(codigo_postal, session);
                }
                Casa casa = daoCasa.obtenerCasaPorDireccion(direccion_casa, session);
                if (casa == null) {
                    casa = new Casa(direccion_casa, numero_habitaciones, numero_personas, localidad);
                    daoCasa.insertar(casa, session);
                    daoHuesped.aniadirCasa(casa, huesped, session);
                    casa = daoCasa.obtenerCasaPorDireccion(direccion_casa, session);
                }
                daoCasa.insertartEstancia(huesped, casa, session);
                linea = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void selectAll2() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        DaoCasa daoCasa = new DaoCasa();
        List<Casa> resultados = daoCasa.findAll(session);
        for (int i = 0; i < resultados.size(); i++) {
            System.out.println("Direccion: " + resultados.get(i).getDireccion());
            System.out.println("Localidad: " + resultados.get(i).getLocalidad().getCiudad());
            System.out.println(resultados.get(i).getHuespeds().toString());


        }
    }

    public static void main(String[] args) {
        cargarCsv();
 
        selectAll2();
    }
}