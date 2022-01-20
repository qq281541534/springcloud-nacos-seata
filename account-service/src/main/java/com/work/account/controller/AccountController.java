package com.work.account.controller;

import com.work.account.service.AccountService;
import com.work.common.apiinterface.AccountInterfaceAPI;
import com.work.common.component.RespBody;
import com.work.common.dto.AccountDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


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
