package ex01.ex01_3;

import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;

public class ChatRoomClient {

	private JFrame client;
	private JTextField textField;
	private BufferedReader bf;
	private DataOutputStream dos;
	private Socket socket;
	JTextArea textArea;
	JLabel label;
	public static void main(String[] args) throws Exception{
		EventQueue.invokeLater(new Runnable() {
			public void run() {
            	JFrame f = new JFrame("Chat Room");  
            	JLabel label = new JLabel("Enter Name: ");
            	label.setFont(new Font("Tahoma", Font.BOLD, 20));
            	label.setBounds(20, 20, 150, 60);  
            	final JTextField name = new JTextField();  
            	name.setFont(new Font("Tahoma", Font.BOLD, 20));
                name.setBounds(180, 20, 350, 60);  
                JButton button = new JButton("Create");  
                button.setFont(new Font("Tahoma", Font.BOLD, 20));
                button.setBounds( 550, 20, 120, 60);  
                button.addActionListener(new ActionListener(){  
                	public void actionPerformed(ActionEvent e){ 
                		try {
                			ChatRoomClient window = new ChatRoomClient();
	                		window.label.setText(name.getText());
	                		window.client.setVisible(true);
	                		f.setVisible(false);

                		} catch (Exception ex) {
    					ex.printStackTrace();
                		}
                    }  
                });  
                f.add(label); f.add(name); f.add(button);  
                f.setBounds(400, 400, 700,140);  
                f.setLayout(null);  
                f.setVisible(true); 
                f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                f.setResizable(false);
			}
		});
	}

	public ChatRoomClient() throws Exception{
		socket = new Socket("localhost", 7002);
		bf = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		dos = new DataOutputStream(socket.getOutputStream());
		initialize();
		new Thread() {
			public void run() {
				while (true) {
					if (socket != null) {
						try {
							String str = "";
							while ((str = bf.readLine()) != null) {
								textArea.append(str + "\n");
							}
						} catch(Exception ex) {}
					}
				}
			}			
		}.start();
	}

	private void initialize() {
		client = new JFrame();
		client.setTitle("Client Chat");
		client.setBounds(400, 400, 900, 600);
		client.setResizable(false);
		client.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {180, 440, 160};
		gridBagLayout.rowHeights = new int[] {400, 70};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0};
		client.getContentPane().setLayout(gridBagLayout);
		
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.insets = new Insets(5, 5, 10, 5);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 0;
		client.getContentPane().add(scrollPane, gbc_scrollPane);
		
		textArea = new JTextArea();
		textArea.setBackground(Color.BLACK);
		textArea.setForeground(Color.BLACK);
		textArea.setEnabled(false);
		textArea.setFont(new Font("Tahoma", Font.BOLD, 20));
		scrollPane.setViewportView(textArea);
		
		label = new JLabel("Client");
		label.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 1;
		client.getContentPane().add(label, gbc_lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.BOLD, 20));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 1;
		gbc_textField.gridy = 1;
		client.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton sendButton = new JButton("Send");
		sendButton.setFont(new Font("Tahoma", Font.BOLD, 20));
		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String str = textField.getText();
					if (str.isEmpty()) return;
					dos.writeBytes(label.getText() + ": " + str);
					dos.write(13);
					dos.write(10);
					dos.flush();
					textField.setText("");
				} catch(Exception ex) {}
			}
		});
		GridBagConstraints gbc_sendButton = new GridBagConstraints();
		gbc_sendButton.fill = GridBagConstraints.BOTH;
		gbc_sendButton.insets = new Insets(0, 0, 5, 5);
		gbc_sendButton.gridx = 2;
		gbc_sendButton.gridy = 1;
		client.getContentPane().add(sendButton, gbc_sendButton);
	}

}
