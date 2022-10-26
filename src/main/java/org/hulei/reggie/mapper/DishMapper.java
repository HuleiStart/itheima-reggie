package org.hulei.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.hulei.reggie.entity.Dish;

/**
 * @author Mr.Hu
 * @create 2022/10/24 8:40
 */
@Mapper
public interface DishMapper extends BaseMapper<Dish> {
}
