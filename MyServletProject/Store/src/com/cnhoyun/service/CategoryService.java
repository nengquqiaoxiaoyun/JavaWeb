package com.cnhoyun.service;

import com.cnhoyun.entity.Category;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-23
 */
public interface CategoryService {

    /**
     * 查询所有商品类别
     *
     * @return
     */
    List<Category> listCategory();
}
