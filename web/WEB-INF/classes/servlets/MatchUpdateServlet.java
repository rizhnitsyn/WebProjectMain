package servlets;

import DTO.MatchViewDto;
import services.MatchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/updateMatch", name = "UpdateMatch")
public class MatchUpdateServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        int firstTeamResult = Integer.parseInt(req.getParameter("firstTeamResult"));
        int secondTeamResult = Integer.parseInt(req.getParameter("secondTeamResult"));
        MatchViewDto updatedMatch = MatchService.getInstance().updateMatch(id, firstTeamResult, secondTeamResult);
        resp.sendRedirect("/match?id=" + updatedMatch.getId());
    }
}
