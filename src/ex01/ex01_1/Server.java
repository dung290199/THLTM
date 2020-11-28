package ex01.ex01_1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	public static void main(String argv[]) throws Exception
    {   
		String stringFromClient;
		String s1, s2, s3, result;
		
        try {
        	ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("Server started!!");
            
            while(true) {
            	
                Socket server = serverSocket.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(server.getInputStream())); 
                DataOutputStream out = new DataOutputStream(server.getOutputStream());
                
                stringFromClient = in.readLine();
                s1 = toUpperCase(stringFromClient) + "\n";
                s2 = toLowerCase(stringFromClient) + "\n";
                s3 = "Number of words: " + stringFromClient.split(" ").length + "\n";
                result = s1 + s2 + s3;
                System.out.println("result: " + result);
                out.writeBytes(result);;
                return;
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
