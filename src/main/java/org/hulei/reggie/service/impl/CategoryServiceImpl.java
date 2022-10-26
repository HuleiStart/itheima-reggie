package org.hulei.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.hulei.reggie.common.CustomException;
import org.hulei.reggie.entity.Category;
import org.hulei.reggie.entity.Dish;
import org.hulei.reggie.entity.Setmeal;
import org.hulei.reggie.mapper.CategoryMapper;
import org.hulei.reggie.service.SetmealService;
import org.hulei.reggie.service.CategoryService;
import org.hulei.reggie.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mr.Hu
 * @create 2022/10/23 10:13
 */
@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper,Category> implements CategoryService {
    @Autowired
    private DishService dishService;
    @Autowired
    private SetmealService setmealService;

    public CategoryServiceImpl() {
    }
    /**
     * 根据id删除分类，删除之前需要进行判断
     * @param id
     */
    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
//        添加查询条件
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        long count = dishService.count(dishLambdaQueryWrapper);
//        查询当前分类是否关联了菜品，如果已经关联则抛出业务异常
        if(count > 0){
            throw new CustomException("当前分类关联了菜品，不能删除！");
        }
//        查询当前分类是否关联了套餐，如果已经关联则抛出业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        long count1 = setmealService.count(setmealLambdaQueryWrapper);
        if(count1 > 0){
            throw new CustomException("当前分类关联了套餐，不能删除！");
        }
//        正常删除分类
        super.removeById(id);
    }
}
