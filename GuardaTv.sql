DROP Database if exists GuardaTv;
CREATE DATABASE GuardaTV;
use GuardaTv;

Create Table Contenuto(
id varchar(33) not null,
titolo varchar(50) not null,
descrizione varchar(255) not null,
regista varchar(50) not null,
durata int(10) not null,
dataDiUscita date not null,   
immagineDelContenuto varchar(255) ,
videoTrailer varchar(255) ,
film boolean not null default true,
stagioni int default null,
puntate int default null,
primary key (id),
FULLTEXT KEY(titolo),
FULLTEXT KEY(titolo, descrizione)
);



Create Table Genere(
nomeGenere varchar(50) not null,
primary key(nomeGenere)
);


Create Table Tipologia(  /*relazione tra Contenuto(1,n) e Genere(0,n) */
Contenuto varchar(33) not null,
Genere varchar(30) not null,
primary key(Contenuto,Genere),
foreign key(Contenuto) references Contenuto(id),
foreign key (Genere) references Genere(nomeGenere)
);


Create Table Utente(
    email varchar(256) not null,
    passwordhash char(40) not null,
    salt varchar(256) not null,
    dataDiNascita date not null,
    username varchar(50) not null,
    administrator boolean not null default false,
    primary key(email)
);

Create Table Recensione(
Utente varchar(256) not null,
Contenuto varchar(33) not null,
punteggio int not null,
descrizione varchar(256),
primary key(Utente,Contenuto),
foreign key (Utente) references Utente(email),
foreign key (Contenuto) references Contenuto(id)
);

Create Table Login (
  id char(36) NOT NULL,
  emailUtente varchar(256) NOT NULL,
  token char(36) NOT NULL,
  time timestamp NOT NULL,
  PRIMARY KEY (id),
  KEY (emailUtente),  /*chiave alternativa*/
  CONSTRAINT FOREIGN KEY (emailUtente) REFERENCES Utente(email)
);

Create Table Lista (
nome varchar(50) not null,
Utente varchar(256) not null,
descrizione varchar(255) not null,
primary key(nome,Utente),
foreign key (Utente) references Utente(email)
);

Create Table ContenutoLista (
ListaNome varchar(50) not null, 
ListaUtente varchar(256) not null,
Contenuto varchar(33) not null,
foreign key(Contenuto) references Contenuto(id),
foreign key(ListaNome) references Lista(nome),
foreign key(ListaUtente) references Lista(Utente),
primary key(Listanome,ListaUtente,Contenuto)
);

create view Generi as
select distinct(nomeGenere)
from Genere;


insert into Contenuto(id,titolo,descrizione,regista,durata,dataDiUscita,immagineDelContenuto,videoTrailer,
film,stagioni,puntate) values 
("0000-0002-6509-0000-0-0000-0008-O","Shutter Island","Rachel Salado, paziente del manicomio criminale sull'isola di Shutter, scompare all'improvviso. L'agente federale Teddy Daniels, affiancato dall'ufficiale Chuck Aule, deve indagare per risolvere il mistero",
"Martin Scorsese",138,"2010-03-05","CopertinaShutterIsland.jpg","https://www.youtube.com/embed/mzkVNB3FpSQ",true,null,null),
("0000-0006-13A9-001F-3-0000-0000-S","Breaking Bad","Walter White, sottopagato insegnante di chimica alla soglia dei cinquanta anni, scopre di avere un cancro terminale ai polmoni. Incapace di accettare questa terribile realtà decide di sfruttare le sue conoscenze di chimica per produrre metamfetamina",
"Vince Gilligan",60,"2008-01-20","CopertinaBreakingBad.jpg","https://www.youtube.com/embed/b8TeqLnXqoA",false,5,62),
("0000-0000-D5F8-0012-M-0000-0000-8","Due uomini e mezzo","Alan e suo figlio Jake si trasferiscono nella casa sulla spiaggia di Charlie in seguito alla separazione del primo dalla moglie. Crescere il figlio in casa del fratello, un irrefrenabile Don Giovanni, si rivela tuttavia un'impresa ardua",
"Lee Aronsohn",20,"2003-07-22","CopertinaDueuominiemezzo.jpg","https://www.youtube.com/embed/NZg_DTQvuiY",false,12,262),
("0000-0004-0137-011E-4-0000-0000-P","A cena con un cretino","Tim sta per ottenere tutto quello che ha sempre desiderato. L'unico ostacolo che deve superare per poter raggiungere il pieno successo sul lavoro è trovare l'ospite perfetto da portare alla cena annuale dedicata alle Persone Straordinarie",
"Jay Roach",114,"2010-05-12","CopertinaAcenaconuncretino.jpg","https://www.youtube.com/embed/rInrjMki198",true,null,null),
("0000-0002-1641-0012-T-0000-0000-O","Eternals","Dopo gli eventi accaduti in Avengers: Endgame, una tragedia inaspettata obbliga gli Eterni a uscire dall'ombra e riunire le forze contro il più antico dei nemici dell'umanità, i Devianti.",
"Dane Whitman",147,"2021-11-03","CopertinaEternals.jpg","https://www.youtube.com/embed/dKyIbkZlV40",true,null,null),
("0000-0004-5D0D-0000-S-0000-R","Game of Thrones","La lotta per il potere tra sette casate nobiliari provoca massacri e disastri, mentre il mondo è minacciato dall'avvento di un'era glaciale che risveglia forze oscure e creature leggendarie.",
"Alan Taylor",80,"2011-05-17","CopertinaIltronodispade.jpg","https://www.youtube.com/embed/Go63zt4yx4U",false,8,73);

