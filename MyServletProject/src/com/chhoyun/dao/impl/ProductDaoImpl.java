package com.chhoyun.dao.impl;

import com.chhoyun.dao.BaseDao;
import com.chhoyun.dao.ProductDao;
import com.chhoyun.entity.Product;

import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-17
 */
public class ProductDaoImpl extends BaseDao<Product> implements ProductDao {

    @Override
    public List<Product> listProducts() {
        String sql = "select pid, pname, market_price as marketPrice, shop_price as shopPrice, " +
                "pimage, pdate, is_hot as isHot, pdesc, pflag, cid from product";
        return super.getBeanList(sql);
    }
}
