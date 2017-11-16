package servlets;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class DatabaseServlet extends HttpServlet {
    @Override
    public void init() throws ServletException {
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();


//        String result = UserDaoImpl.getInstance().getListOfUsers().stream()
//                .map(this::wrapInParagraph)
//                .collect(Collectors.joining());
//        writer.write(result);
    }

//    private String wrapInParagraph(User user) {
//        return "<p>" + user.getFirstName() + " " + user.getSecondName() + "</p>";
//    }
}
