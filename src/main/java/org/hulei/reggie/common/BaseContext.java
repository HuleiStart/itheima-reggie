package org.hulei.reggie.common;

/**
 * @author Mr.Hu
 * @create 2022/10/23 9:48
 */
//寄予ThreadLocal封装工具类，用于保存和获取当前登录用户id
public class BaseContext{
    private static ThreadLocal<Long> threadLocal = new ThreadLocal<>();

    /**
     * 设置值
     * @param id
     */
    public static void setCurrentId(Long id){
        threadLocal.set(id);
    }

    /**
     * 获取值
     * @return
     */
    public static Long getCurrentId(){
        return threadLocal.get();
    }
}
