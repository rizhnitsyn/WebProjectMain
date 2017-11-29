package servlets;

import DTO.ForecastAddDto;
import DTO.MatchViewDto;
import entities.Forecast;
import services.ForecastService;
import services.MatchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.StaticContent.*;

@WebServlet("/saveForecast")
public class ForecastSaveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(createViewPath( "save-forecast"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Integer firstTeamResult = null;
        Integer secondTeamResult = null;
        Long userId = 0L;
        Long matchId = 0L;
        try {
            matchId = Long.valueOf(req.getParameter("id"));
            userId = Long.valueOf(req.getParameter("userId"));
            firstTeamResult = Integer.valueOf(req.getParameter("firstTeamResult"));
            secondTeamResult = Integer.valueOf(req.getParameter("secondTeamResult"));
        } catch (NumberFormatException e) {}

        if (firstTeamResult == null || secondTeamResult == null || userId == 0L || matchId == 0L) {
            MatchViewDto foundMatch = MatchService.getInstance().getMatchById(matchId);
            req.setAttribute("match", foundMatch);
            getServletContext()
                    .getRequestDispatcher(createViewPath( "save-forecast"))
                    .forward(req, resp);
        } else {
            ForecastService.getInstance().addForecast(new ForecastAddDto(firstTeamResult, secondTeamResult, userId, matchId));
            resp.sendRedirect("/match?id=" + matchId);
        }
    }
}
