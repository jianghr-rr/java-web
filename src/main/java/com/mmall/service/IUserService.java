package com.mmall.service;

import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.dto.user.ResetPasswordDTO;
import com.mmall.service.dto.user.UpdateInformationDTO;

public interface IUserService {
    ServerResponse<User> login(String username, String password);

    ServerResponse<String> register(User user);

    ServerResponse<String> checkValid(String str,String type);

    ServerResponse<String> selectQuestion(String str);

    ServerResponse<String> checkAnswer(String username,String question,String answer);

    ServerResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken);

    ServerResponse<User> resetPassword(ResetPasswordDTO resetPasswordDTO);

    ServerResponse<User> updateInformation(UpdateInformationDTO updateInformationDTO);

    ServerResponse<User> getInfomation(int userId);

    ServerResponse<User> getUserInfo();
}
