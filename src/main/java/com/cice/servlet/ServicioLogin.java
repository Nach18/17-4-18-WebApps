package com.cice.servlet;

/*ERRORES HABITUALES:
1) El proyecto no tiene la dependecia de maven
2) El Statment debe llamar a user y pass ya creados antes en el proyecto como '"+user+"', '"+pass+"'
3) La columna de ID debe ser unica por lo que ha de ser primaria y AUTO_INCREMENTABLE*/

import com.sun.net.httpserver.HttpsServer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class ServicioLogin extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("llamando al Post");
        String user = req.getParameter("user");
        String pass = req.getParameter("pass");

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/Prueba", "root", "root");

            Statement statment = connection.createStatement();

            String sql = "INSERT INTO prueba VALUES (null, '"+user+"', '"+pass+"')";

            statment.executeUpdate(sql);

            connection.close();
            statment.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
