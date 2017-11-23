package servlets;

import DTO.MatchShortViewDto;
import services.MatchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.StaticContent.createViewPath;


@WebServlet("/forecastMatches")
public class MatchesAvailableForForecastServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        List<MatchShortViewDto> list = MatchService.getInstance().matchesForForecast(1L,1L);

        req.setAttribute("matches", MatchService.getInstance().matchesForForecast(9L,1L));
        req.getServletContext()
                .getRequestDispatcher(createViewPath("show-forecast-matches"))
                .forward(req, resp);
    }
}
