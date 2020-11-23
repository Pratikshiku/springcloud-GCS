package top.ptcc9.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import top.ptcc9.annotations.LoginRequired;
import top.ptcc9.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * @author: HE LONG CAN
 * @description:
 * @date: 2020-11-22 23:05
 */
@Configuration
public class TokenInterceptor extends WebMvcConfigurationSupport {
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        HandlerInterceptor handlerInterceptor = new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                boolean isPass = true;
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                Method method = handlerMethod.getMethod();
                if (method.isAnnotationPresent(LoginRequired.class)) {
                    String token = request.getHeader("token");
                    if (!JwtUtil.verity(token)) {
                        if (token.isEmpty()) {
                            response.sendError(101);
                        } else {
                            response.sendError(102);
                        }
                        isPass = false;
                    }
                }
                return isPass;
            }

            @Override
            public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
            }

            @Override
            public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
            }
        };
        registry.addInterceptor(handlerInterceptor).addPathPatterns("/**");
    }
}
