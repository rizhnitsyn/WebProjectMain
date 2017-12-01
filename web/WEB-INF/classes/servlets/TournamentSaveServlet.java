package servlets;

import DTO.TournamentCreateUpdateDto;
import DTO.TournamentViewDto;
import com.google.gson.Gson;
import services.TeamService;
import services.TournamentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

import static utils.StaticContent.*;


@WebServlet(urlPatterns = "/saveTournament", name = "SaveTournament")
public class TournamentSaveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("teams", TeamService.getInstance().getListOfTeams());
        getServletContext()
                .getRequestDispatcher(createViewPath( "save-tournament"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        String jsonString = req.getReader().lines().collect(Collectors.joining("\n"));
        TournamentCreateUpdateDto createDto = gson.fromJson(jsonString, TournamentCreateUpdateDto.class);
        TournamentViewDto tournamentView = TournamentService.getInstance().addTournament(createDto);
        String outputJsonString = gson.toJson(tournamentView);
        resp.getWriter().write(outputJsonString);
    }
}
