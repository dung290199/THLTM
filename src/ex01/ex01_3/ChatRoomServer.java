package ex01.ex01_3;

import java.net.ServerSocket;
import java.net.Socket;

public class ChatRoomServer {
private static ServerSocket server;
	
	public static void main(String[] args) throws Exception{
		server = new ServerSocket(7002);
		System.out.println("Server is started");
		while (true) {
			Socket socket = server.accept();
			new ChatRoom(socket);
		}
	}
}
