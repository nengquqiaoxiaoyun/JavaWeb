package com.cnhoyun.service.impl;

import com.cnhoyun.dao.ProductDao;
import com.cnhoyun.dao.impl.ProductDaoImpl;
import com.cnhoyun.entity.Product;
import com.cnhoyun.service.ProductService;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-23
 */
public class ProductServiceImpl implements ProductService {
    
    private ProductDao productDao = new ProductDaoImpl();
    
    @Override
    public List<Product> listProduct() {
        return productDao.listProduct();
    }
}
