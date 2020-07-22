package com.guest.test;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.guest.model.Message;
import com.guest.service.GetMessageListService;
import com.guest.service.MessageListView;


@WebServlet("/test/messageviewtest")
public class GetMessageListServiceTestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public GetMessageListServiceTestServlet() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GetMessageListService service = GetMessageListService.getInstance();
		MessageListView view = service.getMessageList(1);
		
		System.out.println(view);
		List<Message> l = view.getMessageList();
		System.out.println();
		l.forEach( message -> System.out.println(message));
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
