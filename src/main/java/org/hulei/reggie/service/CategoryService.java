package org.hulei.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.hulei.reggie.entity.Category;

/**
 * @author Mr.Hu
 * @create 2022/10/23 10:16
 */
public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
