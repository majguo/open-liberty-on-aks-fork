package cafe.web.view;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "get-name-servlet", urlPatterns = {"/get-name"})
public class GetNameServlet extends HttpServlet {

    @Inject
    private Cafe cafe;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("Coffe name: " + cafe.getName());
    }
}