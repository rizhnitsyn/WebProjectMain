package servlets;

import DTO.ForecastAddDto;
import DTO.MatchViewDto;
import DTO.UserLoggedDto;
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

@WebServlet(urlPatterns = "/saveForecast", name = "SaveForecast")
public class ForecastSaveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(createViewPath( "save-forecast"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long matchId = Long.valueOf(req.getParameter("id"));
        Long userId = ((UserLoggedDto) req.getSession().getAttribute("loggedUser")).getUserId();
        try {
            Integer firstTeamResult = Integer.valueOf(req.getParameter("firstTeamResult"));
            Integer secondTeamResult = Integer.valueOf(req.getParameter("secondTeamResult"));
            ForecastService.getInstance().addForecast(new ForecastAddDto(firstTeamResult, secondTeamResult, userId, matchId));
            resp.sendRedirect("/match?id=" + matchId);
        } catch (Exception e) {
            MatchViewDto foundMatch = MatchService.getInstance().getMatchById(matchId, userId);
            req.setAttribute("match", foundMatch);
            getServletContext()
                    .getRequestDispatcher(createViewPath( "save-forecast"))
                    .forward(req, resp);
        }
    }
}
