package servlets;

import DTO.TournamentViewDto;
import DTO.UserLoggedDto;
import services.TournamentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.StaticContent.*;

@WebServlet(urlPatterns = "/tournament", name = "ShowTournament")
public class TournamentShowUpdateServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        Long userId = ((UserLoggedDto) req.getSession().getAttribute("loggedUser")).getUserId();
        TournamentViewDto foundTournament = TournamentService.getInstance().getTournamentById(id, userId);
        req.setAttribute("tournament", foundTournament);
        getServletContext()
                .getRequestDispatcher(createViewPath( "show-tournament"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("idClose") != null) {
            Long id = Long.valueOf(req.getParameter("idClose"));
            TournamentService.getInstance().closeTournament(id);
            resp.sendRedirect("/tournamentList");
        }
        if (req.getParameter("idReg") != null) {
            Long tournamentId = Long.valueOf(req.getParameter("idReg"));
            Long userId = ((UserLoggedDto) req.getSession().getAttribute("loggedUser")).getUserId();
            TournamentViewDto updatedTournament = TournamentService.getInstance().registerUserOnTournament(tournamentId, userId);
            resp.sendRedirect("/tournament?id=" + updatedTournament.getId());
        }
    }
}
