package top.ptcc9.interceptor;

import cn.hutool.json.JSON;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import top.ptcc9.annotations.LoginRequired;
import top.ptcc9.commonresult.CommonResult;
import top.ptcc9.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
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
            /**
             * 检查是否携带 @LoginRequired 注解
             * 检查 token 是否 null : expired
             * 返回 true : false
             * @param request
             * @param response
             * @param handler
             * @return
             * @throws Exception
             */
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
                response.setCharacterEncoding("UTF-8");
                response.setContentType("application/json; charset=utf-8");
                boolean isPass = true;
                JSONObject jsonObject = null;
                HandlerMethod handlerMethod = (HandlerMethod) handler;
                Method method = handlerMethod.getMethod();
                if (method.isAnnotationPresent(LoginRequired.class)) {
                    String token = request.getHeader("token");
                    jsonObject = ((token == null) ? JSONUtil.parseObj(new CommonResult<String>(CommonResult.State.NO_TOKEN), false, true) : (JwtUtil.verity(token) ? null : JSONUtil.parseObj(new CommonResult<String>(CommonResult.State.EXPIRED_TOKEN), false, true)));
                }
                if (jsonObject != null) {
                    PrintWriter out = null;
                    out = response.getWriter();
                    out.write(jsonObject.toString());
                    out.flush();
                    out.close();
                    isPass = false;
                }
                return isPass;
            }
        };
        registry.addInterceptor(handlerInterceptor).addPathPatterns("/**");
    }
}
