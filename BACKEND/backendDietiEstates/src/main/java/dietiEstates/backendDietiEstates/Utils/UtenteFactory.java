package dietiEstates.backendDietiEstates.Utils;

import dietiEstates.backendDietiEstates.Data.Agente;
import dietiEstates.backendDietiEstates.Data.Amministratore;
import dietiEstates.backendDietiEstates.Data.Cliente;
import dietiEstates.backendDietiEstates.Data.Gestore;
import dietiEstates.backendDietiEstates.Data.Utente;

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

