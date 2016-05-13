package util;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AccessCheckerFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String contextPath = "http://localhost:8081";
        HttpSession session = req.getSession();

        if ((session!=null) && (session.getAttribute("Auth") == 1)) {
            int p = 4;
            chain.doFilter(request, response);
        } else {
            if (req.getRequestURL().toString().equals(contextPath + "/")||
                req.getRequestURL().toString().equals(contextPath+"/faces/auth.xhtml")||
                req.getRequestURL().toString().equals(contextPath+"/faces/registration.xhtml")) {
                chain.doFilter(request, response);
            } else {
                res.sendRedirect(contextPath + "/faces/auth.xhtml");
            }
        }
    }

    @Override
    public void destroy() {

    }
}
