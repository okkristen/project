package com.okkristen.project.logic.sys.service.impl;

import com.okkristen.project.core.service.impl.CommonServiceImpl;
import com.okkristen.project.logic.sys.dao.SysAccountRepository;
import com.okkristen.project.logic.sys.dto.SysAccountDTO;
import com.okkristen.project.logic.sys.entity.SysAccount;
import com.okkristen.project.logic.sys.service.SysAccountService;
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
    public SysAccountDTO findByPasswordAndUserName(String password, String username) {
        return getDTO(sysAccountRepository.findByPasswordAndUsername(password,username));
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
