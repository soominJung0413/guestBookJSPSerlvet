package com.guest.service;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guest.model.Message;


@WebServlet("/test/writemessagetest")
public class WriteMessageServiceTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public WriteMessageServiceTestServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WriteMessageService serivce = WriteMessageService.getInstance();
		
		Message message = new Message();
		message.setGuestName("홍길홍길");
		message.setPassword("1234");
		message.setMessage("안녕하세요 :)");
		
		serivce.write(message);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
