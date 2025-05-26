package pl.zenit.infobazademo.auth;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class HardcodedTokenFilter extends OncePerRequestFilter {

    public static final String TEST_TOKEN = "test-token";
    private static final String TOKEN_HEADER = "Authorization";
    private static final String VALID_TOKEN_EXPR = "Bearer " + TEST_TOKEN;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        String receivedToken = request.getHeader(TOKEN_HEADER);
        boolean validToken = VALID_TOKEN_EXPR.equals(receivedToken);
        boolean onlineTest = "/".equals(request.getRequestURI());

        if (validToken || onlineTest) {
            filterChain.doFilter(request, response);
        }
        else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("401");
        }
    }
}