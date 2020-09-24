package com.sumaojin.suzhenping.hrms.controller;

import com.sumaojin.suzhenping.hrms.entity.Account;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author SMJ
 * @since 2020-09-17
 */
@RestController
@RequestMapping("/account")
public class AccountController {

    @GetMapping("/login")
    public CommonResult<Account> login(String username, String password){
        Account account = new Account();
        account.setId(4);
        account.setPassword("asdfdasf");
        return new CommonResult(account);
    }

    @GetMapping("/register")
    public String register(){
        return "注册";
    }
}
