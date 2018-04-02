package co.edu.javeriana.distribuidos.mundo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		int serverPort = 7896;
		ServerBackend backend = new ServerBackend();
		ServerSocket listenSocket = null;
		
		try {
			listenSocket = new ServerSocket( serverPort );
			
			while( true ) {
				Socket clientSocket = listenSocket.accept( );
				ConnectionThread c = new ConnectionThread( clientSocket, backend );
				c.start();
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				listenSocket.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
