import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteTransfers extends Remote{
	public boolean openAccount( String name, String password ) throws RemoteException;
	public double closeAccount( String name, String password ) throws RemoteException;
	public void deposit( String name, String password, double amount ) throws RemoteException;
	public double withdraw( String name, String password, double amount ) throws RemoteException;
}