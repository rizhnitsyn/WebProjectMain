package servlets;

import services.TournamentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.StaticContent.*;

@WebServlet("/tournamentList")
public class TournamentListRegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("tournaments", TournamentService.getInstance().getAlLActiveTournaments());
        getServletContext()
                .getRequestDispatcher(createViewPath("tournament-list"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/saveTournament");
    }
}
