import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteCards extends Remote{
	public boolean mastercardPayment( String name, String password, double amount ) throws RemoteException;
	public boolean visaPayment( String name, String password, double amount ) throws RemoteException;
}
