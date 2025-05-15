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
            out.println("<title>Movie List</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 40px; }");
            out.println("table { border-collapse: collapse; width: 100%; margin-top: 20px; }");
            out.println("th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println("tr:nth-child(even) { background-color: #f9f9f9; }");
            out.println("</style>");
            out.println("</head><body>");
            
            out.println("<h1>Movie List</h1>");
            
            if (movies.isEmpty()) {
                out.println("<p>No movies submitted yet.</p>");
            } else {
                out.println("<table>");
                out.println("<tr><th>Movie Title</th><th>Author/Director</th></tr>");
                for (int i = 0; i < movies.size(); i++) {
                    out.println("<tr>");
                    out.println("<td>" + escapeHtml(movies.get(i)) + "</td>");
                    out.println("<td>" + escapeHtml(authors.get(i)) + "</td>");
                    out.println("</tr>");
                }
                out.println("</table>");
            }
            
            out.println("<br><a href='../index.jsp'>Back to form</a>");
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
                out.println("<title>Movie Added Successfully</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; margin: 40px; text-align: center; }");
                out.println(".success { color: green; font-size: 1.2em; margin: 20px 0; }");
                out.println(".button { display: inline-block; padding: 10px 20px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 5px; margin: 10px; }");
                out.println(".button:hover { background-color: #45a049; }");
                out.println("</style>");
                out.println("</head><body>");
                out.println("<h1>Movie Added Successfully!</h1>");
                out.println("<p class='success'>Your movie has been added to the list.</p>");
                out.println("<p>Movie: " + escapeHtml(movie) + "</p>");
                out.println("<p>Author: " + escapeHtml(author) + "</p>");
                out.println("<a href='" + request.getContextPath() + "/movies/list' class='button'>View All Movies</a>");
                out.println("<a href='" + request.getContextPath() + "/index.jsp' class='button'>Add Another Movie</a>");
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