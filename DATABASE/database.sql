-- In questo file è riportata la creazione del database

-- Verifica estensione postGIS per dati geografici
CREATE EXTENSION IF NOT EXISTS postgis;

-- Creazione schema
DROP SCHEMA IF EXISTS est CASCADE;
CREATE SCHEMA est;

-- Table Agenzia
CREATE TABLE est.Agenzia (
    nomeAgenzia VARCHAR(255) NOT NULL,
    partitaIVA CHAR(11) PRIMARY KEY,

    CONSTRAINT checkValidPartitaIVA CHECK (partitaIVA ~ '^[0-9]{11}$')
);

-- Table Cliente
CREATE TABLE est.Cliente (
    nome VARCHAR(255) NOT NULL,
    cognome VARCHAR(255) NOT NULL,
    email VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,

    CONSTRAINT checkValidEmail CHECK (email ~ '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$'),
    CONSTRAINT checkValidPasswd CHECK (password ~ '^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$')
);

-- Table Agente
CREATE TABLE est.Agente (
    nome VARCHAR(255) NOT NULL,
    cognome VARCHAR(255) NOT NULL,
    email VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    partitaIVA CHAR(11) REFERENCES est.Agenzia(partitaIVA) ON DELETE CASCADE,

    CONSTRAINT checkValidEmail CHECK (email ~ '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$'),
    CONSTRAINT checkValidPasswd CHECK (password ~ '^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$')
);

-- Table Amministratore
CREATE TABLE est.Amministratore (
    nome VARCHAR(255) NOT NULL,
    cognome VARCHAR(255) NOT NULL,
    email VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    partitaIVA CHAR(11) REFERENCES est.Agenzia(partitaIVA) ON DELETE CASCADE,

    CONSTRAINT checkValidEmail CHECK (email ~ '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$'),
    CONSTRAINT checkValidPasswd CHECK (password ~ '^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$')
);

-- Table Gestore
CREATE TABLE est.Gestore (
    nome VARCHAR(255) NOT NULL,
    cognome VARCHAR(255) NOT NULL,
    email VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    passwordAdmin VARCHAR(255) NOT NULL,
    partitaIVA CHAR(11) REFERENCES est.Agenzia(partitaIVA) ON DELETE CASCADE,

    CONSTRAINT checkValidEmail CHECK (email ~ '^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$'),
    CONSTRAINT checkValidPasswd CHECK (password ~ '^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$'),
    CONSTRAINT checkValidPasswdAdmin CHECK (passwordAdmin ~ '^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[@$!%*?&])[A-Za-z\d@$!%*?&]{8,}$')
);

-- View unificata per tutti gli utenti
CREATE OR REPLACE VIEW est.utenti_unificati AS
SELECT 
    'Cliente' AS tipo_utente,
    c.nome,
    c.cognome,
    c.email,
    c.password,
    NULL AS passwordAdmin,
    NULL AS partitaIVA,
    NULL AS nomeAgenzia
FROM 
    est.Cliente c
UNION ALL
SELECT 
    'Agente' AS tipo_utente,
    a.nome,
    a.cognome,
    a.email,
    a.password,
    NULL AS passwordAdmin,
    a.partitaIVA,
    ag.nomeAgenzia
FROM 
    est.Agente a
    JOIN est.Agenzia ag ON a.partitaIVA = ag.partitaIVA
UNION ALL
SELECT 
    'Amministratore' AS tipo_utente,
    adm.nome,
    adm.cognome,
    adm.email,
    adm.password,
    NULL AS passwordAdmin,
    adm.partitaIVA,
    ag.nomeAgenzia
FROM 
    est.Amministratore adm
    JOIN est.Agenzia ag ON adm.partitaIVA = ag.partitaIVA
UNION ALL
SELECT 
    'Gestore' AS tipo_utente,
    g.nome,
    g.cognome,
    g.email,
    g.password,
    g.passwordAdmin,
    g.partitaIVA,
    ag.nomeAgenzia
FROM 
    est.Gestore g
    JOIN est.Agenzia ag ON g.partitaIVA = ag.partitaIVA;


-- Tipo ENUM_ANNUNCIO
DROP TYPE IF EXISTS ENUM_ANNUNCIO CASCADE;
CREATE TYPE ENUM_ANNUNCIO AS ENUM ('vendita', 'affitto');

-- Table Annuncio
CREATE TABLE est.Annuncio (
    idAnnuncio INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    titolo VARCHAR(255) NOT NULL,
    indirizzo VARCHAR(255) NOT NULL,
    immagine VARCHAR(255) NOT NULL,
    descrizione VARCHAR(255) NOT NULL,
    dimensioni INTEGER NOT NULL,
    prezzo NUMERIC(12,2) NOT NULL,
    piano VARCHAR(255) NOT NULL,
    numeroStanze INTEGER NOT NULL,
    classeEnergetica VARCHAR(255) NOT NULL,
    ascensore BOOLEAN NOT NULL,
    portineria BOOLEAN NOT NULL,
    climatizzazione BOOLEAN NOT NULL,
    boxAuto BOOLEAN NOT NULL,
    terrazzo BOOLEAN NOT NULL,
    giardino BOOLEAN NOT NULL,
    tipoAnnuncio ENUM_ANNUNCIO NOT NULL,
    posizione GEOGRAPHY(Point, 4326),
    email VARCHAR(255) REFERENCES est.Agente(email) ON DELETE CASCADE,

    CONSTRAINT checkValidPrezzo CHECK (prezzo > 0),
    CONSTRAINT checkValidDimensioni CHECK (dimensioni > 0),
    CONSTRAINT checkValidNumeroStanze CHECK (numeroStanze > 0),
    CONSTRAINT checkValidImmagine CHECK (immagine ~ '^[a-zA-Z0-9._%-]+\.(jpg|jpeg|png|gif)$'),
    CONSTRAINT enforceSridPosizione CHECK (ST_SRID(posizione) = 4326)
);

-- Index per posizione (potrebbe essere necessario avere permessi speciali)
CREATE INDEX idxPosizione ON est.Annuncio USING GIST (posizione);

-- Table Prenotazione
CREATE TABLE est.Prenotazione (
    idPrenotazione INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    dataInizio TIMESTAMP NOT NULL,
    dataFine TIMESTAMP NOT NULL,
    isAccettata BOOLEAN,
    idAnnuncio INTEGER REFERENCES est.Annuncio(idAnnuncio) ON DELETE CASCADE,
    email VARCHAR(255) REFERENCES est.Cliente(email) ON DELETE CASCADE,

    CONSTRAINT checkValidData CHECK ((dataInizio < dataFine)),
    CONSTRAINT checkValidDataPrenotazione CHECK ((dataInizio > CURRENT_TIMESTAMP))
);

-- Table Notifica
CREATE TABLE est.Notifica (
    idNotifica INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    dataOra TIMESTAMP NOT NULL,
    email VARCHAR(255) REFERENCES est.Agente(email) ON DELETE CASCADE,
    idPrenotazione INTEGER REFERENCES est.Prenotazione(idPrenotazione) ON DELETE CASCADE,

    CONSTRAINT checkValidDataNotifica CHECK ((dataOra >= CURRENT_TIMESTAMP))
);