package co.edu.javeriana.distribuidos.mundo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Connection extends Thread {
	private DataInputStream in;
	private DataOutputStream out;
	private Socket clientSocket;
	
	public Connection( Socket clientSocketP ) {
		try {
			clientSocket = clientSocketP;
			in = new DataInputStream(clientSocket.getInputStream());
			out = new DataOutputStream(clientSocket.getOutputStream());
			this.start();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run( ) {
		String data;
		try {
			data = in.readUTF();
			out.writeUTF(data);
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				clientSocket.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
