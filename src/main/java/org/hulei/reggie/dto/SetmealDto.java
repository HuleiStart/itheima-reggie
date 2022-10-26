package org.hulei.reggie.dto;

import lombok.Data;
import org.hulei.reggie.entity.Setmeal;
import org.hulei.reggie.entity.SetmealDish;

import java.util.List;


@Data
public class SetmealDto extends Setmeal {

    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
