package servlets;


import DTO.TournamentCreateUpdateDto;
import DTO.TournamentViewDto;
import services.TeamService;
import services.TournamentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.sql.Date;
import java.time.LocalDate;

import static utils.StaticContent.*;


@WebServlet(urlPatterns = "/saveTournament", name = "SaveTournament")
public class TournamentSaveServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("teams", TeamService.getInstance().getListOfTeams());
        getServletContext()
                .getRequestDispatcher(createViewPath( "save-tournament"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        Long organizer = 0L;         //страну тянем из справочника ! Сделать
        LocalDate startDateLocal = null;
        try {
            organizer = Long.valueOf(req.getParameter("organizer"));
            String startDate = req.getParameter("startDate");
            startDateLocal = LocalDate.parse(startDate, dateFormatter);
        } catch (NumberFormatException e) {//при ошибках парсинга будем перекидывать на страницу и отображать текст ошибки!!! Сделать позже
//            e.printStackTrace();
        }
        if (!name.isEmpty() && organizer != 0 && startDateLocal != null ) {//статус вводить через справочник!!! позже исправить
            TournamentViewDto savedTournament = TournamentService.getInstance()
                    .addTournament(new TournamentCreateUpdateDto(name, organizer, startDateLocal, 1));
            resp.sendRedirect("/tournament?id=" + savedTournament.getId());
        } else {
            resp.sendRedirect("/saveTournament");
        }
    }
}
