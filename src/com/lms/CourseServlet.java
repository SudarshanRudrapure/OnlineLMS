package com.lms;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.sql.*;
import org.json.JSONArray;
import org.json.JSONObject;


@WebServlet(name = "CourseServlet", urlPatterns = {"/api/courses"})
public class CourseServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
JSONArray arr = new JSONArray();
try (Connection con = DBConnection.getConnection();
PreparedStatement ps = con.prepareStatement("SELECT id,title,description FROM courses ORDER BY id");
ResultSet rs = ps.executeQuery()) {
while (rs.next()) {
JSONObject o = new JSONObject();
o.put("id", rs.getInt("id"));
o.put("title", rs.getString("title"));
o.put("description", rs.getString("description"));
arr.put(o);
}
} catch (Exception e) { e.printStackTrace(); }
JsonUtil.sendJson(resp, arr.toString());
}
}