package co.edu.javeriana.distribuidos.banco.mundo;

import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankServer implements RemoteBank{
	private Map<String, Account> accounts;
	
	public BankServer( ) {
		this.accounts = new HashMap<String, Account>( );
	}
	
	@Override
	public synchronized boolean openAccount(String name, String password) throws RemoteException {
		boolean flag = true;
		
		if( this.accounts.get(name) != null ) {
			flag = false;
		}
		else {
			Account a = new Account( password );
			this.accounts.put(name, a);
		}
		
		return flag;
	}
	
	@Override
	public synchronized Account verifyAccount(String name, String password) throws RemoteException {
		Account a = this.accounts.get(name);
		
		if( a != null && !password.equals(a.getPassword( )) ) {
			a = null;
		}
		
		return a;
	}

	@Override
	public synchronized double closeAccount(String name, String password) throws RemoteException {
		Account a = this.verifyAccount(name, password);
		double balance = 0;
		
		if( a != null ) {
			synchronized( a ) {
				balance = a.getBalance();
				a.setBalance(0);
			}
		}
		
		return balance;
	}

	@Override
	public synchronized void deposit(String name, String password, double amount) throws RemoteException {
		Account a = this.verifyAccount(name, password);
		
		if( a != null ) {
			synchronized( a ) {
				a.setBalance( a.getBalance() + amount );
			}
		}
	}

	@Override
	public synchronized double withdraw(String name, String password, double amount) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public synchronized double getBalance(String name, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public synchronized List<Transaction> getTransactionHistory(String name, String password) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}