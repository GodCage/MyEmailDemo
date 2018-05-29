package mail;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import bean.Mymail;

public class SMTPclint {

	public static void main(String[] args) {
		   try {
			Socket socket = new Socket("localhost", 10086);
			ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
			Mymail mymail = new Mymail();
			mymail.setSender("kage_wang@163.com");
			mymail.setPassword("864676212wkq");
			mymail.setReceiver("864676212@qq.com");
			mymail.setTitle("测试一下");
			mymail.setMessage("socket测试正文");			
			out.writeObject(mymail);
			out.close();
			socket.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		   

	}

}
