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
import java.util.List;

import static utils.StaticContent.*;

@WebServlet(urlPatterns = "/tournamentList", name = "TournamentsRegistration")
public class TournamentListRegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = ((UserLoggedDto) req.getSession().getAttribute("loggedUser")).getUserId();
        List<TournamentViewDto> alLActiveTournaments = TournamentService.getInstance().getAlLActiveTournaments(userId);
        req.setAttribute("tournaments", alLActiveTournaments);
        getServletContext()
                .getRequestDispatcher(createViewPath("tournament-list"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/saveTournament");
    }
}
