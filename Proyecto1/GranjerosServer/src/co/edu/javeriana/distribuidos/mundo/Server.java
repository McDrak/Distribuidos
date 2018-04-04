package co.edu.javeriana.distribuidos.mundo;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public class Server {

	public static void main(String[] args) {
		int serverPort = 7896;
		ServerBackend backend = new ServerBackend();
		System.out.println("AgroConsejero Server :D");
		
		new Thread(new Runnable() {
			@Override
			public void run() {
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
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				Scanner scan = new Scanner( System.in );
				String nArch = null;
				List<String> content = null;
				boolean flag = true;
				
				try {
					while( flag == true ) {
						System.out.println("Agrega un archivo de noticias:");
						nArch = scan.next();
						if( nArch.equals("exit") != true ) {
							content = Files.readAllLines(Paths.get(nArch), StandardCharsets.UTF_8);
							System.out.println("Inicia carga del archivo.");
							
							for( String s : content ) {
								backend.agregarNoticia(s);
							}
							
							System.out.println("Finalizo la carga del archivo.");
						}
						else {
							flag = false;
							System.out.println("Se ha detenido el cargador de Archivos.");
						}
					}
				}
				catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				finally {
					scan.close();
				}
			}
		}).start();
	}
}