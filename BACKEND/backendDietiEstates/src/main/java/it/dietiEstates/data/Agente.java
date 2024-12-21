package it.dietiEstates.data;

public class Agente extends Utente {
	private String partitaIVA;
	
	public Agente(String nome, String cognome, String email, String password, String partitaIVA) {
		super(nome, cognome, email, password);
		this.partitaIVA = partitaIVA;
	}

	//Getter and Setter for partitaIVA
	public String getPartitaIVA() {
		return partitaIVA;
	}

	public void setPartitaIVA(String partitaIVA) {
		this.partitaIVA = partitaIVA;
	}
	
}
