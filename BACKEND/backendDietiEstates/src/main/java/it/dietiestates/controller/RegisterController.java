package it.dietiestates.controller;

import it.dietiestates.dao.ClienteDAO;
import it.dietiestates.dao.sql.SQLClienteDAO;
import it.dietiestates.data.Cliente;
import it.dietiestates.database.PgSQL;
import it.dietiestates.exception.DataAccessException;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.logging.Logger;

@Path("register")
public class RegisterController {
    private final ClienteDAO clienteDAO;
    private static final Logger logger = Logger.getLogger(RegisterController.class.getName());

    public RegisterController() throws SQLException {
        this.clienteDAO = new SQLClienteDAO(PgSQL.getConnection());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response register(Cliente nuovoCliente) {
        try {
            if (clienteDAO.insert(nuovoCliente)) {
                logger.info(() -> "Cliente registrato: " + nuovoCliente.getEmail());
                return Response.status(Response.Status.CREATED).entity("Cliente creato con successo").build();
            } else {
                return Response.status(Response.Status.CONFLICT).entity("Cliente già registrato").build();
            }
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Errore del server").build();
        }
    }
}
