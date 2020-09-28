package com.sumaojin.suzhenping.hrms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sumaojin.suzhenping.hrms.entity.Menu;
import com.sumaojin.suzhenping.hrms.mapper.EmployeeMapper;
import com.sumaojin.suzhenping.hrms.mapper.MenuMapper;
import com.sumaojin.suzhenping.hrms.result.CommonResult;
import com.sumaojin.suzhenping.hrms.service.IEmployeeService;
import com.sumaojin.suzhenping.hrms.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author SMJ
 * @since 2020-09-25
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

    @Resource
    private EmployeeMapper employeeMapper;
    /**
     * 根据权限查询菜单
     * @param request
     * @return
     */
    @Override
    public CommonResult<List<Menu>> findAll(HttpServletRequest request) {
        Integer userId = Integer.parseInt((String) request.getSession().getAttribute("userId"));
        Integer resource = employeeMapper.getResource(userId);
        List<Menu> menus = list(new QueryWrapper<Menu>().le("permission", resource));
        return new CommonResult<List<Menu>>(menus);
    }
}
