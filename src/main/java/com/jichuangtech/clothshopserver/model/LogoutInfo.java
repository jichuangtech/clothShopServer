package com.jichuangtech.clothshopserver.model;

public class LogoutInfo {

    public String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "LogoutInfo{" +
                "token='" + token + '\'' +
                '}';
    }
}
