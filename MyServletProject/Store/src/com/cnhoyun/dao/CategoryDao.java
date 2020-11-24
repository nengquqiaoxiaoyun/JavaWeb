package com.cnhoyun.dao;

import com.cnhoyun.entity.Category;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-23
 */
public interface CategoryDao {

    /**
     * 查询所有商品类别
     * @return
     */
    List<Category> listCategory();
    
    
}
