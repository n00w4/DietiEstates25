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

-- Trigger per evitare sovrapposizioni di prenotazioni sullo stesso annuncio
CREATE OR REPLACE FUNCTION est.preventOverlappingPrenotazioni() RETURNS TRIGGER AS
$$
DECLARE
    overlappingCount INTEGER;
BEGIN
    -- Conta le prenotazioni esistenti che si sovrappongono con la nuova prenotazione
    SELECT COUNT(*) INTO overlappingCount
    FROM est.Prenotazione AS P
    WHERE P.idAnnuncio = NEW.idAnnuncio
      AND P.isAccettata = TRUE -- Solo prenotazioni accettate contano
      AND (
        (NEW.dataInizio < P.dataFine AND NEW.dataFine > P.dataInizio) -- Controllo sovrapposizione
      );

    IF overlappingCount > 0 THEN
        RAISE EXCEPTION 'Sovrapposizione di prenotazioni per lo stesso annuncio';
    END IF;

    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER preventOverlappingPrenotazioni
BEFORE INSERT OR UPDATE ON est.Prenotazione
FOR EACH ROW EXECUTE FUNCTION est.preventOverlappingPrenotazioni();

-- Creazione notifica
CREATE OR REPLACE FUNCTION est.createNotifica() RETURNS TRIGGER AS
$$
DECLARE
    emailAgente VARCHAR(255);
BEGIN
    SELECT email INTO emailAgente FROM est.Annuncio WHERE idAnnuncio = NEW.idAnnuncio;
    INSERT INTO est.Notifica (dataOra, email, idPrenotazione) VALUES (CURRENT_TIMESTAMP, emailAgente, NEW.idPrenotazione);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER createNotifica
AFTER INSERT ON est.Prenotazione
FOR EACH ROW EXECUTE FUNCTION est.createNotifica();