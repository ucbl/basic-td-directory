package fr.univlyon1.m2tiw.tiw8.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/unbind")
public class Unbind extends HttpServlet {
    @SuppressWarnings({"unchecked"})
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getParameter("tdName") != null) {
            ((Map<String, String>) this.getServletContext().getAttribute("directory")).remove(request.getParameter("tdName"));
            response.getWriter().println("{\"message\":\"TD " + request.getParameter("tdName") + " unbound.\"}");

        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}