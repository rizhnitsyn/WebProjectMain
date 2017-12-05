package servlets;

import DTO.MatchViewDto;
import DTO.UserLoggedDto;
import services.MatchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.StaticContent.createViewPath;

@WebServlet(urlPatterns = "/updateMatch", name = "UpdateMatch")
public class MatchUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(createViewPath( "update-match"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long matchId = Long.valueOf(req.getParameter("matchId"));
        Long userId = ((UserLoggedDto) req.getSession().getAttribute("loggedUser")).getUserId();

        try {
            int firstTeamResult = Integer.parseInt(req.getParameter("firstTeamResult"));
            int secondTeamResult = Integer.parseInt(req.getParameter("secondTeamResult"));
            MatchViewDto updatedMatch = MatchService.getInstance().updateMatch(matchId, firstTeamResult, secondTeamResult);
//            resp.sendRedirect("/match?id=" + updatedMatch.getId());
            resp.sendRedirect("/allMatches?id=" + updatedMatch.getTournamentId());
        } catch (NumberFormatException e) {
            MatchViewDto foundMatch = MatchService.getInstance().getMatchById(matchId, userId);
            req.setAttribute("match", foundMatch);
            getServletContext()
                    .getRequestDispatcher(createViewPath( "save-forecast"))
                    .forward(req, resp);
        }
    }
}
