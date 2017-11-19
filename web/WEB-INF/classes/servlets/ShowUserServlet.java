package servlets;

import DTO.UserDto;
import services.UserService;
import utils.StaticContent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/user")
public class ShowUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        UserDto founedUser = UserService.getInstance().getUserById(id);
        req.setAttribute("user", founedUser);
        getServletContext()
                .getRequestDispatcher(StaticContent.jspPath + "/show-user.jsp")
                .forward(req, resp);
    }
}
