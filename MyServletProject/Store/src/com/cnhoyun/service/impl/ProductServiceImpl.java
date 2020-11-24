package com.cnhoyun.service.impl;

import com.cnhoyun.dao.ProductDao;
import com.cnhoyun.dao.impl.ProductDaoImpl;
import com.cnhoyun.dto.PageBean;
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

    @Override
    public void deleteProductByPid(Connection con, String pid) throws SQLException {
        productDao.deleteProductByPid(con, pid);
    }

    @Override
    public PageBean<Product> findPage(int currentPage) {
        PageBean<Product> productPageBean = new PageBean<>();
        productPageBean.setCurrentPage(currentPage);

        int totalSize = (int)productDao.getCount();
        productPageBean.setTotalSize(totalSize);

        // 每页展示10条数据
        int pageSize = 12;
        productPageBean.setPageSize(pageSize);

        /*
        0 10
        10 20
        20 30
         */
        int start = currentPage * pageSize;

        List<Product> products = productDao.currentData(start, pageSize);
        productPageBean.setPageList(products);

        // 总页数
        int totalPage = (int) Math.ceil(totalSize * 1.0d / pageSize);
        productPageBean.setTotalPage(totalPage);


        return productPageBean;
    }
}
