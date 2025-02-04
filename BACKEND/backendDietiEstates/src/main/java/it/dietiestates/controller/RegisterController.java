package it.dietiestates.controller;

import it.dietiestates.dao.model.ClienteDAO;
import it.dietiestates.dao.sql.model.SQLClienteDAO;
import it.dietiestates.data.dto.SuccessApiResponse;
import it.dietiestates.data.model.Cliente;
import it.dietiestates.database.PgSQL;
import it.dietiestates.exception.DataAccessException;
import it.dietiestates.exception.UniqueConstraintViolationException;
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
            clienteDAO.insert(nuovoCliente);
            logger.info(() -> "Cliente registrato: " + nuovoCliente.getEmail());
            SuccessApiResponse successResponse = new SuccessApiResponse("Cliente creato con successo");
            return Response.status(Response.Status.CREATED).entity(successResponse).build();
        } catch (UniqueConstraintViolationException e) {
            return Response.status(Response.Status.CONFLICT).entity(e.getApiResponse()).build();
        } catch (DataAccessException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getApiResponse()).build();
        }
    }
}
