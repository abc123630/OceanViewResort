<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
    <title>Login - Ocean View Resort</title>
    <link rel="stylesheet" href="css/login.css">
    <script src="js/validation.js"></script>
</head>
<body>

<!-- HEADER -->
<header class="header">
    <div class="logo">
        <img src="images/logo.jpg" alt="Logo">
        <h1>Ocean View Resort</h1>
    </div>
</header>

<!-- BACKGROUND -->
<div class="background"></div>

<div class="login-box">
    <h2>Ocean View Resort Login</h2>

   
   <% if(request.getParameter("error") != null){ %>
        <p style="color:red;">Invalid Username or Password!</p>
    <% } %>
    

    <form action="LoginServlet" method="post" onsubmit="return validateForm()">
        <input type="text" name="username" placeholder="Username"><br>
        <input type="password" name="password" placeholder="Password"><br>
        <button type="submit">Login</button>
    </form>
</div>

<!-- FOOTER -->
<footer class="footer">
    <p>© 2026 Ocean View Resort. All Rights Reserved.</p>
    <p>Email: <a href="mailto:support@oceanviewresort.com">support@oceanviewresort.com</a></p>
</footer>

</body>
</html>
