package com.guest.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.guest.jdbc.JdbcUtil;
import com.guest.model.Message;

//@Repository
public class MessageDao {
	private static MessageDao messageDao = new MessageDao();
	
	public static MessageDao getInstance() {//   싱글톤 자원낭비를 막음
		return messageDao;
	}
	
	private MessageDao() {}// <<<<<생성자 접근을 막아서 인스턴스 생성불가하게 만듬
	
	public int insert(Connection con, Message message) throws SQLException{
		PreparedStatement pstmt = null;
		try{
			pstmt =con.prepareStatement("insert into guestbook_message (guest_name, password, message) values (?, ?, ?)");
			pstmt.setString(1, message.getGuestName());
			pstmt.setString(2, message.getPassword());
			pstmt.setString(3, message.getMessage());
			return pstmt.executeUpdate();
		}finally {
			JdbcUtil.close(pstmt);
		}
	}
	
	public Message select(Connection con, int messageId) throws SQLException{
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("select * from guestbook_message where message_id = ?");
			pstmt.setInt(1, messageId);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return makeMessageFromResultSet(rs);
			}else {
				return null;
			}
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}

	private Message makeMessageFromResultSet(ResultSet rs) throws SQLException{
		Message message = new Message();
			message.setId(rs.getInt("message_id"));
			message.setGuestName(rs.getString("guest_name"));
			message.setPassword(rs.getString("password"));
			message.setMessage(rs.getString("message"));
			return message;
	}
	
	public int selectCount(Connection con) throws SQLException{
		Statement stmt = null;
		ResultSet rs = null;
		try {
			stmt = con.createStatement();
			rs = stmt.executeQuery("select count(*) from guestbook_message");
			rs.next();
			return rs.getInt(1);
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(stmt);
		}
	}
	
	public List<Message> selectList(Connection con, int firstRow, int endRow) throws SQLException{
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			pstmt = con.prepareStatement("select * from guestbook_message order by message_id desc limit ?, ?");
			pstmt.setInt(1, firstRow-1);
			pstmt.setInt(2, endRow - firstRow+1);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				List<Message> messageList = new ArrayList<>();
				do {
					messageList.add(makeMessageFromResultSet(rs));
				}while(rs.next());
				return messageList;
			}else {
				return Collections.emptyList();
			}
		}finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
		}
	}
	
	public int delete(Connection con, int messageId)throws SQLException{
		PreparedStatement pstmt = null;
		try {			
		pstmt = con.prepareStatement("delete from guestbook_message where message_id =?");
		pstmt.setInt(1, messageId);
		return pstmt.executeUpdate();
		}finally{
			JdbcUtil.close(pstmt);
		}
	}
	
}
