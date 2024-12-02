-- In questo file sono riportati i trigger del database

-- Prenotazione valida
CREATE OR REPLACE FUNCTION est.checkValidPrenotazione() RETURNS TRIGGER AS
$$
DECLARE
    prenotazioneInCorso INTEGER;
BEGIN
    SELECT COUNT(*) INTO prenotazioneInCorso
    FROM est.Prenotazione AS P
    WHERE P.email = NEW.email
        AND P.idAnnuncio != NEW.idAnnuncio
        AND P.isAccettata IS NULL;

    IF prenotazioneInCorso > 0 THEN
        RAISE EXCEPTION 'Non puoi prenotare due annunci contemporaneamente';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER checkValidPrenotazione
BEFORE INSERT ON est.Prenotazione
FOR EACH ROW EXECUTE FUNCTION est.checkValidPrenotazione();

-- Creazione notifica
CREATE OR REPLACE FUNCTION est.createNotifica() RETURNS TRIGGER AS
$$
DECLARE
    emailAgente VARCHAR(255);
BEGIN
    SELECT email INTO emailAgente FROM est.Annuncio WHERE idAnnuncio = NEW.idAnnuncio;
    INSERT INTO est.Notifica (dataOra, email, idPrenotazione) VALUES (CURRENT_TIMESTAMP, emailAgente, NEW.idPrenotazione)
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER createNotifica
AFTER INSERT ON est.Prenotazione
FOR EACH ROW EXECUTE FUNCTION est.createNotifica();