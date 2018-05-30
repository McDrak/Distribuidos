import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteQueries extends Remote {
	public double getBalance( String name, String password ) throws RemoteException;
	public List<Transaction> getTransactionHistory( String name, String password ) throws RemoteException;
}
