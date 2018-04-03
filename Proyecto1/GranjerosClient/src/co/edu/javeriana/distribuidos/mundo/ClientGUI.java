package co.edu.javeriana.distribuidos.mundo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class ClientGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;

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
		
		JComboBox<String> comboBox = new JComboBox<String>();
		comboBox.setBounds(103, 147, 86, 20);
		panel.add(comboBox);
		comboBox.addItem("Grande");
		comboBox.addItem("Mediano");
		comboBox.addItem("Pequeño");
		
		textField = new JTextField();
		textField.setBounds(103, 69, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(103, 107, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblTemas = new JLabel("T\u00F3picos");
		lblTemas.setBounds(20, 247, 46, 14);
		panel.add(lblTemas);
		
		JCheckBox chckbxPrediccinClimatica = new JCheckBox("Predicci\u00F3n climatica");
		chckbxPrediccinClimatica.setBounds(103, 243, 146, 23);
		panel.add(chckbxPrediccinClimatica);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Precios insumos");
		chckbxNewCheckBox.setBounds(103, 269, 146, 23);
		panel.add(chckbxNewCheckBox);
		
		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Expectativas precios");
		chckbxNewCheckBox_1.setBounds(103, 295, 162, 23);
		panel.add(chckbxNewCheckBox_1);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.setBounds(103, 190, 111, 34);
		panel.add(btnRegistrarse);
		
		JButton btnSuscribirse = new JButton("Suscribirse");
		btnSuscribirse.setBounds(103, 342, 106, 34);
		panel.add(btnSuscribirse);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(275, 69, 217, 307);
		panel.add(scrollPane);
	}
	
	public void agregarMensaje( String msj ) {
		
	}
}
