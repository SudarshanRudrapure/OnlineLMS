package com.lms;


import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.sql.*;
import org.json.JSONArray;
import org.json.JSONObject;


@WebServlet(name = "ContestServlet", urlPatterns = {"/api/contests"})
public class ContestServlet extends HttpServlet {
@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
JSONArray arr = new JSONArray();
try (Connection con = DBConnection.getConnection();
PreparedStatement ps = con.prepareStatement("SELECT id,name,start_time,end_time FROM contests ORDER BY start_time DESC");
ResultSet rs = ps.executeQuery()) {
while (rs.next()) {
JSONObject o = new JSONObject();
o.put("id", rs.getInt("id"));
o.put("name", rs.getString("name"));
o.put("start_time", rs.getString("start_time"));
o.put("end_time", rs.getString("end_time"));
arr.put(o);
}
} catch (Exception e) { e.printStackTrace(); }
JsonUtil.sendJson(resp, arr.toString());
}
}