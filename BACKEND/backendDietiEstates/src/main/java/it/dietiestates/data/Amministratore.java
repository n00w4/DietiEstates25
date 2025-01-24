package it.dietiestates.data;

public class Amministratore extends Utente {
	private String partitaIVA;
	private String nomeAgenzia;

	public Amministratore() {
		super();
		// Costruttore vuoto per permettere la deserializzazione
	}

	public Amministratore(String nome, String cognome, String email, String password, String partitaIVA, String nomeAgenzia) {
		super(nome, cognome, email, password);
		setPartitaIVA(partitaIVA);
		setNomeAgenzia(nomeAgenzia);
	}

	//Getter and Setter for agenzia
	@Override
	public String getPartitaIVA() {
		return partitaIVA;
	}

	public void setPartitaIVA(String partitaIVA) {
		this.partitaIVA = partitaIVA;
	}

	@Override
	public String getNomeAgenzia() {
		return nomeAgenzia;
	}

	public void setNomeAgenzia(String nomeAgenzia) {
		this.nomeAgenzia = nomeAgenzia;
	}
}
