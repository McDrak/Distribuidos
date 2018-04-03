package co.edu.javeriana.distribuidos.mundo;

import java.util.ArrayList;
import java.util.List;

public class Topic {
	private String nombre;
	private List<User> suscritos;
	private List<News> noticias;
	
	public Topic( String nombreP ) {
		this.nombre = nombreP;
		this.suscritos = new ArrayList<User>();
		this.noticias = new ArrayList<News>();
	}
	
	public boolean estaSuscrito( String ip ) {
		boolean flag = false;
		for( User user : suscritos ) {
			if( user.getIp().equals(ip) ) {
				flag = true;
			}
		}
		
		return flag;
	}
	
	public boolean suscribir( User user ) {
		boolean flag = true;
		if( this.estaSuscrito(user.getIp()) == true ) {
			flag = false;
		}
		else {
			this.suscritos.add(user);
		}
		
		return flag;
	}
	
	public void agregarNoticia( String id, String contenido, String fuente ) {
		News n = new News(id, contenido, fuente);
		this.noticias.add( n );
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<User> getSuscritos() {
		return suscritos;
	}

	public void setSuscritos(List<User> suscritos) {
		this.suscritos = suscritos;
	}

	public List<News> getNoticias() {
		return noticias;
	}

	public void setNoticias(List<News> noticias) {
		this.noticias = noticias;
	}
}
