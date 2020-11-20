package com.chhoyun.dao;

import com.chhoyun.util.JDBCUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-10
 */
public abstract class BaseDao<T> {

    private Class<T> cls;
    private QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());


    {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        cls = (Class<T>) actualTypeArguments[0];
    }

    public void update(Connection con, String sql, Object... args) {
        try {
            queryRunner.update(con, sql, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


    public T getBean(Connection con, String sql, Object... args) {

        try {

            BeanHandler<T> userBeanHandler = new BeanHandler<T>(cls);
            return queryRunner.query(con, sql, userBeanHandler, args);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;

    }

    // 不需要传connectin 自己关闭
    public List<T> getBeanList(String sql, Object... args) {

        try {
            BeanListHandler<T> listBeanHandler = new BeanListHandler(cls);
            return queryRunner.query(sql, listBeanHandler, args);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }




}
