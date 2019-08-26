package kurs.carrental.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import kurs.carrental.beans.NameBean;

public class Service {
	
	static {
		try {
            // The newInstance() call is a work around for some
            // broken Java implementations
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            // handle the error
        	ex.printStackTrace();
        }
	}

	protected String dbUrl;
	protected String user;
	protected String pswd;

	protected Connection conn;

	public Service(String url, String usr, String psw) throws SQLException {
		dbUrl = url;
		user = usr;
		pswd = psw;
	}
	
	public void createConnection(boolean autoCommit) throws SQLException {
		conn = DriverManager.getConnection(dbUrl, user, pswd);
		conn.setAutoCommit(autoCommit);
	}
	
	public void commit() throws SQLException {
		conn.commit();
	}
	
	public void rollback() throws SQLException {
		conn.rollback();
	}
	
	// generic method for listing named items
	public List<NameBean> getNamedItems(String table, String pkName, String nameColumn) throws SQLException {
		
		List<NameBean> items = new ArrayList<NameBean>();

		String sql = String.format("SELECT %s, %s FROM %s", pkName, nameColumn, table);		
		
		try (PreparedStatement stmt = conn.prepareStatement(sql)) {
			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {
				NameBean bean = new NameBean();
				bean.setPk(rs.getInt(1));
				bean.setName(rs.getString(2));
				items.add(bean);
			}
			
			return items;

		} catch (SQLException ex) {
			throw ex;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
