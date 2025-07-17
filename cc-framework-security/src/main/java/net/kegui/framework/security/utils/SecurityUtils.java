package net.kegui.framework.security.utils;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.stp.StpUtil;
import net.kegui.framework.security.domain.LoginUser;
import net.kegui.framework.security.enums.DeviceType;

public class SecurityUtils {

    public static final String LOGIN_USER_KEY = "loginUser";

    public static void loginByDevice(LoginUser loginUser, DeviceType deviceType) {
        SaHolder.getStorage().set(LOGIN_USER_KEY, loginUser);
        StpUtil.login(loginUser.getUserId(), deviceType.name());
        setLoginUser(loginUser);
    }

    /**
     * 设置用户数据(多级缓存)
     *
     * @param loginUser LoginUser
     */
    public static void setLoginUser(LoginUser loginUser) {
        StpUtil.getTokenSession().set(LOGIN_USER_KEY, loginUser);
    }

    /**
     * 获取用户(多级缓存)
     *
     * @return LoginUser
     */
    public static LoginUser getLoginUser() {
        LoginUser loginUser = (LoginUser) SaHolder.getStorage().get(LOGIN_USER_KEY);
        if (loginUser != null) {
            return loginUser;
        }
        loginUser = (LoginUser) StpUtil.getTokenSession().get(LOGIN_USER_KEY);
        SaHolder.getStorage().set(LOGIN_USER_KEY, loginUser);
        return loginUser;
    }
}