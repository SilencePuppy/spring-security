package com.lxb.controller;

import com.lxb.entity.OaAccount;
import com.lxb.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 李晓冰
 * @date 2020年11月09日
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping("/selectAll")
    public List<OaAccount> selectAll(){
        return accountService.selectAll();
    }

}
