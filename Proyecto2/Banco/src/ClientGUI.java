import java.awt.BorderLayout;
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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;

public class ClientGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtDe;
	private JTextField txtA;
	private JTextField txtMonto;
	private String[] columnNames={"Hora", "Tipo", "Resumen"};
	
	private String name;
	private String password;
	private JPanel panel;
	private JComboBox<String> comboBox;
	private JButton btnEjecutar;
	private JScrollPane scrollPane;
	private JTable table;
	
	private RemoteTransfers iTransfers;
	private RemoteCards iCards;
	private RemoteQueries iQueries;

	/**
	 * Create the frame.
	 */
	public ClientGUI( String nameP, String passwordP, RemoteTransfers iTP ) {
		this.name = nameP;
		this.password = passwordP;
		this.iTransfers = iTP;
		
		try {
			this.iCards = (RemoteCards) Naming.lookup("rmi://localhost:5000//cards");
			this.iQueries = (RemoteQueries) Naming.lookup("rmi://localhost:5000//queries");
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		} catch (RemoteException e1) {
			e1.printStackTrace();
		} catch (NotBoundException e1) {
			e1.printStackTrace();
		}
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 583, 433);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		panel = new JPanel();
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
		
		txtDe = new JTextField();
		txtDe.setBounds(10, 51, 86, 20);
		panel.add(txtDe);
		txtDe.setColumns(10);
		
		txtA = new JTextField();
		txtA.setBounds(134, 51, 86, 20);
		panel.add(txtA);
		txtA.setColumns(10);
		
		txtMonto = new JTextField();
		txtMonto.setBounds(461, 51, 86, 20);
		panel.add(txtMonto);
		txtMonto.setColumns(10);
		
		comboBox = new JComboBox<String>();
		comboBox.setBounds(252, 51, 190, 20);
		panel.add(comboBox);
		
		comboBox.addItem("Deposito -> Ahorro");
		comboBox.addItem("Deposito -> Corriente");
		comboBox.addItem("Retiro -> Ahorro");
		comboBox.addItem("Retiro -> Corriente");
		comboBox.addItem("Transferencia Ahorros -> Corriente");
		comboBox.addItem("Transferencia Corriente -> Ahorros");
		comboBox.addItem("Transferencia Ahorros -> Ahorros");
		comboBox.addItem("Transferencia Corriente -> Corriente");
		comboBox.addItem("Pago Tarjeta de Crédito Visa -> Ahorros");
		comboBox.addItem("Pago Tarjeta de Crédito Visa -> Corriente");
		comboBox.addItem("Pago Tarjeta de Crédito Master -> Ahorros");
		comboBox.addItem("Pago Tarjeta de Crédito Master -> Corriente");
		
		btnEjecutar = new JButton("Ejecutar");
		btnEjecutar.setBounds(252, 350, 89, 23);
		btnEjecutar.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String option = (String) comboBox.getSelectedItem();
				
				if( option.equals("Deposito -> Ahorro") ) {
					double monto = Double.parseDouble( txtMonto.getText() );
					
					try {
						iTransfers.deposit(name + "a", password, monto);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
				else if( option.equals("Deposito -> Corriente") ) {
					double monto = Double.parseDouble( txtMonto.getText() );
					
					try {
						iTransfers.deposit(name + "c", password, monto);
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
				else if( option.equals("Retiro -> Ahorros") ) {
					double monto = Double.parseDouble( txtMonto.getText() );
					
					try {
						if( iTransfers.withdraw(name + "a", password, monto) == false ) {
							JOptionPane.showMessageDialog(contentPane, "Error con la operacon.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
				else if( option.equals("Retiro -> Corriente") ) {
					double monto = Double.parseDouble( txtMonto.getText() );
					
					try {
						if( iTransfers.withdraw(name + "c", password, monto) == false ) {
							JOptionPane.showMessageDialog(contentPane, "Error con la operacon.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
				else if( option.equals("Transferencia Ahorros -> Corriente") ) {
					double monto = Double.parseDouble( txtMonto.getText() );
					String destino = txtA.getText( );
					
					try {
						if( iTransfers.withdraw(name + "a", password, monto) ) {
							if( iTransfers.deposit(destino + "c", password, monto) == false ) {
								JOptionPane.showMessageDialog(contentPane, "Error con la operacon.", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
						else {
							JOptionPane.showMessageDialog(contentPane, "Error con la operacon.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
				else if( option.equals("Transferencia Corriente -> Ahorros") ) {
					double monto = Double.parseDouble( txtMonto.getText() );
					String destino = txtA.getText( );
					
					try {
						if( iTransfers.withdraw(name + "c", password, monto) ) {
							if( iTransfers.deposit(destino + "a", password, monto) == false ) {
								JOptionPane.showMessageDialog(contentPane, "Error con la operacon.", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
						else {
							JOptionPane.showMessageDialog(contentPane, "Error con la operacon.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
				else if( option.equals("Transferencia Ahorros -> Ahorros") ) {
					double monto = Double.parseDouble( txtMonto.getText() );
					String destino = txtA.getText( );
					
					try {
						if( iTransfers.withdraw(name + "a", password, monto) ) {
							if( iTransfers.deposit(destino + "a", password, monto) == false ) {
								JOptionPane.showMessageDialog(contentPane, "Error con la operacon.", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
						else {
							JOptionPane.showMessageDialog(contentPane, "Error con la operacon.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
				else if( option.equals("Transferencia Corriente -> Corriente") ) {
					double monto = Double.parseDouble( txtMonto.getText() );
					String destino = txtA.getText( );
					
					try {
						if( iTransfers.withdraw(name + "c", password, monto) ) {
							if( iTransfers.deposit(destino + "c", password, monto) == false ) {
								JOptionPane.showMessageDialog(contentPane, "Error con la operacon.", "Error", JOptionPane.ERROR_MESSAGE);
							}
						}
						else {
							JOptionPane.showMessageDialog(contentPane, "Error con la operacon.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
				else if( option.equals("Pago Tarjeta de Crédito Visa -> Ahorros") ) {
					double monto = Double.parseDouble( txtMonto.getText() );
					
					try {
						if( iCards.visaPayment(name + "a", password, monto) == false ) {
							JOptionPane.showMessageDialog(contentPane, "Error con la operacon.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
				else if( option.equals("Pago Tarjeta de Crédito Visa -> Corriente") ) {
					double monto = Double.parseDouble( txtMonto.getText() );
					
					try {
						if( iCards.visaPayment(name + "c", password, monto) == false ) {
							JOptionPane.showMessageDialog(contentPane, "Error con la operacon.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
				else if( option.equals("Pago Tarjeta de Crédito Master -> Ahorros") ) {
					double monto = Double.parseDouble( txtMonto.getText() );
					
					try {
						if( iCards.mastercardPayment(name + "a", password, monto) == false ) {
							JOptionPane.showMessageDialog(contentPane, "Error con la operacon.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
				else if( option.equals("Pago Tarjeta de Crédito Master -> Corriente") ) {
					double monto = Double.parseDouble( txtMonto.getText() );
					
					try {
						if( iCards.mastercardPayment(name + "c", password, monto) == false ) {
							JOptionPane.showMessageDialog(contentPane, "Error con la operacon.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (RemoteException e1) {
						e1.printStackTrace();
					}
				}
				
				updateList( );
			}
		});
		panel.add(btnEjecutar);
		
		table = new JTable( );
		table.setModel(new DefaultTableModel(
			new Object[][] {
			}, this.columnNames
		));
		
		scrollPane = new JScrollPane(table);
		scrollPane.setBounds(53, 109, 444, 187);
		panel.add(scrollPane);
	}
	
	public void updateList( ) {
		try {
			List<Transaction> listA = iQueries.getTransactionHistory(name + "a", password);
			List<Transaction> listC = iQueries.getTransactionHistory(name + "c", password);
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			
			int rowCount = model.getRowCount();
			for( int i = rowCount - 1; i >= 0; i--) {
				model.removeRow(i);
			}
			
			for( Transaction t : listA ) {
				model.addRow(new Object[] { t.getDate(), t.getTipo(), t.getAmount() });
			}
			
			model.addRow(new Object[] { " - ", " - ", " - "});
			
			for( Transaction t : listC ) {
				model.addRow(new Object[] { t.getDate(), t.getTipo(), t.getAmount() });
			}
			
			table.setModel(model);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
}
