package servlets;

import services.MatchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.StaticContent.createViewPath;


@WebServlet("/allMatches")
public class MatchesForSelectedTournamentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long tournamentId = Long.valueOf(req.getParameter("id"));
        req.setAttribute("matches", MatchService.getInstance().getAllMatchesOfSelectedTournament(tournamentId));
        req.getServletContext()
                .getRequestDispatcher(createViewPath("show-all-matches"))
                .forward(req, resp);
    }
}
