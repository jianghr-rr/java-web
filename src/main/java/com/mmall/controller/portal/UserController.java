package com.mmall.controller.portal;

import com.mmall.common.Const;
import com.mmall.common.ServerResponse;
import com.mmall.pojo.User;
import com.mmall.service.IUserService;
import com.mmall.service.dto.user.*;
import com.mmall.util.CookieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user/")
public class UserController {

    @Autowired
    private IUserService iUserService;

    /**
     * 用户登录
     * @param loginRequestDTO
     * @param session
     */
    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody // 自动序列化成JSON
    public ServerResponse<User> login(@RequestBody LoginRequestDTO loginRequestDTO, HttpSession session) {
        // service -> mybatis -> dao
        ServerResponse<User> response = iUserService.login(loginRequestDTO.getUsername(), loginRequestDTO.getPassword());
        if (response.isSuccess()) {
            session.setAttribute(Const.CURRENT_USER, response.getData());
        }
        return response;
    }

    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session) {
        session.removeAttribute(Const.CURRENT_USER);
        CookieUtil.clearCookie();
        return ServerResponse.createBySuccess("登出成功", "logout");
    }

    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(@RequestBody RegisterRequestDTO registerRequestDTO) {
        User user = new User();
        user.setUsername(registerRequestDTO.getUsername());
        user.setPassword(registerRequestDTO.getPassword());
        user.setEmail(registerRequestDTO.getEmail());
        user.setPhone(registerRequestDTO.getPhone());
        user.setQuestion(registerRequestDTO.getQuestion());
        user.setAnswer(registerRequestDTO.getAnswer());
        return iUserService.register(user);
    }

    @RequestMapping(value = "check_valid.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkValid(String str, String type) {
        return iUserService.checkValid(str,type);
    }

    // 获取用户信息
    @RequestMapping(value = "get_user_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getUserInfo(){
        return iUserService.getUserInfo();
    }

    // 忘记密码
    @RequestMapping(value = "forget_get_question.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String username){
        return iUserService.selectQuestion(username);
    }

    // 校验密码提示问题
    @RequestMapping(value = "forget_check_answer.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(@RequestBody CheckAnswerDTO checkAnswerDTO){
        return iUserService.checkAnswer(checkAnswerDTO.getUsername(), checkAnswerDTO.getQuestion(), checkAnswerDTO.getAnswer());
    }

    // 忘记密码的重置密码
    @RequestMapping(value = "forget_reset_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(@RequestBody ForgetRestPasswordDTO forgetRestPasswordDTO){
        return iUserService.forgetResetPassword(forgetRestPasswordDTO.getUsername(),forgetRestPasswordDTO.getPassword(),forgetRestPasswordDTO.getForgetToken());
    }

    // 已经登录的重置密码
    @RequestMapping(value = "reset_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> resetPassword(@RequestBody ResetPasswordDTO resetPasswordDTO){
        // ??? 具体的拿用户信息
//        User user = (User)session.getAttribute(Const.CURRENT_USER);
//        if(user == null){
//            return ServerResponse.createByError("用户未登录");
//        }
//        return iUserService.resetPassword(passwordOld,passwordNew,user);
        return iUserService.resetPassword(resetPasswordDTO);
    }

    // 更新用户信息
    @RequestMapping(value = "update_information.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> updateInformation(@RequestBody UpdateInformationDTO updateInformationDTO){
        // ??? 通过session拿到用户信息
//        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
//        if(currentUser == null){
//            return ServerResponse.createByError("用户未登录");
//        }
//        user.setId(currentUser.getId());
//        user.setUsername(currentUser.getUsername());
//        // 更新
//        ServerResponse<User> response = iUserService.updateInformation(user);
//        if (response.isSuccess()) {
//            // ??? 为什么要设置一次username
//            response.getData().setUsername(currentUser.getUsername());
//            session.setAttribute(Const.CURRENT_USER, response.getData());
//        }
//        return response;
        return iUserService.updateInformation(updateInformationDTO);
    }

    // 获取用户信息
    @RequestMapping(value = "get_information.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<User> getInformation(HttpSession session){
        User currentUser = (User)session.getAttribute(Const.CURRENT_USER);
        if (currentUser == null) {
            return ServerResponse.createByError("用户未登录");
        }
        return iUserService.getInfomation(currentUser.getId());
    }
}
