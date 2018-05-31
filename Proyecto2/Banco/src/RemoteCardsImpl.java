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
	public boolean mastercardPayment(String name, String password, double amount) throws RemoteException {
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
					balance -= amount;
					a.setBalance(balance);
					this.server.addForQueue(name, a);
				}
			}
		}
		else {
			flag = false;
		}
		
		return flag;
	}

	@Override
	public boolean visaPayment(String name, String password, double amount) throws RemoteException {
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
					balance -= amount;
					a.setBalance(balance);
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
