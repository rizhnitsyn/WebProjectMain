package servlets;

import DTO.MatchViewDto;
import DTO.UserLoggedDto;
import services.MatchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import static utils.StaticContent.*;

@WebServlet(urlPatterns = "/match", name = "ShowMatch")
public class MatchShowServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long matchId = Long.valueOf(req.getParameter("id"));
        Long userId = ((UserLoggedDto) req.getSession().getAttribute("loggedUser")).getUserId();
        Boolean activeForForecast = false;
        MatchViewDto foundMatch = MatchService.getInstance().getMatchById(matchId, userId);
        if (foundMatch != null) {
            if (foundMatch.getMatchDateTime().compareTo(LocalDateTime.now()) > 0) {
                activeForForecast = true;
            }
        }
        req.setAttribute("isActive", activeForForecast);
        req.setAttribute("match", foundMatch);
        req.getServletContext()
                .getRequestDispatcher(createViewPath( "show-match"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("idMatch") != null) {
            Long matchId = Long.valueOf(req.getParameter("idMatch"));
            Long userId = ((UserLoggedDto) req.getSession().getAttribute("loggedUser")).getUserId();
            MatchViewDto foundMatch = MatchService.getInstance().getMatchById(matchId, userId);
            req.setAttribute("match", foundMatch);
            req.getServletContext()
                    .getRequestDispatcher(createViewPath( "update-match"))
                    .forward(req, resp);
        }
        if (req.getParameter("idForecast") != null) {
            Long matchId = Long.valueOf(req.getParameter("idForecast"));
            Long userId = ((UserLoggedDto) req.getSession().getAttribute("loggedUser")).getUserId();
            MatchViewDto foundMatch = MatchService.getInstance().getMatchById(matchId, userId);
            req.setAttribute("match", foundMatch);
            req.getServletContext()
                    .getRequestDispatcher(createViewPath("save-forecast"))
                    .forward(req, resp);
        }
    }
}
