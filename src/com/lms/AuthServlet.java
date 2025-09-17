package com.lms;

import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.sql.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class AuthServlet extends HttpServlet {

    // Utility: hash password using SHA-256
    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashed = md.digest(password.getBytes());
            return Base64.getEncoder().encodeToString(hashed);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        String action = req.getParameter("action"); // "register" or "login"
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try (Connection conn = DBConnection.getConnection()) {
            if ("register".equals(action)) {
                String sql = "INSERT INTO users (name, email, password_hash) VALUES (?, ?, ?)";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, name);
                ps.setString(2, email);
                ps.setString(3, hashPassword(password));
                int rows = ps.executeUpdate();
                System.out.println("REGISTER: inserted rows = " + rows);
                out.print("{\"status\":\"success\", \"message\":\"User registered (" + rows + " row(s))\"}");

            } else if ("login".equals(action)) {
                String sql = "SELECT * FROM users WHERE email=? AND password_hash=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, email);
                ps.setString(2, hashPassword(password));
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    out.print("{\"status\":\"success\", \"message\":\"Login successful\"}");
                } else {
                    out.print("{\"status\":\"error\", \"message\":\"Invalid credentials\"}");
                }
            } else {
                out.print("{\"status\":\"error\", \"message\":\"Unknown action\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.print("{\"status\":\"error\", \"message\":\"" + e.getMessage() + "\"}");
        }
    }
}
