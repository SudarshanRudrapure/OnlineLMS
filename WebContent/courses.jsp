<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    if (session.getAttribute("user") == null) {
        response.sendRedirect("login.html");
        return;
    }
%>
<!doctype html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>Courses</title>
    <link rel="stylesheet" href="assets/styles.css"/>
</head>
<body>
    <div class="nav">
        <a href="index.html">Home</a>
        <a href="dashboard.jsp">Dashboard</a>
        <a class="active" href="courses.jsp">Courses</a>
        <a href="contests.jsp">Contests</a>
        <a href="ide.html">Open IDE</a>
        <a href="leaderboard.jsp">Leaderboard</a>
    </div>
    <div class="container">
        <h1>Courses</h1>
        <p>Here you can browse and enroll in courses.</p>
    </div>
</body>
</html>
