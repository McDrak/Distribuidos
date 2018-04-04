package co.edu.javeriana.distribuidos.mundo;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ServerBackend {
	private List<User> usuarios;
	private List< Topic > temas;
	
	public ServerBackend( ) {
		usuarios = new ArrayList<User>();
		temas = new ArrayList< Topic >();
		this.poblarTemas();
	}
	
	public void poblarTemas( ) {
		Topic clima = new Topic("clima");
		Topic precio = new Topic("precio");
		Topic expectativa = new Topic("expectativa");
		
		this.temas.add(clima);
		this.temas.add(precio);
		this.temas.add(expectativa);
	}
	
	public boolean existeTema( String nombre ) {
		boolean flag = false;
		for( Topic t : temas ) {
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
			Topic t = new Topic( nombre );
			temas.add(t);
		}
		
		return flag;
	}
	
	public boolean anadirUsuario( String ip, String ubicacion, String tipoProducto, String tam ) {
		boolean flag = true;
		
		for( User user : usuarios ) {
			if( user.getIp().equals(ip) ) {
				flag = false;
			}
		}
		
		if( flag == true ) {
			User user = new User( ip, ubicacion, tipoProducto, tam, true );
			usuarios.add(user);
		}
		
		return flag;
	}
	
	public String agregarNoticia( String cadena ) {
		String[] args = cadena.split("%");
		String nombre = args[0];
		String id = args[1];
		String cont = args[2];
		String fuente = args[3];
		
		boolean flag = false;
		
		for( Topic t : temas ) {
			if( nombre.equals(t.getNombre()) ) {
				t.agregarNoticia(id, cont, fuente);
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
	
	public List<String> obtenerNoticiasParaUsuario( String ip ) {
		List<String> news = new ArrayList<String>();
		
		for( Topic t : temas ) {
			if( t.estaSuscrito(ip) ) {
				List<News> ns = t.getNoticias();
				
				for( News n : ns ) {
					String time = LocalTime.now().toString();
					if( time.startsWith(n.getId()) ) {
						news.add( n.getContenido() );
					}
				}
			}
		}
		
		return news;
	}
	
	public User buscarUsuario( String ip ) {
		User user = null;
		for( User u : usuarios ) {
			if( u.getIp().equals(ip) ) {
				user = u;
			}
		}
		
		return user;
	}
	
	public int suscribir( String[] tems, User user ) {
		int cont = 0;
		for( String s : tems ) {
			for( Topic t : temas ) {
				if( s.equals(t.getNombre()) ) {
					boolean susc = t.suscribir(user);
					if( susc == true ) {
						cont++;
					}
				}
			}
		}
		
		return cont;
	}
	
	public void desconectarUsuario( String ip ) {
		User u = buscarUsuario(ip);
		if( u != null ) {
			usuarios.remove(u);
			for( Topic t : temas ) {
				t.desSuscribir(u);
			}
		}
	}

	public List<User> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<User> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Topic> getTemas() {
		return temas;
	}

	public void setTemas(List<Topic> temas) {
		this.temas = temas;
	}
}
