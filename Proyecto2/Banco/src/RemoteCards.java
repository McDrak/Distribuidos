import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteCards extends Remote{
	public double mastercardPayment( String name, String password, double amount ) throws RemoteException;
	public double visaPayment( String name, String password, double amount ) throws RemoteException;
}
