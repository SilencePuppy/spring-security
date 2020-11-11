package com.lxb;

import com.lxb.bo.UserDelegator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 李晓冰
 * @date 2020年11月10日
 */
public class RedisCache {
    private static Map<String, UserDelegator> cacheUserInfo = new HashMap<>();

    private static Map<String, List<String>> cacheUserTokenMap = new HashMap<>();

    public static void putUserInfo(String token, UserDelegator userDelegator) {
        cacheUserInfo.put(token, userDelegator);
        List<String> list = cacheUserTokenMap.computeIfAbsent(userDelegator.getUser().getUsername(), k -> new ArrayList<>());
        list.add(token);
    }

    public static UserDelegator getUserInfo(String token) {
        return cacheUserInfo.get(token);
    }

    public static void kickOutUser(String username) {
        List<String> tokens = cacheUserTokenMap.get(username);
        for (String token : tokens) {
            cacheUserInfo.remove(token);
        }
        cacheUserTokenMap.remove(username);
    }
}
