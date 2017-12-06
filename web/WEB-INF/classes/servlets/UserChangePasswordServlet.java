package servlets;

import DTO.UserUpdateAnswerDto;
import DTO.UserUpdateDto;
import com.google.gson.Gson;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;


@WebServlet(urlPatterns = "/password", name = "ChangePassword")
public class UserChangePasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        String jsonString = req.getReader().lines().collect(Collectors.joining("\n"));
        UserUpdateDto userUpdateDto = gson.fromJson(jsonString, UserUpdateDto.class);
        Long userId = userUpdateDto.getUserId();
        String newPassword = userUpdateDto.getNewPassword();

        UserUpdateAnswerDto dto = UserService.getInstance().changeUserPassword(userId, newPassword);
        String outputJsonString = gson.toJson(dto);
        req.getSession().removeAttribute("loggedUser");
        resp.getWriter().write(outputJsonString);
    }
}
