package net.codejava.hibernate.EventVisualization.client;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class SignInView {

	private JFrame frame;
	private JTextField textFieldPass;
	
	
	private SignInController signInController;
	private JTextField textFieldUsername;
	private JLabel lblMessage;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignInView window = new SignInView();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SignInView() {
		signInController = new SignInController(this);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(73, 34, 60, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(73, 89, 88, 14);
		frame.getContentPane().add(lblPassword);
		
		textFieldPass = new JTextField();
		textFieldPass.setBounds(171, 86, 136, 20);
		frame.getContentPane().add(textFieldPass);
		textFieldPass.setColumns(10);
		
		
		
		textFieldUsername = new JTextField();
		textFieldUsername.setBounds(171, 31, 136, 20);
		frame.getContentPane().add(textFieldUsername);
		textFieldUsername.setColumns(10);
		
		lblMessage = new JLabel("");
		lblMessage.setForeground(new Color(255, 0, 0));
		lblMessage.setBounds(171, 128, 136, 14);
		frame.getContentPane().add(lblMessage);
		
		JButton btnSignIn = new JButton("Sign In");
		textFieldPass.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
				      signInController.btnSignInClicked();
				   }
			}
		});
		btnSignIn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {							
				signInController.btnSignInClicked();
			}
		});
		
		
		btnSignIn.setBackground(new Color(255, 255, 255));
		btnSignIn.setForeground(new Color(0, 0, 0));
		btnSignIn.setBounds(171, 166, 89, 23);
		frame.getContentPane().add(btnSignIn);
	
	}
	
	
	public void close() {
		frame.dispose();
	}
	
	public void displayError(String msg) {
		JOptionPane.showMessageDialog(new Frame(), msg);
	}
	
	public String[] getSignInData() {
		String username = textFieldUsername.getText();
		String password = textFieldPass.getText();
		String[] data = {username, password};
		return data;
	}
}
