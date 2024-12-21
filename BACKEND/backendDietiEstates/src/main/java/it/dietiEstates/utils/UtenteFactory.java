package it.dietiEstates.utils;

import it.dietiEstates.data.Agente;
import it.dietiEstates.data.Amministratore;
import it.dietiEstates.data.Cliente;
import it.dietiEstates.data.Gestore;
import it.dietiEstates.data.Utente;

public class UtenteFactory {
	public static Utente creaUtente(
			String tipoUtente,
            String nome,
            String cognome,
            String email,
            String password,
            String passwordAdmin,
            String partitaIVA) {
		
		switch (tipoUtente) {
			case "Cliente":
                return new Cliente(nome, cognome, email, password);
            case "Gestore":
                return new Gestore(nome, cognome, email, password, partitaIVA, passwordAdmin);
            case "Amministratore":
                return new Amministratore(nome, cognome, email, password, partitaIVA);
            case "Agente":
                return new Agente(nome, cognome, email, password, partitaIVA);
            default:
                throw new IllegalArgumentException("Tipo utente non valido: " + tipoUtente);
        }
    }
}

