package co.edu.javeriana.distribuidos.mundo;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
	
	public static void main(String[] args) {
		Socket s = null;
		try {
			int serverPort = 7896;
			s = new Socket( args[1], serverPort );
			DataInputStream in = new DataInputStream( s.getInputStream());
			DataOutputStream out = new DataOutputStream(s.getOutputStream());
			out.writeUTF(args[0]);
			String data = in.readUTF();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				s.close();
			} 
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
