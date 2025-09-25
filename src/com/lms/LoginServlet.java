package com.lms;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT name FROM users WHERE email=? AND password=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, email);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // ✅ Login success
                String name = rs.getString("name");

                // Create session
                HttpSession session = request.getSession();
                session.setAttribute("user", name);
                session.setAttribute("email", email);

                // 🔑 Redirect to dashboard.jsp (or index.jsp)
                response.sendRedirect("dashboard.jsp");
            } else {
                // ❌ Invalid login → back to login page
                response.sendRedirect("login.html?error=1");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("login.html?error=2");
        }
    }
}
