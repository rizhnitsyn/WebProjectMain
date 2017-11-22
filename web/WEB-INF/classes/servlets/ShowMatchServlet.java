package servlets;

import DTO.MatchViewDto;
import services.MatchService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static utils.StaticContent.*;

@WebServlet("/match")
public class ShowMatchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        MatchViewDto foundMatch = MatchService.getInstance().getMatchById(id);
        req.setAttribute("match", foundMatch);
        req.getServletContext()
                .getRequestDispatcher(createViewPath( "show-match"))
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        //для админа
        if (req.getParameter("idMatch") != null) {
            Long id = Long.valueOf(req.getParameter("idMatch"));
            MatchViewDto foundMatch = MatchService.getInstance().getMatchById(id);
            req.setAttribute("match", foundMatch);
            req.getServletContext()
                    .getRequestDispatcher(createViewPath( "update-match"))
                    .forward(req, resp);
        }
        //для пользователя
        if (req.getParameter("idForecast") != null) {
            Long id = Long.valueOf(req.getParameter("idForecast"));
            MatchViewDto foundMatch = MatchService.getInstance().getMatchById(id);
            req.setAttribute("match", foundMatch);
            req.getServletContext()
                    .getRequestDispatcher(createViewPath("save-forecast"))
                    .forward(req, resp);
        }
    }
}
