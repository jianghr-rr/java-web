package com.mmall.service.dto.user;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class ResetPasswordDTO {
    private String passwordOld;
    private String passwordNew;

    public String getPasswordOld() {
        return passwordOld;
    }

    public String getPasswordNew() {
        return passwordNew;
    }
}
