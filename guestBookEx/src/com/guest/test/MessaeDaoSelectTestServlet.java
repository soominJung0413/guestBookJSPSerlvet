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

@WebServlet("/test/selectone")
public class MessaeDaoSelectTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MessaeDaoSelectTestServlet() {
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		MessageDao dao = MessageDao.getInstance();
		Connection con = null;
		try {
		con = ConnectionProvider.getConnection();
		Message message = dao.select(con, 2);
		System.out.println(message);
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			JdbcUtil.close(con);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request, response);
	}

}
