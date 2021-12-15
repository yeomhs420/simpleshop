package com.javalec.ex.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;

import com.javalec.ex.dto.ProductDto;
import com.javalec.ex.util.staticTemplate;

public class ProductDao {
	
	public static final int	IMG_NONEXISTENT  = 0;
	public static final int IMG_EXISTENT = 1;

	DataSource dataSource;
	
	JdbcTemplate template;
	
	private static ProductDao instance = new ProductDao();
	
	public static ProductDao getInstance(){
		return instance;
	}
	
	public ProductDao() {
		this.template = staticTemplate.template;
	}
	
	private Connection getConnection() {
		
		Context context = null;
		DataSource dataSource = null;
		Connection connection = null;
		try {
			context = new InitialContext();
			dataSource = (DataSource)context.lookup("java:comp/env/jdbc/Oracle11g");
			connection = dataSource.getConnection();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public int imageCheck(String image_url) {
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		
		int ri=0;
		
		try {
			String query = "select image_url from products where image_url=?";
			
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, image_url);
			set = pstmt.executeQuery();
			
			if(set.next()) {
				ri = IMG_EXISTENT;
			}
			else {
				ri = IMG_NONEXISTENT;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(pstmt != null) pstmt.close();
				if(connection != null) connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
	}
	
	
	public void insertproduct(String id,String name,int amount,int price,String image_url) {
		
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				String query = "insert into products  values (?,?,?,?,?)";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, id);
				pstmt.setString(2, name);
				pstmt.setInt(3, amount);
				pstmt.setInt(4, price);
				pstmt.setString(5, image_url);
				return pstmt;
			}
		});
	}
	
	
	public ArrayList<ProductDto> getImagelist(String id) {
		
		String query = "select * from products where id='"+id+"'";	
		
		return (ArrayList<ProductDto>) template.query(query, new BeanPropertyRowMapper<ProductDto>(ProductDto.class));
	}
	
	
	public void plusamount(String name) {
		this.template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				String query = "update products set amount=amount+1 where name='"+name+"'";
				PreparedStatement pstmt = con.prepareStatement(query);
				
				return pstmt;
			}
		});
	}
	
}
