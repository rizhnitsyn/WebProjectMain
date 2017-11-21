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
public class ShowUpdateTournamentServlet extends HttpServlet {

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
        if (req.getParameter("idClose") != null) {
            Long id = Long.valueOf(req.getParameter("idClose"));
            TournamentDto foundTournament = TournamentService.getInstance().getTournamentById(id);
            TournamentDto updatedTournament = TournamentService.getInstance().closeTournament(foundTournament);
            resp.sendRedirect("/tournament?id=" + updatedTournament.getId());
        }
        //для пользователя
        if (req.getParameter("idReg") != null) {
            Long id = Long.valueOf(req.getParameter("idReg"));
            TournamentDto foundTournament = TournamentService.getInstance().getTournamentById(id);
            //получить id пользователя, передать в функцию и сделать insert в БД
            TournamentDto updatedTournament = TournamentService.getInstance().registerUserOnTournament(foundTournament);
            resp.sendRedirect("/tournament?id=" + updatedTournament.getId());
        }
    }
}
