package filters;

import DTO.UserLoggedDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"SaveForecast", "MatchesForForecast", "MatchesOfTournament", "SaveNewMatch", "ShowMatch",
"UpdateMatch", "TournamentsAllMatches", "TournamentsForecasts", "TournamentsRegistration", "TournamentResultTable",
"SaveTournament", "TournamentsForStatistic", "ShowTournament", "UsersForRegistration", "ForecastsOfAnotherUser", "ChangePassword"})
public class AuthenticationFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest && servletResponse instanceof HttpServletResponse) {
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            HttpServletResponse resp = (HttpServletResponse) servletResponse;

            if (isUserLogged(req)) {
                UserLoggedDto loggedUser = getCurrentUser(req);
                if (isBadRole(loggedUser)) {
                    sendToPersonalAccount(loggedUser, resp);
                } else {
                    filterChain.doFilter(servletRequest, servletResponse);
                }
            } else {
                sendToLogin(resp);
            }
        } else {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {

    }

    private boolean isBadRole(UserLoggedDto loggedUser) {
        return loggedUser.getUserStateId() == 1 || loggedUser.getUserStateId() == 3;
    }

    private void sendToPersonalAccount(UserLoggedDto loggedUser, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/user?id=" + loggedUser.getUserId());
    }

    private boolean isUserLogged(HttpServletRequest req) {
        return  req.getSession().getAttribute("loggedUser") != null;
    }

    private void sendToLogin(HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/login");
    }

    private UserLoggedDto getCurrentUser(HttpServletRequest req) {
        return (UserLoggedDto)  req.getSession().getAttribute("loggedUser");
    }
}
