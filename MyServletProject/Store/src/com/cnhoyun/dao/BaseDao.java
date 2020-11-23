package com.cnhoyun.dao;

import com.cnhoyun.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * 提供通用的sql方法
 *
 * @author: huakaimay
 * @since: 2020-11-23
 */
public class BaseDao<T> {

    // 不需要手动关闭资源啊
    private QueryRunner queryRunnerWithDataSource = new QueryRunner(JdbcUtils.getDataSource());
    // 需要手动关闭资源
    private QueryRunner queryRunner = new QueryRunner();


    public QueryRunner getQueryRunnerWithDataSource() {
        return queryRunnerWithDataSource;
    }

    public QueryRunner getQueryRunner() {
        return queryRunner;
    }

    private Class<T> cls;

    {
        ParameterizedType parameterizedType = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
        cls = (Class) actualTypeArguments[0];
    }


    /**
     * update执行成功返回true
     * 修改需要调用处手动关闭con，用于处理事务
     */
    public boolean update(Connection con, String sql, Object... args) throws SQLException {
        int update = queryRunner.update(con, sql, args);
        return update != 0;
    }

    /**
     * 查询一个对象
     */
    public T getBean(String sql, Object... args) {
        BeanHandler<T> beanHandler = new BeanHandler<>(cls);
        try {
            return queryRunnerWithDataSource.query(sql, beanHandler, args);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 查询一个对象, 可提供事务
     */
    public T getBean(Connection con, String sql, Object... args) throws SQLException {
        BeanHandler<T> beanHandler = new BeanHandler<>(cls);
        return queryRunner.query(con, sql, beanHandler, args);
    }

    /**
     * 查询多个对象
     */
    public List<T> listBean(String sql, Object... args) {
        BeanListHandler<T> beanListHandler = new BeanListHandler<>(cls);
        try {
            return queryRunnerWithDataSource.query(sql, beanListHandler, args);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * 查询多个对象, 可提供事务
     */
    public List<T> listBean(Connection con, String sql, Object... args) throws SQLException {
        BeanListHandler<T> beanListHandler = new BeanListHandler<>(cls);
        return queryRunner.query(con, sql, beanListHandler, args);
    }


}
