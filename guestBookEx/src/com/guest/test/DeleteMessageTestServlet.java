package com.guest.test;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guest.dao.MessageDao;
import com.guest.service.DeleteMessageService;


@WebServlet("/test/deletemessage")
public class DeleteMessageTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;   

    public DeleteMessageTestServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DeleteMessageService service = DeleteMessageService.getInstance();		
		service.deleteMessage(103, "1234");
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
