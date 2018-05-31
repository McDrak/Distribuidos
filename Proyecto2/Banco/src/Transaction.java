import java.io.Serializable;
import java.util.Date;

public class Transaction implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public enum TIPO_TRANSACCION {
		RETIRO, DEPOSITO, CREACION, CIERRE, CONSULTA, PAGO, LISTA
	};
	
	private TIPO_TRANSACCION tipo;
	private double amount;
	private Date date;
	
	public Transaction( TIPO_TRANSACCION tipoP, double amountP ) {
		this.tipo = tipoP;
		this.amount = amountP;
		this.date = new Date( );
	}

	public TIPO_TRANSACCION getTipo() {
		return tipo;
	}

	public void setTipo(TIPO_TRANSACCION tipo) {
		this.tipo = tipo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
