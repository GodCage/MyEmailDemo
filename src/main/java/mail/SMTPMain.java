package mail;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import bean.Mymail;
import sun.misc.BASE64Encoder;

public class SMTPMain {
	public static void main(String[] args) throws ClassNotFoundException {
		try {
			ServerSocket serverSocket = new ServerSocket(10086);
			while (true) {
				Socket socket = serverSocket.accept();

				// InputStreamReader inputStreamReader = new
				// InputStreamReader(socket.getInputStream());
				ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
				Mymail mymail = (Mymail) objectInputStream.readObject();
				SMTPMain smtpMain = new SMTPMain();
				smtpMain.send(mymail);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void send(Mymail mymail) {

		String password = mymail.getPassword();
		String sender = mymail.getSender();
		String receiver = mymail.getReceiver();
		String title = mymail.getTitle();
		String message = mymail.getMessage();

		String user = new BASE64Encoder().encode(sender.substring(0, sender.indexOf("@")).getBytes());
		String pass = new BASE64Encoder().encode(password.getBytes());
		try {
			Socket socket = new Socket("smtp.163.com", 25);
			InputStream inputStream = socket.getInputStream();
			OutputStream outputStream = socket.getOutputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
			PrintWriter writter = new PrintWriter(outputStream, true); // 这个true很关键!
			System.out.println(reader.readLine());
			// HELO
			writter.println("HELO first");
			// System.out.println(1);
			System.out.println(reader.readLine());
			// AUTH LOGIN
			writter.println("auth login");
			// System.out.println(2);
			System.out.println(reader.readLine());
			writter.println(user);
			// System.out.println(3);
			System.out.println(reader.readLine());
			writter.println(pass);
			System.out.println(reader.readLine());
			// Above Authentication successful <pre name="code" class="java">
			// Set mail from and rcpt to
			writter.println("mail from:<" + sender + ">");
			System.out.println(reader.readLine());
			writter.println("rcpt to:<" + receiver + ">");
			System.out.println(reader.readLine());

			// Set data
			writter.println("data");
			System.out.println(reader.readLine());
			writter.println("subject:" + title);
			writter.println("from:" + sender);
			writter.println("to:" + receiver);
			writter.println("Content-Type: text/plain;charset=\"utf-8\"");
			writter.println();
			writter.println(message);
			writter.println(".");
			writter.println("");
			System.out.println(reader.readLine());

			// Say GoodBye
			writter.println("rset");
			System.out.println(reader.readLine());
			writter.println("quit");
			System.out.println(reader.readLine());
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println(e.getMessage());
			System.out.println("agan");
			send(mymail);
		}
	}
}