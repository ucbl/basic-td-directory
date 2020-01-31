package fr.univlyon1.m2tiw.tiw8.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/lookup")
public class Lookup extends HttpServlet {
    @SuppressWarnings({"unchecked"})
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(this.getServletContext().getAttribute("directory") == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        String res = ((Map<String, String>) this.getServletContext().getAttribute("directory")).get(request.getParameter("tdName"));
        if(res == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }
        response.getWriter().println(res);
        response.setContentType("application/json");
    }
}