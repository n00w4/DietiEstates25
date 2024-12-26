package it.dietiestates.data;

public class Annuncio {
	private int id;
	private String titolo;
	private String indirizzo;
	private String immagine;
	private String descrizione;
	private int dimensioni;
	private float prezzo;
	private String piano;
	private int numeroStanze;
	private String classeEnergetica;
	private boolean ascensore;
	private boolean portineria;
	private boolean climatizzazione;
	private boolean boxAuto;
	private boolean terrazzo;
	private boolean giardino;
	private String tipoAnnuncio;
	private Agente agente;
	
	public Annuncio(Builder builder) {
		this.id = builder.id;
		this.titolo = builder.titolo;
		this.indirizzo = builder.indirizzo;
		this.immagine = builder.immagine;
		this.descrizione = builder.descrizione;
		this.dimensioni = builder.dimensioni;
		this.prezzo = builder.prezzo;
		this.piano = builder.piano;
		this.numeroStanze = builder.numeroStanze;
		this.classeEnergetica = builder.classeEnergetica;
		this.ascensore = builder.ascensore;
		this.portineria = builder.portineria;
		this.climatizzazione = builder.climatizzazione;
		this.boxAuto = builder.boxAuto;
		this.terrazzo = builder.terrazzo;
		this.giardino = builder.giardino;
		this.tipoAnnuncio = builder.tipoAnnuncio;
		this.agente = builder.agente;
	}

	public int getID() {
		return id;
	}
	public void setID(int id) {
		this.id = id;
	}
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
	public String getImmagine() {
		return immagine;
	}
	public void setImmagine(String immagine) {
		this.immagine = immagine;
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
	public String getPiano() {
		return piano;
	}
	public void setPiano(String piano) {
		this.piano = piano;
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
	public boolean isClimatizzazione() {
		return climatizzazione;
	}
	public void setClimatizzazione(boolean climatizzazione) {
		this.climatizzazione = climatizzazione;
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
	public Agente getAgente() {
		return agente;
	}
	public void setAgente(Agente agente) {
		this.agente = agente;
	}

	public static class Builder {
		private int id;
		private String titolo;
		private String indirizzo;
		private String immagine;
		private String descrizione;
		private int dimensioni;
		private float prezzo;
		private String piano;
		private int numeroStanze;
		private String classeEnergetica;
		private boolean ascensore;
		private boolean portineria;
		private boolean climatizzazione;
		private boolean boxAuto;
		private boolean terrazzo;
		private boolean giardino;
		private String tipoAnnuncio;
		private Agente agente;

		public Builder(int id) {
			this.id = id;
		}

		public Builder titolo(String titolo) {
			this.titolo = titolo;
			return this;
		}

		public Builder indirizzo(String indirizzo) {
			this.indirizzo = indirizzo;
			return this;
		}

		public Builder immagine(String immagine) {
			this.immagine = immagine;
			return this;
		}

		public Builder descrizione(String descrizione) {
			this.descrizione = descrizione;
			return this;
		}

		public Builder dimensioni(int dimensioni) {
			this.dimensioni = dimensioni;
			return this;
		}

		public Builder prezzo(float prezzo) {
			this.prezzo = prezzo;
			return this;
		}

		public Builder piano(String piano) {
			this.piano = piano;
			return this;
		}

		public Builder numeroStanze(int numeroStanze) {
			this.numeroStanze = numeroStanze;
			return this;
		}

		public Builder classeEnergetica(String classeEnergetica) {
			this.classeEnergetica = classeEnergetica;
			return this;
		}

		public Builder ascensore(boolean ascensore) {
			this.ascensore = ascensore;
			return this;
		}

		public Builder portineria(boolean portineria) {
			this.portineria = portineria;
			return this;
		}

		public Builder climatizzazione(boolean climatizzazione) {
			this.climatizzazione = climatizzazione;
			return this;
		}

		public Builder boxAuto(boolean boxAuto) {
			this.boxAuto = boxAuto;
			return this;
		}

		public Builder terrazzo(boolean terrazzo) {
			this.terrazzo = terrazzo;
			return this;
		}

		public Builder giardino(boolean giardino) {
			this.giardino = giardino;
			return this;
		}

		public Builder tipoAnnuncio(String tipoAnnuncio) {
			this.tipoAnnuncio = tipoAnnuncio;
			return this;
		}

		public Builder agente(Agente agente) {
			this.agente = agente;
			return this;
		}

		public Annuncio build() {
			return new Annuncio(this);
		}
	}
}
