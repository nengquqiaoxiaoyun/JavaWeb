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

    /**
     * 根据pid删除product
     */
    void deleteProductByPid(Connection con, String pid) throws SQLException;

    /**
     * 返回所有数据条数
     */
    long getCount();


    /**
     * 返回指定的数据
     * @param start 根据当前页面算出的开始条件
     * @param end 根据当前页面算出的结束条件
     * @return
     */
    List<Product> currentData(int start, int end);
}
