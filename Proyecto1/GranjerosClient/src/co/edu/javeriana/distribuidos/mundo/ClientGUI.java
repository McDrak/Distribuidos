package co.edu.javeriana.distribuidos.mundo;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

public class ClientGUI extends JFrame {
	private static final long serialVersionUID = 1L;
	
	private Client client;
	
	private JPanel contentPane;
	private JTextField txtUb;
	private JTextField txtProd;
	private JTextPane messagesPane;
	private JButton btnRegistrarse;
	private JButton btnSuscribirse;
	private JComboBox<String> comboTam;
	private JTextField txtIp;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientGUI frame = new ClientGUI();
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
	public ClientGUI() {
		client = new Client( this );
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 536, 488);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(5, 5, 527, 444);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Informaci\u00F3n cultivos");
		lblNewLabel.setBounds(174, 11, 116, 14);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Ubicaci\u00F3n");
		lblNewLabel_1.setBounds(20, 72, 73, 14);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tipo producto");
		lblNewLabel_2.setBounds(20, 110, 111, 14);
		panel.add(lblNewLabel_2);
		
		JLabel lblTamao = new JLabel("Tama\u00F1o");
		lblTamao.setBounds(20, 150, 46, 14);
		panel.add(lblTamao);
		
		comboTam = new JComboBox<String>();
		comboTam.setBounds(103, 147, 86, 20);
		panel.add(comboTam);
		comboTam.addItem("Grande");
		comboTam.addItem("Mediano");
		comboTam.addItem("Pequeño");
		
		txtUb = new JTextField();
		txtUb.setBounds(103, 69, 86, 20);
		panel.add(txtUb);
		txtUb.setColumns(10);
		
		txtProd = new JTextField();
		txtProd.setBounds(103, 107, 86, 20);
		panel.add(txtProd);
		txtProd.setColumns(10);
		
		JLabel lblTemas = new JLabel("T\u00F3picos");
		lblTemas.setBounds(20, 290, 46, 14);
		panel.add(lblTemas);
		
		JCheckBox checkClima = new JCheckBox("Predicci\u00F3n climatica");
		checkClima.setBounds(103, 286, 146, 23);
		panel.add(checkClima);
		
		JCheckBox checkPrecios = new JCheckBox("Precios insumos");
		checkPrecios.setBounds(103, 312, 146, 23);
		panel.add(checkPrecios);
		
		JCheckBox checkExpectativas = new JCheckBox("Expectativas precios");
		checkExpectativas.setBounds(103, 338, 162, 23);
		panel.add(checkExpectativas);
		
		btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String ip = txtIp.getText();
				String ubic = txtUb.getText();
				String tipo = txtProd.getText();
				String tam = comboTam.getSelectedItem().toString();
				
				client.realizarConexion(ip, ubic, tipo, tam);
			}
		});
		btnRegistrarse.setBounds(103, 233, 111, 34);
		panel.add(btnRegistrarse);
		
		btnSuscribirse = new JButton("Suscribirse");
		btnSuscribirse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<String> temas = new ArrayList<String>();
				if( checkClima.isSelected() ) {
					temas.add("clima");
				}
				if( checkPrecios.isSelected() ) {
					temas.add("precio");
				}
				if( checkExpectativas.isSelected() ) {
					temas.add("expectativa");
				}
				
				client.suscripciones(temas);
				iniciarRecepcion();
			}
		});
		btnSuscribirse.setBounds(103, 385, 106, 34);
		panel.add(btnSuscribirse);
		
		messagesPane = new JTextPane();
		messagesPane.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(messagesPane);
		scrollPane.setBounds(275, 69, 217, 307);
		panel.add(scrollPane);
		
		JLabel lblNewLabel_3 = new JLabel("Ip Servidor");
		lblNewLabel_3.setBounds(20, 190, 59, 14);
		panel.add(lblNewLabel_3);
		
		txtIp = new JTextField();
		txtIp.setBounds(103, 187, 86, 20);
		panel.add(txtIp);
		txtIp.setColumns(10);
	}
	
	public void iniciarRecepcion() {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				client.recibirNoticias();
			}
		}).start();
	}
	
	public void agregarMensaje( String msj ) {
		String actual = messagesPane.getText();
		actual += '\n';
		actual += msj;
		messagesPane.setText(actual);
	}
}
