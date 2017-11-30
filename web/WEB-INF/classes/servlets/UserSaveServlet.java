package servlets;

import DTO.AnswerJsDto;
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

@WebServlet("/saveUser")
public class UserSaveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(createViewPath( "save-user"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");

        Gson gson = new Gson();
        String jsonString = req.getReader().lines()
                .collect(Collectors.joining("\n"));
        UserCreateDto userCreateDto = gson.fromJson(jsonString, UserCreateDto.class);
        String answerRegistration = UserService.getInstance().checkRegistration(userCreateDto);
        String outputJsonString;

        if (answerRegistration == null) {
            UserViewDto savedUser;
            try {
                savedUser = UserService.getInstance().addUser(userCreateDto);
                outputJsonString = gson.toJson(new AnswerJsDto("Успешно", "/user?id=" + savedUser.getId()));
            } catch (SQLException e) {
                outputJsonString = gson.toJson(new AnswerJsDto("Ошибка при создании пользователя: " + e.toString()));
            }
        } else {
            outputJsonString = gson.toJson(new AnswerJsDto(answerRegistration));
        }
        resp.getWriter().write(outputJsonString);
    }
}
