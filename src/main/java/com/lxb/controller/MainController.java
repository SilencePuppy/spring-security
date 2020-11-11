package com.lxb.controller;

import com.lxb.RedisCache;
import com.lxb.entity.OaAccount;
import com.lxb.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

/**
 * @author 李晓冰
 * @date 2020年11月09日
 */
@RestController
public class MainController {

    @Autowired
    AccountService accountService;

    @RequestMapping("/kick/out/{userName}")
    public void kickOut(@PathVariable("userName") String userName) {
        RedisCache.kickOutUser(userName);
    }

    @RequestMapping("/account/look/list")
    public List<OaAccount> accountList() {
        return accountService.selectAll();
    }

    @RequestMapping("/account/edit/update")
    public String accountEdit(){
        return "account edit";
    }

    @RequestMapping("/order/look/list")
    public List<String> orderList(){
        return Arrays.asList("order1","order2");
    }

    @RequestMapping("/order/edit/update")
    public String orderEdit(){
        return "order edit";
    }
}
