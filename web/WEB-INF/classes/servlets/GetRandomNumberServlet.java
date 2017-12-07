package servlets;

import DTO.LoginDto;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;
import java.util.stream.Collectors;

@WebServlet(urlPatterns = "/getRandom", name = "random")
public class GetRandomNumberServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();

        String jsonString = req.getReader().lines().collect(Collectors.joining("\n"));
        LoginDto loginDto = gson.fromJson(jsonString, LoginDto.class);
        Random random = new Random();
        loginDto.setRandomNumber(String.valueOf(random.nextInt(10000000)));

        String outputJsonString = gson.toJson(loginDto);
        resp.getWriter().write(outputJsonString);
    }
}
