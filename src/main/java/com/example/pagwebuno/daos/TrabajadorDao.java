package com.example.pagwebuno.daos;

import com.example.pagwebuno.beans.Trabajador;

import java.sql.*;
import java.util.ArrayList;

public class TrabajadorDao {
    //mamdamos la consulta en un statement(stmt) , que es el carrito, y la repuesta se de vuelve en un resultset
    //executeQuery(), para Select, devuelve un objeto del tipo Resultset, el cual contiene la información de la consulta
    //executeUpdate, para INSERT, UPDATE o DELETE
    public ArrayList<Trabajador> listar(){

        ArrayList<Trabajador> lista = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/bicicentro";
        String username = "root";
        String password = "root";

        String sql = "select * from trabajadores";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Trabajador job = new Trabajador();
                job.setNombres(rs.getString(1));
                job.setApellidos(rs.getString(2));
                job.setCorreo(rs.getString(3));
                job.setDni(rs.getString(4));
                //job.setIdsede(rs.getInt(5));

                lista.add(job);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return lista;
    }

    public void crear(String trabajadorNombre, String trabajadorApellido, String trabajadorCorreo, String trabajadorDni, int trabajadorIdsede){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/bicicentro";
        String username = "root";
        String password = "root";

        String sql = "insert into trabajadores (nombres, apellidos, correo, dni, idsede) values (?,?,?,?,?)";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,trabajadorNombre);
            pstmt.setString(2,trabajadorApellido);
            pstmt.setString(3,trabajadorCorreo);
            pstmt.setString(4,trabajadorDni);
            pstmt.setInt(5,trabajadorIdsede);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Trabajador buscarPorDni(String dni){

        Trabajador trabajador = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/bicicentro";
        String username = "root";
        String password = "root";

        String sql = "select * from trabajadores where dni = ?";


        try (Connection conn = DriverManager.getConnection(url, username, password);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1,dni);

            try(ResultSet rs = pstmt.executeQuery()){
                while (rs.next()) {
                    trabajador = new Trabajador();
                    trabajador.setNombres(rs.getString(1));
                    trabajador.setApellidos(rs.getString(2));
                    trabajador.setCorreo(rs.getString(3));
                    trabajador.setDni(rs.getString(4));
                    trabajador.setIdsede(rs.getInt(5));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return trabajador;
    }
    public void actualizar(Trabajador trabajador) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/bicicentro";
        String username = "root";
        String password = "root";

        String sql = "update trabajadores set nombres = ?, apellidos = ?, correo = ? where dni = ?";  //aquí creo que es el problema

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,trabajador.getNombres());
            pstmt.setString(2,trabajador.getApellidos());
            pstmt.setString(3,trabajador.getCorreo());
            //pstmt.setInt(4,trabajador.getIdsede());
            pstmt.setString(4,trabajador.getDni());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrar(String trabajadorDni) throws SQLException {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/bicicentro";
        String username = "root";
        String password = "root";

        String sql = "delete from trabajadores where dni = ?";

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement pstmt = connection.prepareStatement(sql)){

            pstmt.setString(1,trabajadorDni);
            pstmt.executeUpdate();

        }
    }
}

