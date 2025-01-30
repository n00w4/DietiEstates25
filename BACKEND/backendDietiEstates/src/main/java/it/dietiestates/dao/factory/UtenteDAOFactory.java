package it.dietiestates.dao.factory;

import it.dietiestates.dao.adapter.AgenteDAOAdapter;
import it.dietiestates.dao.adapter.AmministratoreDAOAdapter;
import it.dietiestates.dao.adapter.UtenteDAOAdapter;
import it.dietiestates.dao.sql.SQLAgenteDAO;
import it.dietiestates.dao.sql.SQLAmministratoreDAO;

import java.sql.Connection;

public class UtenteDAOFactory {
    private final Connection connection;

    public UtenteDAOFactory(Connection connection) {
        this.connection = connection;
    }

    public UtenteDAOAdapter getUtenteDAO(String userType) {
        switch (userType) {
            case "Agente":
                return new AgenteDAOAdapter(new SQLAgenteDAO(connection));
            case "Amministratore":
                return new AmministratoreDAOAdapter(new SQLAmministratoreDAO(connection));
            default:
                throw new IllegalArgumentException("Tipo utente non valido: " + userType);
        }
    }
}


