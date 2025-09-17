package com.lms;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/leaderboard")
public class LeaderboardServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sql = "SELECT name, score FROM users ORDER BY score DESC LIMIT 10";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            StringBuilder json = new StringBuilder("[");
            boolean first = true;
            while (rs.next()) {
                if (!first) json.append(",");
                json.append("{\"name\":\"")
                    .append(rs.getString("name"))
                    .append("\",\"score\":")
                    .append(rs.getInt("score"))
                    .append("}");
                first = false;
            }
            json.append("]");

            JsonUtil.sendJson(resp, json.toString());
        } catch (Exception e) {
            JsonUtil.sendJson(resp, "{\"error\":\"" + e.getMessage().replace("\"","'") + "\"}");
        }
    }
}



//for run---javac -cp "C:\Tomcat\apache-tomcat-10.1.44\lib\servlet-api.jar;C:\OnlineLMS\WebContent\WEB-INF\lib\json-20240303.jar;." com/lms/*.java
