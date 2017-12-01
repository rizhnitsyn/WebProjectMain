package servlets;

import DTO.MatchCreateDto;
import DTO.MatchViewDto;
import DTO.TournamentCreateUpdateDto;
import DTO.TournamentViewDto;
import com.google.gson.Gson;
import entities.Match;
import entities.Tournament;
import services.MatchService;
import services.TournamentService;
import utils.StaticContent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

import static utils.StaticContent.*;


@WebServlet(urlPatterns = "/saveMatch", name = "SaveNewMatch")
public class MatchSaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Gson gson = new Gson();
        String jsonString = req.getReader().lines().collect(Collectors.joining("\n"));
        MatchCreateDto createDto = gson.fromJson(jsonString, MatchCreateDto.class);
        TournamentViewDto tournamentView = TournamentService.getInstance().addTournament(createDto);
        String outputJsonString = gson.toJson(tournamentView);
        resp.getWriter().write(outputJsonString);

//        Long firstTeamId = null;
//        Long secondTeamId = null;
//        Long tournamentId = null;
//        Integer matchType = 0;
//        LocalDateTime startDateLocal = null;
//        try {
//            firstTeamId = Long.valueOf(req.getParameter("firstTeamId"));
//            secondTeamId = Long.valueOf(req.getParameter("secondTeamId"));
//            tournamentId = Long.valueOf(req.getParameter("tournamentId"));
//            matchType = Integer.valueOf(req.getParameter("matchType"));
//            String startDate = req.getParameter("matchDateTime");
//            startDateLocal = LocalDateTime.parse(startDate, dateTimeFormatter);
//        } catch (NumberFormatException e) {
//
//        }
//
//        if (firstTeamId == null || secondTeamId == null || matchType == null || tournamentId == null || startDateLocal == null) {
//            resp.sendRedirect("/saveMatch");
//        } else {
//            MatchViewDto savedMatch = MatchService.getInstance().addMatch(
//                    new MatchCreateDto(startDateLocal, 1, matchType, firstTeamId, secondTeamId, tournamentId));
//            resp.sendRedirect("/match?id=" + savedMatch.getId());
//        }
    }
}
