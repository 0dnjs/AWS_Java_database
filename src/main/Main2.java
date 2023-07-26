package main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import config.DBConnectionMgr;

public class Main2 {
	public static void main(String[] args) {
		System.out.println(getProductByProductCode(20230701));
		insertProduct(20230707, "상품7");
	}
	
	
	
	
	
	public static void insertProduct(int productCode, String productName) {
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		try {

			con = pool.getConnection();
			String sql = "insert into product_tb values(?, ?)";
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, productCode);
			pstmt.setString(2, productName);

			pstmt.executeUpdate(); 
	
			System.out.println("Successfully!!");

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.getInstance().freeConnection(con, pstmt, rs);
		}
	}
	
	public static Map<String, Object> getProductByProductCode(int productCode) {
		Map<String, Object> resultMap = new HashMap<>();
		
		DBConnectionMgr pool = DBConnectionMgr.getInstance();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = pool.getConnection();
			String sql = "select product_code, product_name from product_tb where product_code = ?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1,  productCode);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				resultMap.put("product_code", rs.getInt(1));
				resultMap.put("product_name", rs.getString(2));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pool.freeConnection(con, pstmt, rs);
		}
		
		return resultMap;
	}
}
