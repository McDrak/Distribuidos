import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class LogInGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUser;
	private JPasswordField	 txtPassword;
	private String name;
	private String password;
	
	private RemoteTransfers iTransfers;
	private JButton btnLogin;
	private JButton btnRegister;
	private JPanel panel;

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
		
		try {
			iTransfers = (RemoteTransfers) Naming.lookup("rmi://localhost:5000//transfers");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			e1.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		txtUser = new JTextField();
		txtUser.setBounds(116, 79, 164, 20);
		panel.add(txtUser);
		txtUser.setColumns(10);
		
		txtPassword = new JPasswordField();
		txtPassword.setBounds(117, 121, 164, 20);
		panel.add(txtPassword);
		txtPassword.setColumns(10);
		
		
		
		
		btnLogin = new JButton("Log in");
		btnLogin.setBounds(155, 166, 89, 23);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				name = txtUser.getText();
				password = new String( txtPassword.getPassword() );
				
				try {
					if( iTransfers.checkAccount(name + "a", password) ) {
						ClientGUI c = new ClientGUI( name, password, iTransfers );
						c.setVisible(true);						
					}
					else {
						JOptionPane.showMessageDialog(contentPane, "La cuenta ingresada no esta registrada.");
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnLogin);
		
		btnRegister = new JButton("Sign up");
		btnRegister.setBounds(155, 200, 89, 23);
		btnRegister.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				name = txtUser.getText();
				password = new String( txtPassword.getPassword() );
				
				try {
					if( iTransfers.openAccount(name, password) ) {
						ClientGUI c = new ClientGUI(name, password, iTransfers);
						c.setVisible(true);
					}
					else {
						JOptionPane.showMessageDialog(contentPane, "Hubo un problema con la cuenta ingresada.");
					}
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}
			}
		});
		panel.add(btnRegister);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setBounds(117, 64, 74, 14);
		panel.add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(118, 105, 162, 14);
		panel.add(lblPassword);
	}
}
