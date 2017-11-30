package servlets;

import services.MatchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.StaticContent.createViewPath;


@WebServlet(urlPatterns =  "/forecastMatches", name = "MatchesForForecast")
public class MatchesAvailableForForecastServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long tournamentId = Long.valueOf(req.getParameter("id"));
        req.setAttribute("matches", MatchService.getInstance().matchesForForecast(tournamentId,1L));
        req.getServletContext()
                .getRequestDispatcher(createViewPath("show-forecast-matches"))
                .forward(req, resp);
    }
}
