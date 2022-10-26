package org.hulei.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.hulei.reggie.entity.Employee;

/**
 * @author Mr.Hu
 * @create 2022/10/21 14:40
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
