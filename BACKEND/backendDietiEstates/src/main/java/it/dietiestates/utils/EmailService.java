package it.dietiestates.utils;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import it.dietiestates.data.model.Prenotazione;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.logging.Logger;

public class EmailService {
    private static final String API_KEY = System.getenv("SENDGRID_API_KEY");
    private static final String FROM_EMAIL = "dietiestates25@protonmail.com";
    private static final Logger logger = Logger.getLogger(EmailService.class.getName());

    private EmailService() {
        throw new UnsupportedOperationException("Utility class should not be instantiated");
    }

    /**
     * Invia un'email tramite SendGrid.
     *
     * @param to      Email del destinatario
     * @param subject Oggetto dell'email
     * @param content Contenuto dell'email
     */
    public static void sendEmail(String to, String subject, String content) {
        Email from = new Email(FROM_EMAIL);
        Email toEmail = new Email(to);
        Content emailContent = new Content("text/plain", content);
        Mail mail = new Mail(from, subject, toEmail, emailContent);

        SendGrid sg = new SendGrid(API_KEY);
        Request request = new Request();

        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            sg.api(request);
            logger.info(() -> "Email inviata correttamente a " + to);
        } catch (IOException e) {
            logger.warning("Errore durante l'invio della email: " + e.getMessage());
        }
    }

    /**
     * Genera il soggetto e il contenuto dell'email per un Agente in base alla prenotazione.
     *
     * @param prenotazione Oggetto della prenotazione
     * @return Un array con [soggetto, contenuto]
     */
    public static String[] generateEmailContentAgente(Prenotazione prenotazione) {
        String subject = "Nuova Prenotazione Ricevuta - Annuncio #" + prenotazione.getIdAnnuncio();

        String content = "Gentile Agente,\n\n"
                + "Hai ricevuto una nuova prenotazione per l'annuncio #" + prenotazione.getIdAnnuncio() + ".\n\n"
                + "📅 Data e Orario:\n"
                + "   - Inizio: " + prenotazione.getDataInizio().format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")) + "\n"
                + "   - Fine: " + prenotazione.getDataFine().format(DateTimeFormatter.ofPattern("dd MMM yyyy, HH:mm")) + "\n\n"
                + "👤 Cliente: " + prenotazione.getEmailCliente() + "\n"
                + "Per maggiori dettagli, accedi alla tua area riservata.\n\n"
                + "Cordiali saluti,\nIl Team Dietiestates";

        return new String[]{subject, content};
    }
}

