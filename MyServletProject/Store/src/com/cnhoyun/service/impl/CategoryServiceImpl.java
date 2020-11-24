package com.cnhoyun.service.impl;

import com.cnhoyun.dao.CategoryDao;
import com.cnhoyun.dao.impl.CateGoryDaoImpl;
import com.cnhoyun.entity.Category;
import com.cnhoyun.service.CategoryService;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-23
 */
public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = new CateGoryDaoImpl();

    @Override
    public List<Category> listCategory() {
        return categoryDao.listCategory();
    }
}
