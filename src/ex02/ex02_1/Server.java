package ex02.ex02_1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String argv[]) throws Exception
    {   
		String stringFromClient;
		String s1, s2, s3, result;
		
        try {
        	DatagramSocket socket = new DatagramSocket(8000);
            
            while(true) {
            	DatagramPacket in = new DatagramPacket(new byte[1000], 1000);
            	socket.receive(in);
            	stringFromClient = new String(in.getData()).substring(0, in.getLength());
                
                s1 = toUpperCase(stringFromClient) + "\n";
                s2 = toLowerCase(stringFromClient) + "\n";
                s3 = "Number of words: " + stringFromClient.split(" ").length + "\n";
                result = s1 + s2 + s3;
                System.out.println("result: " + result);
                
                DatagramPacket out = new DatagramPacket(result.getBytes(), result.length(), in.getAddress(), in.getPort());
                socket.send(out);
            }
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }

	private static String toLowerCase(String stringFromClient) {
		String string = "";
		char c;
		
		for (int i = 0; i < stringFromClient.length(); i++) {
			c = stringFromClient.charAt(i);
			string += (char) ((c >= 97 && c <= 122) ? (c - 32) : c);			
		}
		 
		return string;
	}

	private static String toUpperCase(String stringFromClient) {
		String string = "";
		String add = "";
		char c;
		
		for (int i = 0; i < stringFromClient.length(); i++) {
			c = stringFromClient.charAt(i);
			string += (char) ((c >= 65 && c <= 90) ? (c + 32) : c);			
		}
		
		return string;
	}
}
