package com.guest.service;

import java.sql.Connection;

import com.guest.dao.MessageDao;
import com.guest.jdbc.ConnectionProvider;
import com.guest.jdbc.JdbcUtil;
import com.guest.model.Message;

//@Service
public class WriteMessageService {
	private static WriteMessageService instance = new WriteMessageService();
	
	private WriteMessageService() {}
	
	public static WriteMessageService getInstance() {
		return instance;
	}
	
	public void write(Message message) {
		Connection con =null;
		try {
			con = ConnectionProvider.getConnection();
			MessageDao dao = MessageDao.getInstance();
			dao.insert(con, message);
		}catch(Exception e) {
			throw new ServiceException("메시지 등록 실패: "+e.getMessage(), e);			
		}finally {
			JdbcUtil.close(con);
		}
	}
}
