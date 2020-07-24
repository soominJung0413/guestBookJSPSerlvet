package com.guest.service;

import java.sql.Connection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
			if( message.getGuestName() == null ||message.getGuestName().trim().isEmpty()
					|| message.getMessage() == null || message.getMessage().trim().isEmpty()) {
				throw new ServiceException("메시지 등록에 실패하였습니다. ");
			}else {
				dao.insert(con, message);
			}
//			dao.insert(con, message);
			
		}catch(Exception e) {
			throw new ServiceException("메시지 등록 실패: "+e.getMessage(), e);
		}finally {
			JdbcUtil.close(con);
		}
	}
}
