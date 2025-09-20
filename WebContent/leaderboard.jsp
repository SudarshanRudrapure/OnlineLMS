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
    <title>Leaderboard</title>
    <link rel="stylesheet" href="assets/styles.css"/>
</head>
<body>
    <div class="nav">
        <a href="index.html">Home</a>
        <a href="dashboard.jsp">Dashboard</a>
        <a href="courses.jsp">Courses</a>
        <a href="contests.jsp">Contests</a>
        <a href="ide.html">Open IDE</a>
        <a class="active" href="leaderboard.jsp">Leaderboard</a>
    </div>
    <div class="container">
        <h1>Leaderboard</h1>
        <p>See who’s on top of the leaderboard!</p>
    </div>
</body>
</html>
