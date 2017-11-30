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
//        UserViewDto foundUser = ;
        req.setAttribute("user", UserService.getInstance().getUserById(id));
        req.setAttribute("userStates", UserService.getInstance().getUserStates());
        getServletContext()
                .getRequestDispatcher(createViewPath("show-user-adminView"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");

//        if (req.getParameter("idReg") != null) {
            Long id = Long.valueOf(req.getParameter("id"));
            UserViewDto foundUser = UserService.getInstance().getUserById(id);
            Integer userStateId = Integer.valueOf(req.getParameter("userState"));
            UserViewDto updatedUser = UserService.getInstance().changeUserState(foundUser, userStateId);
            resp.sendRedirect("/userList");
//        }
        //для пользователя
//        if (req.getParameter("idBlock") != null) {
//            Long id = Long.valueOf(req.getParameter("idBlock"));
//            UserViewDto foundUser = UserService.getInstance().getUserById(id);
//            UserViewDto updatedUser = UserService.getInstance().blockUser(foundUser);
//            resp.sendRedirect("/user?id=" + updatedUser.getId());
//        }
    }
}
