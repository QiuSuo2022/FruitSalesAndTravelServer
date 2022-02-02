package com.guet.qiusuo.fruittravel.service;

import com.guet.qiusuo.fruittravel.bean.vo.UserRoleVO;
import com.guet.qiusuo.fruittravel.bean.vo.UserVO;
import com.guet.qiusuo.fruittravel.common.SysRole;
import com.guet.qiusuo.fruittravel.common.SystemConstants;
import com.guet.qiusuo.fruittravel.config.ErrorCode;
import com.guet.qiusuo.fruittravel.config.SystemException;
import com.guet.qiusuo.fruittravel.dao.RoleDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.UserDynamicSqlSupport;
import com.guet.qiusuo.fruittravel.dao.UserMapper;
import com.guet.qiusuo.fruittravel.model.User;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.mybatis.dynamic.sql.render.RenderingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mybatis.dynamic.sql.SqlBuilder.*;

/**
 * @author libuyan
 * @date 2022/1/29 18:34
 */
@Service
public class UserService {
    private UserMapper userMapper;

    private RoleService roleService;

    @Autowired
    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    public UserVO login(String code) {
        String openId = getOpenId(code);
        List<UserVO> userList = getUserByOpenId(openId);
        if (userList.size() == 0) {
            register(openId);
            userList = getUserByOpenId(openId);
        }
        if (userList.size() == 0) {
            throw new SystemException(ErrorCode.LOGIN_EXCEPTION);
        }
        UserVO loginUser = userList.get(0);
        if (loginUser == null) {
            throw new SystemException(ErrorCode.LOGIN_EXCEPTION);
        }
        loginUser.setToken(UUID.randomUUID().toString().replaceAll("-", ""));
        loginUser.setUpdateTime(System.currentTimeMillis());
        userMapper.updateByPrimaryKeySelective(loginUser);
        return loginUser;
    }

    private void register(String openId) {
        User user = new User();
        user.setId(UUID.randomUUID().toString());
        user.setOpenid(openId);
        user.setStatus(SystemConstants.USER_INFO_INCOMPLETE);
        long now = System.currentTimeMillis();
        user.setRoleId(SysRole.USER);
        user.setCreateTime(now);
        user.setUpdateTime(now);
        int i = userMapper.insert(user);
        if (i == 0) {
            throw new SystemException(ErrorCode.INSERT_ERROR);
        }
    }

    private List<UserVO> getUserByOpenId(String openId) {
        return userMapper.selectUserVOs(select(
                UserDynamicSqlSupport.id,
                UserDynamicSqlSupport.userName,
                UserDynamicSqlSupport.realName,
                UserDynamicSqlSupport.token,
                UserDynamicSqlSupport.phone,
                UserDynamicSqlSupport.gender,
                UserDynamicSqlSupport.avatarUrl,
                UserDynamicSqlSupport.openid,
                UserDynamicSqlSupport.roleId,
                UserDynamicSqlSupport.status,
                UserDynamicSqlSupport.createTime,
                UserDynamicSqlSupport.updateTime,
                UserDynamicSqlSupport.createUserId,
                UserDynamicSqlSupport.updateUserId,
                RoleDynamicSqlSupport.name.as("roleName")
        )
                .from(UserDynamicSqlSupport.user)
                .leftJoin(RoleDynamicSqlSupport.role)
                .on(UserDynamicSqlSupport.roleId, equalTo(RoleDynamicSqlSupport.id))
                .where(UserDynamicSqlSupport.openid, isEqualTo(openId))
                .build().render(RenderingStrategies.MYBATIS3));
    }

    private String getOpenId(String code) {
        String open_id;
        HttpClient httpClient = HttpClients.createDefault();
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wxcd0f34d6f1e3d032&secret" +
                "=511fb18c98bc1c30bfb6f6a25ec4ac82&js_code=" + code + "&grant_type=authorization_code";
        try {
            URIBuilder uriBuilder = new URIBuilder(url);
            HttpGet httpGet = new HttpGet(uriBuilder.build());
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(1200).setSocketTimeout(1200).build();
            httpGet.setConfig(requestConfig);
            HttpResponse httpResponse;
            httpResponse = httpClient.execute(httpGet);
            if (httpResponse != null && httpResponse.getStatusLine() != null) {
                String content;
                if (httpResponse.getEntity() != null) {
                    content = EntityUtils.toString(httpResponse.getEntity(), "utf-8");
                    JSONObject json = new JSONObject(content);
                    open_id = (String) json.get("openid");
                    return open_id;
                }
            }
        } catch (URISyntaxException | IOException e) {
            throw new SystemException(ErrorCode.LOGIN_EXCEPTION);
        }
        throw new SystemException(ErrorCode.LOGIN_EXCEPTION);
    }

