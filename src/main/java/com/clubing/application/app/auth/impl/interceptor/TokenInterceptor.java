package com.clubing.application.app.auth.impl.interceptor;

import com.clubing.application.app.auth.api.manager.TokenManager;
import com.clubing.application.app.rest.impl.exception.UnauthorizedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.AccessDeniedException;

/**
 * @author Sergio Jiménez del Coso
 */

@Component
public class TokenInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenManager tokenManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);

            if (tokenManager.isUserLoggedIn(token)) {
                return true;
            }
        }

        throw new AccessDeniedException("Unauthorized user");
    }
}
