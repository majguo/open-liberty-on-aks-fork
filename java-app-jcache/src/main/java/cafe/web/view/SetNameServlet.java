package cafe.web.view;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "set-name-servlet", urlPatterns = {"/set-name"})
public class SetNameServlet extends HttpServlet {

    @Inject
    private Cafe cafe;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String name = req.getParameter("name");
        if (name == null || name.isEmpty()) {
            resp.getWriter().write("Please add a parameter name with the coffee name, e.g., name=latte");
        } else {
            cafe.setName(name);
            resp.sendRedirect(req.getContextPath() + "/get-name");
        }
    }
}