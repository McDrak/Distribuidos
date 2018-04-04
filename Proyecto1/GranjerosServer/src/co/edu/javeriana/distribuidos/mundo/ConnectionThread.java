package co.edu.javeriana.distribuidos.mundo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.time.LocalTime;
import java.util.List;

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
		String ip = null;
		try {
			data = in.readUTF();
			String[] sepData = data.split(",");
			if( sepData.length == 3 ) {
				ip = clientSocket.getInetAddress().toString();
				boolean entra = server.anadirUsuario( ip, sepData[0], sepData[1], sepData[2] );
				
				if( entra == true ) {
					out.writeUTF("aproved");
					System.out.println("Entra usuario " + ip);
					data = in.readUTF();
					sepData = data.split(",");
					int nts = server.suscribir(sepData, server.buscarUsuario(ip));
					out.writeUTF("suscrito a " + nts + " temas.");
					System.out.println(ip + " suscrito a " + nts + " temas.");
					
					while( true ) {
						List<String> ns = server.obtenerNoticiasParaUsuario(ip);
						for( String s : ns ) {
							out.writeUTF(s);
							System.out.println(LocalTime.now() + ": Se envio noticia " + ip);
						}
						
						try {
							sleep(60000);
						} 
						catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
				else {
					out.writeUTF("error: no se pudo crear usuario");
					this.clientSocket.close();
				}
			}
			else {
				out.writeUTF("error: numero de argumentos");
				this.clientSocket.close();
			}
		} 
		catch (IOException e) {
			System.out.println("El cliente " + ip + " se ha desconectado.");
			server.desconectarUsuario(ip);
		}
	}
}
