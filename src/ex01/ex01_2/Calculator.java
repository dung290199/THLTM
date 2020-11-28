package ex01.ex01_2;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.Vector;

public class Calculator extends Thread{
	private Socket socket;
	private DataInputStream din;
	private DataOutputStream dos;
	public Calculator(Socket socket) throws Exception{
		this.socket = socket;
		din = new DataInputStream(this.socket.getInputStream());
		dos = new DataOutputStream(this.socket.getOutputStream());
		start();
	}
	
	public void run() {
		try {
			while (true) {
				String text = din.readUTF();
				System.out.println(text);
				text = text.replaceAll(" ", "");
				String result;
				try {
					result = calculate(text);
				} catch(Exception e) {
					result = "Error Expression";
				}
				dos.writeUTF(result);
			}
		} catch(Exception ex) {
//			ex.printStackTrace();
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
	
	public static void main(String[] args) {
		String str = "(- 89 - 17/7 + 90) - (8 + 9)";
		str = str.replaceAll(" ", "");
		try {
			String result = calculate(str);
			System.out.println("Ket qua: " + result);
		} catch(Exception e) {
			System.out.println("Loi bieu thuc");
		}	
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

