package servlets;

import DTO.TournamentDto;
import entities.Tournament;
import services.TournamentService;
import utils.StaticContent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/tournament")
public class ShowTournamentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        TournamentDto foundTournament = TournamentService.getInstance().getTournamentById(id);
        req.setAttribute("tournament", foundTournament);
        getServletContext()
                .getRequestDispatcher(StaticContent.jspPath + "/show-tournament.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Long id = Long.valueOf(req.getParameter("id"));
        TournamentDto updatedTournament = TournamentService.getInstance().updateTournament(id);
        resp.sendRedirect("/tournament?id=" + updatedTournament.getId());
    }
}
