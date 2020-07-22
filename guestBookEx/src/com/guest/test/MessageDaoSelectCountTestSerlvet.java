package com.guest.test;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guest.dao.MessageDao;
import com.guest.jdbc.ConnectionProvider;
import com.guest.jdbc.JdbcUtil;


@WebServlet("/test/selectcount")
public class MessageDaoSelectCountTestSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MessageDaoSelectCountTestSerlvet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		MessageDao dao = MessageDao.getInstance();
		try {
			con = ConnectionProvider.getConnection();
			int recodeCount = dao.selectCount(con);
			System.out.println(recodeCount);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(con);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		super.doGet(request,response);
	}

}
