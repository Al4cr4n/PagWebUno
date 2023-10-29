package com.example.pagwebuno.servlets;

import com.example.pagwebuno.beans.Trabajador;
import com.example.pagwebuno.daos.TrabajadorDao;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(name = "TrabajadorServlet", value = "/TrabajadorServlet")
public class TrabajadorServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");

        String action = request.getParameter("action") == null ? "lista" : request.getParameter("action");

        //saca del modelo
        TrabajadorDao trabajadorDao = new TrabajadorDao();



        switch (action) {
            case "lista":
                //saca del modelo
                ArrayList<Trabajador> list = trabajadorDao.listar();

                //mandar la lista a la vista -> job/lista.jsp
                request.setAttribute("lista", list);
                RequestDispatcher rd = request.getRequestDispatcher("trabajador/lista.jsp");
                rd.forward(request, response);
                break;
            case "new":
                request.getRequestDispatcher("trabajador/form_new.jsp").forward(request,response);
                break;
            case "edit":
                String dni = request.getParameter("id"); //primary key
                Trabajador trabajador = trabajadorDao.buscarPorDni(dni);

                if(trabajador != null){
                    request.setAttribute("trabajador",trabajador);
                    request.getRequestDispatcher("trabajador/form_edit.jsp").forward(request,response);
                }else{
                    response.sendRedirect(request.getContextPath() + "/TrabajadorServlet");
                }
                break;
            case "del":
                String dnii = request.getParameter("id");
                Trabajador trabajador1 = trabajadorDao.buscarPorDni(dnii);

                if(trabajador1 != null){
                    try {
                        trabajadorDao.borrar(dnii);
                    } catch (SQLException e) {
                        System.out.println("Log: excepcion: " + e.getMessage());
                    }
                }
                response.sendRedirect(request.getContextPath() + "/TrabajadorServlet");
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");


        TrabajadorDao trabajadorDao = new TrabajadorDao();

        String action = request.getParameter("action") == null ? "crear" : request.getParameter("action");

        switch (action){
            case "crear"://voy a crear un nuevo trabajo
                String trabajadorNombre = request.getParameter("trabajadorNombre");
                String trabajadorApellido = request.getParameter("trabajadorApellido");
                String trabajadorCorreo = request.getParameter("trabajadorCorreo");
                String trabajadorDni = request.getParameter("trabajadorDni");
                String trabajadorIdsede = request.getParameter("trabajadorIdsede");


                boolean isAllValid = true;

                if(trabajadorNombre.length() > 35){
                    isAllValid = false;
                }

                if(trabajadorDni.length() > 9){
                    isAllValid = false;
                }

                if(isAllValid){

                    Trabajador trabajador = trabajadorDao.buscarPorDni(trabajadorDni); //Busca si hay alguien con el mismo dni
                    //Creamos Trabajador
                    if(trabajador == null){  //Se verifica que no se repita el primary key
                        trabajadorDao.crear(trabajadorNombre,trabajadorApellido,trabajadorCorreo,trabajadorDni,Integer.parseInt(trabajadorIdsede));
                        response.sendRedirect(request.getContextPath() + "/TrabajadorServlet"); //Una vez creado y dado click a submit se devuelve a la página donde está la lista
                    }else{
                        request.getRequestDispatcher("trabajador/form_new.jsp").forward(request,response);
                    }
                }else{
                    request.getRequestDispatcher("trabajador/form_new.jsp").forward(request,response);
                }
                break;
            case "e": //voy a actualizar de la parte editar
                String trabajadorNombre2 = request.getParameter("trabajadorNombre");  //Obtenemos el valor de lo que tipeo el usuario
                String trabajadorApellido2 = request.getParameter("trabajadorApellido");
                String trabajadorCorreo2 = request.getParameter("trabajadorCorreo");
                String trabajadorDni2 = request.getParameter("trabajadorDni");
                String trabajadorIdsede2 = request.getParameter("trabajadorIdsede");

                boolean isAllValid2 = true;

                if(trabajadorApellido2.length() > 35){
                    isAllValid2 = false;
                }

                if(trabajadorDni2.length() > 9){
                    isAllValid2 = false;
                }
                if(isAllValid2){
                    Trabajador trabajador = new Trabajador();
                    trabajador.setNombres(trabajadorNombre2);  // Guardamos el valor que se dio por consola
                    trabajador.setApellidos(trabajadorApellido2);
                    trabajador.setCorreo(trabajadorCorreo2);
                    trabajador.setDni(trabajadorDni2);
                    trabajador.setIdsede(Integer.parseInt(trabajadorIdsede2));

                    trabajadorDao.actualizar(trabajador); // Se manda a actualizar
                    response.sendRedirect(request.getContextPath() + "/TrabajadorServlet");
                }else{
                    request.setAttribute("trabajador",trabajadorDao.buscarPorDni(trabajadorIdsede2));
                    request.getRequestDispatcher("trabajador/form_edit.jsp").forward(request,response);
                }
                break;

        }
    }
}

