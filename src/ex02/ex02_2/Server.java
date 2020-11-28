package ex02.ex02_2;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Server {
	public static void main(String argv[]) throws Exception
    {   
		String stringFromClient;
		
        try {
        	DatagramSocket socket = new DatagramSocket(8000);
            
            while(true) {
            	DatagramPacket in = new DatagramPacket(new byte[1000], 1000);
            	socket.receive(in);
            	stringFromClient = new String(in.getData()).substring(0, in.getLength()).replaceAll(" ", "");
            	
            	String result = calculate(stringFromClient);
            	System.out.println(result);
                
                DatagramPacket out = new DatagramPacket(result.getBytes(), result.length(), in.getAddress(), in.getPort());
                socket.send(out);
            }
        }catch(Exception e) {
        	e.printStackTrace();
        }
    }

	public static int getPriority(char c) {
		if (c == '(') return 0;
		else if (c == '+' || c == '-') return 1;
		else return 2;
	}
	
	public static boolean isDigit(char c) {
		if (c >= '0' && c <= '9') return true;
		return false;
	}
	
	public static boolean isOperation(char c) {
		if (c == '-' || c == '+' || c == '/' || c == '*') return true;
		return false;
	}
	
	public static boolean isOperation(String s) {
		if (s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")) return true;
		return false;
	}
	
	public static float cal(float nd1, float nd2, char op) throws Exception{
		switch (op) {
			case '+': return nd1 + nd2;
			case '-': return nd1 - nd2;
			case '*': return nd1 * nd2;
			case '/': 
				if (nd2 == 0) throw new Exception();
				return nd1 / nd2;
		}
		return 0;
	}
	
	public static String calculate(String str) throws Exception{
		Stack<String> ndStack = new Stack<String>();
		Stack<Character> opStack = new Stack<Character>();
		int len = str.length(), i = 0;
		while (i < len) {
			char c = str.charAt(i);
			if (isDigit(c)) {
				int j = i + 1;
				while (j < len && isDigit(str.charAt(j))) j++;
				ndStack.push(str.substring(i,j));
				i = j;
			}
			else if ( c == '(') {
				opStack.push(c);
				i++;
			}
			else if (isOperation(c)) {
				while (!opStack.isEmpty() && isOperation(opStack.peek()) 
						&& getPriority(c) <= getPriority(opStack.peek())) {
					ndStack.push("" + opStack.pop());	
				}
				opStack.push(c);
				i++;
			}
			else if (c == ')') {
				while (!opStack.isEmpty() && opStack.peek() != '(') {
					ndStack.push("" + opStack.pop());
				}
				opStack.pop();
				i++;
			} 
			else throw new Exception();
		}
		while (!opStack.isEmpty()) ndStack.push("" + opStack.pop());
		System.out.println(ndStack);
		Iterator<String> itr = ndStack.iterator();
		List<String> list = new ArrayList<String>();
		while (itr.hasNext()) {
			String st = itr.next();
			if (isOperation(st)) {
				System.out.println(list);
				float nd2 = Float.parseFloat(list.remove(list.size() - 1));
				float nd1 = Float.parseFloat(list.remove(list.size() - 1));
				list.add(Float.toString(cal(nd1, nd2, st.charAt(0))));
			} else {
				list.add(st);
			}
		}
		return list.get(0);
	}
}
