package ex01.ex01_2;

import java.net.ServerSocket;
import java.net.Socket;

public class CalculatorServer {
	private static ServerSocket server;
	
	public static void main(String[] args) {
		System.out.println("Server is started");
		try {
			server = new ServerSocket(7001);
			while (true) {
				Socket socket = server.accept();
				new Calculator(socket);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
}

