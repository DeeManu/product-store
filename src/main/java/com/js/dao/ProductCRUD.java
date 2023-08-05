package com.js.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.js.dto.Product;

public class ProductCRUD {
	
	public static final String URL="jdbc:mysql://localhost:3306/jdbc_ps";
	public static final String USER="root";
	public static final String PWD="deekshi";
	public static final String PATH="com.mysql.cj.jdbc.Driver";
	
	
	public static int insertProduct(Product p) {
		Connection c=null;
		String query = "insert into product values(?,?,?,?,?)";
		try {
			Class.forName(PATH);
			c = DriverManager.getConnection(URL,USER,PWD);
			PreparedStatement ps=c.prepareStatement(query);
			ps.setInt(1, p.getId());
			ps.setString(2, p.getName());
			ps.setString(3, p.getBrand());
			ps.setDouble(4, p.getPrice());
			ps.setInt(5, p.getQuality());
			return ps.executeUpdate();
		}catch(ClassNotFoundException e) {
		e.printStackTrace();	
			}catch (SQLException e) {
				e.printStackTrace();
				}
		finally {
			try {
				c.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}
	


public int deleteProductById(int id) {
	String query = "deleted from product where id=?";
	Connection c=null;
	try {
		Class.forName(PATH);
		c = DriverManager.getConnection(URL,USER,PWD);
		PreparedStatement ps=c.prepareStatement(query);
		ps.setInt(1,id);
		return ps.executeUpdate();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
			}catch(SQLException e) {
				e.printStackTrace();
				}
	     finally {
	    	 try {
	    		 c.close();
	    		  }catch(SQLException e) {
	    			  e.printStackTrace();
	    		  }
	     }
	return 0;
	
	
}
	public int UpdateProduct(int id,Product p) {
		String quary="update product set name=?,brand=?,quality=? where id=?";
		Connection c=null;
		try {
			Class.forName(PATH);
			c=DriverManager.getConnection(URL,USER,PWD);
			PreparedStatement ps=c.prepareStatement(quary);
			ps.setString(1, p.getName());
			ps.setString(2, p.getBrand());
			ps.setDouble(3, p.getPrice());
			ps.setInt(4, p.getQuality());
			ps.setInt(5, id);
			return ps.executeUpdate();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		return 0;
	}
	


public int deletedByProductPrice(double price) {
	String query="delete from product where price=?";
	Connection c=null;
	try {
		Class.forName(PATH);
		c=DriverManager.getConnection(URL,USER,PWD);
		PreparedStatement ps=c.prepareStatement(query);
		ps.setDouble(1,price);
		return ps.executeUpdate();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	finally {
		try {
			c.close();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
	return 0;
}


public Product getProductById(int id) {
	String query ="select * from product where id=?";
	Connection c=null;
	try {
		Class.forName(PATH);
		c=DriverManager.getConnection(URL,USER,PWD);
		PreparedStatement ps=c.prepareStatement(query);
		ps.setInt(1, id);
		ResultSet rs=ps.executeQuery();
		if(rs.next()) {
			Product p=new Product();
			p.setId(rs.getInt(1));
			p.setName(rs.getString(2));
			p.setBrand(rs.getString(3));
			p.setPrice(rs.getDouble(4));
			p.setQuality(rs.getInt(5));
			return p;
		}
	} catch (ClassNotFoundException e ) {
		e.printStackTrace();
	}catch(SQLException e) {
	    e.printStackTrace();
	}
	finally {
		try {
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	return null;
}










public ArrayList<Product> getAllProducts(){
	ArrayList<Product> al=new ArrayList<Product>();
	String query ="select * from product";
	Connection c=null;
	try {
		Class.forName(PATH);
		c=DriverManager.getConnection(URL,USER,PWD);
		PreparedStatement ps=c.prepareStatement(query);
		ResultSet rs=ps.executeQuery();
		while(rs.next()) {
			Product p=new Product();
			p.setId(rs.getInt(1));
			p.setName(rs.getString(2));
			p.setBrand(rs.getString(3));
			p.setPrice(rs.getDouble(4));
			p.setQuality(rs.getInt(5));
			al.add(p);//adding every product into array list
		}
		return al;
	} catch (ClassNotFoundException e ) {
		e.printStackTrace();
	}catch(SQLException e) {
	    e.printStackTrace();
	}
	finally {
		try {;
			c.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	return null;
}

public int[] batchExecutionToInsert(ArrayList<Product>products) {
	Connection c=null;
	String query="insert into product values(?,?,?,?,?3)";
	try {
		Class.forName(PATH);
		c=DriverManager.getConnection(URL,USER,PWD);
		PreparedStatement ps=c.prepareStatement(query);
		for(Product p:products) {
			ps.setInt(1,p.getId());
			ps.setString(2, p.getName());
			ps.setString(3, p.getBrand());
			ps.setDouble(4, p.getPrice());
			ps.setInt(5,p.getQuality());
			
			ps.addBatch();
		}
		return ps.executeBatch();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	finally {
		try {
			c.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
	}
	return null;
}

public int[]batchExecutionToDelete(int[] arr){
	Connection c=null;
	String query="delete from product where id=?";
	try {
		Class.forName(PATH);
		c=DriverManager.getConnection(PATH, USER, PWD);
		PreparedStatement ps=c.prepareStatement(query);
		for(int x:arr) {
			ps.setInt(1, x);
			ps.addBatch();
		}
		return ps.executeBatch();
	} catch (ClassNotFoundException e) {
		e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}
	finally {
		try {
			c.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		}
		
	}
	return null;
	
}
}



	
	


