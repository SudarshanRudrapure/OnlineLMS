package com.lms;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet(name = "ContestServlet", urlPatterns = {"/api/contests"})
public class ContestServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        StringBuilder json = new StringBuilder("[");
        boolean first = true;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(
                     "SELECT id, name, start_time, end_time FROM contests ORDER BY start_time DESC");
             ResultSet rs = ps.executeQuery();
             PrintWriter out = resp.getWriter()) {

            while (rs.next()) {
                if (!first) json.append(",");
                json.append("{")
                        .append("\"id\":").append(rs.getInt("id")).append(",")
                        .append("\"name\":\"").append(rs.getString("name")).append("\",")
                        .append("\"start_time\":\"").append(rs.getString("start_time")).append("\",")
                        .append("\"end_time\":\"").append(rs.getString("end_time")).append("\"")
                        .append("}");
                first = false;
            }

            json.append("]");
            resp.getWriter().write(json.toString());

        } catch (Exception e) {
            e.printStackTrace();
            resp.getWriter().write("{\"error\":\"" + e.getMessage().replace("\"","'") + "\"}");
        }
    }
}
