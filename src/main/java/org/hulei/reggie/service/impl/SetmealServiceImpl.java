package org.hulei.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.hulei.reggie.common.CustomException;
import org.hulei.reggie.dto.SetmealDto;
import org.hulei.reggie.entity.Setmeal;
import org.hulei.reggie.entity.SetmealDish;
import org.hulei.reggie.mapper.SetmealMapper;
import org.hulei.reggie.service.SetmealService;
import org.hulei.reggie.service.SetmealDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mr.Hu
 * @create 2022/10/24 8:48
 */
@Service("SetmealService")
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal> implements SetmealService {
    @Autowired
    private SetmealDishService setmealDishService;
    /**
     * 新增套餐，同时需要保存套餐和菜品的关系
     * @param setmealDto
     */
    @Transactional
    public void saveWithDish(SetmealDto setmealDto){
//        保存套餐的基本信息，操作setmeal，执行insert操作
        this.save(setmealDto);
//
        List<SetmealDish> setmealDishes = setmealDto.getSetmealDishes();
        setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDto.getId());
            return item;
        }).collect(Collectors.toList());

//        保存套餐和菜品的关联信息，操作setmeal_dish，执行insert操作
        setmealDishService.saveBatch(setmealDishes);
    }

    /**
     * 删除套餐，同时删除套餐和菜的关联数据
     * @param id
     */
    @Transactional
    public void deleteWithDish(List<Long> id) {
//        查询套餐状态，确定是否可以删除
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.in(Setmeal::getId,id);
        queryWrapper.eq(Setmeal::getStatus,1);
        Long count = this.count(queryWrapper);
        if(count > 0){
//            如果不能删除，抛出一个业务异常
            throw new CustomException("套餐正在售卖中，不能删除");
        }

//        如果可以删除，先删除套餐表中的数据--setmeal
        this.removeByIds(id);

        LambdaQueryWrapper<SetmealDish> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        lambdaQueryWrapper.in(SetmealDish::getSetmealId,id);
//        删除关系表中的数据--setmeal_dish
        setmealDishService.remove(lambdaQueryWrapper);
    }

}
