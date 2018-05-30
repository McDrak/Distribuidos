import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RemoteCardsImpl extends UnicastRemoteObject implements RemoteCards{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Server server;
	
	public RemoteCardsImpl( Server serverP ) throws RemoteException {
		super();
		this.server = serverP;
	}

	@Override
	public double mastercardPayment(String name, String password, double amount) throws RemoteException {
		Account a = this.server.verifyAccount(name, password);
		double balance = 0;
		
		if( a != null ) {
			synchronized( a ) {
				balance = a.getBalance();
				
				if( balance < amount ) {
					System.out.println("No hay suficientes fondos.");
				}
				else {
					balance -= amount;
					a.setBalance(balance);
					this.server.addForQueue(name, a);
				}
			}
		}
		
		return balance;
	}

	@Override
	public double visaPayment(String name, String password, double amount) throws RemoteException {
		Account a = this.server.verifyAccount(name, password);
		double balance = 0;
		
		if( a != null ) {
			synchronized( a ) {
				balance = a.getBalance();
				
				if( balance < amount ) {
					System.out.println("No hay suficientes fondos.");
				}
				else {
					balance -= amount;
					a.setBalance(balance);
					this.server.addForQueue(name, a);
				}
			}
		}
		
		return balance;
	}
}
