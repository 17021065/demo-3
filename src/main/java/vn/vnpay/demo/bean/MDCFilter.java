package vn.vnpay.demo.bean;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        } catch (Exception e) {
            log.error("Applying thread context failed: ", e);
        } finally {
            MDC.clear();
        }
    }
}