    public UserVO getUserInfoByToken(String token) {
        if (token == null || "".equals(token)) {
            throw new SystemException(ErrorCode.TOKEN_ERROR);
        }
        List<User> userList = userMapper.select(u ->
                u.where(UserDynamicSqlSupport.token, isEqualTo(token)));
        if (userList.isEmpty()) {
            return null;
        }
        User user = userList.get(0);
        UserVO userVO = new UserVO();
        userVO.setId(user.getId());
        userVO.setUserName(user.getUserName());
        userVO.setRealName(user.getRealName());
        userVO.setGender(user.getGender());
        userVO.setPhone(user.getPhone());
        userVO.setEmail(user.getEmail());
        userVO.setIdCard(user.getIdCard());
        userVO.setAvatarUrl(user.getAvatarUrl());
        userVO.setToken(user.getToken());
        userVO.setOpenid(user.getOpenid());
        userVO.setRoleId(user.getRoleId());
        userVO.setStatus(user.getStatus());
        userVO.setCreateTime(user.getCreateTime());
        userVO.setUpdateTime(user.getUpdateTime());
        userVO.setCreateUserId(user.getCreateUserId());
        userVO.setUpdateUserId(user.getUpdateUserId());

        List<UserRoleVO> userRoleVOS = roleService.selectUserRoleByUserId(userList.get(0).getId());
        if (userRoleVOS.size() == 0) {
            throw new SystemException(ErrorCode.USER_NO_ROLE);
        }
        userVO.setRoleName(userRoleVOS.get(0).getRoleName());
        return userVO;
    }


    /**
     * 添加用户
     *
     * @param user
     */
    public void addUser(User user) {
        user.setId(UUID.randomUUID().toString());
        long now = System.currentTimeMillis();
        user.setCreateTime(now);
        user.setUpdateTime(now);
        user.setRoleId(SysRole.USER);
        user.setStatus(SystemConstants.USER_INFO_NOT_COMPLETE);
        userMapper.insertSelective(user);
        System.out.println("创建用户成功:" + user.toString());
    }

    /**
     * 修改用户
     *
     * @param user
     */
    public void updateUser(User user) {
        if (user.getId() == null || user.getId().trim().length() == 0) {
            throw new SystemException(ErrorCode.ID_NOT_EXIST_ERROR);
        }
        List<User> userList = userMapper.select(u ->
                u.where(UserDynamicSqlSupport.id, isEqualTo(user.getId()))
                        .and(UserDynamicSqlSupport.status, isNotEqualTo(SystemConstants.STATUS_NEGATIVE)));
        if (userList.size() == 0) {
            throw new SystemException(ErrorCode.ID_NOT_EXIST_ERROR);
        }
        user.setUpdateTime(System.currentTimeMillis());
        //如果没有进行逻辑删除 则将用户信息设置成完整
        if (user.getStatus() != 0) {
            user.setStatus(SystemConstants.USER_INFO_COMPLETE);
        }
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 删除用户(逻辑删除)
     *
     * @param userId
     */
    public void deleteUser(String userId) {
        Optional<User> optionalUser = userMapper.selectByPrimaryKey(userId);
        User user = optionalUser.orElseThrow(() -> new SystemException(ErrorCode.USER_NOT_FOUND));
        user.setStatus(SystemConstants.STATUS_NEGATIVE);
        user.setUpdateTime(System.currentTimeMillis());
        userMapper.updateByPrimaryKeySelective(user);
    }

    /**
     * 修改用户角色
     *
     * @param userId
     * @param roleId
     */
    public void updateUserRole(String userId, String roleId) {
        //鉴权，不是管理员，不能使用此功能
/*        String userRoleId = UserContextHolder.getUser().getRoleId();
        if (!userRoleId.equals("1")) {
            throw new SystemException(ErrorCode.NO_PERMISSION);
        }*/
        Optional<User> optionalUser = userMapper.selectByPrimaryKey(userId);
        User user = optionalUser.orElseThrow(() -> new SystemException(ErrorCode.USER_NOT_FOUND));
        user.setRoleId(roleId);
        user.setUpdateTime(System.currentTimeMillis());
        userMapper.updateByPrimaryKeySelective(user);
    }
}
