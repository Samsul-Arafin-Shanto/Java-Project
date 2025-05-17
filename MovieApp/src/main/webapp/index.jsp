<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MovieFlix - Submit Your Movie</title>
    <link href="https://fonts.googleapis.com/css2?family=Netflix+Sans:wght@300;400;500;700&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        :root {
            --netflix-red: #E50914;
            --netflix-black: #141414;
            --netflix-dark: #181818;
            --netflix-gray: #808080;
            --netflix-light: #FFFFFF;
        }

        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Netflix Sans', sans-serif;
            background-color: var(--netflix-black);
            color: var(--netflix-light);
            line-height: 1.6;
            min-height: 100vh;
        }

        .hero {
            position: relative;
            height: 100vh;
            background: linear-gradient(rgba(0, 0, 0, 0.7), rgba(0, 0, 0, 0.7)),
                        url('https://assets.nflxext.com/ffe/siteui/vlv3/9c5457b8-9ab0-4a04-9fc1-e608d5670f1a/710d74e0-7158-408e-8d9b-23c219dee5df/US-en-20210719-popsignuptwoweeks-perspective_alpha_website_small.jpg');
            background-size: cover;
            background-position: center;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .container {
            max-width: 450px;
            width: 100%;
            background: rgba(0, 0, 0, 0.75);
            padding: 3rem;
            border-radius: 4px;
            box-shadow: 0 0 20px rgba(0, 0, 0, 0.5);
        }

        .logo {
            text-align: center;
            margin-bottom: 2rem;
        }

        .logo h1 {
            color: var(--netflix-red);
            font-size: 2.5rem;
            font-weight: 700;
            letter-spacing: -1px;
        }

        h2 {
            color: var(--netflix-light);
            text-align: center;
            margin-bottom: 2rem;
            font-size: 1.8rem;
            font-weight: 500;
        }

        .form-group {
            margin-bottom: 1.5rem;
            position: relative;
        }

        label {
            display: block;
            margin-bottom: 0.5rem;
            color: var(--netflix-gray);
            font-size: 0.9rem;
        }

        input {
            width: 100%;
            padding: 16px;
            background: #333;
            border: none;
            border-radius: 4px;
            color: var(--netflix-light);
            font-size: 1rem;
            transition: all 0.3s ease;
        }

        input:focus {
            outline: none;
            background: #454545;
        }

        input::placeholder {
            color: #8c8c8c;
        }

        button {
            width: 100%;
            padding: 16px;
            background-color: var(--netflix-red);
            color: var(--netflix-light);
            border: none;
            border-radius: 4px;
            font-size: 1rem;
            font-weight: 500;
            cursor: pointer;
            transition: background-color 0.3s ease;
            margin-top: 1rem;
        }

        button:hover {
            background-color: #f40612;
        }

        .error {
            color: #ffa00a;
            background-color: rgba(255, 160, 10, 0.1);
            padding: 1rem;
            border-radius: 4px;
            margin-bottom: 1rem;
            font-size: 0.9rem;
            animation: fadeIn 0.3s ease;
        }

        .success {
            color: #00b207;
            background-color: rgba(0, 178, 7, 0.1);
            padding: 1rem;
            border-radius: 4px;
            margin-bottom: 1rem;
            font-size: 0.9rem;
            animation: fadeIn 0.3s ease;
        }

        .view-movies {
            text-align: center;
            margin-top: 1.5rem;
            padding-top: 1.5rem;
            border-top: 1px solid #333;
        }

        .view-movies a {
            color: var(--netflix-light);
            text-decoration: none;
            font-size: 0.9rem;
            transition: color 0.3s ease;
        }

        .view-movies a:hover {
            color: var(--netflix-red);
        }

        .view-movies i {
            margin-right: 0.5rem;
        }

        @keyframes fadeIn {
            from { opacity: 0; transform: translateY(-10px); }
            to { opacity: 1; transform: translateY(0); }
        }

        @media (max-width: 768px) {
            .container {
                padding: 2rem;
                margin: 1rem;
            }

            .hero {
                height: auto;
                min-height: 100vh;
                padding: 2rem 0;
            }
        }
    </style>
</head>
<body>
    <div class="hero">
        <div class="container">
            <div class="logo">
                <h1>MovieFlix</h1>
            </div>
            <h2>Submit Your Movie</h2>
            <%
            String error = request.getParameter("error");
            String success = request.getParameter("success");
            if (error != null) {
                out.println("<div class='error'><i class='fas fa-exclamation-circle'></i> " + error + "</div>");
            }
            if (success != null) {
                out.println("<div class='success'><i class='fas fa-check-circle'></i> " + success + "</div>");
            }
            %>
            <form action="${pageContext.request.contextPath}/movies/submit" method="post" onsubmit="return validateForm()">
                <div class="form-group">
                    <label for="movie">Movie Title</label>
                    <input type="text" id="movie" name="movie" placeholder="Enter movie title" required>
                </div>
                
                <div class="form-group">
                    <label for="author">Director</label>
                    <input type="text" id="author" name="author" placeholder="Enter director's name" required>
                </div>
                
                <button type="submit">Submit Movie</button>
            </form>
            
            <div class="view-movies">
                <a href="${pageContext.request.contextPath}/movies/list">
                    <i class="fas fa-film"></i> Browse Movie Collection
                </a>
            </div>
        </div>
    </div>
    
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