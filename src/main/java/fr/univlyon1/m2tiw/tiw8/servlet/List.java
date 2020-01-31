package fr.univlyon1.m2tiw.tiw8.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(urlPatterns = "/list")
public class List extends HttpServlet {
    @SuppressWarnings({"unchecked"})
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        boolean first = true;

        out.println("[");
        for(String entry: ((Map<String, String>) this.getServletContext().getAttribute("directory")).keySet()) {
            if(!first)
                out.println(',');
            else
                first = false;

            out.println("\"" + entry + "\"");
        }
        out.println("]");

        response.setContentType("application/json");
    }
}