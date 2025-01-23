package it.dietiestates.data.dto;

public class RicercaAnnuncio {
    private int dimensioni;
    private float prezzoMin;
    private float prezzoMax;
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
    private double latitudine;
    private double longitudine;

    public RicercaAnnuncio() {
        // Costruttore predefinito pubblico per deserializzazione dei dati
    }

    public int getDimensioni() {
        return dimensioni;
    }

    public void setDimensioni(int dimensioni) {
        this.dimensioni = dimensioni;
    }

    public float getPrezzoMin() {
        return prezzoMin;
    }

    public void setPrezzoMin(float prezzoMin) {
        this.prezzoMin = prezzoMin;
    }

    public float getPrezzoMax() {
        return prezzoMax;
    }

    public void setPrezzoMax(float prezzoMax) {
        this.prezzoMax = prezzoMax;
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

    public double getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(double latitudine) {
        this.latitudine = latitudine;
    }

    public double getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(double longitudine) {
        this.longitudine = longitudine;
    }
}


