package com.guest.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.guest.model.Message;
import com.guest.service.ServiceException;
import com.guest.service.WriteMessageService;


@WebServlet("/write")
public class WriteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public WriteServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Message message = new Message();
		
		String name = request.getParameter("name");
		String pw = request.getParameter("password");
		String body = request.getParameter("message");
		
		message.setGuestName(name);
		message.setPassword(pw);
		message.setMessage(body);
		
		HttpSession session = request.getSession();
		
		try {
		WriteMessageService service = WriteMessageService.getInstance();
		service.write(message);
		session.setAttribute("info","메시지가 등록되었습니다.");
		}catch(ServiceException e) {
			session.setAttribute("info",e.getMessage());
		}finally {
			response.sendRedirect("main");			
		}
	}

}
