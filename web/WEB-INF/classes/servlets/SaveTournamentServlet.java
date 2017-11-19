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
import java.text.ParseException;
import java.sql.Date;


@WebServlet("/saveTournament")
public class SaveTournamentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        getServletContext()
                .getRequestDispatcher(StaticContent.jspPath + "/save-tournament.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        int organizer = 0;         //страну тянем из справочника ! Сделать
        Long startDateL = 0L;
        try {
            organizer = Integer.parseInt(req.getParameter("organizer"));
            String startDate = req.getParameter("startDate");
            startDateL = StaticContent.dateFormat.parse(startDate).getTime();
        } catch (ParseException | NumberFormatException e) {//при ошибках парсинга будем перекидывать на страницу и отображать текст ошибки!!! Сделать позже
//            e.printStackTrace();
        }
        if (!name.isEmpty() && organizer != 0 && startDateL != 0L) {//статус вводить через справочник!!! позже исправить
            TournamentDto savedTournament = TournamentService.getInstance()
                    .addTournament(new Tournament(name, organizer, new Date(startDateL), 1));
            resp.sendRedirect("/tournament?id=" + savedTournament.getId());
        } else {
            resp.sendRedirect("/saveTournament");
        }
    }
}
