package com.cnhoyun.service;

import com.cnhoyun.entity.Product;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-23
 */
public interface ProductService {

    /**
     * 查询所有的商品
     * @return
     */
    List<Product> listProduct();
}
