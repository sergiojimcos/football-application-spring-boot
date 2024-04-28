package com.clubing.application.app.auth.api.manager;

/**
 * @author Sergio Jiménez del Coso
 */

public interface TokenManager {

    public String loginUser(String username, String password) throws Exception;

    public boolean isUserLoggedIn(String token);
}
