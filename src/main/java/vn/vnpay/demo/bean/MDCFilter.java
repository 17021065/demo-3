package vn.vnpay.demo.bean;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
@RequiredArgsConstructor
@Slf4j
public class MDCFilter extends OncePerRequestFilter {

    private final RequestTracker requestTracker;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
        try {
            MDC.put("tracking", requestTracker.getTrackingString());
            filterChain.doFilter(request, response);
            MDC.clear();
        } catch (IllegalArgumentException | IOException | ServletException e) {
            log.error("Applying thread context failed: ", e);
        }
    }
}
