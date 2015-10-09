package com.hotel.dao.impl;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.hotel.dao.IBaseDao;
import org.springframework.stereotype.Repository;

@Repository
public class BaseDao implements IBaseDao{
	
	protected Connection conn;    
	
	protected void openconnection() throws ClassNotFoundException,SQLException{		
		try {
			if( conn == null || conn.isClosed() ){
				Class.forName("oracle.jdbc.driver.OracleDriver");  //
				conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:orcl","fujisheng","fujisheng");
			}			
		} catch (ClassNotFoundException e) {
		    throw e;
		} catch (SQLException e) {	
			throw e;
		}
	}	
	

	/* (non-Javadoc)
	 * @see com.hotel.dao.IBaseDao#closeResource()
	 */
	@Override
	public void closeResource(){
		
		if(conn != null){
			try {
				conn.close();
				conn = null;
			} catch (Exception e) {
				e.printStackTrace();
			}			
		}		
	}
	
	/* (non-Javadoc)
	 * @see com.hotel.dao.IBaseDao#beginTransaction()
	 */
	@Override
	public void beginTransaction() throws Exception{
		if(conn != null){
			conn.setAutoCommit(false);
		}
			
	}
	
	/* (non-Javadoc)
	 * @see com.hotel.dao.IBaseDao#commit()
	 */
	@Override
	public void commit() throws Exception{
		if(conn != null){
			conn.commit();
		}	
	}
	
	/* (non-Javadoc)
	 * @see com.hotel.dao.IBaseDao#rollback()
	 */
	@Override
	public void rollback() throws Exception{
		if(conn != null){
			conn.rollback();
		}
		
	}
	
	

}
