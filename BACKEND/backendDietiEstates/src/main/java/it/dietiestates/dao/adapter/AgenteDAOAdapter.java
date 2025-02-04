package it.dietiestates.dao.adapter;

import it.dietiestates.dao.model.AgenteDAO;
import it.dietiestates.data.dto.AddUtenteForm;
import it.dietiestates.data.model.Agente;
import it.dietiestates.exception.DataAccessException;
import it.dietiestates.utils.PasswordGenerator;

public class AgenteDAOAdapter implements UtenteDAOAdapter {
    private final AgenteDAO agenteDAO;

    public AgenteDAOAdapter(AgenteDAO agenteDAO) {
        this.agenteDAO = agenteDAO;
    }

    @Override
    public boolean insertUtente(AddUtenteForm form) throws DataAccessException {
        String generatedPassword = PasswordGenerator.generatePassword();

        Agente agente = new Agente(form.getName(), form.getSurname(), form.getEmail(), generatedPassword, form.getPartitaIVA());
        return agenteDAO.insert(agente);
    }
}
