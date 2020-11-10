package com.lxb;

import com.lxb.bo.UserDelegator;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 李晓冰
 * @date 2020年11月10日
 */
public class RedisCache {
    private static Map<String, UserDelegator> cacheUserInfo = new HashMap<>();

    private static Map<String, String> cacheUserTokenMap = new HashMap<>();

    public static void putUserInfo(String token, UserDelegator userDelegator) {
        cacheUserInfo.put(token, userDelegator);
        cacheUserTokenMap.put(userDelegator.getUser().getUsername(), userDelegator.getDetail().getToken());
    }

    public static UserDelegator getUserInfo(String token) {
        return cacheUserInfo.get(token);
    }

    public static String getUserToken(String username){
        return cacheUserTokenMap.get(username);
    }

    public static void kickOutUser(String username,String newToken) {
        String token = cacheUserTokenMap.get(username);
         cacheUserInfo.remove(token);
    }
}
