package it.dietiestates.data;

public class Gestore extends Amministratore{
	private String passwordAdmin;

	public Gestore(String nome, String cognome, String email, String password, String partitaIVA, String nomeAgenzia, String passwordAdmin) {
		super(nome, cognome, email, password, partitaIVA, nomeAgenzia);
		setPasswordAdmin(passwordAdmin);
	}

	//Getter and Setter for passwordAdmin
	public String getPasswordAdmin() {
		return passwordAdmin;
	}

	public void setPasswordAdmin(String passwordAdmin) {
		this.passwordAdmin = passwordAdmin;
	}
	
}
