package org.hulei.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.hulei.reggie.dto.DishDto;
import org.hulei.reggie.entity.Dish;

/**
 * @author Mr.Hu
 * @create 2022/10/24 8:42
 */
public interface DishService extends IService<Dish> {

    public void saveWithFlavor(DishDto dishDto);

    public DishDto getByIdWithFlavor(Long id);

    public void updateWithFlavor(DishDto dishDto);
}
