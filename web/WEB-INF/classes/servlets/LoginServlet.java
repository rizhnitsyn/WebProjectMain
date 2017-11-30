package servlets;

import DTO.AnswerJsDto;
import DTO.LoginDto;
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

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(createViewPath("login"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json");
        Gson gson = new Gson();
        String jsonString = req.getReader().lines()
                .collect(Collectors.joining("\n"));
        LoginDto loginDto = gson.fromJson(jsonString, LoginDto.class);
        AnswerJsDto loginAnswer = UserService.getInstance().checkPassword(loginDto);
        String outputJsonString = gson.toJson(loginAnswer);
        resp.getWriter().write(outputJsonString);
    }

}
