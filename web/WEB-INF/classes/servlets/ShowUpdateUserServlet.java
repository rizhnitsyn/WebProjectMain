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

import static utils.StaticContent.*;

@WebServlet("/user")
public class ShowUpdateUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        UserDto foundUser = UserService.getInstance().getUserById(id);
        req.setAttribute("user", foundUser);
        getServletContext()
                .getRequestDispatcher(createViewPath("show-user"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

        if (req.getParameter("idReg") != null) {
            Long id = Long.valueOf(req.getParameter("idReg"));
            UserDto foundUser = UserService.getInstance().getUserById(id);
            UserDto updatedUser = UserService.getInstance().approveUserRegistration(foundUser);
            resp.sendRedirect("/user?id=" + updatedUser.getId());
        }
        //для пользователя
        if (req.getParameter("idBlock") != null) {
            Long id = Long.valueOf(req.getParameter("idBlock"));
            UserDto foundUser = UserService.getInstance().getUserById(id);
            UserDto updatedUser = UserService.getInstance().blockUser(foundUser);
            resp.sendRedirect("/user?id=" + updatedUser.getId());
        }
    }
}
