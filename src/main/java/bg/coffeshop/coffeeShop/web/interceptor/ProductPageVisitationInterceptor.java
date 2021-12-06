package bg.coffeshop.coffeeShop.web.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProductPageVisitationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        response.setHeader("visitor", getClientIP(request));
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }

    public String getClientIP(HttpServletRequest request) {
        String ip;
        ip = request.getHeader("X-FORWARDED-FOR");
        if (ip == null || "".equals(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }

}
