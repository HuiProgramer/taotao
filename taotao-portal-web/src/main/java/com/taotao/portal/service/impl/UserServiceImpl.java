package com.taotao.portal.service.impl;

import com.taotao.common.util.CookieUtils;
import com.taotao.pojo.TbUser;
import com.taotao.portal.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.HttpClientUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserServiceImpl implements UserService {

    @Override
    public TbUser getUserByToken(HttpServletRequest request, HttpServletResponse response) {
        //从cookie中取token
        String token = CookieUtils.getCookieValue(request,"TT_TOKEN");
        //判断token是否有值
        if(StringUtils.isBlank(token))
            return null;
        //调用SSO的服务查询系统查询用户信息

        return null;
    }
}
