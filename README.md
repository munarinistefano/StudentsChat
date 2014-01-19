# [PROGRAMMAZIONE PER IL WEB](http://georgeosddev.github.com/markdown-edit)
### Bonadiman Gabriele & Munarini Stefano



Il prgetto richiedeva l'implementazione di un forum per studenti dove potersi scambiare idee, informazioni e materiale su argomenti trattati a lezione. Le altre funzionalita' sono spigate qui sotto. 


## INFO DELL’APPLICAZIONE

Questo progetto è stato sviluppato con tecnologia Java Servlet e JSP (Java Servlet Page) e pattern MVC (Model-View-Controller). Il server utilizzato è GlassFish 4.0, mentre il database è Derby.


## LE LIBRERIE 
Abbiamo importato per la gestione dell’intero progetto alcune librerie

JSTL, che ci permette di manipolare dati nelle JSP, senza l’utilizzo di Java, per avere del codice HTML più pulito
cos ci permette di utilizzare form-multipart, utilizzati per il caricamento di file
gson per creare oggetti json da utilizzare nella tabella del moderatore
jquery per l’utilizzo di alcuni script a frontend





##DATABASE
Analizziamo il Database sul quale si basa il progetto. E’ un DB relazionale con una struttura semplice e le relazioni essenziali. Si sono create le seguenti tabelle:

	UTENTI (id,username,password,email,avatar,tipo_utente,data_time)
	POSTS (id, testo, utente*, gruppo*,date_time,file)
	GRUPPI (id, nome, data_creazione, flag, proprietario*,stato)
	GRUPPIUTENTE(id, utente*, gruppo*)
	INVITI (id,utente*,gruppo*,stato)

Questa e’ la configurazione precisa utilizzata per immagazzinare i dati e gestirli. La tabella GRUPPI UTENTE funge da relazione tra UTENTE e GRUPPO, contenendo unicamene il loro rispettivo ID. 







##ARCHITETTURA

L’architettura implementata e’ la seguente e rispecchia il pattern MVC. Abbiamo deciso di utilizzare diversi package com.secondoProgetto per la suddivisione delle varie componenti del progetto.
  

Controller 
		Contiene le Servlet che fungono da Controller.  Si occupano di generare 		il Model (ovvero i Bean) ed inoltrarlo alle relative View (ovvero le pagine 		JSP);

Database
		contiene un’ unica classe. Da questa creiamo la connessione al 		database con i dati presenti nel file web.xml

Filters
		Contiene i JavaFilter, mappati nel file web.xml. I filtri sono mappati per 		nome, classe di riferimento e url. L’url contiene tutte le servlet sulle 		quali il filtro deve agire.

Listener 
		Contiene un'unica classe: il ContextListener. Ha la funzione di 
		ascoltatore all’interno del server. Essa ci permette di utilizzare un'unico 		oggetto DBManager per ogni servlet che lo richiede, senza la necessità 		di aprire connessioni multiple.

Model 
		contiene classi JAVA che mappano tutti i dati relazionali. In questa 		maniera possiamo utilizzare i Beans per le modifiche, separando la parte 		relazionale da quella logica.

Services 
		contiene tutte le classi utilizzate per effettuare interazioni con il 		database;

Servlet 
		Contiene le Servlet che non fungono da Controller, ma hanno il solo 		compito di effettuare chiamate per inserire, modificare od eliminare 		oggetti nel database.

Questo tipo di architettura ci ha permesso la netta suddivisione del progetto sia tra back-end e front-end sia dalla parte relazionale e logica. 
Nel file web.xml vengono mappate le servlet, i filtri, le pagine d'errore, l'url al database e infine il welcome­file­list. La cartella “ build/web/Resources/File ” funge da contenitore alle cartelle contenenti i file di ogni gruppo, mentre la cartella “ build/web/UploadedAvatar” contiene tutti gli avatar caricati ed utilizzati dagli utenti.



##I FILTRI

I filtri inseriti all’interno del progetto sono quattro. Uno effettua una propria funzione e non sono legati tra loro. Troviamo di seguito il filtro di

	FILE 		che non permette la visualizzazione di file da parte di un utente 			non registrato al gruppo dove e’ stato caricato il file stesso
	GRUPPO	non lascia accedere al forum di un gruppo privato alcun utente 			che non risulti registrato
	MOD	 	non permette la visualizzazione delle funzioni di gestione dei 			gruppi o la possibilita’ di aprire/chiudere un gruppo
	SESSION 	un semplice filtro che ci permette di capire se l’utente e’ loggato 			oppure no

Mappati successivamente nel file web.xml i filtri vengono attivati sulle servlet definite dal pattern-url. 
