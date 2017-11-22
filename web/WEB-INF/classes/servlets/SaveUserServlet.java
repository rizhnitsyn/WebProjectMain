package servlets;

import DTO.UserDto;
import entities.User;
import services.UserService;
import utils.StaticContent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.StaticContent.*;

@WebServlet("/saveUser")
public class SaveUserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(createViewPath( "save-user"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String firstName = req.getParameter("firstName");
        String secondName = req.getParameter("secondName");
        String email = req.getParameter("email");

        if (firstName.isEmpty() || secondName.isEmpty() || email.isEmpty()) {
            resp.sendRedirect("/saveUser");
        } else {
            UserDto savedUser = UserService.getInstance().addUser(new User(firstName, secondName, email));
            resp.sendRedirect("/user?id=" + savedUser.getId());
        }
    }
}
