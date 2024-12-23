package it.dietiestates.data;

public class Amministratore extends Utente {
	private String partitaIVA;
	
	public Amministratore(String nome, String cognome, String email, String password, String partitaIVA) {
		super(nome, cognome, email, password);
		this.partitaIVA = partitaIVA;
	}

	//Getter and Setter for agenzia
	public String getPartitaIVA() {
		return partitaIVA;
	}

	public void setPartitaIVA(String partitaIVA) {
		this.partitaIVA = partitaIVA;
	}
}
