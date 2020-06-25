package com.luobee.home.platform.util;

import com.luobee.home.platform.entity.User;
import com.luobee.home.platform.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

public class JPARealm extends AuthorizingRealm {

    UserService userService;
    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        return authorizationInfo;
    }

    /**
     * 使用JWT替代原生Token
     * @param token
     * @return
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
//        String username = authenticationToken.getPrincipal().toString();
//        User user = userService.getByUsername(username);
//        if(user == null){
//            return null;
//        } else {
//            String passwordInDB = user.getPassword();
//            String salt = user.getSalt();
//            SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(username, passwordInDB, ByteSource.Util.bytes(salt), getName());
//            return authenticationInfo;
//        }

        String token = (String) authenticationToken.getCredentials();
        String username = JWTUtil.getUsername(token);
        User user = userService.getByUsername(username);

        if (user != null) {
            // 密码验证
            if (!JWTUtil.verify(token, username, user.getPassword())) {
                throw new UnknownAccountException();
            }
            return new SimpleAuthenticationInfo(token, token, "realm");
        } else {
            throw new UnknownAccountException();
        }
    }
}
