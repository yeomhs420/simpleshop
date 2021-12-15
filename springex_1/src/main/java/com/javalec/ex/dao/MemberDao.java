package com.javalec.ex.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.javalec.ex.dto.MemberDto;
import com.javalec.ex.util.staticTemplate;


public class MemberDao {

	public static final int MEMBER_NONEXISTENT  = 0;
	public static final int MEMBER_EXISTENT = 1;
	public static final int MEMBER_JOIN_FAIL = 0;
	public static final int MEMBER_JOIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_PW_NO_GOOD = 0;
	public static final int MEMBER_LOGIN_SUCCESS = 1;
	public static final int MEMBER_LOGIN_IS_NOT = -1;
	
	public static final int BUY_EXISTENT = 1;
	public static final int BUY_EXISTENT_FAIL = 0;
	
	public static int balance = 0;
	
	int ri=0;
	
	DataSource dataSource;
	
	JdbcTemplate template;
	
	private static MemberDao instance = new MemberDao();
	
	public MemberDao() {
		this.template = staticTemplate.template;
	}
	
	public static MemberDao getInstance(){
		return instance;
	}
	
	
	public void write(final String bName, final String bTitle, final String bContent) {
		// TODO Auto-generated method stub
		
	
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				String query = "insert into mvc_board (bId, bName, bTitle, bContent, bHit, bGroup, bStep, bIndent) values (mvc_board_seq.nextval, ?, ?, ?, 0, mvc_board_seq.currval, 0, 0 )";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, bName);
				pstmt.setString(2, bTitle);
				pstmt.setString(3, bContent);
				return pstmt;
			}
		});
	}
	
	
	public int insertMember(MemberDto dto) {
		int ri = 0;
		
		String query = "insert into members values (?,?,?,?,?,?,?)";
		
		ri = this.template.update(query, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				pstmt.setString(1, dto.getId());
				pstmt.setString(2, dto.getPw());
				pstmt.setString(3, dto.getName());
				pstmt.setString(4, dto.geteMail());
				pstmt.setTimestamp(5, dto.getrDate());
				pstmt.setString(6, dto.getAddress());
				pstmt.setInt(7, 0);
			}
		});
		
		return ri;
	}
	
	
	public int confirmId(String id) {
		int ri = 0;
		
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select id from members where id = ?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			if(set.next()){
				ri = MemberDao.MEMBER_EXISTENT;
			} else {
				ri = MemberDao.MEMBER_NONEXISTENT;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return ri;
	}


	public int userCheck( String id, String pw) {
		int ri = 0;
		String dbPw;
		
		Connection connection = null;
		PreparedStatement pstmt = null;
		ResultSet set = null;
		String query = "select pw from members where id = ?";
		
		try {
			connection = getConnection();
			pstmt = connection.prepareStatement(query);
			pstmt.setString(1, id);
			set = pstmt.executeQuery();
			
			if(set.next()) {	
				dbPw = set.getString("pw");
				if(dbPw.equals(pw)) {
					ri = MemberDao.MEMBER_LOGIN_SUCCESS;				
				} else {
					ri = MemberDao.MEMBER_LOGIN_PW_NO_GOOD;		
				}
			} 
			else {
				ri = MemberDao.MEMBER_LOGIN_IS_NOT;			
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
				pstmt.close();
				connection.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return ri;
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


	

	public MemberDto getMember(String id) {

		String query = "select * from members where id = '" + id + "'";
		
		
		return template.queryForObject(query, new BeanPropertyRowMapper<MemberDto>(MemberDto.class)); //queryForObject() : 반환값이 한개의 행일때 사용하는 메소드
	}
	
	
	public int updateMember(MemberDto dto) {
		int ri = 0;
		
		ri = this.template.update(new PreparedStatementCreator() {
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				String query = "update members set pw=?, eMail=?, address=? where id=?";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, dto.getPw());
				pstmt.setString(2, dto.geteMail());
				pstmt.setString(3, dto.getAddress());
				pstmt.setString(4, dto.getId());
				return pstmt;
			}
		});

		return ri;
		
	}
	
	
	public void chargemoney(int money,String id) {
		
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con)
					throws SQLException {
				String query = "update members set balance=balance+? where id=?";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setInt(1, money);
				pstmt.setString(2, id);
				return pstmt;
			}
		});
	}
	

	public int buyitem(String id,int amount) {
	
		String query2 = "select balance from members where id='"+id+"'" ;
		
		balance=template.queryForObject(query2, new BeanPropertyRowMapper<MemberDto>(MemberDto.class)).getBalance();
		
		this.template.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String query = "update members set balance=? where id='"+id+"'";
				PreparedStatement pstmt = con.prepareStatement(query);
				if(balance>=amount) {
					pstmt.setInt(1, balance-amount);
					ri=BUY_EXISTENT;
				}
				else {
					pstmt.setInt(1, balance);
					ri=BUY_EXISTENT_FAIL;
				}
				return pstmt;
			}
		});
		return ri;
	}
	
}
