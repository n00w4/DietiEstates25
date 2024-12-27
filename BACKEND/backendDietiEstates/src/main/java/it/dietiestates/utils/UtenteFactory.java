package it.dietiestates.utils;

import it.dietiestates.data.Agente;
import it.dietiestates.data.Amministratore;
import it.dietiestates.data.Cliente;
import it.dietiestates.data.Gestore;
import it.dietiestates.data.Utente;

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

