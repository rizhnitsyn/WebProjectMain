package servlets;

import DTO.UserLoggedDto;
import services.TournamentService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.StaticContent.createViewPath;

@WebServlet(urlPatterns = "/tournamentAllMatches", name = "TournamentsAllMatches")
public class TournamentListAllMatches extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = ((UserLoggedDto) req.getSession().getAttribute("loggedUser")).getUserId();
        req.setAttribute("tournaments", TournamentService.getInstance().getAllUserTournaments(userId));
        getServletContext()
                .getRequestDispatcher(createViewPath("tournament-list-allMatches"))
                .forward(req, resp);
    }
}
