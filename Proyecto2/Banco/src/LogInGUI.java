import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogInGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private String name;
	private String password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogInGUI frame = new LogInGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogInGUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(116, 79, 164, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(117, 121, 164, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		
		
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.setBounds(155, 166, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = textField.getText();
				password = textField_1.getText();
				
				ClientGUI c = new ClientGUI( name, password );
				c.setVisible(true);
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Sign up");
		btnNewButton_1.setBounds(155, 200, 89, 23);
		panel.add(btnNewButton_1);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(117, 64, 74, 14);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(118, 105, 162, 14);
		panel.add(lblPassword);
	}
}
