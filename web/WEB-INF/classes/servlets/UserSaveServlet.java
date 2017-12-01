package servlets;

import DTO.UserLoggedDto;
import DTO.UserCreateDto;
import DTO.UserViewDto;
import com.google.gson.Gson;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.stream.Collectors;

import static utils.StaticContent.*;

@WebServlet(urlPatterns = "/saveUser", name = "SaveUser")
public class UserSaveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(createViewPath( "save-user"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        String jsonString = req.getReader().lines().collect(Collectors.joining("\n"));
        UserCreateDto userCreateDto = gson.fromJson(jsonString, UserCreateDto.class);
        String answerRegistration = UserService.getInstance().checkRegistration(userCreateDto);
        String outputJsonString;

        if (answerRegistration == null) {
            try {
                UserService.getInstance().addUser(userCreateDto);
                outputJsonString = gson.toJson(new UserLoggedDto("Успешно", "/login"));
            } catch (SQLException e) {
                outputJsonString = gson.toJson(new UserLoggedDto("Ошибка при создании пользователя: " + e.toString()));
            }
        } else {
            outputJsonString = gson.toJson(new UserLoggedDto(answerRegistration));
        }
        resp.getWriter().write(outputJsonString);
    }
}
