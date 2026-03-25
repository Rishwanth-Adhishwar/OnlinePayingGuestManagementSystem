package com.guestmanagement.model;

public class Message {
	 public int    id;
	    public int    tenantId;
	    public String tenantName;
	    public String text;
	    public String date;

	    public Message(int id, int tenantId, String tenantName, String text, String date) {
	        this.id         = id;
	        this.tenantId   = tenantId;
	        this.tenantName = tenantName;
	        this.text       = text;
	        this.date       = date;
	    }

	    public String toString() {
	        return "MsgID:" + id + " | From:" + tenantName + " | " + date + "\n   " + text;
	    }
}
