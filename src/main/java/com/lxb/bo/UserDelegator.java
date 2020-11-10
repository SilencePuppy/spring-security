package com.lxb.bo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.User;

/**
 * @author 李晓冰
 * @date 2020年11月10日
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDelegator {
    private User user;

    private UserDelegatorDetail detail;

    @Data
    public static class UserDelegatorDetail{
        private String token;
    }
}
