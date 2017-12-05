package servlets;

import DTO.UserViewDto;
import services.UserService;
import utils.StaticContent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/userList", name = "UsersForRegistration")
public class UserRegistrationListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("users", UserService.getInstance().getUsersForRegistration());
        getServletContext()
                .getRequestDispatcher(StaticContent.createViewPath("show-userList"))
                .forward(req, resp);
    }
}
