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
import java.io.PrintWriter;
import java.sql.*;

public class ServicioLogin extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        System.out.println("llamando al Post");
        String user = req.getParameter("user");
        String pass = req.getParameter("pass");

        PrintWriter writer = resp.getWriter();

        try {
            Class.forName("com.mysql.jdbc.Driver");

            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:8889/Prueba", "root", "root");

            Statement statment = connection.createStatement();

            //String sql = "INSERT INTO prueba VALUES (null, '"+user+"', '"+pass+"')";

            String sql = "SELECT * FROM prueba WHERE user = '"+user+"' AND pass = '"+pass+"'";

            //statment.executeUpdate(sql);
            ResultSet rs= statment.executeQuery(sql);
            if (rs.first()) {
                //coinciden!
                writer.print("Bienvenido: " + user);
            } else {
                //no coinciden!
                writer.print("403 - DENIED ACCESS, GET OUT OF HERE MOTHERFU****!");
            }

            rs.close();
            writer.close();
            connection.close();
            statment.close();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
