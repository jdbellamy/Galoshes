package com.example.filters;

import com.example.logging.CorrelationContext;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

import static com.example.logging.CorrelationContext.CORRELATION_ID_HEADER;

@Component
public class CorrelationFilter implements Filter {

    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest)servletRequest;
        String header = req.getHeader(CORRELATION_ID_HEADER);
        String corrId = Optional.ofNullable(header).orElse(UUID.randomUUID().toString());
        CorrelationContext.setId(corrId);
        filterChain.doFilter(req, servletResponse);
    }

    @Override
    public void destroy() {
    }

}