package it.dietiEstates.data;

public class Agenzia {
	private String nomeAgenzia;
	private String partitaIVA;

	public Agenzia(String nomeAgenzia, String partitaIVA) {
		this.nomeAgenzia = nomeAgenzia;
		this.partitaIVA = partitaIVA;
	}
	
	public String getNomeAgenzia() {
		return nomeAgenzia;
	}
	public void setNomeAgenzia(String nomeAgenzia) {
		this.nomeAgenzia = nomeAgenzia;
	}
	
	public String getPartitaIVA() {
		return partitaIVA;
	}
	public void setPartitaIVA(String partitaIVA) {
		this.partitaIVA = partitaIVA;
	}

	
}
