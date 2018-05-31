import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteTransfersImpl extends UnicastRemoteObject implements RemoteTransfers{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Server server;
	
	public RemoteTransfersImpl( Server serverP ) throws RemoteException {
		this.server = serverP;
	}
	
	@Override
	public synchronized boolean checkAccount(String name, String password) throws RemoteException {
		boolean flag = true;
		
		if( this.server.verifyAccount(name, password) == null ) {
			flag = false;
		}
		
		return flag;
	}
	
	@Override
	public synchronized boolean openAccount(String name, String password) throws RemoteException {
		boolean flag = true;
		
		if( this.server.verifyAccount(name, password) != null ) {
			flag = false;
		}
		else {
			Account a = new Account( password );
			Account c = new Account( password );
			a.addTransaction("CRE", 0);
			c.addTransaction("CRE", 0);
			this.server.addAccount( name + "a", a );
			this.server.addAccount(name + "c", c);
			System.out.println("Hola " + name);
		}
		
		return flag;
	}

	@Override
	public synchronized boolean closeAccount(String name, String password) throws RemoteException {
		Account a = this.server.verifyAccount(name, password);
		double balance = 0;
		boolean flag = true;
		
		if( a != null ) {
			synchronized( a ) {
				balance = a.getBalance();
				a.setBalance(0);
				a.addTransaction("C", balance);
				this.server.deleteAccount(name);
				this.server.addForQueue(name, a);
			}
		}
		else {
			flag = false;
		}
		
		return flag;
	}

	@Override
	public synchronized boolean deposit(String name, String password, double amount) throws RemoteException {
		Account a = this.server.getSpecificAccount(name);
		boolean flag = true;
		
		if( a != null ) {
			synchronized( a ) {
				a.setBalance( a.getBalance() + amount );
				a.addTransaction("D", amount);
				this.server.addForQueue(name, a);
			}
		}
		else {
			flag = false;
		}
		
		return flag;
	}

	@Override
	public synchronized boolean withdraw(String name, String password, double amount) throws RemoteException {
		Account a = this.server.verifyAccount(name, password);
		double balance = 0;
		boolean flag = true;
		
		if( a != null ) {
			synchronized( a ) {
				balance = a.getBalance();
				
				if( balance < amount ) {
					flag = false;
				}
				else {
					a.setBalance(balance - amount);
					balance -= amount;
					a.addTransaction("R", amount);
					this.server.addForQueue(name, a);
				}
			}
		}
		else {
			flag = false;
		}
		
		return flag;
	}
}