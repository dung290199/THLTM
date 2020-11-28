package ex01.ex01_2;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;

public class CalculatorClient {

	private Socket socket;
	private JFrame calculator;
	private JTextField textField;
	private JTextField result;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CalculatorClient window = new CalculatorClient();
					window.calculator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public CalculatorClient() throws Exception{
		socket = new Socket("localhost", 7001);
		initialize();
	}

	private void initialize() {
		calculator = new JFrame();
		calculator.getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 20));
		calculator.setTitle("Calculator");
		calculator.setBounds(100, 100, 694, 381);
		calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {176, 279, 100};
		gridBagLayout.rowHeights = new int[] {76, 85, 30, 63, 27};
		gridBagLayout.columnWeights = new double[]{1.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
		calculator.getContentPane().setLayout(gridBagLayout);
		JLabel lblNewLabel = new JLabel("Enter expression:");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.fill = GridBagConstraints.VERTICAL;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		calculator.getContentPane().add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.gridwidth = 2;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.BOTH;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		calculator.getContentPane().add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton calculateBtn = new JButton("Calculate");
		calculateBtn.setFont(new Font("Tahoma", Font.PLAIN, 20));
		calculateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
					DataInputStream din = new DataInputStream(socket.getInputStream());
					String str = textField.getText();
					if (str.isEmpty()) return;
					dos.writeUTF(str);
					result.setText(din.readUTF());
				} catch(Exception ex) {
					ex.printStackTrace();
				}
			}
		});
		GridBagConstraints gbc_calculateBtn = new GridBagConstraints();
		gbc_calculateBtn.insets = new Insets(0, 0, 5, 0);
		gbc_calculateBtn.fill = GridBagConstraints.BOTH;
		gbc_calculateBtn.gridx = 2;
		gbc_calculateBtn.gridy = 1;
		calculator.getContentPane().add(calculateBtn, gbc_calculateBtn);
		
		JLabel lblNewLabel_1 = new JLabel("Result: ");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 0;
		gbc_lblNewLabel_1.gridy = 3;
		calculator.getContentPane().add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		result = new JTextField();
		result.setFont(new Font("Tahoma", Font.PLAIN, 20));
		result.setEnabled(false);
		GridBagConstraints gbc_result = new GridBagConstraints();
		gbc_result.insets = new Insets(0, 0, 5, 5);
		gbc_result.fill = GridBagConstraints.BOTH;
		gbc_result.gridx = 1;
		gbc_result.gridy = 3;
		calculator.getContentPane().add(result, gbc_result);
		result.setColumns(10);
	}

}