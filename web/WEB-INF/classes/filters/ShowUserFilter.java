package filters;

import DTO.UserLoggedDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(servletNames = {"ShowUser"})
public class ShowUserFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) servletRequest;
            if (isUserLogged(req)) {
                filterChain.doFilter(servletRequest, servletResponse);
            } else {
                sendToLogin(servletResponse);
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

    private void sendToLogin(ServletResponse resp) throws IOException {
        ((HttpServletResponse) resp).sendRedirect("/login");
    }
}
