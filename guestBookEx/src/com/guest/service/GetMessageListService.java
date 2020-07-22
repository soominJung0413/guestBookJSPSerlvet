package com.guest.service;

import java.sql.Connection;
import java.util.Collections;
import java.util.List;

import com.guest.dao.MessageDao;
import com.guest.jdbc.ConnectionProvider;
import com.guest.jdbc.JdbcUtil;
import com.guest.model.Message;
import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

//@Service
public class GetMessageListService {
private static GetMessageListService instance = new GetMessageListService();

	private GetMessageListService() {
	}
	
	public static GetMessageListService getInstance() {
		return instance;
	}
	
	private static final int MESSAGE_COUNT_PER_PAGE = 3;
	
	public MessageListView getMessageList(int pageNumber) {
		Connection con =null;
		int currentPageNumber = pageNumber;
		try {
			con = ConnectionProvider.getConnection();
			MessageDao dao = MessageDao.getInstance();
			
			int messageTotalCount = dao.selectCount(con);
			
			List<Message> messageList = null;
			int firstRow =0;
			int endRow = 0;
			if(messageTotalCount > 0) {
				firstRow = (pageNumber -1 )*MESSAGE_COUNT_PER_PAGE +1;
				endRow = firstRow + MESSAGE_COUNT_PER_PAGE -1;
				messageList = dao.selectList(con, firstRow, endRow);
			}else {
				currentPageNumber = 0;
				messageList = Collections.emptyList();
			}
			return new MessageListView(messageList, messageTotalCount, currentPageNumber, 
					MESSAGE_COUNT_PER_PAGE,firstRow,endRow);
		}catch(Exception e) {
			throw new ServiceException("목록구하기 실패: "+e.getMessage(),e);
		}finally {
			JdbcUtil.close(con);
		}
	}
}
