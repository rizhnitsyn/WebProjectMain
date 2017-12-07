package servlets;

import DTO.UnloadFileDto;
import DTO.UsersResultTableDto;
import com.google.gson.Gson;
import entities.User;
import services.MatchService;
import services.TeamService;
import services.TournamentService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static utils.StaticContent.createViewPath;


@WebServlet(urlPatterns = "/resultTable", name = "TournamentResultTable")
public class TournamentResultTableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long tournamentId = Long.valueOf(req.getParameter("id"));
        req.setAttribute("users", UserService.getInstance().getUsersWithStatistic(tournamentId));
        req.setAttribute("tournament", TournamentService.getInstance().getTournamentName(tournamentId));
        req.getServletContext()
                .getRequestDispatcher(createViewPath("show-result-table"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        String jsonString = req.getReader().lines().collect(Collectors.joining("\n"));
        UnloadFileDto unloadFileDto = gson.fromJson(jsonString, UnloadFileDto.class);

        Long tournamentID = unloadFileDto.getIdTournament();
        UnloadFileDto answerDto = UserService.getInstance().printResultTable(unloadFileDto);

        req.setAttribute("users", UserService.getInstance().getUsersWithStatistic(tournamentID));
        req.setAttribute("tournament", TournamentService.getInstance().getTournamentName(tournamentID));

        resp.getWriter().write(gson.toJson(answerDto));
    }
}
