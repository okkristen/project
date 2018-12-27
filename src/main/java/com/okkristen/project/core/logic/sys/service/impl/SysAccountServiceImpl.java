package com.okkristen.project.core.logic.sys.service.impl;

import com.okkristen.project.common.dto.LoginAccountDTO;
import com.okkristen.project.core.service.impl.CommonServiceImpl;
import com.okkristen.project.core.logic.sys.dao.SysAccountRepository;
import com.okkristen.project.core.logic.sys.dto.SysAccountDTO;
import com.okkristen.project.core.logic.sys.entity.SysAccount;
import com.okkristen.project.core.logic.sys.service.SysAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 区级考核项信息ServiceImpl
 *
 * @author zpy
 * @create 2018/5/4
 */
@Service
public class SysAccountServiceImpl extends CommonServiceImpl<SysAccount, SysAccountDTO> implements SysAccountService {
    @Autowired
    SysAccountRepository sysAccountRepository;

    @Override
    public LoginAccountDTO findByPasswordAndUserName(String password, String username) {
        return getTargetDTO(sysAccountRepository.findByPasswordAndUsername(password,username), LoginAccountDTO.class);
    }

    @Override
    public SysAccountDTO findByUserName(String username) {
        List<SysAccount> list = sysAccountRepository.findByUsername(username);
        if (list == null || list.isEmpty()) {
            return  new SysAccountDTO();
        } else {
            return  getDTO(list.get(0));
        }
    }
}
