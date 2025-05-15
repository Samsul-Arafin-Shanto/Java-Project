<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Movie Submission Form</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        form { max-width: 500px; margin: 0 auto; }
        input, button { padding: 8px; margin: 5px 0; width: 100%; }
        .error { color: red; }
        .success { color: green; }
    </style>
</head>
<body>
    <h1>Submit Your Favorite Movie</h1>
    <%
    String error = request.getParameter("error");
    String success = request.getParameter("success");
    if (error != null) {
        out.println("<p class='error'>Error: " + error + "</p>");
    }
    if (success != null) {
        out.println("<p class='success'>Success: " + success + "</p>");
    }
    %>
    <form action="${pageContext.request.contextPath}/movies/submit" method="post" onsubmit="return validateForm()">
        <label for="movie">Movie Title:</label>
        <input type="text" id="movie" name="movie" placeholder="Enter movie title" required>
        
        <label for="author">Author:</label>
        <input type="text" id="author" name="author" placeholder="Enter author" required>
        
        <input type="submit" value="Submit Movie">
    </form>
    
    <script>
    function validateForm() {
        var movie = document.getElementById('movie').value.trim();
        var author = document.getElementById('author').value.trim();
        
        if (!movie || !author) {
            alert('Please fill in all fields');
            return false;
        }
        return true;
    }
    </script>
</body>
</html>