package com.guet.qiusuo.fruittravel.config;

import com.guet.qiusuo.fruittravel.bean.vo.UserVO;
import com.guet.qiusuo.fruittravel.service.UserService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Aspect
@Component
public class UserAspect {

    private static final Map<String, Object> WHITE_LIST = new HashMap<>();

    @Autowired
    private UserService userService;

    static {
        WHITE_LIST.put("/v1/user/login", "POST");
        WHITE_LIST.put("/v1/user/register", "POST");
        WHITE_LIST.put("/v1/user/defaultKaptcha", "GET");
        WHITE_LIST.put("/v1/notification/system", "GET");
        WHITE_LIST.put("/v1/banner", "GET");
    }

    public void ignoreToken() {
    }

    @Pointcut("execution(public * com.guet.qiusuo.fruittravel.controller.*.*(..))")
    public void userAspect() {
    }

    @Before("userAspect()")
    public void beforeUserAspect(JoinPoint joinPoint) {
        //获取request
        HttpServletRequest request =
                ((ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes())).getRequest();
        String requestURI = request.getRequestURI();
        boolean ignoreToken = false;
        // uri是否在白名单内, 是则不需要校验token
        if (WHITE_LIST.containsKey(requestURI)) {
            if (request.getMethod().equals(WHITE_LIST.get(requestURI))) {
                ignoreToken = true;
            }
        }

        if (!ignoreToken) {
            String token = request.getHeader("token");
            if (token == null) {
                throw new SystemException(ErrorCode.USER_NOT_FOUND);
            }
            UserVO user = userService.getUserInfoByToken(token);
            if (user == null) {
                throw new SystemException(ErrorCode.LOGIN_EXPIRED);
            }
            UserContextHolder.setUserVO(user);
        }
    }
}
