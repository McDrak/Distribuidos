import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RemoteTransfers extends Remote{
	public boolean checkAccount( String name, String password ) throws RemoteException;
	public boolean openAccount( String name, String password ) throws RemoteException;
	public boolean closeAccount( String name, String password ) throws RemoteException;
	public boolean deposit( String name, String password, double amount ) throws RemoteException;
	public boolean withdraw( String name, String password, double amount ) throws RemoteException;
}