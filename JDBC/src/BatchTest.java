import com.cnhoyun.jdbc.utils.JDBCUtil;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @author: huakaimay
 * @since: 2020-11-05
 */
public class BatchTest {


    @Test
    public void testInsertBatch2() {
        Connection con = null;
        PreparedStatement ps = null;
        long start = System.currentTimeMillis();
        try {
            con = JDBCUtil.getConnection();
            String sql = "insert into goods(name) values (?)";
            ps = con.prepareStatement(sql);

            con.setAutoCommit(false);
            for (int i = 1; i <= 1000000; i++) {
                ps.setString(1, "name_" + i);

                if(i % 10000 == 0) {
                    ps.addBatch();
                    ps.executeBatch();
                    ps.clearBatch();
                }

            }
            con.commit();
            long end = System.currentTimeMillis();
            System.out.println("花费时间: " + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closed(con, ps);
        }
    }



    @Test
    public void testInsertBatch() {
        Connection con = null;
        PreparedStatement ps = null;
        long start = System.currentTimeMillis();
        try {
            con = JDBCUtil.getConnection();
            String sql = "insert into goods(name) values (?)";
            ps = con.prepareStatement(sql);
            for (int i = 1; i <= 20000; i++) {
                ps.setString(1, "name_" + i);
                ps.executeUpdate();
            }
            long end = System.currentTimeMillis();
            System.out.println("花费时间: " + (end - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closed(con, ps);
        }
    }
}
