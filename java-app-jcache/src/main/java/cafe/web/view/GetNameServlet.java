package cafe.web.view;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "get-name-servlet", urlPatterns = {"/name"})
public class GetNameServlet extends HttpServlet {

    @Inject
    private Cafe bean;

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write("name from session bean = " + bean.getName());
        resp.getWriter().write("\n");
        resp.getWriter().write("name from session = " + req.getSession().getAttribute("name"));
    }
}
