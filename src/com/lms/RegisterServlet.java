// package com.lms;

// import java.io.*;
// import javax.servlet.*;
// import javax.servlet.http.*;
// import java.sql.*;   
// import com.lms.DBConnection;



import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import com.lms.DBConnection;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;



public class RegisterServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection conn = DBConnection.getConnection()) {
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO users (name, email, password) VALUES (?, ?, ?)"
            );
            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, password);

            int rows = ps.executeUpdate();

            PrintWriter out = response.getWriter();
            if (rows > 0) {
                out.println("Registration successful!");
            } else {
                out.println("Registration failed!");
            }
        } catch (Exception e) {
            throw new ServletException("Database error", e);
        }
    }
}
