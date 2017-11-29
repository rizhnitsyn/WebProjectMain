package servlets;

import DTO.UsersResultTableDto;
import entities.User;
import services.MatchService;
import services.TeamService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static utils.StaticContent.createViewPath;


@WebServlet("/resultTable")
public class TournamentResultTableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long tournamentId = Long.valueOf(req.getParameter("id"));
        req.setAttribute("users", UserService.getInstance().getUsersWithStatistic(tournamentId));
        req.getServletContext()
                .getRequestDispatcher(createViewPath("show-result-table"))
                .forward(req, resp);
    }
}
