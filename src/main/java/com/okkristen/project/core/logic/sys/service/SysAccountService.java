package com.okkristen.project.core.logic.sys.service;

import com.okkristen.project.common.dto.LoginAccountDTO;
import com.okkristen.project.core.service.CommonService;
import com.okkristen.project.core.logic.sys.dto.SysAccountDTO;
import com.okkristen.project.core.logic.sys.entity.SysAccount;
import com.okkristen.project.logic.test.dto.ExamineDistrictDTO;
import com.okkristen.project.logic.test.entity.ExamineDistrict;

/**
 *用户Service
 */
public interface SysAccountService extends CommonService<SysAccount, SysAccountDTO> {
    LoginAccountDTO findByPasswordAndUserName(String password, String username);

    SysAccountDTO findByUserName(String username);
}
