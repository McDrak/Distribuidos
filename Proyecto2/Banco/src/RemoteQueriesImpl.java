import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class RemoteQueriesImpl extends UnicastRemoteObject implements RemoteQueries{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Server server;
	
	public RemoteQueriesImpl( Server serverP ) throws RemoteException {
		this.server = serverP;
	}
	
	@Override
	public synchronized double getBalance(String name, String password) throws RemoteException {
		Account a = this.server.verifyAccount(name, password);
		double balance = 0;
		
		if( a != null ) {
			synchronized( a ) {
				balance = a.getBalance();
				a.addTransaction("CON", 0);
				this.server.addForQueue(name, a);
			}
		}
		
		return balance;
	}

	@Override
	public synchronized List<Transaction> getTransactionHistory(String name, String password) throws RemoteException {
		Account a = this.server.verifyAccount(name, password);
		List<Transaction> list = null;
		
		if( a != null ) {
			synchronized (a) {
				a.addTransaction("L", 0);
				list = a.getTransactions();
				this.server.addForQueue(name, a);
			}
		}
		
		return list;
	}
}
