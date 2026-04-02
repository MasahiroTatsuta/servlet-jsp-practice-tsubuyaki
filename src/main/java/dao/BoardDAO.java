package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BoardDAO extends DAO {
	
	public void insertContents(String loginId, String contents) throws Exception {
		java.time.LocalDateTime now = java.time.LocalDateTime.now();
		// 接続
		Connection con = getConnection();
		// SQL文準備
		PreparedStatement st;
		st = con.prepareStatement(
		"insert into board2 (id, mydate, login_id, contents) values (null, ?, ?, ?);");
		st.setObject(1, now);
		st.setString(2, loginId);
		st.setString(3, contents);
		int result = st.executeUpdate();
		
		// DB接続クローズ
		st.close();
		con.close();
	}
	
	public List<Map<String, String>> viewContents() throws Exception {
		List<Map<String, String>> contentsList = new ArrayList<>();
		// 1. 接続
	    Connection con = getConnection();
	    // SQL文準備
 		PreparedStatement st;
 		st = con.prepareStatement(
 		"SELECT id, mydate, login_id, contents, likes, dislikes FROM board2 ORDER BY myDate DESC;");
 		
 		// 3. SQL文実行
 	    ResultSet rs = st.executeQuery();
 	    
 	    while(rs.next()) {
 	        Map<String, String> boardMap = new HashMap<>();
 	        
 	       boardMap.put("id", rs.getString("id"));

 	    // 1. DBから文字列として取得 (2026-04-01 13:02:14)
 	       String rawDate = rs.getString("mydate");
 	       
 	       if (rawDate != null) {
 	           // 2. 解析用のフォーマッター（DBの形式に合わせる）
 	           DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
 	           // 3. 表示用のフォーマッター（日本語形式）
 	           DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy年M月d日 H時m分s秒");
 	           
 	           // 4. 文字列 → LocalDateTime → フォーマット済み文字列
 	           LocalDateTime dateTime = LocalDateTime.parse(rawDate, inputFormatter);
 	           String formattedDate = dateTime.format(outputFormatter);
 	           
 	           boardMap.put("myDate", formattedDate);
 	       } else {
 	           boardMap.put("myDate", "");
 	       }	        

 	        boardMap.put("loginId", rs.getString("login_id")); 

 	        boardMap.put("contents", rs.getString("contents"));
 	        
 	        boardMap.put("likes", rs.getString("likes")); 

	        boardMap.put("disLikes", rs.getString("dislikes"));
 	        
 	        contentsList.add(boardMap);
 	    }
		
		return contentsList;
	}
	
	public List<Map<String, String>> viewContents(String loginId) throws Exception {
		List<Map<String, String>> contentsList = new ArrayList<>();
		// 1. 接続
	    Connection con = getConnection();
	    // SQL文準備
 		PreparedStatement st;
 		st = con.prepareStatement(
 		"SELECT id, mydate, login_id, contents, likes, dislikes FROM board2 WHERE login_id=? ORDER BY myDate DESC;");
 		st.setString(1, loginId);
 		
 		// 3. SQL文実行
 	    ResultSet rs = st.executeQuery();
 	    
 	    while(rs.next()) {
 	        Map<String, String> boardMap = new HashMap<>();
 	        
 	       boardMap.put("id", rs.getString("id"));

 	    // 1. DBから文字列として取得 (2026-04-01 13:02:14)
 	       String rawDate = rs.getString("mydate");
 	       
 	       if (rawDate != null) {
 	           // 2. 解析用のフォーマッター（DBの形式に合わせる）
 	           DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
 	           // 3. 表示用のフォーマッター（日本語形式）
 	           DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("yyyy年M月d日 H時m分s秒");
 	           
 	           // 4. 文字列 → LocalDateTime → フォーマット済み文字列
 	           LocalDateTime dateTime = LocalDateTime.parse(rawDate, inputFormatter);
 	           String formattedDate = dateTime.format(outputFormatter);
 	           
 	           boardMap.put("myDate", formattedDate);
 	       } else {
 	           boardMap.put("myDate", "");
 	       }	        

 	        boardMap.put("loginId", rs.getString("login_id")); 

 	        boardMap.put("contents", rs.getString("contents"));
 	        
 	        boardMap.put("likes", rs.getString("likes")); 

	        boardMap.put("disLikes", rs.getString("dislikes"));
 	        
 	        contentsList.add(boardMap);
 	    }
		
		return contentsList;
	}
	
	public void deleteBoardItem(String id) throws Exception {
		Connection con = getConnection();
	    // IDを指定して1件だけ削除するSQL
	    PreparedStatement st;
	    st = con.prepareStatement(
	    "DELETE FROM board2 WHERE id = ?");
	    st.setString(1, id);
	    
	    st.executeUpdate();
	    
	    st.close();
	    con.close();
	    
	}
	
	public void deleteBoard(String loginId) throws Exception {
		Connection con = getConnection();
	    // 同じloginIDの投稿をすべて削除するSQL
	    PreparedStatement st;
	    st = con.prepareStatement(
	    "DELETE FROM board2 WHERE login_id = ?");
	    st.setString(1, loginId);
	    
	    st.executeUpdate();
	    
	    st.close();
	    con.close();
	    
	}
	
	public void updateLike(String id) throws Exception {
		Connection con = getConnection();
		PreparedStatement st;
	    st = con.prepareStatement(
	    "UPDATE board2 SET likes=likes+1 WHERE id = ?");
	    st.setString(1, id);
	    
	    st.executeUpdate();
	    st.close();
	    con.close();
		
	}
	
	public void updateDisLike(String id) throws Exception {
		Connection con = getConnection();
		PreparedStatement st;
	    st = con.prepareStatement(
	    "UPDATE board2 SET dislikes=dislikes+1 WHERE id = ?");
	    st.setString(1, id);
	    
	    st.executeUpdate();
	    st.close();
	    con.close();
		
	}
	

}
