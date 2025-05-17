package com.movie.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class MovieServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    @Override
    public void init() throws ServletException {
        System.out.println("MovieServlet initialized");
    }

    @SuppressWarnings("unchecked")
    private List<String> getMovies(HttpSession session) {
        try {
            List<String> movies = (List<String>) session.getAttribute("movies");
            System.out.println("Current movies in session: " + (movies != null ? movies.size() : 0));
            if (movies == null) {
                movies = new ArrayList<>();
                session.setAttribute("movies", movies);
                System.out.println("Created new movies list in session");
            }
            return movies;
        } catch (Exception e) {
            System.out.println("Error getting movies from session: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @SuppressWarnings("unchecked")
    private List<String> getAuthors(HttpSession session) {
        try {
            List<String> authors = (List<String>) session.getAttribute("authors");
            System.out.println("Current authors in session: " + (authors != null ? authors.size() : 0));
            if (authors == null) {
                authors = new ArrayList<>();
                session.setAttribute("authors", authors);
                System.out.println("Created new authors list in session");
            }
            return authors;
        } catch (Exception e) {
            System.out.println("Error getting authors from session: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            System.out.println("doGet called with path: " + request.getPathInfo());
            String pathInfo = request.getPathInfo();
            
            // Debug session information
            HttpSession session = request.getSession();
            System.out.println("Session ID: " + session.getId());
            System.out.println("Session is new: " + session.isNew());
            
            if (pathInfo == null || pathInfo.equals("/") || pathInfo.equals("/list")) {
                handleListMovies(request, response);
            } else {
                System.out.println("Invalid GET path: " + pathInfo);
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println("Error in doGet: " + e.getMessage());
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request");
        }
    }

    private void handleListMovies(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            System.out.println("Handling list movies request");
            HttpSession session = request.getSession();
            List<String> movies = getMovies(session);
            List<String> authors = getAuthors(session);
            
            System.out.println("Number of movies to display: " + movies.size());
            System.out.println("Number of authors to display: " + authors.size());
            
            // Debug session attributes
            System.out.println("Session attributes: " + Collections.list(session.getAttributeNames()));
            
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            
            out.println("<!DOCTYPE html>");
            out.println("<html><head>");
            out.println("<meta charset='UTF-8'>");
            out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
            out.println("<title>MovieFlix - Browse Movies</title>");
            out.println("<link href='https://fonts.googleapis.com/css2?family=Netflix+Sans:wght@300;400;500;700&display=swap' rel='stylesheet'>");
            out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css'>");
            out.println("<style>");
            out.println(":root {");
            out.println("    --netflix-red: #E50914;");
            out.println("    --netflix-black: #141414;");
            out.println("    --netflix-dark: #181818;");
            out.println("    --netflix-gray: #808080;");
            out.println("    --netflix-light: #FFFFFF;");
            out.println("}");
            out.println("* {");
            out.println("    margin: 0;");
            out.println("    padding: 0;");
            out.println("    box-sizing: border-box;");
            out.println("}");
            out.println("body {");
            out.println("    font-family: 'Netflix Sans', sans-serif;");
            out.println("    background-color: var(--netflix-black);");
            out.println("    color: var(--netflix-light);");
            out.println("    line-height: 1.6;");
            out.println("    -webkit-font-smoothing: antialiased;");
            out.println("    -moz-osx-font-smoothing: grayscale;");
            out.println("}");
            out.println(".navbar {");
            out.println("    background: linear-gradient(to bottom, rgba(0,0,0,0.7) 10%, transparent);");
            out.println("    padding: 1.5rem 4rem;");
            out.println("    position: fixed;");
            out.println("    top: 0;");
            out.println("    left: 0;");
            out.println("    right: 0;");
            out.println("    z-index: 1000;");
            out.println("    will-change: background-color;");
            out.println("    transition: background-color 0.3s ease;");
            out.println("}");
            out.println(".navbar.scrolled {");
            out.println("    background-color: var(--netflix-black);");
            out.println("}");
            out.println(".navbar-content {");
            out.println("    max-width: 1400px;");
            out.println("    margin: 0 auto;");
            out.println("    display: flex;");
            out.println("    justify-content: space-between;");
            out.println("    align-items: center;");
            out.println("}");
            out.println(".logo {");
            out.println("    color: var(--netflix-red);");
            out.println("    font-size: 2rem;");
            out.println("    font-weight: 700;");
            out.println("    text-decoration: none;");
            out.println("    letter-spacing: -1px;");
            out.println("}");
            out.println(".nav-links {");
            out.println("    display: flex;");
            out.println("    gap: 2rem;");
            out.println("    align-items: center;");
            out.println("}");
            out.println(".nav-link {");
            out.println("    color: var(--netflix-light);");
            out.println("    text-decoration: none;");
            out.println("    font-size: 0.9rem;");
            out.println("    transition: color 0.3s ease;");
            out.println("}");
            out.println(".nav-link:hover {");
            out.println("    color: var(--netflix-gray);");
            out.println("}");
            out.println(".add-movie {");
            out.println("    background-color: var(--netflix-red);");
            out.println("    color: var(--netflix-light);");
            out.println("    padding: 0.5rem 1.5rem;");
            out.println("    border-radius: 4px;");
            out.println("    text-decoration: none;");
            out.println("    font-weight: 500;");
            out.println("    will-change: transform;");
            out.println("    transition: transform 0.3s ease;");
            out.println("}");
            out.println(".add-movie:hover {");
            out.println("    background-color: #f40612;");
            out.println("    transform: translateY(-2px);");
            out.println("}");
            out.println(".featured {");
            out.println("    height: 80vh;");
            out.println("    position: relative;");
            out.println("    background: linear-gradient(to right, rgba(0,0,0,0.8) 0%, rgba(0,0,0,0.4) 50%, rgba(0,0,0,0.8) 100%),");
            out.println("                url('https://assets.nflxext.com/ffe/siteui/vlv3/9c5457b8-9ab0-4a04-9fc1-e608d5670f1a/710d74e0-7158-408e-8d9b-23c219dee5df/US-en-20210719-popsignuptwoweeks-perspective_alpha_website_small.jpg');");
            out.println("    background-size: cover;");
            out.println("    background-position: center;");
            out.println("    transform: translateZ(0);");
            out.println("}");
            out.println(".featured-content {");
            out.println("    position: absolute;");
            out.println("    bottom: 35%;");
            out.println("    left: 4rem;");
            out.println("    max-width: 600px;");
            out.println("}");
            out.println(".featured-title {");
            out.println("    font-size: 4rem;");
            out.println("    font-weight: 700;");
            out.println("    margin-bottom: 1rem;");
            out.println("    text-shadow: 2px 2px 4px rgba(0,0,0,0.5);");
            out.println("}");
            out.println(".featured-description {");
            out.println("    font-size: 1.5rem;");
            out.println("    margin-bottom: 2rem;");
            out.println("    text-shadow: 1px 1px 2px rgba(0,0,0,0.5);");
            out.println("}");
            out.println(".featured-buttons {");
            out.println("    display: flex;");
            out.println("    gap: 1rem;");
            out.println("}");
            out.println(".featured-button {");
            out.println("    padding: 0.8rem 2rem;");
            out.println("    border: none;");
            out.println("    border-radius: 4px;");
            out.println("    font-size: 1.2rem;");
            out.println("    font-weight: 500;");
            out.println("    cursor: pointer;");
            out.println("    display: flex;");
            out.println("    align-items: center;");
            out.println("    gap: 0.5rem;");
            out.println("    will-change: transform;");
            out.println("    transition: transform 0.3s ease;");
            out.println("}");
            out.println(".featured-button.play {");
            out.println("    background-color: var(--netflix-light);");
            out.println("    color: var(--netflix-black);");
            out.println("}");
            out.println(".featured-button.more {");
            out.println("    background-color: rgba(255, 255, 255, 0.2);");
            out.println("    color: var(--netflix-light);");
            out.println("}");
            out.println(".featured-button:hover {");
            out.println("    transform: translateY(-2px);");
            out.println("}");
            out.println(".movie-section {");
            out.println("    padding: 2rem 4rem;");
            out.println("    max-width: 1400px;");
            out.println("    margin: 0 auto;");
            out.println("}");
            out.println(".section-header {");
            out.println("    display: flex;");
            out.println("    justify-content: space-between;");
            out.println("    align-items: center;");
            out.println("    margin-bottom: 1.5rem;");
            out.println("}");
            out.println(".section-title {");
            out.println("    font-size: 1.8rem;");
            out.println("    font-weight: 500;");
            out.println("    color: var(--netflix-light);");
            out.println("}");
            out.println(".movie-row {");
            out.println("    display: grid;");
            out.println("    grid-template-columns: repeat(auto-fill, minmax(200px, 1fr));");
            out.println("    gap: 1rem;");
            out.println("    margin-bottom: 3rem;");
            out.println("}");
            out.println(".movie-card {");
            out.println("    position: relative;");
            out.println("    background: var(--netflix-dark);");
            out.println("    border-radius: 4px;");
            out.println("    overflow: hidden;");
            out.println("    will-change: transform;");
            out.println("    transition: transform 0.3s ease;");
            out.println("    cursor: pointer;");
            out.println("}");
            out.println(".movie-card:hover {");
            out.println("    transform: scale(1.05);");
            out.println("    z-index: 2;");
            out.println("}");
            out.println(".movie-poster {");
            out.println("    width: 100%;");
            out.println("    height: 300px;");
            out.println("    background: linear-gradient(45deg, #1a1a1a, #333);");
            out.println("    display: flex;");
            out.println("    align-items: center;");
            out.println("    justify-content: center;");
            out.println("    color: var(--netflix-gray);");
            out.println("    font-size: 3rem;");
            out.println("    position: relative;");
            out.println("}");
            out.println(".movie-poster::after {");
            out.println("    content: '';");
            out.println("    position: absolute;");
            out.println("    top: 0;");
            out.println("    left: 0;");
            out.println("    right: 0;");
            out.println("    bottom: 0;");
            out.println("    background: linear-gradient(to top, rgba(0,0,0,0.8) 0%, transparent 100%);");
            out.println("    opacity: 0;");
            out.println("    transition: opacity 0.3s ease;");
            out.println("}");
            out.println(".movie-card:hover .movie-poster::after {");
            out.println("    opacity: 1;");
            out.println("}");
            out.println(".movie-info {");
            out.println("    padding: 1rem;");
            out.println("    background: linear-gradient(to top, rgba(0,0,0,0.9) 0%, rgba(0,0,0,0.7) 50%, transparent 100%);");
            out.println("    position: absolute;");
            out.println("    bottom: 0;");
            out.println("    left: 0;");
            out.println("    right: 0;");
            out.println("    opacity: 0;");
            out.println("    transition: opacity 0.3s ease;");
            out.println("}");
            out.println(".movie-card:hover .movie-info {");
            out.println("    opacity: 1;");
            out.println("}");
            out.println(".movie-title {");
            out.println("    font-size: 1rem;");
            out.println("    font-weight: 500;");
            out.println("    margin-bottom: 0.5rem;");
            out.println("    color: var(--netflix-light);");
            out.println("}");
            out.println(".movie-director {");
            out.println("    font-size: 0.9rem;");
            out.println("    color: var(--netflix-gray);");
            out.println("}");
            out.println(".movie-actions {");
            out.println("    display: flex;");
            out.println("    gap: 0.5rem;");
            out.println("    margin-top: 1rem;");
            out.println("    opacity: 0;");
            out.println("    transform: translateY(10px);");
            out.println("    transition: all 0.3s ease;");
            out.println("}");
            out.println(".movie-card:hover .movie-actions {");
            out.println("    opacity: 1;");
            out.println("    transform: translateY(0);");
            out.println("}");
            out.println(".movie-action {");
            out.println("    width: 32px;");
            out.println("    height: 32px;");
            out.println("    border-radius: 50%;");
            out.println("    border: 2px solid var(--netflix-light);");
            out.println("    display: flex;");
            out.println("    align-items: center;");
            out.println("    justify-content: center;");
            out.println("    color: var(--netflix-light);");
            out.println("    background: transparent;");
            out.println("    cursor: pointer;");
            out.println("    will-change: background-color, color;");
            out.println("    transition: background-color 0.3s ease, color 0.3s ease;");
            out.println("}");
            out.println(".movie-action:hover {");
            out.println("    background: var(--netflix-light);");
            out.println("    color: var(--netflix-black);");
            out.println("}");
            out.println("@media (max-width: 768px) {");
            out.println("    .navbar {");
            out.println("        padding: 1rem;");
            out.println("    }");
            out.println("    .featured {");
            out.println("        height: 60vh;");
            out.println("    }");
            out.println("    .featured-content {");
            out.println("        left: 1rem;");
            out.println("        right: 1rem;");
            out.println("        bottom: 20%;");
            out.println("    }");
            out.println("    .featured-title {");
            out.println("        font-size: 2.5rem;");
            out.println("    }");
            out.println("    .featured-description {");
            out.println("        font-size: 1.2rem;");
            out.println("    }");
            out.println("    .featured-buttons {");
            out.println("        flex-direction: column;");
            out.println("    }");
            out.println("    .movie-section {");
            out.println("        padding: 1rem;");
            out.println("    }");
            out.println("    .movie-row {");
            out.println("        grid-template-columns: repeat(auto-fill, minmax(150px, 1fr));");
            out.println("    }");
            out.println("}");
            out.println("</style>");
            out.println("<script>");
            out.println("document.addEventListener('DOMContentLoaded', function() {");
            out.println("    const navbar = document.querySelector('.navbar');");
            out.println("    let ticking = false;");
            out.println("    window.addEventListener('scroll', function() {");
            out.println("        if (!ticking) {");
            out.println("            window.requestAnimationFrame(function() {");
            out.println("                if (window.scrollY > 50) {");
            out.println("                    navbar.classList.add('scrolled');");
            out.println("                } else {");
            out.println("                    navbar.classList.remove('scrolled');");
            out.println("                }");
            out.println("                ticking = false;");
            out.println("            });");
            out.println("            ticking = true;");
            out.println("        }");
            out.println("    });");
            out.println("});");
            out.println("</script>");
            out.println("</head><body>");
            out.println("<nav class='navbar'>");
            out.println("    <div class='navbar-content'>");
            out.println("        <a href='" + request.getContextPath() + "/' class='logo'>MovieFlix</a>");
            out.println("        <div class='nav-links'>");
            out.println("            <a href='" + request.getContextPath() + "/movies/list' class='nav-link'>Home</a>");
            out.println("            <a href='" + request.getContextPath() + "/index.jsp' class='add-movie'>Add Movie</a>");
            out.println("        </div>");
            out.println("    </div>");
            out.println("</nav>");
            out.println("<div class='featured'>");
            out.println("    <div class='featured-content'>");
            out.println("        <h1 class='featured-title'>Movie Collection</h1>");
            out.println("        <p class='featured-description'>Discover and share your favorite movies with the world</p>");
            out.println("        <div class='featured-buttons'>");
            out.println("            <button class='featured-button play'><i class='fas fa-play'></i> Browse Movies</button>");
            out.println("            <button class='featured-button more'><i class='fas fa-info-circle'></i> More Info</button>");
            out.println("        </div>");
            out.println("    </div>");
            out.println("</div>");
            out.println("<div class='movie-section'>");
            out.println("    <div class='section-header'>");
            out.println("        <h2 class='section-title'>All Movies</h2>");
            out.println("    </div>");
            out.println("    <div class='movie-row'>");
            
            for (int i = 0; i < movies.size(); i++) {
                out.println("<div class='movie-card'>");
                out.println("    <div class='movie-poster'>");
                out.println("        <i class='fas fa-film'></i>");
                out.println("    </div>");
                out.println("    <div class='movie-info'>");
                out.println("        <div class='movie-title'>" + escapeHtml(movies.get(i)) + "</div>");
                out.println("        <div class='movie-director'>Directed by " + escapeHtml(authors.get(i)) + "</div>");
                out.println("        <div class='movie-actions'>");
                out.println("            <button class='movie-action'><i class='fas fa-play'></i></button>");
                out.println("            <button class='movie-action'><i class='fas fa-plus'></i></button>");
                out.println("            <button class='movie-action'><i class='fas fa-thumbs-up'></i></button>");
                out.println("        </div>");
                out.println("    </div>");
                out.println("</div>");
            }
            
            out.println("    </div>");
            out.println("</div>");
            out.println("</body></html>");
        } catch (Exception e) {
            System.out.println("Error in handleListMovies: " + e.getMessage());
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            System.out.println("doPost called with path: " + request.getPathInfo());
            String pathInfo = request.getPathInfo();
            if (pathInfo == null || pathInfo.equals("/") || pathInfo.equals("/submit")) {
                handleSubmitMovie(request, response);
            } else {
                System.out.println("Invalid POST path: " + pathInfo);
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        } catch (Exception e) {
            System.out.println("Error in doPost: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/index.jsp?error=An error occurred while processing your request");
        }
    }

    private void handleSubmitMovie(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            request.setCharacterEncoding("UTF-8");
            String movie = request.getParameter("movie");
            String author = request.getParameter("author");
            
            System.out.println("Submitting movie: " + movie);
            System.out.println("Submitting author: " + author);
            
            HttpSession session = request.getSession();
            List<String> movies = getMovies(session);
            List<String> authors = getAuthors(session);
            
            if (movie != null && !movie.trim().isEmpty() && 
                author != null && !author.trim().isEmpty()) {
                movies.add(movie.trim());
                authors.add(author.trim());
                session.setAttribute("movies", movies);
                session.setAttribute("authors", authors);
                System.out.println("Successfully added movie. Total movies now: " + movies.size());
                
                // Show success message and redirect to list
                response.setContentType("text/html;charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<!DOCTYPE html>");
                out.println("<html><head>");
                out.println("<meta charset='UTF-8'>");
                out.println("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
                out.println("<title>MovieFlix - Movie Added</title>");
                out.println("<link href='https://fonts.googleapis.com/css2?family=Netflix+Sans:wght@300;400;500;700&display=swap' rel='stylesheet'>");
                out.println("<link rel='stylesheet' href='https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css'>");
                out.println("<style>");
                out.println(":root {");
                out.println("    --netflix-red: #E50914;");
                out.println("    --netflix-black: #141414;");
                out.println("    --netflix-dark: #181818;");
                out.println("    --netflix-gray: #808080;");
                out.println("    --netflix-light: #FFFFFF;");
                out.println("}");
                out.println("* {");
                out.println("    margin: 0;");
                out.println("    padding: 0;");
                out.println("    box-sizing: border-box;");
                out.println("}");
                out.println("body {");
                out.println("    font-family: 'Netflix Sans', sans-serif;");
                out.println("    background-color: var(--netflix-black);");
                out.println("    color: var(--netflix-light);");
                out.println("    line-height: 1.6;");
                out.println("    min-height: 100vh;");
                out.println("    display: flex;");
                out.println("    align-items: center;");
                out.println("    justify-content: center;");
                out.println("    background: linear-gradient(rgba(0,0,0,0.7), rgba(0,0,0,0.7)),");
                out.println("                url('https://assets.nflxext.com/ffe/siteui/vlv3/9c5457b8-9ab0-4a04-9fc1-e608d5670f1a/710d74e0-7158-408e-8d9b-23c219dee5df/US-en-20210719-popsignuptwoweeks-perspective_alpha_website_small.jpg');");
                out.println("    background-size: cover;");
                out.println("    background-position: center;");
                out.println("    background-attachment: fixed;");
                out.println("}");
                out.println(".success-container {");
                out.println("    max-width: 600px;");
                out.println("    width: 100%;");
                out.println("    background: rgba(0, 0, 0, 0.75);");
                out.println("    padding: 3rem;");
                out.println("    border-radius: 4px;");
                out.println("    text-align: center;");
                out.println("    box-shadow: 0 0 30px rgba(0, 0, 0, 0.5);");
                out.println("    animation: fadeInUp 0.6s ease;");
                out.println("    backdrop-filter: blur(10px);");
                out.println("    border: 1px solid rgba(255, 255, 255, 0.1);");
                out.println("}");
                out.println("@keyframes fadeInUp {");
                out.println("    from {");
                out.println("        opacity: 0;");
                out.println("        transform: translateY(20px);");
                out.println("    }");
                out.println("    to {");
                out.println("        opacity: 1;");
                out.println("        transform: translateY(0);");
                out.println("    }");
                out.println("}");
                out.println(".success-icon {");
                out.println("    width: 80px;");
                out.println("    height: 80px;");
                out.println("    background: var(--netflix-red);");
                out.println("    border-radius: 50%;");
                out.println("    display: flex;");
                out.println("    align-items: center;");
                out.println("    justify-content: center;");
                out.println("    margin: 0 auto 2rem;");
                out.println("    animation: scaleIn 0.5s ease 0.3s both;");
                out.println("    box-shadow: 0 0 20px rgba(229, 9, 20, 0.3);");
                out.println("    position: relative;");
                out.println("}");
                out.println(".success-icon::after {");
                out.println("    content: '';");
                out.println("    position: absolute;");
                out.println("    top: -5px;");
                out.println("    left: -5px;");
                out.println("    right: -5px;");
                out.println("    bottom: -5px;");
                out.println("    border-radius: 50%;");
                out.println("    border: 2px solid var(--netflix-red);");
                out.println("    animation: pulse 2s infinite;");
                out.println("}");
                out.println("@keyframes pulse {");
                out.println("    0% {");
                out.println("        transform: scale(1);");
                out.println("        opacity: 1;");
                out.println("    }");
                out.println("    100% {");
                out.println("        transform: scale(1.5);");
                out.println("        opacity: 0;");
                out.println("    }");
                out.println("}");
                out.println("@keyframes scaleIn {");
                out.println("    from {");
                out.println("        transform: scale(0);");
                out.println("    }");
                out.println("    to {");
                out.println("        transform: scale(1);");
                out.println("    }");
                out.println("}");
                out.println(".success-icon i {");
                out.println("    font-size: 2.5rem;");
                out.println("    color: var(--netflix-light);");
                out.println("}");
                out.println("h1 {");
                out.println("    font-size: 2.5rem;");
                out.println("    font-weight: 700;");
                out.println("    margin-bottom: 1.5rem;");
                out.println("    color: var(--netflix-light);");
                out.println("    text-shadow: 2px 2px 4px rgba(0,0,0,0.5);");
                out.println("}");
                out.println(".movie-details {");
                out.println("    background: rgba(255, 255, 255, 0.05);");
                out.println("    padding: 2rem;");
                out.println("    border-radius: 4px;");
                out.println("    margin: 2rem 0;");
                out.println("    animation: fadeIn 0.5s ease 0.6s both;");
                out.println("    border: 1px solid rgba(255, 255, 255, 0.1);");
                out.println("    position: relative;");
                out.println("    overflow: hidden;");
                out.println("}");
                out.println(".movie-details::before {");
                out.println("    content: '';");
                out.println("    position: absolute;");
                out.println("    top: 0;");
                out.println("    left: -100%;");
                out.println("    width: 100%;");
                out.println("    height: 100%;");
                out.println("    background: linear-gradient(90deg, transparent, rgba(255,255,255,0.1), transparent);");
                out.println("    animation: shine 3s infinite;");
                out.println("}");
                out.println("@keyframes shine {");
                out.println("    to {");
                out.println("        left: 100%;");
                out.println("    }");
                out.println("}");
                out.println("@keyframes fadeIn {");
                out.println("    from { opacity: 0; }");
                out.println("    to { opacity: 1; }");
                out.println("}");
                out.println(".movie-title {");
                out.println("    font-size: 1.5rem;");
                out.println("    font-weight: 500;");
                out.println("    margin-bottom: 0.5rem;");
                out.println("    color: var(--netflix-light);");
                out.println("}");
                out.println(".movie-director {");
                out.println("    font-size: 1.1rem;");
                out.println("    color: var(--netflix-gray);");
                out.println("}");
                out.println(".button-group {");
                out.println("    display: flex;");
                out.println("    gap: 1rem;");
                out.println("    justify-content: center;");
                out.println("    margin-top: 2rem;");
                out.println("    animation: fadeIn 0.5s ease 0.9s both;");
                out.println("}");
                out.println(".button {");
                out.println("    padding: 1rem 2rem;");
                out.println("    border: none;");
                out.println("    border-radius: 4px;");
                out.println("    font-size: 1rem;");
                out.println("    font-weight: 500;");
                out.println("    cursor: pointer;");
                out.println("    text-decoration: none;");
                out.println("    transition: all 0.3s ease;");
                out.println("    position: relative;");
                out.println("    overflow: hidden;");
                out.println("    display: flex;");
                out.println("    align-items: center;");
                out.println("    gap: 0.5rem;");
                out.println("}");
                out.println(".button::after {");
                out.println("    content: '';");
                out.println("    position: absolute;");
                out.println("    top: 0;");
                out.println("    left: 0;");
                out.println("    width: 100%;");
                out.println("    height: 100%;");
                out.println("    background: linear-gradient(45deg, transparent, rgba(255,255,255,0.1), transparent);");
                out.println("    transform: translateX(-100%);");
                out.println("    transition: transform 0.6s ease;");
                out.println("}");
                out.println(".button:hover::after {");
                out.println("    transform: translateX(100%);");
                out.println("}");
                out.println(".button.primary {");
                out.println("    background-color: var(--netflix-red);");
                out.println("    color: var(--netflix-light);");
                out.println("}");
                out.println(".button.primary:hover {");
                out.println("    background-color: #f40612;");
                out.println("    transform: translateY(-2px);");
                out.println("    box-shadow: 0 5px 15px rgba(229, 9, 20, 0.3);");
                out.println("}");
                out.println(".button.secondary {");
                out.println("    background-color: transparent;");
                out.println("    color: var(--netflix-light);");
                out.println("    border: 1px solid var(--netflix-gray);");
                out.println("}");
                out.println(".button.secondary:hover {");
                out.println("    background-color: rgba(255, 255, 255, 0.1);");
                out.println("    transform: translateY(-2px);");
                out.println("    border-color: var(--netflix-light);");
                out.println("}");
                out.println("@media (max-width: 768px) {");
                out.println("    .success-container {");
                out.println("        padding: 2rem;");
                out.println("        margin: 1rem;");
                out.println("    }");
                out.println("    .button-group {");
                out.println("        flex-direction: column;");
                out.println("    }");
                out.println("    h1 {");
                out.println("        font-size: 2rem;");
                out.println("    }");
                out.println("}");
                out.println("</style>");
                out.println("</head><body>");
                out.println("<div class='success-container'>");
                out.println("    <div class='success-icon'>");
                out.println("        <i class='fas fa-check'></i>");
                out.println("    </div>");
                out.println("    <h1>Movie Added Successfully!</h1>");
                out.println("    <div class='movie-details'>");
                out.println("        <div class='movie-title'>" + escapeHtml(movie) + "</div>");
                out.println("        <div class='movie-director'>Directed by " + escapeHtml(author) + "</div>");
                out.println("    </div>");
                out.println("    <div class='button-group'>");
                out.println("        <a href='" + request.getContextPath() + "/movies/list' class='button primary'><i class='fas fa-film'></i> View All Movies</a>");
                out.println("        <a href='" + request.getContextPath() + "/index.jsp' class='button secondary'><i class='fas fa-plus'></i> Add Another Movie</a>");
                out.println("    </div>");
                out.println("</div>");
                out.println("</body></html>");
            } else {
                System.out.println("Failed to add movie - invalid input");
                response.sendRedirect(request.getContextPath() + "/index.jsp?error=Please fill in all fields");
            }
        } catch (Exception e) {
            System.out.println("Error in handleSubmitMovie: " + e.getMessage());
            e.printStackTrace();
            response.sendRedirect(request.getContextPath() + "/index.jsp?error=An error occurred while saving your movie");
        }
    }
    
    private String escapeHtml(String input) {
        if (input == null) {
            return "";
        }
        return input.replace("&", "&amp;")
                   .replace("<", "&lt;")
                   .replace(">", "&gt;")
                   .replace("\"", "&quot;")
                   .replace("'", "&#39;");
    }
} 