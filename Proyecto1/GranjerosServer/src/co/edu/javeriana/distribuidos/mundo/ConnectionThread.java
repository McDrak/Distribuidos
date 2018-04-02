package co.edu.javeriana.distribuidos.mundo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ConnectionThread extends Thread {
	private ServerBackend server;
	private DataInputStream in;
	private DataOutputStream out;
	private Socket clientSocket;
	
	public ConnectionThread( Socket clientSocketP, ServerBackend serverP ) {
		this.server = serverP;
		try {
			this.clientSocket = clientSocketP;
			this.in = new DataInputStream(clientSocket.getInputStream());
			this.out = new DataOutputStream(clientSocket.getOutputStream());
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run( ) {
		String data = null;
		try {
			data = in.readUTF();
			String[] sepData = data.split(",");
			if( sepData.length == 4 ) {
				boolean entra = server.anadirUsuario(sepData[0], sepData[1], sepData[2], sepData[3], sepData[4]);
				
				if( entra == true ) {
					out.writeUTF("aproved");
				}
				else {
					out.writeUTF("error");
				}
			}
			else {
				//TODO: Rechazo por falta de argumentos
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}
