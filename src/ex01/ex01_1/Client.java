package ex01.ex01_1;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Client {
	public static void main(String argv[]) throws Exception {
		try {

	        System.out.println("Connecting...");
	        Socket client = new Socket("localhost", 8000);
	        System.out.println("Client is connected to socket server!");
	        
	        System.out.print("Please enter any sentences : ");
	        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
	        String stringToServer = input.readLine(); 
	        
	        DataOutputStream out = new DataOutputStream(client.getOutputStream());
	        BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
	        
	        out.writeBytes( stringToServer + '\n');
	        
	        String s1 = in.readLine();
	        String s2 = in.readLine();
	        String s3 = in.readLine();
	        System.out.println("Output from socket server: ");
	        System.out.println("To upper case sentences: " + s1);
	        System.out.println("To lower case sentences: " + s2);
	        System.out.println(s3);
	        client.close();    

		} catch(Exception e) {
			e.printStackTrace();
		}
    } 
}
