package com.chhoyun.dao.impl;

import com.chhoyun.dao.BaseDao;
import com.chhoyun.dao.ProductDao;
import com.chhoyun.entity.Product;
import com.chhoyun.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import java.sql.SQLException;
import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-17
 */
public class ProductDaoImpl extends BaseDao<Product> implements ProductDao {

    private QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());

    @Override
    public List<Product> listProducts() {
        String sql = "select pid, pname, market_price as marketPrice, shop_price as shopPrice, " +
                "pimage, pdate, is_hot as isHot, pdesc, pflag, cid from product";
        return super.getBeanList(sql);
    }

    @Override
    public List<String> listProductsNameByInput(String val) {
        String parm = "%" + val + "%";
        String sql = "SELECT pname FROM product WHERE pname LIKE ? UNION " +
                "SELECT pdesc FROM product WHERE pdesc LIKE ?";

        ColumnListHandler<String> columnListHandler = new ColumnListHandler("pname");
        try {
           return queryRunner.query(sql, columnListHandler, parm, parm);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return null;
    }
}
