/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music.data;


import java.sql.*;
import java.sql.SQLException;

/**
 *
 * @author Roy
 */
public class ProductDB {
   
   public static int selectAll() {
       
   }
    
   public static int update()  {
    ConnectionPool pool = ConnectionPool.getInstance();
       Connection connection = pool.getConnection();
      
      PreparedStatement ps = null;
       
    String preparedSQL = "UPDATE Product SET "
                    + " ProductCode = ?, "
                    + " ProductDescription = ?, "
                    + " ProductPrice = ?"
                    + "WHERE ProductCode = ?";
    try{
            ps = connection.prepareStatement(preparedSQL);
            ps.setString(1, product.getCode());
            ps.setString(2, product.getDescription());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getCode());
            
            return ps.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e);
        return 0;
    } finally {
        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
    }
}
       

   
   public static int insert() {
       ConnectionPool pool = ConnectionPool.getInstance();
       Connection connection = pool.getConnection();
       PreparedStatement ps = null;
       
       String preparedQuery =
               "INSERT INTO Product (ProductCode, ProductDescription, ProductPrice) "
               + "VALUES (?, ?, ?)";
    try {
       ps = connection.prepareStatement(preparedQuery);
       ps.setString(1, product.getCode());
       ps.setString(2, product.getDescription());
       ps.setDouble(3, product.getPrice());
       return ps.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e);
        return 0;
    } finally {
        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
    }

}
   
   
   public static int delete()  {
       ConnectionPool pool = ConnectionPool.getInstance();
       Connection connection = pool.getConnection();
       PreparedStatement ps = null;
       
    String preparedQuery = "DELETE FROM Product "
            + "WHERE ProductCode = ?";
    try {
    ps = connection.prepareStatement(preparedQuery);
    ps.setString(1, productCode);
    
    return ps.executeUpdate();
    } catch (SQLException e) {
        System.out.println(e);
        return 0;
    } finally {
        DBUtil.closePreparedStatement(ps);
        pool.freeConnection(connection);
    }
       
   }
   
   

   
}

    

