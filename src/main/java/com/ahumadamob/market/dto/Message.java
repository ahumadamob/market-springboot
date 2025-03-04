package com.ahumadamob.market.dto;

public class Message {
	private MessageType type;
	private String field;
	private String content;
	
	public Message(MessageType type, String field, String content) {
		super();
		this.type = type;
		this.field = field;
		this.content = content;
	}

	public MessageType getType() {
		return type;
	}

	public String getField() {
		return field;
	}

	public String getContent() {
		return content;
	}
	
	
	
	
	
	
}
