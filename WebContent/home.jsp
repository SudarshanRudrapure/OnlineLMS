<%@ page session="true" %>
<%
    String user = (String) session.getAttribute("user");
    if (user == null) {
        response.sendRedirect("login.html");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>OnlineLMS - Home</title>
    <link rel="stylesheet" href="assets/styles.css">
</head>
<body>
    <div class="navbar">
        <a href="home.jsp">Home</a>
        <a href="dashboard.jsp">Dashboard</a>
        <a href="courses.jsp">Courses</a>
        <a href="contests.jsp">Contests</a>
        <a href="leaderboard.jsp">Leaderboard</a>
        <a href="logout">Logout</a>
    </div>

    <h2>Welcome, <%= user %> 👋</h2>
    <p>This is your LMS portal.</p>
</body>
</html>
