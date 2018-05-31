import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Server {
	private Map<String, Account> accounts;
	private Map<String, Account> queue;
	
	public Server( ) {
		this.accounts = new HashMap<String, Account>( );
		this.queue = new HashMap<String, Account>( );
	}
	
	public Account verifyAccount( String name, String password ) {
		Account a = this.accounts.get(name);
		
		if( a != null && !password.equals(a.getPassword( )) ) {
			a = null;
		}
		
		return a;
	}
	
	public Account getSpecificAccount( String name ) {
		return this.accounts.get(name);
	}
	
	public void deleteAccount( String name ) {
		this.accounts.remove(name);
	}
	
	public void addAccount( String name, Account a ) {
		this.accounts.put(name, a);
	}
	
	public void addForQueue( String name, Account a ) {
		this.queue.put(name, a);
	}
	
	public synchronized void releaseTheQueueken( ) {
		Set<String> keys = this.queue.keySet();
		for( String s : keys ) {
			this.accounts.replace(s, this.queue.get(s));
		}
		
		System.out.println("Queueken");
		
		this.queue = new HashMap<String, Account>( );
	}

	public Map<String, Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(Map<String, Account> accounts) {
		this.accounts = accounts;
	}

	public Map<String, Account> getQueue() {
		return queue;
	}

	public void setQueue(Map<String, Account> queue) {
		this.queue = queue;
	}

	public static void main(String[] args) {
		Server server = new Server( );
		
		try {
			LocateRegistry.createRegistry(5000);
			RemoteCards iCards = new RemoteCardsImpl(server);
			RemoteQueries iQueries = new RemoteQueriesImpl(server);
			RemoteTransfers iTransfers = new RemoteTransfersImpl(server);
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Naming.rebind("rmi://localhost:5000//cards", iCards);
					} catch (RemoteException | MalformedURLException e) {
						e.printStackTrace();
					}					
				}
			}).start();
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Naming.rebind("rmi://localhost:5000//queries", iQueries);
					} catch (RemoteException | MalformedURLException e) {
						e.printStackTrace();
					}					
				}
			}).start();
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					try {
						Naming.rebind("rmi://localhost:5000//transfers", iTransfers);
					} catch (RemoteException | MalformedURLException e) {
						e.printStackTrace();
					}					
				}
			}).start();
			
			new Thread(new Runnable() {
				@Override
				public void run() {
					while( true ) {
						if( server.getQueue().size() >= 5 ) {
							server.releaseTheQueueken();
						}
					}
				}
			}).start();
		} 
		catch (RemoteException e1) {
			e1.printStackTrace();
		}
	}

}
