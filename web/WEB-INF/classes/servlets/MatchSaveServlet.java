package servlets;

import DTO.*;
import com.google.gson.Gson;
import services.MatchService;
import services.TeamService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

import static utils.StaticContent.*;


@WebServlet(urlPatterns = "/saveMatch", name = "SaveNewMatch")
public class MatchSaveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long tournamentId = Long.valueOf(req.getParameter("id"));
        req.setAttribute("matchTypes", MatchService.getInstance().getMatchTypes());
        req.setAttribute("countries", TeamService.getInstance().getListOfTeams());
        req.setAttribute("tournamentId", tournamentId);
        getServletContext()
                .getRequestDispatcher(createViewPath("save-match"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        String jsonString = req.getReader().lines().collect(Collectors.joining("\n"));
        MatchShortViewDto savedMatch;
        try {
            MatchCreateDto createDto = gson.fromJson(jsonString, MatchCreateDto.class);
            savedMatch = MatchService.getInstance().addMatch(createDto);
            savedMatch.setRedirectPath("/match?id=" + savedMatch.getId());
        } catch (Exception ex) {
            savedMatch = new MatchShortViewDto(true,"Есть ошибки при сохранении матча: " + ex.toString());
        }
        String outputJsonString = gson.toJson(savedMatch);
        resp.getWriter().write(outputJsonString);
    }
}
