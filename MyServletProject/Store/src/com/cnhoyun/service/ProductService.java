package com.cnhoyun.service;

import com.cnhoyun.dto.PageBean;
import com.cnhoyun.entity.Product;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-23
 */
public interface ProductService {

    /**
     * 查询所有的商品
     *
     * @return
     */
    List<Product> listProduct();

    /**
     * 添加商品
     */
    void addProduct(Connection con, Product product) throws SQLException;
    
    Product getProductByPid(String pid);

    /**
     * 编辑商品
     */
    void editProduct(Connection con, Product product) throws SQLException;

    /**
     * 根据pid删除product
     */
    void deleteProductByPid(Connection con, String pid) throws SQLException;

    /**
     * 封装pagebean
     * @return
     */
    PageBean<Product> findPage(int currentPage);
}
