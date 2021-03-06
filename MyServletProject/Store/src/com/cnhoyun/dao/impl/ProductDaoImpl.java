package com.cnhoyun.dao.impl;

import com.cnhoyun.dao.BaseDao;
import com.cnhoyun.dao.ProductDao;
import com.cnhoyun.entity.Category;
import com.cnhoyun.entity.Product;
import com.cnhoyun.utils.JdbcUtils;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
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
        String sql = "SELECT pid, pname, pimage, shop_price as shopPrice, is_hot as isHot, category.cid, cname FROM product,category WHERE product.cid = category.cid";

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

    @Override
    public void addProduct(Connection con, Product product) throws SQLException {
        Connection connection = JdbcUtils.getConnection();
        String sql = "insert into product (pid, pname, market_price, shop_price, pimage, is_hot, " +
                "pdesc, cid) values(?, ?, ?, ?, ?, ?, ?, ?) ";
        super.update(connection, sql, product.getPid(), product.getPname(), product.getMarketPrice(), product.getShopPrice(),
                product.getPimage(), product.getIsHot(), product.getPdesc(), product.getCategory().getCid());


    }

    @Override
    public Product getProductById(String pid) {
        String sql = "SELECT pid, pname, market_price marketPrice, shop_price shopPrice, pimage, is_hot isHot, " +
                "pdesc, product.cid, cname FROM product, category " +
                "WHERE pid= ? AND product.cid = category.cid";

        MapHandler mapHandler = new MapHandler();
        try {
            Map<String, Object> map = getQueryRunnerWithDataSource().query(sql, mapHandler, pid);
            Product product = new Product();
            Category category = new Category();
            BeanUtils.populate(product, map);
            BeanUtils.populate(category, map);
            product.setCategory(category);
            return product;
        } catch (Exception throwables) {
            throwables.printStackTrace();
        }


        return null;
    }

    @Override
    public void editProduct(Connection con, Product product) throws SQLException {
        String sql = "UPDATE product SET pname = ?, market_price = ?, shop_price = ?, " +
                "pimage = ?, is_hot = ?, pdesc = ?, cid = ? WHERE pid = ?";

        super.update(con, sql, product.getPname(), product.getMarketPrice(), product.getShopPrice(),
                product.getPimage(), product.getIsHot(), product.getPdesc(), product.getCategory().getCid(), product.getPid());

    }

    @Override
    public void deleteProductByPid(Connection con, String pid) throws SQLException {
        String sql = "delete from product where pid = ?";
        super.update(con, sql, pid);
    }

    @Override
    public long getCount() {
        String sql = "select count(1) from product";
        ScalarHandler<Long> scalarHandler = new ScalarHandler<>();
        try {
            return super.getQueryRunnerWithDataSource().query(sql, scalarHandler);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return -1;
    }

    @Override
    public List<Product> currentData(int start, int end) {
        String sql = "select pid, pname, market_price marketPrice, shop_price shopPrice, pimage, " +
                "pdate, is_hot isHot, pdesc, cid from product limit ?, ?";
        return super.listBean(sql, start, end);
    }

}
