package servlets;

import DTO.TournamentViewDto;
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
        TournamentViewDto foundTournament = TournamentService.getInstance().getTournamentById(id);
        req.setAttribute("tournament", foundTournament);
        getServletContext()
                .getRequestDispatcher(createViewPath( "show-tournament"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if (req.getParameter("idClose") != null) {
            Long id = Long.valueOf(req.getParameter("idClose"));
//            TournamentViewDto foundTournament = TournamentService.getInstance().getTournamentById(id);
            TournamentViewDto updatedTournament = TournamentService.getInstance().closeTournament(id);
            resp.sendRedirect("/tournament?id=" + updatedTournament.getId());
        }
        //для пользователя
        if (req.getParameter("idReg") != null) {
            Long tournamentId = Long.valueOf(req.getParameter("idReg"));
            //получить id пользователя, передать в функцию и сделать insert в БД
            //не давать региться на уже оконченные турниры, убрать кнопку
            Long userId = 1L;
            TournamentViewDto updatedTournament = TournamentService.getInstance().registerUserOnTournament(tournamentId, userId);
            resp.sendRedirect("/tournament?id=" + updatedTournament.getId());
        }
    }
}
