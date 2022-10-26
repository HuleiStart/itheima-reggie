package org.hulei.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hulei.reggie.entity.Employee;
import org.hulei.reggie.mapper.EmployeeMapper;
import org.hulei.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * 员工信息(Employee)表服务实现类
 *
 * @author makejava
 * @since 2022-10-21 14:37:51
 */
@Service("employeeService")
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper,Employee> implements EmployeeService{
}
