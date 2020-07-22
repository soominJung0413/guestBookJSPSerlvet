package com.guest.service;

import java.sql.Connection;
import java.sql.SQLException;

import com.guest.dao.MessageDao;
import com.guest.jdbc.ConnectionProvider;
import com.guest.jdbc.JdbcUtil;
import com.guest.model.Message;

//@Service
public class DeleteMessageService {
	private static DeleteMessageService instance = new DeleteMessageService();
	
	private DeleteMessageService() {}
	
	public static DeleteMessageService getInstance() {
		return instance;
	}
	
	public void deleteMessage(int messageId, String password) {
		Connection con =null;
		try {
			con = ConnectionProvider.getConnection();
			con.setAutoCommit(false);
			MessageDao dao = MessageDao.getInstance();
			
			Message message = dao.select(con, messageId);
			if(message == null) {
				throw new MessageNotFoundException("메시지 없음");
			}
			if(!message.matchPassword(password)) {
				throw new InvalidPasswordException("bad password");
			}
			dao.delete(con, messageId);
			con.commit();
			
		}catch(SQLException e) {
			JdbcUtil.rollback(con);
			throw new ServiceException("삭제 실패 : "+e.getMessage(), e);		
		}catch(InvalidPasswordException | MessageNotFoundException ex){
			JdbcUtil.rollback(con);
			throw ex;
		}finally {
			JdbcUtil.close(con);
		}
	}
}
