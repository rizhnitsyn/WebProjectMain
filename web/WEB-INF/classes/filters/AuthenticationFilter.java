package filters;

import DTO.UserLoggedDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"SaveForecast", "MatchesForForecast", "MatchesOfTournament", "SaveNewMatch", "ShowMatch",
"UpdateMatch", "TournamentsAllMatches", "TournamentsForecasts", "TournamentsRegistration", "TournamentResultTable",
"SaveTournament", "TournamentsForStatistic", "ShowTournament", "UsersForRegistration"})
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            if (req.getSession().getAttribute("loggedUser") != null) {
                UserLoggedDto loggedUser = (UserLoggedDto) req.getSession().getAttribute("loggedUser");
                if (loggedUser.getUserStateId() == 1 || loggedUser.getUserStateId() == 3) {
                    ((HttpServletResponse) servletResponse).sendRedirect("/user?=" + loggedUser.getUserId());
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } else {
                ((HttpServletResponse) servletResponse).sendRedirect("/login");
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }
}
