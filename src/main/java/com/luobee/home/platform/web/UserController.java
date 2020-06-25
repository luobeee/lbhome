package com.luobee.home.platform.web;

import com.luobee.home.platform.entity.User;
import com.luobee.home.platform.service.UserService;
import com.luobee.home.platform.util.Result;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@RestController
public class UserController {

    UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 注册时监听用户名输入框 判断用户名是否存在
     * @param userCheck
     * @return
     */
    @PostMapping("/platform_register_checkUsername")
    public Object checkUsernameIsExist(@RequestBody User userCheck){
        String username =  userCheck.getUsername();
        boolean isExist = userService.isExist(username);
        if(isExist){
            String message ="该用户名已存在";
            return Result.fail(message);
        } else {
            return Result.success();
        }
    }

    /**
     * 用户登录
     * @param userLogin 用户登录信息对象
     * @param session session
     * @return Object
     */
    @PostMapping("/platform_login")
    public Object login(@RequestBody User userLogin, HttpSession session) {
        String username =  userLogin.getUsername();
        //使用HtmlUtils进行转义,使用<script>alert('s')</script> 这样的名称，会导致网页打开就弹出一个对话框，转义之后就不会了
        username = HtmlUtils.htmlEscape(username);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, userLogin.getPassword());
        try {
            subject.login(token);
            User user = userService.getByUsername(username);
            session.setAttribute("user", user);
            return Result.success();
        } catch (AuthenticationException e) {
            String message ="用户名密码错误";
            return Result.fail(message);
        }
    }

    /**
     * 用户注册
     * @param userRegister 用户注册信息对象
     * @return Object
     */
    @PostMapping("/platform_register")
    public Object register(@RequestBody User userRegister){
        String realName = userRegister.getRealName();
        String username = userRegister.getUsername();
        String password = userRegister.getPassword();
        String email = userRegister.getEmail();
        int gender = userRegister.getGender();
        //再对用户名唯一性进行一次判断
        boolean nameIsOnly = userService.isExist(username);
        if(nameIsOnly){
            String message ="用户名已存在";
            return Result.fail(message);
        }
        //获取随机的盐
        String salt = new SecureRandomNumberGenerator().nextBytes().toString();
        //通过随机盐，将密码用md5方式加密2次
        String finalPassword = new SimpleHash("md5", password, salt, 2).toString();
        User user = new User();
        user.setGUID(UUID.randomUUID().toString());
        user.setRealName(realName);
        user.setUsername(username);
        user.setPassword(finalPassword);
        user.setEmail(email);
        user.setGender(gender);
        user.setSalt(salt);
        userService.add(user);
        return Result.success();
    }
}
