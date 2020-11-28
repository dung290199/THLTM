package ex01.ex01_3;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.CharArrayWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatRoom extends Thread{
	private Socket socket;
	private BufferedReader bf;
	private static ByteArrayOutputStream byteArr;
	private static List<Socket> list;
	static {
		byteArr = new ByteArrayOutputStream();
		list = new ArrayList<Socket>();
	}
	
	public ChatRoom(Socket s) throws Exception{
		socket = s;
		bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		list.add(socket);
		System.out.println("Accept a client");
		bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		start();
	}
	
	public void run() {
		try {
			while (true) {
				if (socket != null) {
					String str =  "";
					while ((str  = bf.readLine()) != null) {
						byteArr.write(str.getBytes());
						byteArr.write(13);
						byteArr.write(10);
						for (int i = 0; i < list.size(); i++) {
							try {
								byteArr.writeTo(list.get(i).getOutputStream());
							} catch(Exception ex) {};
						}
						byteArr.flush();
						byteArr.reset();
					}
				}
			}
		} catch(Exception ex) {}
	}
}
