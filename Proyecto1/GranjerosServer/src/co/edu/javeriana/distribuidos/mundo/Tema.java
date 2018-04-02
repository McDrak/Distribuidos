package co.edu.javeriana.distribuidos.mundo;

import java.util.ArrayList;
import java.util.List;

public class Tema {
	private String nombre;
	private List<Usuario> suscritos;
	private List<Noticia> noticias;
	
	public Tema( String nombreP ) {
		this.nombre = nombreP;
		this.suscritos = new ArrayList<Usuario>();
		this.noticias = new ArrayList<Noticia>();
	}
	
	public boolean estaSuscrito( String username ) {
		boolean flag = false;
		for( Usuario user : suscritos ) {
			if( user.getUsername().equals(username) ) {
				flag = true;
			}
		}
		
		return flag;
	}
	
	public boolean suscribir( Usuario user ) {
		boolean flag = true;
		if( this.estaSuscrito(user.getUsername()) == true ) {
			flag = false;
		}
		else {
			this.suscritos.add(user);
		}
		
		return flag;
	}
	
	public void agregarNoticia( String id, String contenido, String fuente ) {
		Noticia n = new Noticia(id, contenido, fuente);
		this.noticias.add( n );
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Usuario> getSuscritos() {
		return suscritos;
	}

	public void setSuscritos(List<Usuario> suscritos) {
		this.suscritos = suscritos;
	}

	public List<Noticia> getNoticias() {
		return noticias;
	}

	public void setNoticias(List<Noticia> noticias) {
		this.noticias = noticias;
	}
}
