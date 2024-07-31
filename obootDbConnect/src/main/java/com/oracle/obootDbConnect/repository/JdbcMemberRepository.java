package com.oracle.obootDbConnect.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.oracle.obootDbConnect.domain.Member7;

@Repository
public class JdbcMemberRepository implements MemberRepository {
	
	private final DataSource dataSource;

		
	@Autowired
	/*
	 * public JdbcMemberRepository(DataSource dataSource) { this.dataSource =
	 * dataSource; }
	 */	
	// 내장객체인 DataSourceUtils 이용하여 DB 연결
	
    public JdbcMemberRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
		
	
	
	private Connection getConnection() {
		
		return DataSourceUtils.getConnection(dataSource);
		
	}
	

	@Override
	public Member7 save(Member7 member7) {
		
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs			= null;
		String sql				= "INSERT INTO scott.member7 VALUES(MEMBER_SEQ.NEXTVAL, ?)";
		
		try {
			conn	= this.getConnection();
			pstmt	= conn.prepareStatement(sql);
			
			pstmt.setString(1, member7.getName());			
			pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}		
		
		return null;
	}

	@Override
	public List<Member7> findAll() {
		
		List<Member7> memberList = new ArrayList<>();
		
		Connection conn 		= null;
		PreparedStatement pstmt = null;
		ResultSet rs			= null;
		String sql				= "SELECT id, name FROM scott.member7";
		
		try {			
			conn	= this.getConnection();
			pstmt	= conn.prepareStatement(sql);
			rs		= pstmt.executeQuery();
			
			if(rs.next()) {
				do{
					Member7 member7 = new Member7();
					
					member7.setId(rs.getLong(1));
					member7.setName(rs.getString(2));
					
					memberList.add(member7);
					
				}while(rs.next());
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
		
		return memberList;
	}

	
	private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) {
		try {
			if(rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(conn != null) {
				close(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void close(Connection conn) throws SQLException{
		// Memory를 최적화해주면서 connection을 해제해줌
		// conn.close(); 사용해도 가능
		DataSourceUtils.releaseConnection(conn, dataSource);
	}
	
}
