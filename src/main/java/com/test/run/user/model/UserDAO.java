package com.test.run.user.model;


import javax.naming.Context;
import javax.naming.InitialContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.sql.DataSource;



public class UserDAO {
	
	
	

	public static Connection getConnection() {
		
		Connection conn = null;;
	   
		
        try {
            Context initCtx= new InitialContext();
            DataSource ds = (DataSource) initCtx.lookup("java:comp/env/jdbc/pool");
               conn = ds.getConnection();
               System.out.println("연결성공");
       } catch (Exception e) {
           System.out.println("getConnection()");
           e.printStackTrace();
       }

       return conn;
   }

	public UserDTO login(UserDTO dto) {
		Connection conn = UserDAO.getConnection();
		Statement stat;
		PreparedStatement pstat;
		ResultSet rs;
		//queryParamDTOReturn
		try {
					
			String sql = "select * from tblTestUser where id = ? and pw = ?";
			
			pstat = conn.prepareStatement(sql);
			pstat.setString(1, dto.getId());
			pstat.setString(2, dto.getPw());
			
			rs = pstat.executeQuery();
			
			if (rs.next()) {
				
				UserDTO result = new UserDTO();
				
				result.setId(rs.getString("id"));
				result.setName(rs.getString("name"));
				result.setNickname(rs.getString("nickname"));
				
				return result;
			}	
			
		} catch (Exception e) {
			System.out.println("login()");
			
			e.printStackTrace();
		}
				
		
		return null;
	}

}