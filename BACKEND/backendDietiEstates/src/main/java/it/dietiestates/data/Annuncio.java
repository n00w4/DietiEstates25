package it.dietiestates.data;

public class Annuncio {
	private Integer id;
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
	private String posizione;
	private String emailAgente;

	public Annuncio() {
		// Costruttore vuoto per deserializzazione dati
	}

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
		this.posizione = builder.posizione;
		this.emailAgente = builder.emailAgente;
	}

	public int getID() {
		return id;
	}
	public String getTitolo() {
		return titolo;
	}
	public String getIndirizzo() {
		return indirizzo;
	}
	public String getImmagine() {
		return immagine;
	}
	public String getDescrizione() {
		return descrizione;
	}
	public int getDimensioni() {
		return dimensioni;
	}
	public float getPrezzo() {
		return prezzo;
	}
	public String getPiano() {
		return piano;
	}
	public int getNumeroStanze() {
		return numeroStanze;
	}
	public String getClasseEnergetica() {
		return classeEnergetica;
	}
	public boolean isAscensore() {
		return ascensore;
	}
	public boolean isPortineria() {
		return portineria;
	}
	public boolean isClimatizzazione() {
		return climatizzazione;
	}
	public boolean isBoxAuto() {
		return boxAuto;
	}
	public boolean isTerrazzo() {
		return terrazzo;
	}
	public boolean isGiardino() {
		return giardino;
	}
	public String getTipoAnnuncio() {
		return tipoAnnuncio;
	}
	public String getPosizione() {
		return posizione;
	}
	public String getEmailAgente() {
		return emailAgente;
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
		private String posizione;
		private String emailAgente;

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

		public Builder posizione(String posizione) {
			this.posizione = posizione;
			return this;
		}

		public Builder emailAgente(String emailAgente) {
			this.emailAgente = emailAgente;
			return this;
		}

		public Annuncio build() {
			return new Annuncio(this);
		}
	}
}
