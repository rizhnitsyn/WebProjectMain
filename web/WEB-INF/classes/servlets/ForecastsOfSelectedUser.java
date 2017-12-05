package servlets;


import services.MatchService;
import services.TournamentService;
import services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.StaticContent.createViewPath;


@WebServlet(urlPatterns = "/allUserForecasts", name = "ForecastsOfAnotherUser")
public class ForecastsOfSelectedUser extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("userId"));
        Long tournamentId = Long.valueOf(req.getParameter("tournamentId"));
        req.setAttribute("matches", MatchService.getInstance().getAllMatchesOfSelectedTournament(tournamentId, userId));
        req.setAttribute("tournament", TournamentService.getInstance().getTournamentName(tournamentId));
        req.setAttribute("user", UserService.getInstance().getShortUserById(userId));
        req.getServletContext()
                .getRequestDispatcher(createViewPath("show-user-matches"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long tournamentId = Long.valueOf(req.getParameter("idTournament"));
        resp.sendRedirect("/saveMatch?id=" + tournamentId);
    }
}
