package com.cnhoyun.jdbc;

import com.cnhoyun.jdbc.utils.JDBCUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;

/**
 * @author: huakaimay
 * @since: 2020-11-05
 */
public class Exa {


    @Test
    public void execute() {
        insertStudent();
    }


    public void insertStudent() {

        System.out.println("请输入考生的详情信息");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Type: ");
        String type = scanner.next();

        System.out.println("IDCard: ");
        String idCard = scanner.next();

        System.out.println("ExamCard: ");
        String examCard = scanner.next();

        System.out.println("StudentName:");
        String studentName = scanner.next();

        System.out.println("Location: ");
        String location = scanner.next();

        System.out.println("Grade: ");
        String grade = scanner.next();



        String sql = "insert into exaastudent(Type, IDCard, ExamCard, StudentName, Location, Grade) values (?, ?, ?, ?, ?, ?)";
        boolean update = update(sql, type, idCard, examCard, studentName, location, grade);
        if (update) {
            System.out.println("插入学生信息成功");

        }
        System.out.println("插入学生信息失败");


    }

    public static boolean update(String sql, Object... args) {

        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = JDBCUtil.getConnection();
            ps = connection.prepareStatement(sql);
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i + 1, args[i]);
            }
            return ps.executeUpdate() == 1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closed(connection, ps);
        }
        return false;

    }
}
