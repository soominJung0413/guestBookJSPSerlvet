package com.guest.service;

import java.util.List;


import com.guest.model.Message;

//@Service
public class MessageListView {
	
	private List<Message> messageList;
	private int currenPageNumber;
	private int messageTotalCount;
	private int pageTotalCount;
	private int messageCountPerPage;
	private int firstRow;
	private int endRow;
	public MessageListView(List<Message> messageList,  int messageTotalCount, int currenPageNumber, int messageCountPerPage,
			 int firstRow, int endRow) {
		this.messageList = messageList;
		this.currenPageNumber = currenPageNumber;
		this.messageTotalCount = messageTotalCount;
		this.messageCountPerPage = messageCountPerPage;
		this.firstRow = firstRow;
		this.endRow = endRow;
		
		calculatePageTotalCount();
	}
	private void calculatePageTotalCount() {
		if(messageTotalCount == 0) {
			pageTotalCount =0;
		}else {
			pageTotalCount = messageTotalCount/messageCountPerPage;
			if(messageTotalCount % messageCountPerPage >0) {
				pageTotalCount++;
			}
		}
	}
	
	public int getMessageTotalCount() {
		return messageTotalCount;
	}
	public int getCurrenPageNumber() {
		return currenPageNumber;
	}
	
	public List<Message> getMessageList() {
		return messageList;
	}
	
	public int getPageTotalCount() {
		return pageTotalCount;
	}
	
	public int getMessageCountPerPage() {
		return messageCountPerPage;
	}
	public int getFirstRow() {
		return firstRow;
	}
	
	public int getEndRow() {
		return endRow;
	}
	
	public boolean isEmpty() {
		return messageTotalCount == 0;
	}
	@Override
	public String toString() {
		return "MessageListView [messageList=" + messageList + ", currenPageNumber=" + currenPageNumber
				+ ", messageTotalCount=" + messageTotalCount + ", pageTotalCount=" + pageTotalCount
				+ ", messageCountPerPage=" + messageCountPerPage + ", firstRow=" + firstRow + ", endRow=" + endRow
				+ "]";
	}
	
	
	
}
