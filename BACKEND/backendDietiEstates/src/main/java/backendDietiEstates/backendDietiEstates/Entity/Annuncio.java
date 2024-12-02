package backendDietiEstates.backendDietiEstates.Entity;

public class Annuncio {
	private String titolo;
	private String indirizzo;
	private String descrizione;
	private int dimensioni;
	private float prezzo;
	private int numeroStanze;
	private String classeEnergetica;
	private boolean ascensore;
	private boolean portineria;
	private boolean climatizzaione;
	private boolean boxAuto;
	private boolean terrazzo;
	private boolean giardino;
	private String tipoAnnuncio;
	
	public String getTitolo() {
		return titolo;
	}
	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	public int getDimensioni() {
		return dimensioni;
	}
	public void setDimensioni(int dimensioni) {
		this.dimensioni = dimensioni;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public void setPrezzo(float prezzo) {
		this.prezzo = prezzo;
	}
	public int getNumeroStanze() {
		return numeroStanze;
	}
	public void setNumeroStanze(int numeroStanze) {
		this.numeroStanze = numeroStanze;
	}
	public String getClasseEnergetica() {
		return classeEnergetica;
	}
	public void setClasseEnergetica(String classeEnergetica) {
		this.classeEnergetica = classeEnergetica;
	}
	public boolean isAscensore() {
		return ascensore;
	}
	public void setAscensore(boolean ascensore) {
		this.ascensore = ascensore;
	}
	public boolean isPortineria() {
		return portineria;
	}
	public void setPortineria(boolean portineria) {
		this.portineria = portineria;
	}
	public boolean isClimatizzaione() {
		return climatizzaione;
	}
	public void setClimatizzaione(boolean climatizzaione) {
		this.climatizzaione = climatizzaione;
	}
	public boolean isBoxAuto() {
		return boxAuto;
	}
	public void setBoxAuto(boolean boxAuto) {
		this.boxAuto = boxAuto;
	}
	public boolean isTerrazzo() {
		return terrazzo;
	}
	public void setTerrazzo(boolean terrazzo) {
		this.terrazzo = terrazzo;
	}
	public boolean isGiardino() {
		return giardino;
	}
	public void setGiardino(boolean giardino) {
		this.giardino = giardino;
	}
	public String getTipoAnnuncio() {
		return tipoAnnuncio;
	}
	public void setTipoAnnuncio(String tipoAnnuncio) {
		this.tipoAnnuncio = tipoAnnuncio;
	}
}
