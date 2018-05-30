import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JTable;

public class ClientGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private DefaultTableModel tableModel;
	private JTable table;
	private String[] columnNames={"Hora", "Tipo", "Resumen"};
	private String name;
	private String password;
	
	private RemoteCards iCards;
	private RemoteQueries iQueries;
	private RemoteTransfers iTransfers;

	/**
	 * Create the frame.
	 */
	public ClientGUI( String nameP, String passwordP ) {
		this.name = nameP;
		this.password = passwordP;
		
		try {
			iCards = (RemoteCards) Naming.lookup("rmi://localhost:5000//cards");
			iQueries = (RemoteQueries) Naming.lookup("rmi://localhost:5000//queries");
			iTransfers = (RemoteTransfers) Naming.lookup("rmi://localhost:5000//transfers");
			iTransfers.openAccount(name, password);
		} 
		catch (MalformedURLException | RemoteException | NotBoundException e) {
			e.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 583, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblDe = new JLabel("De");
		lblDe.setBounds(40, 26, 46, 14);
		panel.add(lblDe);
		
		JLabel lblA = new JLabel("A");
		lblA.setBounds(165, 26, 46, 14);
		panel.add(lblA);
		
		JLabel lblOperacin = new JLabel("Operaci\u00F3n");
		lblOperacin.setBounds(308, 26, 86, 14);
		panel.add(lblOperacin);
		
		JLabel lblMonto = new JLabel("Monto");
		lblMonto.setBounds(484, 26, 46, 14);
		panel.add(lblMonto);
		
		textField = new JTextField();
		textField.setBounds(10, 51, 86, 20);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(134, 51, 86, 20);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(461, 51, 86, 20);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setBounds(252, 51, 190, 20);
		panel.add(comboBox);
		
		comboBox.addItem("Deposito");
		comboBox.addItem("Retiro");
		comboBox.addItem("Consulta");
		comboBox.addItem("Transferencia cuenta ahorros");
		comboBox.addItem("Transferencia crédito");
		comboBox.addItem("Pago tarjeta de crédito visa");
		comboBox.addItem("Pago tarjeta de crédito master");
		
		JButton btnNewButton = new JButton("Ejecutar");
		btnNewButton.setBounds(122, 350, 89, 23);
		btnNewButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String op = comboBox.getSelectedItem().toString();
				
				if( op.equals("Deposito")) {
					try {
						iTransfers.deposit(name, password, Double.parseDouble(textField_2.getText()));
					} catch (NumberFormatException | RemoteException e1) {
						e1.printStackTrace();
					}
				}
				else if( op.equals("Retiro")) {
					try {
						iTransfers.withdraw(name, password, Double.parseDouble(textField_2.getText()));
					} catch (NumberFormatException | RemoteException e1) {
						e1.printStackTrace();
					}
				}
				else if( op.equals("Pago tarjeta de crédito visa")) {
					try {
						iCards.visaPayment(name, password, Double.parseDouble(textField_2.getText()));
					} catch (NumberFormatException | RemoteException e1) {
						e1.printStackTrace();
					}
				}
				else if( op.equals("Pago tarjeta de crédito master")) {
					try {
						iCards.mastercardPayment(name, password, Double.parseDouble(textField_2.getText()));
					} catch (NumberFormatException | RemoteException e1) {
						e1.printStackTrace();
					}
				}
				else if( op.equals("Consulta")) {
					try {
						iQueries.getBalance(name, password);
					} catch (NumberFormatException | RemoteException e1) {
						e1.printStackTrace();
					}
				}
				
				/**try {
					List<Transaction> update = iQueries.getTransactionHistory(name, password);
					updateTable(update);
				} catch (RemoteException e1) {
					e1.printStackTrace();
				}*/
			}
		});
		panel.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Salir");
		btnNewButton_1.setBounds(358, 350, 89, 23);
		panel.add(btnNewButton_1);
		
		tableModel = new DefaultTableModel(columnNames,3);
	
		table = new JTable(tableModel);
		table.setBounds(41, 317, 477, -201);
		
		
		panel.add(table.getTableHeader(), BorderLayout.NORTH);
		panel.add(table, BorderLayout.CENTER);
		
	}
	
	public void updateTable( List<Transaction> list ) {
		tableModel = new DefaultTableModel(columnNames,3);
		
		for( Transaction t : list ) {
			Object[] o = new Object[3];
			o[ 0 ] = t.getDate().toString();
			o[ 1 ] = t.getTipo();
			o[ 2 ] = t.getAmount();
			
			tableModel.addRow(o);
		}
		
		table.setModel(tableModel);
	}
}
