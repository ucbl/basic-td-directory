package fr.univlyon1.m2tiw.tiw8.servlet;

import fr.univlyon1.m2tiw.tiw8.model.Validator;
import org.everit.json.schema.ValidationException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/bind", loadOnStartup = 1)
public class Bind extends HttpServlet {
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        this.getServletContext().setAttribute("directory", new HashMap<String, String>());
    }

    @SuppressWarnings({"unchecked"})
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        if(request.getParameter("tdName") != null) {
            PrintWriter out = response.getWriter();
            response.setContentType("application/json");
            String tdContent = request.getParameter("tdContent");
            try {
                Validator.validate(tdContent);
                ((Map<String, String>) this.getServletContext().getAttribute("directory")).put(request.getParameter("tdName"), tdContent);
                out.println("{\"message\":\"TD " + request.getParameter("tdName") + " (re)bound.\"}");
            } catch(IOException e) {
                response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
                out.println("{\"message\":\"" + e.getMessage() + "\"");
            } catch(ValidationException e) {
                response.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);
                out.print("{\"message\":\"" + e.getMessage() + "\",\"cause\":\"");
                e.getCausingExceptions().stream()
                        .map(ValidationException::getMessage)
                        .forEach(out::print);
                out.println("\"}");
            }
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}