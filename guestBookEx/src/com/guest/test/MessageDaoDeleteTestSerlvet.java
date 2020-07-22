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


@WebServlet("/test/delete")
public class MessageDaoDeleteTestSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MessageDaoDeleteTestSerlvet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection con = null;
		int messageId =3;
		try {
			con = ConnectionProvider.getConnection();
			MessageDao dao = MessageDao.getInstance();
			int count = dao.delete(con, messageId);
			System.out.println(count +"개 삭제 됨");
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			JdbcUtil.close(con);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
