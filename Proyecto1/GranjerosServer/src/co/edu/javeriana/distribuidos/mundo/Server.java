package co.edu.javeriana.distribuidos.mundo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		try {
			int serverPort = 7896;
			ServerSocket listenSocket = new ServerSocket( serverPort );
			
			while( true ) {
				Socket clientSocket = listenSocket.accept( );
				Connection c = new Connection( clientSocket );
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
