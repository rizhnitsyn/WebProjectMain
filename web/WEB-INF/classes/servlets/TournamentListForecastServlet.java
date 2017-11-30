package servlets;

import services.TournamentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.StaticContent.createViewPath;

@WebServlet(urlPatterns = "/forecastTournaments", name = "TournamentsForecasts")
public class TournamentListForecastServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //передавать ID тукущего юзера
        req.setAttribute("tournaments", TournamentService.getInstance().getTournamentsForForecasts(1L));
        getServletContext()
                .getRequestDispatcher(createViewPath("tournament-list-forecasts"))
                .forward(req, resp);
    }
}
