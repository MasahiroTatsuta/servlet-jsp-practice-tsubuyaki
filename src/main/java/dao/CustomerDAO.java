package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Customer2;

public class CustomerDAO extends DAO {
	// ログイン名とパスワードからDB検索
	public Customer2 search(String loginId, String password) throws Exception {
		Customer2 customer = null;
		// 接続
		Connection con = getConnection();
		// SQL文準備
		PreparedStatement st;
		st = con.prepareStatement(
		"select * from customer2 where binary login_id=? and binary password=?");
		st.setString(1, loginId);
		st.setString(2, password);
		// SQL文実行
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			customer = new Customer2();
			customer.setId(rs.getInt("id"));
			customer.setLoginId(rs.getString("login_id"));
			customer.setPassword(rs.getString("password"));
			customer.setRole(rs.getString("role"));
		}
		// DB接続クローズ
		rs.close();
		st.close();
		con.close();
		
		return customer;
	}
	
	// ログイン名からDB検索
	public Customer2 search(String loginId) throws Exception {
		Customer2 customer = null;
		// 接続
		Connection con = getConnection();
		// SQL文準備
		PreparedStatement st;
		st = con.prepareStatement(
		"select * from customer2 where binary login_id=?");
		st.setString(1, loginId);
		// SQL文実行
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			customer = new Customer2();
			customer.setId(rs.getInt("id"));
			customer.setLoginId(rs.getString("login_id"));
			customer.setPassword(rs.getString("password"));
			customer.setRole(rs.getString("role"));
		}
		// DB接続クローズ
		rs.close();
		st.close();
		con.close();
		
		return customer;
	}
	
	// DBにアカウントを登録
	public void register(String loginId, String password, String role) throws Exception {
		// 接続
		Connection con = getConnection();
		// SQL文準備
		PreparedStatement st;
		st = con.prepareStatement(
		"insert into customer2 values (null, ?, ?, ?);");
		st.setString(1, loginId);
		st.setString(2, password);
		st.setString(3, role);
		int result = st.executeUpdate();
		
		// DB接続クローズ
		st.close();
		con.close();
	}
	
	// パスワードを変更
	public void passwordUpdate(String loginId, String password) throws Exception {
		// 接続
		Connection con = getConnection();
		// SQL文準備
		PreparedStatement st;
		st = con.prepareStatement(
		"update customer2 set password=? where login_id=?;");
		st.setString(1, password);
		st.setString(2, loginId);
		int result = st.executeUpdate();
		
		// DB接続クローズ
		st.close();
		con.close();
	}
	
	public List<Map<String, String>> viewAccounts() throws Exception {
		List<Map<String, String>> loginIdList = new ArrayList<>();
		// 接続
		Connection con = getConnection();
		// SQL文準備
		PreparedStatement st;
		st = con.prepareStatement(
		"select login_id,role from customer2");
		// SQL文実行
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			Map<String, String> loginMap = new HashMap<>();
			loginMap.put("loginId",rs.getString("login_id"));
			loginMap.put("role",rs.getString("role"));
			loginIdList.add(loginMap);
		}
		// DB接続クローズ
		rs.close();
		st.close();
		con.close();
		
		return loginIdList;
	}

}
