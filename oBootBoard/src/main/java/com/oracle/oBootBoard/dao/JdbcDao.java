package com.oracle.oBootBoard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.stereotype.Repository;

import com.oracle.oBootBoard.dto.BDto;

@Repository
public class JdbcDao implements BDao {
	
	private final DataSource dataSource;
	
	@Autowired
	public JdbcDao(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	private Connection getConnection() {
		return DataSourceUtils.getConnection(dataSource);
	}

	@Override
	public ArrayList<BDto> boardList() {
		
		ArrayList<BDto> bList		= new ArrayList<BDto>();
		Connection conn			= null;
		PreparedStatement pstmt = null;
		ResultSet rs			= null;
		String sql				= "SELECT	bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent "
								+ "FROM 	mvc_board "
								+ "ORDER BY bgroup DESC, bstep ASC";
		
		System.out.println("JdbcDao boardList() is started");
		
		try {
			
			conn	= this.getConnection();
			pstmt	= conn.prepareStatement(sql);
			rs		= pstmt.executeQuery();
			// bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent
			if(rs.next()) {
				do {
					BDto bdto = new BDto();
					
					bdto.setbId(rs.getInt(1));
					bdto.setbName(rs.getString(2));
					bdto.setbTitle(rs.getString(3));
					bdto.setbContent(rs.getString(4));
					bdto.setbDate(rs.getTimestamp(5));
					bdto.setbHit(rs.getInt(6));
					bdto.setbGroup(rs.getInt(7));
					bdto.setbStep(rs.getInt(8));
					bdto.setbIndent(rs.getInt(9));
					
					/* --> 생성자를 이용
					int var1 		= rs.getInt(1);
					String var2		= rs.getString(2); 
					String var3		= rs.getString(3);
					String var4		= rs.getString(4); 		
					Timestamp var5	= rs.getTimestamp(5);  
					int var6		= rs.getInt(6); 
					int var7		= rs.getInt(7);
					int var8		= rs.getInt(8);
					int var9		= rs.getInt(9);
			
					BDto dto = new BDto(var1, var2, var3, var4, var5, var6, var7, var8, var9);
					
					bList.add(dto);
					*/
					
					bList.add(bdto);
					
				}while(rs.next());
				
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}		
		return bList;
	}

	@Override
	public BDto contentView(String strId) {
		
		BDto bdto = null;
		Connection conn			= null;
		PreparedStatement pstmt = null;
		ResultSet rs			= null;
		String sql				= "SELECT	bid, bname, btitle, bcontent, bdate, bhit, bgroup, bstep, bindent "
								+ "FROM 	mvc_board "
								+ "WHERE bId = ?";
		upHit(strId);
		
		try {
			conn	= this.getConnection();
			pstmt	= conn.prepareStatement(sql);
			pstmt.setString(1, strId);
			rs		= pstmt.executeQuery();
			
			if(rs.next()) {
				
				int var1 		= rs.getInt(1);
				String var2		= rs.getString(2); 
				String var3		= rs.getString(3);
				String var4		= rs.getString(4); 		
				Timestamp var5	= rs.getTimestamp(5);  
				int var6		= rs.getInt(6); 
				int var7		= rs.getInt(7);
				int var8		= rs.getInt(8);
				int var9		= rs.getInt(9);
				
				System.out.println("contentView var4 -> " + var4);
				
				bdto = new BDto(var1, var2, var3, var4, var5, var6, var7, var8, var9);				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}				
		
		return bdto;
	}
	
	public void upHit(String strId) {
		
		Connection conn			= null;
		PreparedStatement pstmt = null;
		ResultSet rs			= null;
		String sql				= "UPDATE mvc_board SET bHit = bHit + 1 WHERE bId = ?";
		
		try {
			conn	= this.getConnection();
			pstmt	= conn.prepareStatement(sql);
			pstmt.setString(1, strId);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}				
		
	}
	
	@Override
	public int modifyBoard(BDto board) {
		int result = 0;
		
		Connection conn			= null;
		PreparedStatement pstmt = null;
		ResultSet rs			= null;
		String sql				= "UPDATE mvc_board "
								+ "SET	bName = ?, "
								+ "		bTitle = ?, "
								+ "		bContent = ? "
								+ " WHERE bId = ?";
		
		try {
			
			conn	= this.getConnection();
			pstmt	= conn.prepareStatement(sql);
			pstmt.setString(1, board.getbName());
			
			System.out.println("board.getbName() -> " + board.getbName());
			
			pstmt.setString(2, board.getbTitle());
			pstmt.setString(3, board.getbContent());
			pstmt.setInt(4, board.getbId());
			
			System.out.println("board.getbId() -> " + board.getbId());
			
			result = pstmt.executeUpdate();
			
			System.out.println("modifyBoard(BDto board) result -> " + result);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(conn, pstmt, rs);
		}
			
		return result;
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
