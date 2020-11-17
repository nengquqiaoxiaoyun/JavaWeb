package com.chhoyun.service.impl;

import com.chhoyun.dao.ProductDao;
import com.chhoyun.dao.impl.ProductDaoImpl;
import com.chhoyun.entity.Product;
import com.chhoyun.service.ProductService;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-17
 */
public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = new ProductDaoImpl();

    @Override
    public List<Product> listProducts() {
        return productDao.listProducts();
    }
}
