package com.cnhoyun.jdbc.dao;

import com.cnhoyun.jdbc.utils.JDBCUtil;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-06
 */
public abstract class BaseDao<T> {

    private Class<T> cls = null;

    {
        // this是实际的子类
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] actualTypeArguments = pt.getActualTypeArguments();
        cls = (Class<T>)actualTypeArguments[0];
    }


    /**
     * 增删改的操作
     */
    public void update(Connection con, String sql, Object... args) {
        PreparedStatement ps = null;
        try {
            ps = con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.closed(null, ps);
        }

    }


    /**
     * 查询一个对象
     *
     * @param
     * @return
     */
    public T getObjectById(Connection con, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            T t;
            if (rs.next()) {
                t = cls.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    String columnLabel = metaData.getColumnLabel(i + 1);

                    Field declaredField = t.getClass().getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(t, columnValue);
                }
                return t;
            }


        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.closed(null, ps, rs);
        }

        return null;
    }

    /**
     * 查询多个对象
     *
     * @param
     * @return
     */
    public List<T> getObjectList(Connection con, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }

            rs = ps.executeQuery();
            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            T t;
            List<T> list = new ArrayList<>();
            while (rs.next()) {
                t = cls.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i + 1);
                    String columnLabel = metaData.getColumnLabel(i + 1);

                    Field declaredField = t.getClass().getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(t, columnValue);
                }
                list.add(t);
            }

            return list;


        } catch (Exception throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.closed(null, ps, rs);
        }

        return null;
    }

    /**
     * 获取数据库一些通用的操作
     *
     * @param <T>
     * @return
     */
    public <T> T getGenericInfo(Connection con, String sql, Object... args) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            ps = con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            rs = ps.executeQuery();

            if (rs.next()) {
                return (T) rs.getObject(1);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            JDBCUtil.closed(null, ps, rs);
        }

        return null;
    }


}
