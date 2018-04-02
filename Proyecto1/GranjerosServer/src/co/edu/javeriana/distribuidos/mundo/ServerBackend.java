package co.edu.javeriana.distribuidos.mundo;

import java.util.ArrayList;
import java.util.List;

public class ServerBackend {
	private List<Usuario> usuarios;
	private List< Tema > temas;
	
	public ServerBackend( ) {
		usuarios = new ArrayList<Usuario>();
		temas = new ArrayList< Tema >();
	}
	
	public boolean existeTema( String nombre ) {
		boolean flag = false;
		for( Tema t : temas ) {
			if( t.getNombre().equals(nombre) ) {
				flag = true;
			}
		}
		
		return flag;
	}
	
	public boolean crearTema( String nombre ) {
		boolean flag = true;
		if( this.existeTema(nombre) == true ) {
			flag = false;
		}
		else {
			Tema t = new Tema( nombre );
			temas.add(t);
		}
		
		return flag;
	}
	
	public boolean anadirUsuario( String userName, String ip, String ubicacion, String tipoProducto, String tam ) {
		boolean flag = true;
		
		for( Usuario user : usuarios ) {
			if( user.getUsername().equals(userName) ) {
				flag = false;
			}
		}
		
		if( flag == true ) {
			Usuario user = new Usuario( userName, ip, ubicacion, tipoProducto, tam, true );
			usuarios.add(user);
		}
		
		return flag;
	}
	
	public String agregarNoticia( String cadena ) {
		String[] args = cadena.split("|");
		boolean flag = false;
		
		for( Tema t : temas ) {
			if( args[0].equals(t.getNombre()) ) {
				t.agregarNoticia(args[1], args[2], args[3]);
				flag = true;
			}
		}
		
		if( flag == true ) {
			return args[2];
		}
		else {
			return "error";
		}
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Tema> getTemas() {
		return temas;
	}

	public void setTemas(List<Tema> temas) {
		this.temas = temas;
	}
}
