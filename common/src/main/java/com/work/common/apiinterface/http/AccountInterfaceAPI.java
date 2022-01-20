package com.work.common.apiinterface.http;

import com.work.common.component.RespBody;
import com.work.common.dto.AccountDTO;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 账户接口
 * @author liuyu
 */
public interface AccountInterfaceAPI {

    @PostMapping("/account/decAccount")
    RespBody decreaseAccount(AccountDTO accountDTO);
}
