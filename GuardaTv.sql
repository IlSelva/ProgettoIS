CREATE DATABASE GuardaTV;
use GuardaTv;

Create Table Contenuto(
id varchar(26) not null,
titolo varchar(50) not null,
descrizione varchar(255) not null,
regista varchar(50) not null,
durata int(10) not null,
dataDiUscita date not null,   /*rivedere data*/
immagineDelContenuto varchar(255) ,
videoTrailer varchar(255) ,
film boolean not null default true,
stagioni int default null,
puntate int default null,
primary key (id)
);



Create Table Genere(
nomeGenere varchar(30) not null,
primary key(nomeGenere)
);


Create Table Tipologia(  /*relazione tra Contenuto(1,n) e Genere(0,n) */
Contenuto varchar(26) not null,
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
Contenuto varchar(26) not null,
punteggio int not null,
descrizione varchar(255),
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
Contenuto varchar(26) not null,
foreign key(Contenuto) references Contenuto(id),
foreign key(ListaNome) references Lista(nome),
foreign key(ListaUtente) references Lista(Utente),
primary key(Listanome,ListaUtente,Contenuto)
);

create view Generi as
select distinct(nomeGenere)
from Genere;
