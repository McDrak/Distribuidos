import java.util.ArrayList;
import java.util.List;

public class Account {
	private String password;
	double balance;
	List<Transaction> transactions;
	
	public Account( String passwordP ) {
		this.password = passwordP;
		this.balance = 0;
		this.transactions = new ArrayList<Transaction>( );
	}
	
	public void addTransaction( String tipo, double amount ) {
		if( tipo.equals("C") ) {
			Transaction t = new Transaction( Transaction.TIPO_TRANSACCION.CIERRE, amount );
			this.transactions.add(t);
		}
		else if( tipo.equals("CON") ) {
			Transaction t = new Transaction( Transaction.TIPO_TRANSACCION.CONSULTA, amount );
			this.transactions.add(t);
		}
		else if( tipo.equals("CRE") ) {
			Transaction t = new Transaction( Transaction.TIPO_TRANSACCION.CREACION, amount );
			this.transactions.add(t);
		}
		else if( tipo.equals("D") ) {
			Transaction t = new Transaction( Transaction.TIPO_TRANSACCION.DEPOSITO, amount );
			this.transactions.add(t);
		}
		else if( tipo.equals("L") ) {
			Transaction t = new Transaction( Transaction.TIPO_TRANSACCION.LISTA, amount );
			this.transactions.add(t);
		}
		else if( tipo.equals("P") ) {
			Transaction t = new Transaction( Transaction.TIPO_TRANSACCION.PAGO, amount );
			this.transactions.add(t);
		}
		else if( tipo.equals("R") ) {
			Transaction t = new Transaction( Transaction.TIPO_TRANSACCION.RETIRO, amount );
			this.transactions.add(t);
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}
}
