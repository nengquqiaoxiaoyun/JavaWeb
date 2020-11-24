package com.cnhoyun.service.impl;

import com.cnhoyun.dao.ProductDao;
import com.cnhoyun.dao.impl.ProductDaoImpl;
import com.cnhoyun.entity.Product;
import com.cnhoyun.service.ProductService;

import java.sql.Connection;
import java.sql.SQLException;
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

    @Override
    public void addProduct(Connection con, Product product) throws SQLException {
        productDao.addProduct(con, product);
    }

    @Override
    public Product getProductByPid(String pid) {
        return productDao.getProductById(pid);
    }

    @Override
    public void editProduct(Connection con, Product product) throws SQLException {
        productDao.editProduct(con, product); 
    }
}
