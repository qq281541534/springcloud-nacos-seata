package com.work.account.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.work.account.model.Account;
import com.work.account.repository.AccountMapper;
import com.work.common.component.RespBody;
import com.work.common.component.RespCode;
import com.work.common.dto.AccountDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
/**
 * @author liuyu
 * @Description 账户服务类
 * @createTime 2022/1/13 23:05
 */
@Service
@Transactional
public class AccountService extends ServiceImpl<AccountMapper, Account> {

    /**
     * @title 创建账单
     * @description 
     * @author liuyu 
     * @updateTime 2022/1/13 23:06
     * @throws
     */
    public RespBody decreaseAccount(AccountDTO accountDTO) {
        int account = baseMapper.decreaseAccount(accountDTO.getUserId(), accountDTO.getAmount().doubleValue());
        if (account > 0) {
            return new RespBody<>(RespCode.SUCCESS);
        }
        return new RespBody<>(RespCode.FAIL);
    }

}
