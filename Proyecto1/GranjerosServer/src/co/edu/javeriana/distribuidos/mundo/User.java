package co.edu.javeriana.distribuidos.mundo;

public class User {
	private String ip;
	private String ubicacion;
	private String tipoProducto;
	private String tamano;
	private boolean online;
	
	public User( String ipP, String ubiacionP, String tipoProductoP, String tamanoP, boolean onlineP ) {
		this.ip = ipP;
		this.ubicacion = ubiacionP;
		this.tipoProducto = tipoProductoP;
		this.tamano = tamanoP;
		this.online = onlineP;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}

	public String getTipoProducto() {
		return tipoProducto;
	}

	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	public String getTamano() {
		return tamano;
	}

	public void setTamano(String tamano) {
		this.tamano = tamano;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}
}