insert into Genere(nomeGenere) values
("Azione"),
("Thriller"),
("Drammatico"),
("Avventura"),
("Fantasy"),
("Commedia"),
("Tragedia"),
("Horror");

insert into Tipologia(Contenuto,Genere) values 
("0000-0004-5D0D-0000-S-0000-R","Azione"),
("0000-0004-5D0D-0000-S-0000-R","Thriller"),
("0000-0004-5D0D-0000-S-0000-R","Drammatico"),
("0000-0004-5D0D-0000-S-0000-R","Avventura"),
("0000-0004-5D0D-0000-S-0000-R","Fantasy"),
("0000-0002-1641-0012-T-0000-0000-O","Azione"),
("0000-0002-1641-0012-T-0000-0000-O","Drammatico"),
("0000-0002-1641-0012-T-0000-0000-O","Avventura"),
("0000-0002-1641-0012-T-0000-0000-O","Fantasy"),
("0000-0004-0137-011E-4-0000-0000-P","Commedia"),
("0000-0004-0137-011E-4-0000-0000-P","Avventura"),
("0000-0000-D5F8-0012-M-0000-0000-8","Commedia"),
("0000-0000-D5F8-0012-M-0000-0000-8","Azione"),
("0000-0006-13A9-001F-3-0000-0000-S","Drammatico"),
("0000-0006-13A9-001F-3-0000-0000-S","Thriller"),
("0000-0006-13A9-001F-3-0000-0000-S","Tragedia"),
("0000-0002-6509-0000-0-0000-0008-O","Drammatico"),
("0000-0002-6509-0000-0-0000-0008-O","Thriller"),
("0000-0002-6509-0000-0-0000-0008-O","Horror");

insert into Utente(email,passwordhash,salt,dataDiNascita,username,administrator) values
("niccolocacace@gmail.com",SHA1("NiccoloCacace1E1F53135E559C553"),"E1F53135E559C553","1999-12-24","Niccolò",1),
("spastore@gmail.com",SHA1("SilvioPastore2E1F53135E559C253"),"E1F53135E559C253","1999-04-23","Silvio",1),
("achilleprezioso@gmail.com",SHA1("AchillePrezioso3S1F13135E509C523"),"S1F13135E509C523","1999-03-04","Achille",0),
("alessandroricchetti@gmail.com",SHA1("AlessandroRicchetti4W1F43135F559C553"),"W1F43135F559C553","1999-12-12","Alessandro",0);

insert into Recensione(Utente,Contenuto,punteggio,descrizione) values
("spastore@gmail.com","0000-0006-13A9-001F-3-0000-0000-S",4,"Difficilmente ho visto serie televisive così ben fatte sotto ogni punto di vista. Regia, sceneggiatura, montaggio, sonoro e recitazione toccano l'apice. Per non parlare della storia."),
("niccolocacace@gmail.com","0000-0002-6509-0000-0-0000-0008-O",5,"Tornare alla quiete vita di ogni giorno dopo questo tuffo noir nei meandri della mente risulta davevro frustrante ma al tempop stesso un sollievo.
Ecco la sensazione che mi ha lasciato questo ultimo capolavoro di Scorsese."),
("achilleprezioso@gmail.com","0000-0000-D5F8-0012-M-0000-0000-8",3,"Ogni puntata é una storia a se. Io la guardo se proprio non ho lavori da fare in giardino e non devo vedere gli amici e non ci sono bei film, si passa un po' di tempo con qualcosa di divertente."),
("alessandroricchetti@gmail.com","0000-0002-1641-0012-T-0000-0000-O",1,"Sinceramente mi aspettavo tanto da questo film. Sono rimasto alquanto deluso da effetti speciali e in particolar modo dalla storia che non mi ha affatto stupito.La Marvel mi ha deluso un pò
in questo caso.....si poteva fare di meglio."),
("niccolocacace@gmail.com","0000-0004-5D0D-0000-S-0000-R",4,"Bellissima serie coninvolgente con bravissimi attori ed effetti speciali pazzeschi. Sono rimasto un poco deluso dalla quarta stagione ma nel complesso veramente un bel lavoro.");

insert into Lista(nome,Utente,descrizione) values
("MieiThriller","niccolocacace@gmail.com","Una lista con tutti i miei film genere Thriller preferiti."),
("Filmdavedere","spastore@gmail.com","La lista di tutti i film che mi voglio vedere quando avrò tempo."),
("Divertenti","achilleprezioso@gmail.com","La lista dei film per quando mi voglio dare due risate."),
("Filmdifantasia","alessandroricchetti@gmail.com","La lista dei miei film fantasy preferiti");


insert into ContenutoLista(ListaNome,ListaUtente, Contenuto) values 
("MieiThriller","niccolocacace@gmail.com","0000-0002-6509-0000-0-0000-0008-O"),
("MieiThriller","niccolocacace@gmail.com","0000-0006-13A9-001F-3-0000-0000-S"),
("Filmdavedere","spastore@gmail.com","0000-0002-6509-0000-0-0000-0008-O"),
("Filmdavedere","spastore@gmail.com","0000-0002-1641-0012-T-0000-0000-O"),
("Divertenti","achilleprezioso@gmail.com","0000-0000-D5F8-0012-M-0000-0000-8"),
("Divertenti","achilleprezioso@gmail.com","0000-0004-0137-011E-4-0000-0000-P"),
("Filmdifantasia","alessandroricchetti@gmail.com","0000-0004-5D0D-0000-S-0000-R"),
("Filmdifantasia","alessandroricchetti@gmail.com","0000-0002-1641-0012-T-0000-0000-O");

