package org.hulei.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.hulei.reggie.common.R;
import org.hulei.reggie.dto.SetmealDto;
import org.hulei.reggie.entity.Category;
import org.hulei.reggie.entity.Setmeal;
import org.hulei.reggie.service.CategoryService;
import org.hulei.reggie.service.SetmealDishService;
import org.hulei.reggie.service.SetmealService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Mr.Hu
 * @create 2022/10/24 10:52
 */
@RestController
@Slf4j
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private SetmealDishService setmealDishService;

    @Autowired
    private SetmealService setmealService;

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public R<String> save(@RequestBody SetmealDto setmealDto){
        log.info("套餐信息：{}",setmealDto);
        setmealService.saveWithDish(setmealDto);
        return R.success("套餐新增成功！");
    }

    /**
     * 分页查询套餐信息
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> page(int page,int pageSize,String name){
//        分页构造器对象
        Page<Setmeal> pageinfo = new Page<>(page,pageSize);
        Page<SetmealDto> dtoPage = new Page<>();

        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
//        添加查询条件，根据name进行like模糊查询
        queryWrapper.like(name != null,Setmeal::getName,name);
//        添加排序条件，根据更新时间进行降序排序
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);

        setmealService.page(pageinfo,queryWrapper);
//        对象拷贝
        BeanUtils.copyProperties(pageinfo,dtoPage,"records");
        List<Setmeal> records = pageinfo.getRecords();
        List<SetmealDto> list  = records.stream().map((item) -> {
            SetmealDto setmealDto = new SetmealDto();
            BeanUtils.copyProperties(item,setmealDto);
//            分类id
            Long categoryId = item.getCategoryId();
//            分类名称
            Category category = categoryService.getById(categoryId);

            if(category != null){
                String categoryName = category.getName();
                setmealDto.setCategoryName(categoryName);
            }
            return setmealDto;
        }).collect(Collectors.toList());
        dtoPage.setRecords(list);
        return R.success(dtoPage);
    }

    /**
     * 根据id删除套餐
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam List<Long> id){
        setmealService.deleteWithDish(id);
        return R.success("套餐删除成功");

    }

    @PostMapping("/status/{status}")
    public R<String> sale(@PathVariable int status,String[] id){
        for (String ids:id){
            Setmeal setmeal = setmealService.getById(ids);
            setmeal.setStatus(status);
            setmealService.updateById(setmeal);
        }
        return R.success("修改成功");
    }
}
