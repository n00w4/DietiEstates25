package it.dietiestates.data.factory;

import it.dietiestates.data.model.*;

public class UtenteFactory {
    // Costruttore privato per evitare l'instanziazione della classe
    private UtenteFactory() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

	public static Utente creaUtente(
			String tipoUtente,
            String nome,
            String cognome,
            String email,
            String password,
            String passwordAdmin,
            String partitaIVA,
            String nomeAgenzia) {

		switch (tipoUtente) {
			case "Cliente":
                return new Cliente(nome, cognome, email, password);
            case "Gestore":
                return new Gestore(nome, cognome, email, password, partitaIVA, nomeAgenzia, passwordAdmin);
            case "Amministratore":
                return new Amministratore(nome, cognome, email, password, partitaIVA, nomeAgenzia);
            case "Agente":
                return new Agente(nome, cognome, email, password, partitaIVA, nomeAgenzia);
            default:
                throw new IllegalArgumentException("Tipo utente non valido: " + tipoUtente);
        }
    }
}

