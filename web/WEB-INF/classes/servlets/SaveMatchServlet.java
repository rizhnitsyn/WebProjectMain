package servlets;

import DTO.MatchViewDto;
import entities.Match;
import entities.Tournament;
import services.MatchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;

import static utils.StaticContent.*;


@WebServlet("/saveMatch")
public class SaveMatchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(createViewPath( "save-match"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        int firstTeamId = 0;
        int secondTeamId = 0;
        Long tournamentId = 0L;
        int matchType = 0;
        Long startDateLong = 0L;
        try {
            firstTeamId = Integer.parseInt(req.getParameter("firstTeamId"));
            secondTeamId = Integer.parseInt(req.getParameter("secondTeamId"));
            tournamentId = Long.valueOf(req.getParameter("tournamentId"));
            matchType = Integer.parseInt(req.getParameter("matchType"));
            String startDate = req.getParameter("matchDateTime");
            startDateLong = dateTimeFormat.parse(startDate).getTime();
        } catch (NumberFormatException | ParseException e) {}

        if (firstTeamId == 0 || secondTeamId == 0 || matchType == 0 || tournamentId == 0L || startDateLong == 0) {
            resp.sendRedirect("/saveMatch");
        } else {
            MatchViewDto savedMatch = MatchService.getInstance().addMatch(
                    new Match(new Date(startDateLong), 1, matchType, firstTeamId, secondTeamId,
                    new Tournament(tournamentId)));
            resp.sendRedirect("/match?id=" + savedMatch.getId());
        }
    }
}
