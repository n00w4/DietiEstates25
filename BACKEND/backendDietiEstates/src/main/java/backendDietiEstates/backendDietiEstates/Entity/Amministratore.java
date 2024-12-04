package backendDietiEstates.backendDietiEstates.Entity;

public class Amministratore extends Utente {
	private Agenzia agenzia;
	
	//Getter and Setter for agenzia
	public Agenzia getAgenzia() {
		return agenzia;
	}

	public void setAgenzia(Agenzia agenzia) {
		this.agenzia = agenzia;
	}
}
