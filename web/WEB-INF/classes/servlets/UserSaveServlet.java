package servlets;

import DTO.UserLoggedDto;
import DTO.UserCreateDto;
import com.google.gson.Gson;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
        UserLoggedDto checkedUser = UserService.getInstance().checkRegistration(userCreateDto);
        String outputJsonString;

        if (!checkedUser.isError()) {
            try {
                UserService.getInstance().addUser(userCreateDto);
                checkedUser.setRedirectPath("/login");
                outputJsonString = gson.toJson(checkedUser);
            } catch (Exception e) {
                checkedUser.setError(true);
                checkedUser.setMessage("Есть ошибки при регистрации: " + e.toString());
                outputJsonString = gson.toJson(checkedUser);
            }
        } else {
            outputJsonString = gson.toJson(checkedUser);
        }
        resp.getWriter().write(outputJsonString);
    }
}
