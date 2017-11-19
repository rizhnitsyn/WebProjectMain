package servlets;

import DTO.MatchDto;
import services.MatchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/updateMatch")
public class UpdateMatchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id")); //сделать проверку!!!!
        int firstTeamResult = Integer.parseInt(req.getParameter("firstTeamResult"));
        int secondTeamResult = Integer.parseInt(req.getParameter("secondTeamResult"));
        MatchDto updatedMatch = MatchService.getInstance().updateMatch(id, firstTeamResult, secondTeamResult);
        resp.sendRedirect("/match?id=" + updatedMatch.getId());
    }
}
