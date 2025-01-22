-- In questo file sono contenuti dati con cui testare il database

-- Insert cliente
INSERT INTO est.Cliente (nome, cognome, email, password) 
VALUES (
    'Mario', 
    'Rossi', 
    'mario.rossi@example.com', 
    'Password1!'
);

-- Insert Agenzia
INSERT INTO est.Agenzia (
    nomeAgenzia, partitaIVA
) 
VALUES (
    'Agenzia Immobiliare Rossi',
    '12345678901'
);

-- Insert Agente
INSERT INTO est.Agente (
    nome, cognome, email, password, partitaIVA
) 
VALUES (
    'Mario',
    'Rossi',
    'agente1@example.com',
    'Str0ng@Pass123',
    '12345678901'
);

-- Insert Gestore
INSERT INTO est.Gestore (nome, cognome, email, password, passwordAdmin, partitaIVA)
VALUES (
    'Marco', 
    'Bianchi', 
    'marco.bianchi@example.com', 
    'Password@123', 
    'AdminPass@456', 
    '12345678901'
);

-- Insert Annuncio
INSERT INTO est.Annuncio (
    titolo, indirizzo, immagine, descrizione, dimensioni, prezzo, piano, numeroStanze, 
    classeEnergetica, ascensore, portineria, climatizzazione, boxAuto, terrazzo, giardino, 
    tipoAnnuncio, posizione, email
) 
VALUES (
    'Appartamento Elegante',
    'Via Roma 123',
    'immagine1.jpg',
    'Appartamento in centro città, vicino a tutti i servizi',
    120,
    250000.00,
    '2° piano',
    3,
    'A',
    TRUE,
    TRUE,
    TRUE,
    TRUE,
    TRUE,
    TRUE,
    'vendita',
    ST_GeomFromText('POINT(12.4924 41.8902)', 4326),
    'agente1@example.com'
);
