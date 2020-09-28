package com.sumaojin.suzhenping.hrms.service;

import com.sumaojin.suzhenping.hrms.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sumaojin.suzhenping.hrms.result.CommonResult;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author SMJ
 * @since 2020-09-25
 */
public interface IMenuService extends IService<Menu> {

    CommonResult<List<Menu>> findAll(HttpServletRequest request);
}
