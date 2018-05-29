package bean;

import java.io.Serializable;

public class Mymail implements Serializable {
	/**
	 * 发件人密码(授权码)
	 */
	String password;
	/**
	 * 发件人帐号
	 */
	String sender;
	/**
	 * 收件人帐号
	 */
	String receiver;
	/**
	 * 主题
	 */
	String title;
	/**
	 * 正文
	 */
	String message;
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}
