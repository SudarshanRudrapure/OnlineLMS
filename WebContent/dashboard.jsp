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
    <title>Dashboard</title>
    <link rel="stylesheet" href="assets/styles.css"/>
</head>
<body>
    <div class="nav">
        <a href="index.html">Home</a>
        <a class="active" href="dashboard.jsp">Dashboard</a>
        <a href="courses.jsp">Courses</a>
        <a href="contests.jsp">Contests</a>
        <a href="ide.html">Open IDE</a>
        <a href="leaderboard.jsp">Leaderboard</a>
    </div>
    <div class="container">
        <h1>Welcome, <%= session.getAttribute("user") %>!</h1>
        <p>This is your dashboard.</p>
    </div>
</body>
</html>
