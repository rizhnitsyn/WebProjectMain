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
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            if (req.getSession().getAttribute("loggedUser") != null) {
                UserLoggedDto loggedUser = (UserLoggedDto) req.getSession().getAttribute("loggedUser");
                if (loggedUser.getUserStateId() != 4) {
                    //переадресация на прошлую страницу!!!
                    ((HttpServletResponse) servletResponse).sendRedirect("/homepage");
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } else {
                //переадресация на прошлую страницу!!!
                ((HttpServletResponse) servletResponse).sendRedirect("/homepage");
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {

    }
}
