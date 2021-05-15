package com.yzm.mysql.jdbc;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.sql.*;

@Component
public class JDBCDemo {

    public static void main(String[] args) {
//        selectDemo();
//        insertDemo();
        updateDemo();
//        deleteDemo();
//        callDemo();
    }

    private static void selectDemo() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.INSTANCE.getConnection();
            String sql = "select * from `user`";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " +
                        rs.getString(2) + " " +
                        rs.getString(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.INSTANCE.close(conn, ps, rs);
        }
    }

    private static void insertDemo() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.INSTANCE.getConnection();
            String sql = "insert into `user`(username,password) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "ABC");
            ps.setString(2, "abc");
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.INSTANCE.close(conn, ps, null);
        }
    }

    private static void updateDemo() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.INSTANCE.getConnection();
            String sql = "update `user` set password = ? where username = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "566");
            ps.setString(2, "ABC");
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.INSTANCE.close(conn, ps, null);
        }
    }

    private static void deleteDemo() {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtils.INSTANCE.getConnection();
            String sql = "delete from `user` where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 11);
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.INSTANCE.close(conn, ps, null);
        }
    }

    /**
     * CREATE PROCEDURE select_all()
     * BEGIN
     *  SELECT * FROM account;
     * END;
     */
    private static void callDemo() {
        Connection conn = null;
        CallableStatement cs = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtils.INSTANCE.getConnection();
            String sql = "call select_all()";
            cs = conn.prepareCall(sql);
            rs = cs.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getDouble(3));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            JDBCUtils.INSTANCE.close(conn, cs, rs);
        }
    }

}
