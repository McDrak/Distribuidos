package co.edu.javeriana.distribuidos.mundo;

public class News {
	private String id;
	private String contenido;
	private String fuente;
	
	public News( String idP, String contenidoP, String fuenteP ) {
		this.id = idP;
		this.contenido = contenidoP;
		this.fuente = fuenteP;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getFuente() {
		return fuente;
	}

	public void setFuente(String fuente) {
		this.fuente = fuente;
	}
}
