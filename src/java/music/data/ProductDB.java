/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package music.data;

import java.sql.*;
import java.util.ArrayList;
import music.business.Product;
import music.data.DBUtil;

/**
 *
 * @author Roy
 */
public class ProductDB {
   
   public static ArrayList<Product> selectAll() {
    ConnectionPool pool = ConnectionPool.getInstance();
    Connection connection = pool.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;
       
    String preparedQuery = "SELECT * FROM Product";    
        try {
            ps = connection.prepareStatement(preparedQuery);
            rs = ps.executeQuery();
            ArrayList<Product> products = new ArrayList<Product>();
            while (rs.next())
            {
                Product product = new Product();
                product.setCode(rs.getString("ProductCode"));
                product.setDescription(rs.getString("ProductDescription"));
                product.setPrice(rs.getDouble("ProductPrice"));
                products.add(product);
            }
            return products;
        } catch (SQLException e) {
            System.out.println(e);
            return null;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        
    }
   }
   
   public static Boolean codeExists(String code) {
    //
    // use emailExists example
    //
    // execute code to select a single product.
    // Select * FROM Product WHERE ProductCode = ?
    // does it return false?
   ConnectionPool pool = ConnectionPool.getInstance();
        Connection connection = pool.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String preparedQuery = "SELECT * FROM Product "
                + "WHERE ProductCode = ?";
        try {
            ps = connection.prepareStatement(preparedQuery);
            ps.setString(1, code);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        } finally {
            DBUtil.closeResultSet(rs);
            DBUtil.closePreparedStatement(ps);
            pool.freeConnection(connection);
        }
    }
    
   public static int update(Product product)  {
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
       

   
   public static int insert(Product product) {
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

   public static int delete(Product product)  {
       ConnectionPool pool = ConnectionPool.getInstance();
       Connection connection = pool.getConnection();
       PreparedStatement ps = null;
       
    String preparedQuery = "DELETE FROM Product "
            + "WHERE ProductCode = ?";
    try {
    ps = connection.prepareStatement(preparedQuery);
    ps.setString(1, product.getCode());
    
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

    

