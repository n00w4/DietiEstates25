package it.dietiestates.dao.adapter;

import it.dietiestates.dao.model.AmministratoreDAO;
import it.dietiestates.data.dto.AddUtenteForm;
import it.dietiestates.data.model.Amministratore;
import it.dietiestates.exception.DataAccessException;
import it.dietiestates.utils.PasswordGenerator;

public class AmministratoreDAOAdapter implements UtenteDAOAdapter {
    private final AmministratoreDAO amministratoreDAO;

    public AmministratoreDAOAdapter(AmministratoreDAO amministratoreDAO) {
        this.amministratoreDAO = amministratoreDAO;
    }

    @Override
    public boolean insertUtente(AddUtenteForm form) throws DataAccessException {
        String generatedPassword = PasswordGenerator.generatePassword();

        Amministratore amministratore = new Amministratore(form.getName(), form.getSurname(), form.getEmail(), generatedPassword, form.getPartitaIVA());
        return amministratoreDAO.insert(amministratore);
    }
}
