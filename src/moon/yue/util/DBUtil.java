/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ***REMOVED***.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Benny~
 */
public class DBUtil {

    private String url;
    private Connection con;
    private Statement sql;
    private ResultSet rs;
/**
 * 构造函数，负责加载驱动
 */
    public DBUtil() {

        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            url = "jdbc:derby:market;create=true";
            con = DriverManager.getConnection(url);
            sql = con.createStatement();
            
            /**
             * 取消对我的注释来Run Everywhere而不用带着数据库目录
             * 
            sql.execute("create table product(pID char(10) primary key, pName char(20),"
                + "pProducer char(20),pOrigin char(10),pDate char(20),pPrice1 char(20),"
                + "pPrice2 char(20))");
            sql.execute("create table admin(aID char(1),aUser char(10) primary key,aPassword char(40))");
            sql.execute("insert into admin values('0','moon','956e3f6be3d2792bd93efc7fdb3d19941ca20e43')");
            sql.execute("insert into admin values('1','Bunny','62221f301a1c106d1f6ec9e982c60fcab3d8aad0')");
          *
          */
            
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动程序失败!");
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
/**
 * 执行查询
 * @param sqlCommand
 *          要执行的查询SQL语句
 * @return
 *          结果集
 * @throws SQLException
 *          SQL异常
 */
    public ResultSet select(String sqlCommand) throws SQLException {

        rs = sql.executeQuery(sqlCommand);
        return rs;

    }
/**
 * 执行删除、修改、增加
 * @param sqlCommand
 *          要执行的查询SQL语句
 * @throws SQLException 
 *          SQL异常
 */
    public void OrdinaryQuery(String sqlCommand) throws SQLException {
        //删除，修改，增加
        sql.executeUpdate(sqlCommand);

    }
/**
 * 关闭数据库连接
 * @throws SQLException 
 *          SQL异常
 */
    public void closeDB() throws SQLException {

        con.close();
    }

}
