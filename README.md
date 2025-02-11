# DietiEstates25

DietiEstates25 è una piattaforma per la gestione di servizi immobiliari. Il sistema permette a più agenzie di pubblicare inserzioni immobiliari.  
Gli utenti possono quindi visualizzare le inserzioni, prenotare visite e fare offerte per acquistare/affittare un immobile.  

L'obiettivo del progetto è fornire un'applicazione performante e affidabile (mobile, desktop o web-based), attraverso cui gli utenti possano fruire delle funzionalità in modo intuitivo, rapido e piacevole.  

## Funzionalità principali

- **Gestione annunci**: le agenzie possono pubblicare e modificare le inserzioni immobiliari.
- **Ricerca avanzata**: gli utenti possono filtrare gli annunci in base a criteri specifici.
- **Prenotazione visite**: possibilità di prenotare appuntamenti per visitare gli immobili.
- **Offerte di acquisto/affitto**: gli utenti possono fare offerte direttamente dalla piattaforma.
- **Registrazione tramite GitHub e Google**: login sicuro per gli utenti.
- **Interfaccia intuitiva**: design moderno e usabilità migliorata per un'esperienza fluida.

## Tecnologie utilizzate

- **Backend**: Java 17+ con JakartaEE, PostgreSQL (PostGIS)
- **Frontend**: Android (per app mobile)
- **Architettura**: REST API per la comunicazione tra frontend e backend
- **Containerizzazione**: Docker per il backend
- **Cloud**: Deployment su Azure

## Installazione e avvio

### Prerequisiti

- Docker e Docker Compose
- Java 17+
- PostgreSQL con estensione PostGIS
- Android Studio (per il client mobile)

### Avvio del backend (da Docker)
- Clona la repository ed avvia il container:
```sh
git clone https://github.com/n00w4/DietiEstates25
cd BACKEND/backendDietiEstates
docker-compose up -d
```

### Avvio dell'app mobile
- Aprire il progetto in Android Studio
- Configurare l'API URL nel file di configurazione
- Eseguire l'app su un dispositivo/emulatore

## Autori
- Luigi Cesaro ([@n00w4](https://github.com/n00w4))
- Sabrina Cassone ([@itssabrinaa](https://github.com/itssabrinaa))

## Licenza
Questo progetto è rilasciato sotto la licenza MIT. Vedi il file [LICENSE](LICENSE) per maggiori dettagli.

