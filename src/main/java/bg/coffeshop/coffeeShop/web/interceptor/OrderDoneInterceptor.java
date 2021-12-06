package bg.coffeshop.coffeeShop.web.interceptor;

import bg.coffeshop.coffeeShop.util.email.JavaMailUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OrderDoneInterceptor implements HandlerInterceptor {

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView modelAndView) throws Exception {
        JavaMailUtil.sendMail("coffeshopPlovdiv@gmail.com");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }
}
