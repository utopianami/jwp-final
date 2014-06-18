package next.dao;

import java.sql.Connection;
import java.sql.SQLException;

import next.support.db.ConnectionManager;

public abstract  class DBaccessDao {
	protected Connection conn;
	protected Object result;
	
	public DBaccessDao() throws SQLException {
		try {
			conn = ConnectionManager.getConnection();
			result = executeQuery();
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
					System.out.println("Connection return");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
		
		public Object getResult() {
			return result;
		}
		
		public abstract Object executeQuery();
}

