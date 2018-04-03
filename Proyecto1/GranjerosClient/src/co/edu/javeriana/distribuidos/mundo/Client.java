package co.edu.javeriana.distribuidos.mundo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.List;

public class Client {
	public ClientGUI gui;
	public int serverPort;
	private DataInputStream in;
	private DataOutputStream out;
	private Socket socket;
	
	public Client( ClientGUI guiP ) {
		this.gui = guiP;
		this.serverPort = 7896;
	}
	
	public void realizarConexion( String ipServer, String ubicacion, String tipoProducto, String tam ) {
		String data = null;
		try {
			this.socket = new Socket( InetAddress.getByName(ipServer), serverPort );
			this.in = new DataInputStream(socket.getInputStream());
			this.out = new DataOutputStream(socket.getOutputStream());
			
			out.writeUTF( ubicacion + "," + tipoProducto + "," + tam);
			data = in.readUTF();
			if( data != null ) {
				gui.agregarMensaje(data);
			}
		}
		catch (UnknownHostException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void finalizarConexion( ) {
		try {
			this.in.close();
			this.out.close();
			this.socket.close();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void suscripciones( List<String> temas ) {
		String susc = "";
		for( String s : temas ) {
			susc += "," + s;
		}
		
		susc.replaceFirst(",", "");
		String data = null;
		try {
			out.writeUTF(susc);
			data = in.readUTF();
			
			if( data != null ) {
				gui.agregarMensaje(data);
			}
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void recibirNoticias( ) {
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				String data = null;
				
				try {
					while( true ) {
						data = in.readUTF();
						if( data != null ) {
							gui.agregarMensaje(data);
						}
						data = null;
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
