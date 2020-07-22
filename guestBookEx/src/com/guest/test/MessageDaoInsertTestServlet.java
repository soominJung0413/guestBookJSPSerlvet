package com.guest.test;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guest.dao.MessageDao;
import com.guest.jdbc.ConnectionProvider;
import com.guest.jdbc.JdbcUtil;
import com.guest.model.Message;


@WebServlet("/test/insert")
public class MessageDaoInsertTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MessageDaoInsertTestServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageDao dao =MessageDao.getInstance();
		Connection con = null;
		try {
		con = ConnectionProvider.getConnection();
		Message message= new Message();
		message.setGuestName("홍길동");
		message.setPassword("hong");
		message.setMessage("홍길동 다녀감 :)");		
		dao.insert(con, message);

		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(con);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
