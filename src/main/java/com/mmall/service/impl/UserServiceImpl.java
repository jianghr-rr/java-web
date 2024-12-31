package com.mmall.service.impl;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.common.TokenCache;
import com.mmall.dao.UserMapper;
import com.mmall.pojo.User;
import com.mmall.service.dto.user.ResetPasswordDTO;
import com.mmall.service.dto.user.UpdateInformationDTO;
import com.mmall.util.CookieUtil;
import com.mmall.util.JwtUtil;
import com.mmall.util.MD5Util;
import com.mmall.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Service("iUserService")
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserMapper userMapper;

    private final HttpServletRequest request;

    public UserServiceImpl(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public ServerResponse<User> login(String username, String password) {
        // 判断用户名存在不存在
        int resultCount = userMapper.checkUsername(username);

        if (resultCount == 0) {
            return ServerResponse.createByError("用户名不存在");
        }

        String md5Password = MD5Util.MD5EncodeUtf8(password);
        User user = userMapper.selectLogin(username, md5Password);

        if (user == null) {
            return ServerResponse.createByError("密码错误");
        }

        // ??? 为什么要设置为空
        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);

        // 设置jwt
        String token = JwtUtil.generateToken(username);
        // 设置 Cookie
        CookieUtil.setAuthTokenCookie(token);

        return ServerResponse.createBySuccess("登录成功",user);
    }

    public ServerResponse<String> register(User user) {

        ServerResponse<String> validResponse = this.checkValid(user.getUsername(),Const.USERNAME);
        if(!validResponse.isSuccess()){
            return validResponse;
        }
        validResponse = this.checkValid(user.getEmail(),Const.EMAIL);
        if(!validResponse.isSuccess()){
            return validResponse;
        }

        user.setRole(Const.Role.ROLE_CUSTOMER);
        //MD5加密
        user.setPassword(MD5Util.MD5EncodeUtf8(user.getPassword()));

        int resultCount = userMapper.insert(user);
        if(resultCount == 1){
            return ServerResponse.createBySuccessMessage("注册成功");
        }
        return ServerResponse.createByError("注册失败");
    }

    public ServerResponse<String> checkValid(String str, String type) {
        if (StringUtils.isNotBlank(type)) {
            if(Const.USERNAME.equals(type)){
                int resultCount = userMapper.checkUsername(str);
                if(resultCount > 0 ){
                    return ServerResponse.createByError("用户名已存在");
                }
            }
            if(Const.EMAIL.equals(type)){
                int resultCount = userMapper.checkEmail(str);
                if(resultCount > 0 ){
                    return ServerResponse.createByError("email已存在");
                }
            }
        }
        else {
            return ServerResponse.createByError("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }

    public ServerResponse<String> selectQuestion(String username){
        ServerResponse<String> validResponse = this.checkValid(username,Const.USERNAME);
        if(validResponse.isSuccess()){
            //用户不存在
            return ServerResponse.createByError("用户不存在");
        }
        String question = userMapper.selectQuestionByUsername(username);
        if(org.apache.commons.lang3.StringUtils.isNotBlank(question)){
            return ServerResponse.createBySuccess(question);
        }
        return ServerResponse.createByError("找回密码的问题是空的");
    }

    public ServerResponse<String> checkAnswer(String username,String question,String answer){
        int resultCount = userMapper.checkAnswer(username,question,answer);
        if(resultCount>0){
            //说明问题及问题答案是这个用户的,并且是正确的
            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+username,forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByError("问题的答案错误");
    }

    // 重置密码
    public ServerResponse<String> forgetResetPassword(String username,String passwordNew,String forgetToken){
        if(org.apache.commons.lang3.StringUtils.isBlank(forgetToken)){
            return ServerResponse.createByError("参数错误,token需要传递");
        }
        ServerResponse<String> validResponse = this.checkValid(username,Const.USERNAME);
        if(validResponse.isSuccess()){
            //用户不存在
            return ServerResponse.createByError("用户不存在");
        }
        // 判断token
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX+username);
        if(org.apache.commons.lang3.StringUtils.isBlank(token)){
            return ServerResponse.createByError("token无效或者过期");
        }

        if(org.apache.commons.lang3.StringUtils.equals(forgetToken,token)){
            String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
            int rowCount = userMapper.updatePasswordByUsername(username, md5Password);

            if(rowCount > 0){
                return ServerResponse.createBySuccess("修改密码成功", "修改密码成功");
            }
        }
        else {
            return ServerResponse.createByError("token错误,请重新获取重置密码的token");
        }
        return ServerResponse.createByError("修改密码失败");
    }

    // 登录时的重置密码
    public ServerResponse<User> resetPassword(ResetPasswordDTO resetPasswordDTO) {
        //防止横向越权,要校验一下这个用户的旧密码,一定要指定是这个用户.因为我们会查询一个count(1),如果不指定id,那么结果就是true啦count>0;
//        int resultCount = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld), user.getId());
//        if(resultCount == 0){
//            return ServerResponse.createByError("旧密码错误");
//        }
//
//        user.setPassword(MD5Util.MD5EncodeUtf8(passwordNew));
//        int updateCount = userMapper.updateByPrimaryKeySelective(user);
//        if(updateCount > 0){
//            return ServerResponse.createBySuccessMessage("密码更新成功");
//        }
//        return ServerResponse.createByError("密码更新失败");

        String username = (String) request.getAttribute("username");
        User user = userMapper.selectByUsername(username);
        int resultCount = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(resetPasswordDTO.getPasswordOld()), user.getId());

        if(resultCount == 0){
            return ServerResponse.createByError("旧密码错误");
        }

        user.setPassword(MD5Util.MD5EncodeUtf8(resetPasswordDTO.getPasswordNew()));
        int updateCount = userMapper.updateByPrimaryKeySelective(user);
        if(updateCount > 0){
            return ServerResponse.createBySuccess("密码更新成功", user);
        }
        return ServerResponse.createByError("密码更新失败");
    }

    // 更新用户信息
    // username不能被更新
    // 校验新的email
    public ServerResponse<User> updateInformation(UpdateInformationDTO updateInformationDTO) {
//        int resultCount = userMapper.checkEmailByUserId(user.getEmail(),user.getId());
//        if(resultCount > 0){
//            return ServerResponse.createByError("email已存在,请更换email再尝试更新");
//        }
//        User updateUser = new User();
//        updateUser.setId(user.getId());
//        updateUser.setEmail(user.getEmail());
//        updateUser.setPhone(user.getPhone());
//        updateUser.setQuestion(user.getQuestion());
//        updateUser.setAnswer(user.getAnswer());
//
//        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
//        if(updateCount > 0){
//            return ServerResponse.createBySuccess("更新个人信息成功",updateUser);
//        }

        String username = (String) request.getAttribute("username");
        User user = userMapper.selectByUsername(username);
        int resultCount = userMapper.checkEmailByUserId(updateInformationDTO.getEmail(),user.getId());
        if(resultCount > 0){
            return ServerResponse.createByError("email已存在,请更换email再尝试更新");
        }
        User updateUser = new User();
        updateUser.setId(user.getId());
        updateUser.setEmail(updateInformationDTO.getEmail());
        updateUser.setPhone(updateInformationDTO.getPhone());
        updateUser.setQuestion(updateInformationDTO.getQuestion());
        updateUser.setAnswer(updateInformationDTO.getAnswer());

        int updateCount = userMapper.updateByPrimaryKeySelective(updateUser);
        if(updateCount > 0){
            return ServerResponse.createBySuccess("更新个人信息成功",updateUser);
        }
        return ServerResponse.createByError("更新个人信息失败");
    }

    public ServerResponse<User> getInfomation(int userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        if(user == null){
            return ServerResponse.createByError("找不到当前用户");
        }
        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);
    }

    public ServerResponse<User> getUserInfo() {
        String username = (String) request.getAttribute("username");
        User user = userMapper.selectByUsername(username);

        if(user == null){
            return ServerResponse.createByError("找不到当前用户");
        }
        user.setPassword(org.apache.commons.lang3.StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);
    }
}
