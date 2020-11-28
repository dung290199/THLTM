package ex02.ex02_1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Client {
	public static void main(String argv[]) throws Exception {
		try {  
			DatagramSocket socket = new DatagramSocket();
			
	        System.out.print("Please enter any sentences : ");
	        Scanner scanner = new Scanner(System.in);
	        String string = scanner.nextLine(); 
	        
	        DatagramPacket out = new DatagramPacket(string.getBytes(), string.length(), InetAddress.getByName("localhost"), 8000);
	        socket.send(out);
	        
	        DatagramPacket in = new DatagramPacket(new byte[1000], 1000);
	        socket.receive(in);
	        
	        String reveiveFromServer = new String(in.getData()).substring(0, in.getLength());
	        System.out.println(reveiveFromServer);

		} catch(Exception e) {
			e.printStackTrace();
		}
    } 
}
