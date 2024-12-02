package backendDietiEstates.backendDietiEstates.Entity;

public class Agenzia {
	private String nomeAgenzia;
	private String partitaIVA;
	private Agente agente;
	
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
	public Agente getAgente() {
		return agente;
	}
	public void setAgente(Agente agente) {
		this.agente = agente;
	}
	
	
}
