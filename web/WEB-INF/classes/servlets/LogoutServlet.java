package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.StaticContent.createViewPath;

@WebServlet(urlPatterns = "/logout", name = "logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            req.getSession().removeAttribute("loggedUser");
        }
        finally {
            getServletContext()
                    .getRequestDispatcher(createViewPath("home-page"))
                    .forward(req, resp);
        }
    }
}
