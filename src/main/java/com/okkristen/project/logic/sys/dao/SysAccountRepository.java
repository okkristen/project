package com.okkristen.project.logic.sys.dao;

import com.okkristen.project.core.repository.MyRepository;
import com.okkristen.project.logic.sys.entity.SysAccount;
import com.okkristen.project.logic.test.entity.ExamineDistrict;

import java.util.List;

/**
 * 用户信息Repository
 */
public interface SysAccountRepository extends MyRepository<SysAccount, String> {
    /**
     * 根据密码账户 查询用户
     * @param password
     * @param username
     * @return
     */
    SysAccount findByPasswordAndUsername(String password,String username);

    /**
     * 根据用户名 查看用户
     * @param username
     * @return
     */
    List<SysAccount> findByUsername(String username);
}
