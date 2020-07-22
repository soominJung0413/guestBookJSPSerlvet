package com.guest.test;

import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guest.dao.MessageDao;
import com.guest.jdbc.ConnectionProvider;
import com.guest.model.Message;


@WebServlet("/test/selectlist")
public class MessageDaoSelectListTestSerlvet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public MessageDaoSelectListTestSerlvet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		MessageDao dao = MessageDao.getInstance();
		Connection con = null;
		try {
			con = ConnectionProvider.getConnection();
			List<Message> testlist = 	dao.selectList(con, 1, 65);
			
			testlist.forEach( message -> System.out.println(message.getId()+" > \n"+message) );
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 super.doGet(request, response);
	}

}
