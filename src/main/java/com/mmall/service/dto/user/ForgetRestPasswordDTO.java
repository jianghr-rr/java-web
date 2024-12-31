package com.mmall.service.dto.user;

//username
//passwordNew
//forgetToken
public class ForgetRestPasswordDTO {
    private String username;
    private String password;
    private String forgetToken;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getForgetToken() {
        return forgetToken;
    }
    public void setForgetToken(String forgetToken) {
        this.forgetToken = forgetToken;
    }
}
