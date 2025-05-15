<html>
<head>
    <title>Movie Submission Form</title>
    <style>
        body { font-family: Arial, sans-serif; margin: 40px; }
        form { max-width: 500px; margin: 0 auto; }
        input, button { padding: 8px; margin: 5px 0; width: 100%; }
    </style>
</head>
<body>
    <h1>Submit Your Favorite Movie</h1>
    <form action="${pageContext.request.contextPath}/" method="post">
        <label for="movie">Movie Title:</label>
        <input type="text" id="movie" name="movie" placeholder="Enter movie title" required>
        
        <label for="author">Author/Director:</label>
        <input type="text" id="author" name="author" placeholder="Enter author/director" required>
        
        <input type="submit" value="Submit Movie">
    </form>
    
    <p><a href="${pageContext.request.contextPath}/">View all submitted movies</a></p>
</body>
</html>