package br.com.clinic.configuration.web;

import br.com.clinic.application.utils.DefaultPatternLogger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

public class RequestFilter extends HandlerInterceptorAdapter {

    private static final DefaultPatternLogger LOG = DefaultPatternLogger.getLogger(RequestFilter.class);
    private static final String HEADER_TOKEN = "authCode";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {

            if (request.getRequestURL().toString().contains("/public")) {
                return true;
            }

            final String authToken = request.getHeader(HEADER_TOKEN);
            if (Objects.nonNull(authToken)) {

                if (authToken.equals("123456")) {
                    return true;
                }

            }

            response.setStatus(401);
            return false;

        } catch (Exception e) {
            return false;
        }
    }

    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

//        LOG.info("ffsdf");
        // todo se response gerou erro, pegar o erro e retornar uma mensagem no body

    }

}
