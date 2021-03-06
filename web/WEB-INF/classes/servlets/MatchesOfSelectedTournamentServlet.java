package servlets;

import DTO.UserLoggedDto;
import services.MatchService;
import services.TeamService;
import services.TournamentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.StaticContent.createViewPath;


@WebServlet(urlPatterns = "/allMatches", name = "MatchesOfTournament")
public class MatchesOfSelectedTournamentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long tournamentId = Long.valueOf(req.getParameter("id"));
        Long userId = ((UserLoggedDto)req.getSession().getAttribute("loggedUser")).getUserId();
        req.setAttribute("matches", MatchService.getInstance().getAllMatchesOfSelectedTournament(tournamentId, userId));
        req.setAttribute("tournament", TournamentService.getInstance().getTournamentName(tournamentId));
        req.getServletContext()
                .getRequestDispatcher(createViewPath("show-all-matches"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long tournamentId = Long.valueOf(req.getParameter("idTournament"));
        resp.sendRedirect("/saveMatch?id=" + tournamentId);
    }
}
