package org.hulei.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.hulei.reggie.dto.SetmealDto;
import org.hulei.reggie.entity.Setmeal;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Mr.Hu
 * @create 2022/10/24 8:44
 */
public interface SetmealService extends IService<Setmeal> {
    /**
     * 新增套餐，同时需要保存套餐和菜品的关联
     * @param setmealDto
     */
    public void saveWithDish(SetmealDto setmealDto);


    public void deleteWithDish(List<Long> id);
}
