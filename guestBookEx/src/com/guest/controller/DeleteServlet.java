package com.guest.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.guest.service.DeleteMessageService;
import com.guest.service.InvalidPasswordException;
import com.guest.service.MessageNotFoundException;


@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public DeleteServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DeleteMessageService service = DeleteMessageService.getInstance();
		
		String idStr = request.getParameter("id");
		String password = request.getParameter("password");
		String message = "";
		if(idStr == null || idStr.trim().isEmpty() || password == null || password.trim().isEmpty()) {
			message ="공백은 허용되지 않습니다.";
		}else {
		try {
			int id = Integer.valueOf(idStr);
		service.deleteMessage(id, password);
		message = "메세지가 삭제되었습니다.";
		}catch(InvalidPasswordException e) {
			message ="암호가 올바르지 않습니다.";
		}catch(MessageNotFoundException e1) {
			message ="해당 메세지가 존재하지 않습니다.";
		}catch(NumberFormatException e2) {
			message ="아이디는 숫자로만 구성됩니다.";
		}
	}		
		HttpSession session = request.getSession();
		session.setAttribute("deleteMessage",message );
		response.sendRedirect("/main");
	}

}
