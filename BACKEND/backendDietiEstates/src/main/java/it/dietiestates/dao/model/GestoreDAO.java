package it.dietiestates.dao.model;

import it.dietiestates.data.dto.ChangeAdminPwdForm;
import it.dietiestates.data.model.Gestore;
import it.dietiestates.exception.DataAccessException;

public interface GestoreDAO {
	boolean insert(Gestore gestore) throws DataAccessException;
	boolean updateAdminPassword(ChangeAdminPwdForm form) throws DataAccessException;
}
