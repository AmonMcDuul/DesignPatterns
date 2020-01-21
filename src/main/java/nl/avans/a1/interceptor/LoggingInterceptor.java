package nl.avans.a1.interceptor;

import java.util.GregorianCalendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * RED WORD: PERFORMANCE
 * Will time incoming and outgoing requests
 */
@Component("loggingInterceptor")
public class LoggingInterceptor implements HandlerInterceptor {
    Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @Override
    public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3) {
        logger.info("Completed request and rendered page at: " + new GregorianCalendar().toString());
    }

    @Override
    public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3) {
        logger.info("Executed Method: " + new GregorianCalendar().toString());
    }

    @Override
    public boolean preHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2) {
        logger.info("Started request at: " + new GregorianCalendar().toString());
        return true;
    }
}