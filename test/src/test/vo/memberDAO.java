package test.vo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class memberDAO {
	 
		 	private static memberDAO instance = new memberDAO();
		    
		    public static memberDAO getInstance() {return instance; }
		    
		    private memberDAO() {}
		    
		    private Connection getConnection() throws Exception {
		      Context initCtx = new InitialContext();
		      Context envCtx = (Context) initCtx.lookup("java:comp/env");
		      DataSource ds = (DataSource)envCtx.lookup("jdbc/xe");
		      return ds.getConnection();
		    }
		 
		    public void insertMember(memberVO member) 
		    throws Exception {
		        Connection conn = null;
		        PreparedStatement pstmt = null;
		        
		        try {
		            conn = getConnection();
		            
		            pstmt = conn.prepareStatement(
		            	"insert into MEMBER values (?,?,?,?,?,?,?,?)");
		            pstmt.setString(1, member.getId());
		            pstmt.setString(2, member.getPasswd());
		            pstmt.setString(3, member.getName());
		            pstmt.setString(4, member.getJumin1());
		            pstmt.setString(5, member.getJumin2());
		            pstmt.setString(6, member.getEmail());
		            pstmt.setString(7, member.getBlog());
					pstmt.setTimestamp(8, member.getReg_date());
		            pstmt.executeUpdate();
		        } catch(Exception ex) {
		            ex.printStackTrace();
		        } finally {
		            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		        }
		    }
		 
			public int userCheck(String id, String passwd) 
			throws Exception {
				Connection conn = null;
		        PreparedStatement pstmt = null;
				ResultSet rs= null;
		        String dbpasswd="";
				int x=-1;
		        
				try {
		            conn = getConnection();
		            
		            pstmt = conn.prepareStatement(
		            	"select passwd from MEMBER where id = ?");
		            pstmt.setString(1, id);
		            rs= pstmt.executeQuery();

					if(rs.next()){
						dbpasswd= rs.getString("passwd"); 
						if(dbpasswd.equals(passwd))
							x= 1; //인증 성공
						else
							x= 0; //비밀번호 틀림
					}else
						x= -1;//해당 아이디 없음
					
		        } catch(Exception ex) {
		            ex.printStackTrace();
		        } finally {
					if (rs != null) try { rs.close(); } catch(SQLException ex) {}
		            if (pstmt != null) try { pstmt.close(); } catch(SQLException ex) {}
		            if (conn != null) try { conn.close(); } catch(SQLException ex) {}
		        }
				return x;
			}
}