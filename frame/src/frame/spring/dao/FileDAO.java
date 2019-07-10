package frame.spring.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import com.sun.crypto.provider.RSACipher;
import frame.spring.vo.FileVO;

public class FileDAO {
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	private Connection getConnection() throws Exception{
		Context initCtx = new InitialContext();
		Context envCtx =(Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/xe");
		return ds.getConnection();
	}


	public int getNum()throws Exception{
		int num =0;
		try {
			conn = getConnection();
			String sql ="insert into fileNum values(fileNum_seq.nextval)";
			pstmt = conn.prepareStatement(sql);
			pstmt.executeUpdate();
			sql = "select max(num) from fileNum";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				num = rs.getInt(1);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if(rs!=null)try { rs.close();}catch(SQLException ex){}
				if(pstmt!=null)try { pstmt.close();}catch(SQLException ex){}
				if(conn!=null)try { conn.close();}catch(SQLException ex){}
				}
				return num;
				}
				
		
		public void fileInsert(FileVO vo )throws Exception{
			try {
				conn = getConnection();
				String sql = "insert into profile values(profile_seq.nextval,?,?,?)";
				pstmt= conn.prepareStatement(sql);
				pstmt.setString(1, vo.getWriter());
				pstmt.setString(2, vo.getOrgname());
				pstmt.setString(3, vo.getNewname());
				pstmt.executeUpdate();
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
		
		}
		public List getList()throws Exception {
			List list = null;
			try {
				conn = getConnection();
				String sql = "select * from profile order by num desc";
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				if(rs.next()) {
					list = new ArrayList();
					do {
					FileVO vo = new FileVO();
					vo.setWriter(rs.getString("writer"));
					vo.setOrgname(rs.getString("orgname"));
					vo.setNewname(rs.getString("newname"));
					list.add(vo);
		
				}while(rs.next());
				}
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				if (rs != null) try { rs.close(); } catch(SQLException ex) {}
				if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
				if (conn != null) try { conn.close(); } catch(SQLException ex) {}
			}
			return list;
		}
		public void deleteImg(String imgs) throws Exception{
			int x = -1;
			conn = getConnection();
			pstmt = conn.prepareStatement(
					"delete from profile where newname=?");
			pstmt.setString(1, imgs);
			pstmt.executeUpdate();
			x=1;
			
			
		}
	}