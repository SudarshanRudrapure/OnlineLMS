
package com.lms;


import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class JsonUtil {
public static void sendJson(HttpServletResponse resp, String json) {
try {
resp.setContentType("application/json;charset=UTF-8");
PrintWriter out = resp.getWriter();
out.print(json);
out.flush();
} catch (Exception e) { e.printStackTrace(); }
}
}