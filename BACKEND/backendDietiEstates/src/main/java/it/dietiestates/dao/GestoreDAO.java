package it.dietiestates.dao;

import it.dietiestates.data.Gestore;
import it.dietiestates.dto.ChangeAdminPwdForm;
import it.dietiestates.exception.DataAccessException;

public interface GestoreDAO {
	boolean insert(Gestore gestore) throws DataAccessException;
	boolean updateAdminPassword(ChangeAdminPwdForm form) throws DataAccessException;
}
