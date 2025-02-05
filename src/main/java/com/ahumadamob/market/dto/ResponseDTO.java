package com.ahumadamob.market.dto;

import java.util.ArrayList;
import java.util.List;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

public class ResponseDTO<T> {
	private int status;
	private List<Message> messages;
	private T data;
	
	public ResponseDTO(int status){
		this.status = status;
		this.messages = new ArrayList<Message>();
	}	
	
	public ResponseDTO(int status, T data){
		this.status = status;
		this.data = data;
		this.messages = new ArrayList<Message>();
	}
	
	public ResponseDTO(int status, ConstraintViolationException ex){
		this.status = status;
		this.messages = new ArrayList<Message>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
        	addError(violation.getPropertyPath().toString(), violation.getMessage());
        }
	}	

	public void addError(String field, String content) {
		Message msg = new Message(MessageType.ERROR, field, content);
		this.messages.add(msg);		
	}
	
	public void addWarning(String field, String content) {
		Message msg = new Message(MessageType.WARNING, field, content);
		this.messages.add(msg);		
	}
	
	public void addInfo(String field, String content) {
		Message msg = new Message(MessageType.INFO, field, content);
		this.messages.add(msg);		
	}

	public int getStatus() {
		return status;
	}

	public List<Message> getMessages() {
		return messages;
	}

	public T getData() {
		return data;
	}

	
	
	
}
