package filters;

import DTO.UserLoggedDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"UsersForRegistration", "SaveNewMatch", "SaveTournament", "UpdateMatch"})
public class AdminFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            HttpServletResponse resp = (HttpServletResponse) servletResponse;

            if (isUserLogged(req)) {
                UserLoggedDto loggedUser = (UserLoggedDto) req.getSession().getAttribute("loggedUser");
                if (loggedUser.getUserStateId() != 4) {
                    resp.sendRedirect("/homepage");
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } else {
                resp.sendRedirect("/homepage");
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isUserLogged(HttpServletRequest req) {
        return  req.getSession().getAttribute("loggedUser") != null;
    }
}
