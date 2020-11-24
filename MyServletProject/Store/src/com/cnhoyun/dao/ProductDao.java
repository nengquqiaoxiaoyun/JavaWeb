package com.cnhoyun.dao;

import com.cnhoyun.entity.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-23
 */
public interface ProductDao {

    /**
     * 查询所有的商品
     * @return
     */
    List<Product> listProduct();

    /**
     * 添加商品
     */
    void addProduct(Connection con, Product product) throws SQLException;


    /**
     * 根据id获取product
     * @return
     */
    Product getProductById(String pid);

    /**
     * 编辑商品
     */
    void editProduct(Connection con, Product product) throws SQLException;
}
