package com.cnhoyun.dao.impl;

import com.cnhoyun.dao.BaseDao;
import com.cnhoyun.dao.CategoryDao;
import com.cnhoyun.entity.Category;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-23
 */
public class CateGoryDaoImpl extends BaseDao<Category> implements CategoryDao {
    @Override
    public List<Category> listCategory() {
        String sql = "select cid, cname from category GROUP BY cid, cname";
        return super.listBean(sql);
    }
}
