package com.clubing.application.app.auth.api.manager;

public interface TokenManager {

    public String loginUser(String username, String password) throws Exception;
}
