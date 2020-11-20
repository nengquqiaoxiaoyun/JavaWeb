package com.chhoyun.service;

import com.chhoyun.entity.Product;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-17
 */
public interface ProductService {

    /**
     * 查询所有的商品
     *
     * @return
     */
    List<Product> listProducts();


    /**
     * 模糊查询商品名称
     * @return
     */
    List<String> listProductsNameByInput(String val);
}
