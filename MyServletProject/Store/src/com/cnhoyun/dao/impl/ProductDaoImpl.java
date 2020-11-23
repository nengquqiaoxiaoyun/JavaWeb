package com.cnhoyun.dao.impl;

import com.cnhoyun.dao.BaseDao;
import com.cnhoyun.dao.ProductDao;
import com.cnhoyun.entity.Category;
import com.cnhoyun.entity.Product;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.handlers.MapListHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: huakaimay
 * @since: 2020-11-23
 */
public class ProductDaoImpl extends BaseDao<Product> implements ProductDao {
    @Override
    public List<Product> listProduct() {
        String sql = "SELECT pname, pimage, shop_price as shopPrice, is_hot as isHot, cname FROM product,category WHERE product.cid = category.cid";

        List<Product> products = new ArrayList<>();
        MapListHandler mapListHandler = new MapListHandler();
        try {
            List<Map<String, Object>> mapList = super.getQueryRunnerWithDataSource().query(sql, mapListHandler);
            for (Map<String, Object> map : mapList) {
                Product product = new Product();
                Category category = new Category();
                BeanUtils.populate(product, map);
                BeanUtils.populate(category, map);
                product.setCategory(category);
                products.add(product);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }


        return products;
    }
}
