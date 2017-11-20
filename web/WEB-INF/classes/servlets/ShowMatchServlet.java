package servlets;

import DTO.MatchDto;
import services.MatchService;
import utils.StaticContent;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/match")
public class ShowMatchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        MatchDto foundMatch = MatchService.getInstance().getMatchById(id);
        req.setAttribute("match", foundMatch);
        //для админа и юзера наименование кнопки разные
        req.getServletContext()
                .getRequestDispatcher(StaticContent.jspPath + "/show-match.jsp")
                .forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        Long id = Long.valueOf(req.getParameter("id")); //сделать проверку!!!!
        MatchDto foundMatch = MatchService.getInstance().getMatchById(id);
        req.setAttribute("match", foundMatch);

        //это для админа
//        req.getServletContext()
//                .getRequestDispatcher(StaticContent.jspPath + "/update-match.jsp")
//                .forward(req, resp);
        //это для юзера
        req.getServletContext()
                .getRequestDispatcher(StaticContent.jspPath + "/save-forecast.jsp")
                .forward(req, resp);
    }
}
