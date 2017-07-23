/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moon.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Database manipulate class
 * @author moon~
 */
public class DBUtil {
    //@moon:这是以后操作数据库经常需要用到的四个变量，将其作为DBUtil私有的成员变量，更加符合OOP思想
    private String url;
    private Connection con;
    private Statement sql;
    private ResultSet rs;
/**
 * 构造函数，负责加载驱动
 */
    public DBUtil() {
        //@moon:这里是DBUtil的构造函数，当你运行Login时，它的一个成员变量就来时用这个类初始化实例了。
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
            sql.execute("create table admin(aID char(1),aUser varchar(10) primary key,aPassword varchar(140))");
            sql.execute("insert into admin values('0','moon','1000:6c0cfefea37e6b2a21d1822a4a9e5f0bb1e37b91971e578d"
                    + ":32907c9856b72c9190ed71f602f9138ba077af9d7df1ea85')");
            sql.execute("insert into admin values('1','Benny','1000:871eced7f4773c952b2fae7688087af85f1886840f04c645"
                    + ":39afda635b7a2d33553ba234866e86d96089729ec93fef60')");
          *
          */
           
        } catch (ClassNotFoundException e) {
            System.out.println("加载驱动程序失败!");
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("SQL Exception catched...");
            System.exit(1);
        }
        
        
        /**
        * MySQL驱动，下载地址https://dev.mysql.com/downloads/connector/j/
        * 需要加入jre或者作为库添加到IDE中。
        try {         
            Class.forName("com.mysql.jdbc.Driver");
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            url = "jdbc:mysql://localhost:3306/supermarket?user=root&password=root";    //这里
            con = DriverManager.getConnection(url);
            sql = con.createStatement();
        } catch (ClassNotFoundException ex) {
            System.out.println("加载驱动程序失败!");
        } catch (InstantiationException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        **/

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
        //@moon:过滤一些特殊关键字、字符串来预防SQL注入
        sqlCommand=sqlCommand.replaceAll("'or", "").replaceAll("%", "").replaceAll("--", "");
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
        //@moon:将SQL操作分成两种，是因为查询需要返回结果集而删除、修改、增加不需要（或者是返回布尔值）
        //@moon:没有使用重载是因为增删改查的参数数量、参数类型都是一致的。
        //@moon:当然我们也可以自己加个判定参数来实现重载。
        sqlCommand=sqlCommand.replaceAll("'or", "").replaceAll("%", "").replaceAll("--", "");
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
