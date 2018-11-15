package com.okkristen.project.common.Base;

import com.okkristen.project.common.dto.BaseDTO;
import com.okkristen.project.core.msg.AjaxResult;
import com.okkristen.project.core.msg.MessageCode;
import com.okkristen.project.core.page.PageParam;
import com.okkristen.project.core.service.CommonService;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 *基础Controller
 * @param <E>
 */
public class BaseController<E> {

    /**
     * 查询符合条件的所有数据
     * @param service
     * @param queryParam
     * @param <S>
     * @return
     */
    public <S extends CommonService> AjaxResult findAll(S service, PageParam<E> queryParam) {
        return findAll(service, queryParam, null);
    }

    /**
     * 查询符合条件的所有数据,并转换为指定DTO
     * @param service
     * @param queryParam
     * @param <S>
     * @return
     */
    public <S extends CommonService> AjaxResult findAll(S service, PageParam<E> queryParam, Class dtoClass) {
        try {
            if (dtoClass == null) {
                return AjaxResult.createSuccessResult(service.findListByDTO(queryParam.getParam(), queryParam.getPageable().getSort()));
            }
            return AjaxResult.createSuccessResult(service.findListByDTO(queryParam.getParam(), queryParam.getPageable().getSort(), dtoClass));
        } catch (Exception e) {
            return AjaxResult.createErrorResult(MessageCode.QUERY_FAILED, e);
        }
    }

    /**
     * 查询符合条件的所有数据
     * @param service
     * @param queryParam
     * @param <S>
     * @return
     */
    public <S extends CommonService> AjaxResult findPage(S service, PageParam<E> queryParam) {
        return findPage(service, queryParam, null);
    }

    /**
     * 查询符合条件的所有数据,并转换为指定DTO
     * @param service
     * @param queryParam
     * @param <S>
     * @return
     */
    public <S extends CommonService> AjaxResult findPage(S service, PageParam<E> queryParam, Class dtoClass) {
        try {
            if (dtoClass == null) {
                return AjaxResult.createSuccessResult(service.findPageByDTO(queryParam.getParam(), queryParam.getPageable()));
            }
            return AjaxResult.createSuccessResult(service.findPageByDTO(queryParam.getParam(), queryParam.getPageable(), dtoClass));
        } catch (Exception e) {
            return AjaxResult.createErrorResult(MessageCode.QUERY_FAILED, e);
        }
    }

    /**
     * 根据ID查询数据
     * @param service
     * @param id
     * @param <S>
     * @return
     */
    public <S extends CommonService> AjaxResult findOne(S service, String id) {
        return findOne(service, id, null);
    }

    /**
     * 根据ID查询数据,并转换为指定DTO
     * @param service
     * @param id
     * @param <S>
     * @return
     */
    public <S extends CommonService> AjaxResult findOne(S service, String id, Class dtoClass) {
        try {
            if (!StringUtils.isEmpty(id)) {
                return AjaxResult.createErrorResult(MessageCode.QUERY_FAILED, "数据ID不能为空");
            }
            if (dtoClass == null) {
                return AjaxResult.createSuccessResult(service.findDTOById(id));
            }
            return AjaxResult.createSuccessResult(service.findDTOById(id, dtoClass));
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.createErrorResult(MessageCode.QUERY_FAILED, e);
        }
    }

    /**
     * 保存数据
     * @param service
     * @param dto
     * @return
     */
    public <S extends CommonService, D extends BaseDTO> AjaxResult save(S service, D dto) {
        try {
            if (dto == null) {
                return AjaxResult.createErrorResult(MessageCode.SAVE_FAILED, "数据内容不能为空");
            }
            return AjaxResult.createSuccessResult(service.saveByDTO(dto));
        } catch (Exception e) {
            return AjaxResult.createErrorResult(MessageCode.SAVE_FAILED, e);
        }
    }

    /**
     * 更新数据
     * @param service
     * @param dto
     * @return
     */
    public <S extends CommonService, D extends BaseDTO> AjaxResult update(S service, D dto) {
        try {
            if (dto == null || StringUtils.isEmpty(dto.getId())) {
                return AjaxResult.createErrorResult(MessageCode.UPDATE_FAILED, "数据内容不能为空");
            }
            dto = (D) service.updateByDTO(dto);//service.updateByDTO(dto, dto.getClass());
            if (dto != null) {
                return AjaxResult.createSuccessResultWithCode(MessageCode.UPDATE_SUCCESS);
            } else {
                return AjaxResult.createErrorResult(MessageCode.UPDATE_FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.createErrorResult(MessageCode.UPDATE_FAILED, e);
        }
    }

    /**
     * 删除数据
     * @param service
     * @param ids 待删除数据ID数组
     * @return
     */
    public <S extends CommonService> AjaxResult delete(S service, List<String> ids) {
        try {
            if (ids == null || ids.size() == 0) {
                return AjaxResult.createErrorResult(MessageCode.DELETE_FAILED, "数据内容不能为空");
            }
            int i = service.deleteByIdList(ids);
            if (i > 0) {
                return AjaxResult.createSuccessResultWithCode(MessageCode.DELETE_SUCCESS);
            } else {
                return AjaxResult.createErrorResult(MessageCode.DELETE_FAILED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.createErrorResult(MessageCode.QUERY_FAILED,e);
        }
    }
}
