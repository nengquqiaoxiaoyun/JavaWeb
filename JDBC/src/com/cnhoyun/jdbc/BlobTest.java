package com.cnhoyun.jdbc;

import com.cnhoyun.jdbc.utils.JDBCUtil;
import org.junit.Test;

import java.io.*;
import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: huakaimay
 * @since: 2020-11-05
 */
public class BlobTest {

    @Test
    public void test() throws Exception {
        String sql = "SELECT id, name, email, birth, photo FROM customers WHERE id = ?";
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        InputStream is = null;
        OutputStream os = null;

        try {
            conn = JDBCUtil.getConnection();
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 27);
            rs = ps.executeQuery();
            if (rs.next()) {
                Integer id = rs.getInt(1);
                String name = rs.getString(2);
                String email = rs.getString(3);
                Date birth = rs.getDate(4);
                User cust = new User(id, name, email, birth);
                System.out.println(cust);
                //读取Blob类型的字段
                Blob photo = rs.getBlob(5);
                is = photo.getBinaryStream();
                os = new FileOutputStream("src/ccc.txt");
                byte[] buffer = new byte[1024];
                int len = 0;
                while ((len = is.read(buffer)) != -1) {
                    os.write(buffer, 0, len);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closed(conn, ps, rs);

            if (is != null) {
                is.close();
            }

            if (os != null) {
                os.close();
            }
        }
    }


    @Test
    public void insertBlod() throws FileNotFoundException {

        FileInputStream fileInputStream = new FileInputStream(new File("/Users/wentimei/Documents/Calibration.txt"));
        String sql = "insert into customers(name, photo) values(?, ?)";
        update(sql, "小车", fileInputStream);


    }


    public void update(String sql, Object... args) {
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = JDBCUtil.getConnection();
            ps = con.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(1 + i, args[i]);
            }
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closed(con, ps);
        }

    }


    public <T> List<T> genericSelectList(Class<T> cls, String sql, Object... args) {
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = JDBCUtil.getConnection();
            ps = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(1 + i, args[i]);
            }

            rs = ps.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            List<T> list = new ArrayList<>();
            T t;
            while (rs.next()) {
                t = cls.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(1 + i);
                    String columnLabel = metaData.getColumnLabel(i + 1);

                    Field declaredField = t.getClass().getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(t, columnValue);
                }
                list.add(t);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closed(connection, ps, rs);
        }
        return null;
    }


    public <T> T genericSelect(Class<T> cls, String sql, Object... args) {

        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            connection = JDBCUtil.getConnection();
            ps = connection.prepareStatement(sql);

            for (int i = 0; i < args.length; i++) {
                ps.setObject(1 + i, args[i]);
            }

            rs = ps.executeQuery();

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();

            T t;
            if (rs.next()) {
                t = cls.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(1 + i);
                    String columnLabel = metaData.getColumnLabel(i + 1);

                    Field declaredField = t.getClass().getDeclaredField(columnLabel);
                    declaredField.setAccessible(true);
                    declaredField.set(t, columnValue);
                }

                return t;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closed(connection, ps, rs);
        }


        return null;
    }
}
