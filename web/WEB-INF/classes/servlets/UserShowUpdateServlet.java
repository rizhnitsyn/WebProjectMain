package servlets;

import DTO.UserViewDto;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.StaticContent.*;

@WebServlet(urlPatterns = "/user", name = "ShowUser")
public class UserShowUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        req.setAttribute("user", UserService.getInstance().getUserById(id));
        req.setAttribute("userStates", UserService.getInstance().getUserStates());
        getServletContext()
                .getRequestDispatcher(createViewPath("show-user"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getParameter("idChangeState") != null) {
            Long id = Long.valueOf(req.getParameter("idChangeState"));
            UserViewDto foundUser = UserService.getInstance().getUserById(id);
            Integer userStateId = Integer.valueOf(req.getParameter("userState"));
            UserService.getInstance().changeUserState(foundUser, userStateId);
            resp.sendRedirect("/userList");
        }
        if (req.getParameter("idPassword") != null) {
            Long id = Long.valueOf(req.getParameter("idPassword"));
            UserViewDto foundUser = UserService.getInstance().getUserById(id);
            req.setAttribute("user", foundUser);
            getServletContext()
                    .getRequestDispatcher(createViewPath("update-user"))
                    .forward(req, resp);
        }
    }
}
