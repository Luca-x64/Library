# Library management system
# This project was a school assignment


Gestione di una biblioteca in java 
con dati in file esterni txt

Vogliamo sviluppare un'applicativo per gestire il sistema di prestito bibliotecario provinciale.
Si modelli il diagramma delle classi e successivamente si implementi il progetto in Java.

In allegato lo schema base proposto in classe ed un template di progetto.


Descrizione 

Il sistema bibliotecario provinciale è composto da una rete di biblioteche presenti in vari comuni della provincia. Una sede è di fatto un edificio con una sala di ingresso, in cui sono presenti le postazioni degli addetti al prestito e restituzione dei libri, alcune sale con gli scaffali dei libri, a cui si può accedere per la consultazione. Tra il materiale disponibile, sono presenti anche CD Audio (musicali) e DVD Video (film), anch'essi prenotabili.

Gli utenti, sono cittadini residenti nella provincia, che si sono registrati nel sistema informatico (e pertanto sono censiti nel data base). Ogni utente può prendere in prestito fino a 10 libri al mese, rinnovabili per un ulteriore mese, se non risultano nel frattempo prenotati da un utente differente. Nel caso dei CD Audio e DVD Video, possono venir tenuti in prestito per 2 settimane, eventualmente rinnovabili.

Il sistema di gestione delle prenotazioni, consente di effettuare la ricerca dei libri disponibili, per titolo, autore o editore, restituendo le informazioni del libro, se censito. In caso sia presente, verranno specificate le seguenti informazioni:
 titolo, autore, editore
 identificativo univoco del libro
 sede di appartenenza
 collocazione a scaffale
 stato del volume: DISPONIBILE, IN PRESTITO, PRENOTATO, IN TRANSITO, SOLO CONSULTAZIONE
 tipo di risorsa: LIBRO, MUSICA (Cd Audio), FILM (Dvd Video)

L'utente, una volta trovato il libro di suo interesse, può decidere di prenotarne una copia (specificando quale), scegliendo presso quale sede vuole ritirarlo. Una volta che il libro sarà disponibile per il ritiro, l'utente riceverà una mail automatica di avviso. Recatosi nella sede del ritiro, il personale, dopo l'identificazione, consegnerà il libro, registrando la data di consegna.
Se il libro dovesse risultare già in prestito, sarà possibile PRENOTARLO. Fino a 3 utenti possono prenotare lo stesso libro, trovandosi così in lista di attesa, in base all'ordine di prenotazione.


Attività progettuale:
definizione delle classi (oggetti) e loro proprietà: libro, sede (biblioteca), utente, autore, editore, personale (?)
definizione delle strutture dati che conterranno gli oggetti (es. liste di libri, utenti, oppure mappe di associazione autore con id dei libri scritti da lui)
definizione ed implementazione dei metodi di supporto e gestione 


Fasi di gestione:
lettura da input dei dati di ricerca (per autore, per titolo, per editore)
ricerca delle risorse che rispettano i criteri selezionati (estrazione della lista di libri/cd/dvd)
visualizzazione dei risultati di ricerca 
modifica dello stato della risorsa (libro, cd, dvd) e dell'utente che a cui è stata assegnata


Note integrative.


A beneficio di tutti, ho aggiornato il progetto template allegato all'elaborato da consegnare giorno 15/11. Sono state fatte le seguenti modifiche:
semplificazione del metodo main() nella classe principale (di avvio).
creazione di una classe "Gestione" contenente la logica applicativa (di fatto è stata tolta dal main()).
completamento delle classi bean, relative a Libro, Sede, Utente e Personale, con integrazione di attributi mancanti, aggiunta dei metodi "toString()", "equals()", "hashCode()", getter/setter e costruttore vuoto.
è stato illustrato meglio il processo di popolamento della lista di libri, con la creazione dei singoli elementi (oggetti) ed il popolamento degli attributi. Si ricorda che i valori degli attributi non dovranno essere costanti ma inseriti da tastiera mediante l'utilizzo di un oggetto di tipo scanner. Andrà anche specificato il numero di oggetti da caricare (al momento ho utilizzato la costante MAX).
Premesso che quanto fatto comprende gli oggetti e parte della procedura di inserimento, dovrete:
prevedere / gestire l'attributo relativo allo stato di prestito per ogni libro, nonché la tipologia di documento prestato (libro, cd, dvd)
aggiungere la funzionalità di ricerca di un elemento, a partire dalla lista
aggiungere la funzionalità di prestito (stato del documento, data di prestito, nominativo dell'utente che lo ha ritirato, data di restituzione, numero di rinnovi effettuati)
aggiungere la funzionalità di visualizzazione dei documenti (a seconda di una chiave), tra censiti, presenti a scaffale, prestati, prestati ad uno specifico utente
