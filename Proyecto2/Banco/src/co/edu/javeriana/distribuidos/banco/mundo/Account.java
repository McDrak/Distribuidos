package co.edu.javeriana.distribuidos.banco.mundo;

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
