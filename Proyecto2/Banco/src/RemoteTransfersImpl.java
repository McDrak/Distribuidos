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
	public synchronized boolean openAccount(String name, String password) throws RemoteException {
		boolean flag = true;
		
		if( this.server.verifyAccount(name, password) != null ) {
			flag = false;
		}
		else {
			Account a = new Account( password );
			a.addTransaction("CRE", 0);
			this.server.addAccount( name, a );
			System.out.println("Hola " + name);
		}
		
		return flag;
	}

	@Override
	public synchronized double closeAccount(String name, String password) throws RemoteException {
		Account a = this.server.verifyAccount(name, password);
		double balance = 0;
		
		if( a != null ) {
			synchronized( a ) {
				balance = a.getBalance();
				a.setBalance(0);
				a.addTransaction("C", balance);
				this.server.addForQueue(name, a);
			}
		}
		
		return balance;
	}

	@Override
	public synchronized void deposit(String name, String password, double amount) throws RemoteException {
		Account a = this.server.verifyAccount(name, password);
		
		if( a != null ) {
			synchronized( a ) {
				a.setBalance( a.getBalance() + amount );
				a.addTransaction("D", amount);
				this.server.addForQueue(name, a);
			}
		}
	}

	@Override
	public synchronized double withdraw(String name, String password, double amount) throws RemoteException {
		Account a = this.server.verifyAccount(name, password);
		double balance = 0;
		
		if( a != null ) {
			synchronized( a ) {
				balance = a.getBalance();
				
				if( balance < amount ) {
					System.out.println("Fondos no suficientes");
				}
				else {
					a.setBalance(balance - amount);
					balance -= amount;
					a.addTransaction("R", amount);
					this.server.addForQueue(name, a);
				}
			}
		}
		
		return balance;
	}
}