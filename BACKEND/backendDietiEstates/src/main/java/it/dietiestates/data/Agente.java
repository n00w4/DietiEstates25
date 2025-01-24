package it.dietiestates.data;

public class Agente extends Utente {
	private String partitaIVA;
	private String nomeAgenzia;

	public Agente() {
		super();
		// Costruttore vuoto per permettere la deserializzazione
	}

	public Agente(String nome, String cognome, String email, String password, String partitaIVA, String nomeAgenzia) {
		super(nome, cognome, email, password);
		setPartitaIVA(partitaIVA);
		setNomeAgenzia(nomeAgenzia);
	}

	//Getter and Setter for partitaIVA
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
