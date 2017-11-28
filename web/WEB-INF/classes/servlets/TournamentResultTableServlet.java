package servlets;

import services.MatchService;
import services.TeamService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.StaticContent.createViewPath;


@WebServlet("/resultTable")
public class TournamentResultTableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
// тут получаем набор данных для формирования таблицы результатов и отображаем результаты
//        ФИО кликабельны для просмотра статистики по пользователю
        //        Long tournamentId = Long.valueOf(req.getParameter("id"));
//        req.setAttribute("matches", MatchService.getInstance().getAllMatchesOfSelectedTournament(tournamentId));
//        req.getServletContext()
//                .getRequestDispatcher(createViewPath("show-all-matches"))
//                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Long tournamentId = Long.valueOf(req.getParameter("idTournament"));
//        req.setAttribute("matchTypes", MatchService.getInstance().getMatchTypes());
//        req.setAttribute("countries", TeamService.getInstance().getListOfTeams());
//        req.setAttribute("tournamentId", tournamentId);
//        getServletContext()
//                .getRequestDispatcher(createViewPath("save-match"))
//                .forward(req, resp);
    }
}
