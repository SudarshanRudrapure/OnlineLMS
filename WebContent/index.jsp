<%@ page session="true" %>
<%
    String name = (String) session.getAttribute("name");
    if (name == null) {
        response.sendRedirect("login.html");
        return;
    }
%>
<!doctype html>
<html>
<head>
  <meta charset="utf-8"/>
  <meta name="viewport" content="width=device-width,initial-scale=1"/>
  <title>OnlineLMS</title>
  <link rel="stylesheet" href="assets/styles.css"/>
</head>
<body>
  <div class="nav">
    <a class="active" href="index.jsp">Home</a>
    <a href="dashboard.jsp">Dashboard</a>
    <a href="courses.jsp">Courses</a>
    <a href="contests.jsp">Contests</a>
    <a href="ide.jsp">Open IDE</a>
    <a href="leaderboard.jsp">Leaderboard</a>
    <a href="logout.jsp">Logout</a>
  </div>

  <div class="container">
    <div class="card">
      <h1>Welcome to OnlineLMS</h1>
      <p>Hello <b><%= name %></b> 👋, you are logged in.</p>
      <p>Use the navigation bar to explore.</p>
    </div>
  </div>
</body>
</html>
