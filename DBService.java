package com.dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBService {

	/**
	 * get DBConnection
	 * @return Current Connection
	 */
	private static Connection getConn()
	{
//		String driver = "org.postgresql.Driver";
//      String url = "jdbc:postgresql://127.0.0.1:5432/dbname";
//		String user = "USER";
//		String pwd = "PWD";
		String driver = "org.sqlite.JDBC";
		String url = "jdbc:sqlite:Student.db";
        Connection conn = null;
		try
        {
	        Class.forName(driver);
	        conn = DriverManager.getConnection(url);
//	        Class.forName(driver).newInstance();
//	        conn = DriverManager.getConnection(url,user,pwd);
        }
         catch(Exception e)
        {
        	 e.printStackTrace();
        }
		return conn;
	}
	
	/**
	 * insert
	 * @param map
	 * @return
	 */
	public static int insert(Map<String, Object> map) {
	    Connection conn = getConn();
	    int i = 0;
	    StringBuffer sql = new StringBuffer();
	    sql.append("insert into Student (name, weight, height, color, gpa) values ( '");
	    sql.append(map.get("name")+"' ");
	    sql.append(","+map.get("weight"));
	    sql.append(","+map.get("height"));
	    sql.append(", '"+map.get("color")+"' ");
	    sql.append(", '"+map.get("gpa")+"' ");
	    sql.append(")");
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql.toString());
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	/**
	 * update
	 * @param map
	 * @return
	 */
	public static int update(Map<String, Object> map) {
	    Connection conn = getConn();
	    int i = 0;
	    StringBuffer sql = new StringBuffer();
	    sql.append("update Student set");
	    if(!"".equals(null_Turns(map.get("name").toString()))){
	    	sql.append(" name = '"+ map.get("name") +"'");
	    }
	    if(!"".equals(null_Turns(map.get("weight").toString()))){
	    	sql.append(" ,weight = '"+ map.get("weight") +"'");
	    }
	    if(!"".equals(null_Turns(map.get("height").toString()))){
	    	sql.append(" ,height = '"+ map.get("height") +"'");
	    }
	    if(!"".equals(null_Turns(map.get("color").toString()))){
	    	sql.append(" ,color = '"+ map.get("color") +"'");
	    }
	    if(!"".equals(null_Turns(map.get("gpa").toString()))){
	    	sql.append(" ,gpa = '"+ map.get("gpa") +"'");
	    }
	    sql.append(" where id = "+ map.get("id"));
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql.toString());
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	/**
	 * delete
	 * @param id
	 * @return
	 */
	public static int delete(String id) {
	    Connection conn = getConn();
	    int i = 0;
	    String sql = "delete from Student where id=" + id;
	    PreparedStatement pstmt;
	    try {
	        pstmt = (PreparedStatement) conn.prepareStatement(sql);
	        i = pstmt.executeUpdate();
	        pstmt.close();
	        conn.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return i;
	}
	
	/**
	 * search
	 * @return
	 */
	public static List<Map<String, Object>> getAllResult() {
	    Connection conn = getConn();
	    String sql = "select * from Student";
	    PreparedStatement pstmt;
	    List<Map<String, Object>> arr = new ArrayList<Map<String, Object>>();
	    try {
	        pstmt = (PreparedStatement)conn.prepareStatement(sql);
	        ResultSet rs = pstmt.executeQuery();
	        Map<String, Object> map;
	        while (rs.next()) {
	        	map = new HashMap<String, Object>();
	        	map.put("id", rs.getInt("id"));
	        	map.put("name", null_Turns(rs.getString("name")));
	        	map.put("weight", rs.getInt("weight"));
	        	map.put("height", rs.getInt("height"));
	        	map.put("color", null_Turns(rs.getString("color")));
	        	map.put("gpa", null_Turns(rs.getString("gpa")));
	        	arr.add(map);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return arr;
	}
	
	public static String null_Turns(String str){
		if(str == null || str.equals("")){
			str = "";
		}
		return str;
	}
	
}
