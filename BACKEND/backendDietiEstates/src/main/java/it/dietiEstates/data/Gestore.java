package it.dietiEstates.data;

public class Gestore extends Amministratore{
	private String passwordAdmin;

	public Gestore(String nome, String cognome, String email, String password, String partitaIVA, String passwordAdmin) {
		super(nome, cognome, email, password, partitaIVA);
		this.passwordAdmin = passwordAdmin;
	}

	//Getter and Setter for passwordAdmin
	public String getPasswordAdmin() {
		return passwordAdmin;
	}

	public void setPasswordAdmin(String passwordAdmin) {
		this.passwordAdmin = passwordAdmin;
	}
	
}
