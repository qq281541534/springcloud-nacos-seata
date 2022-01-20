package com.work.account.controller;

import ch.qos.logback.classic.Logger;
import com.work.account.model.Account;
import com.work.account.repository.AccountMapper;
import com.work.account.service.AccountService;
import com.work.common.apiinterface.http.AccountInterfaceAPI;
import com.work.common.component.RespBody;
import com.work.common.dto.AccountDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RestController
@RequestMapping("account")
@RequiredArgsConstructor
public class AccountController implements AccountInterfaceAPI {

    private final AccountService accountService;

    @Override
    @PostMapping("/decAccount")
    public RespBody decreaseAccount(@RequestBody AccountDTO accountDTO) {
        log.info("请求账户微服务：{}", accountDTO.toString());
        return accountService.decreaseAccount(accountDTO);
    }

}
