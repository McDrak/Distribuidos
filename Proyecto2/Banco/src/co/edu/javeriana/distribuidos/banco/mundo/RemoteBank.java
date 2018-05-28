package co.edu.javeriana.distribuidos.banco.mundo;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface RemoteBank extends Remote{
	public boolean openAccount( String name, String password ) throws RemoteException;
	public double closeAccount( String name, String password ) throws RemoteException;
	public Account verifyAccount( String name, String password ) throws RemoteException;
	public void deposit( String name, String password, double amount ) throws RemoteException;
	public double withdraw( String name, String password, double amount ) throws RemoteException;
	public double getBalance( String name, String password ) throws RemoteException;
	public List<Transaction> getTransactionHistory( String name, String password ) throws RemoteException;
}