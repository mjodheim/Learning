create database Demo;

use Demo;

create Table person (
  person_id int identity(1,1),
  first_name varchar(50) not null,
  last_name varchar(50) not null,
  birth_date datetime,
  constraint PK_person primary key (person_id)
)

alter table person add constraint CK_birth_date check (year(birth_date) > 1970);



create database Exercices;

use Exercices;

create table T_MAINTENANCE_MTN (
	mtn_jour varchar(3) not null,
	mtn_machine varchar(50) not null,
	mtn_numero int unique not null, --PK
	mtn_vitesse int,
	mtn_temperature int,
	mtn_heure time not null,
	mtn_evenement varchar(200),
	constraint PK_MTN primary key (mtn_numero),
	constraint CK_MNT_JOUR check (mtn_jour in ('Lun','Mar','Mer','Jeu','Ven','Sam')),
	constraint CK_MNT_TEMPERATURE check (mtn_temperature > 0),
	constraint CK_MNT_VITESSE check (mtn_vitesse > 0)
)

create table T_FABRIQUANT_FBQ(
	fbq_id int identity (1,1), --PK
	fbq_nom varchar(50),
	constraint PK_FBQ primary key (fbq_id)
)

create table T_TAUX_TVA (
	tva_id int identity (1,1), --PK
	tva_taux decimal not null,
	constraint PK_TVA primary key (tva_id)
)

create table T_RAYON_RYN (
	ryn_id int identity (1,1), --PK
	ryn_nom varchar(50) not null,
	constraint PK_RYN primary key (ryn_id)
)

create table T_PRODUIT_PDT (
	pdt_id int identity (1,1), --PK
	pdt_ref_magasin varchar(50) not null,
	pdt_codeEAN13 int not null, --limité à 13 chiffres et unique
	pdt_prix_de_vente money,
	fk_pdt_fbq int not null, --FK --> FABRIQUANT
	fk_pdt_tva int not null, --FK --> TAUX
	fk_pdt_ryn int not null, --FK --> RAYON
	constraint PK_PDT primary key (pdt_id),
	constraint UK_PDT_CODEEAN13 unique (pdt_codeEAN13),
	constraint FK_PDT_FBQ foreign key (fk_pdt_fbq) references T_FABRIQUANT_FBQ (fbq_id),
	constraint FK_PDT_TVA foreign key (fk_pdt_tva) references T_TAUX_TVA (tva_id),
	constraint FK_PDT_RYN foreign key (fk_pdt_ryn) references T_RAYON_RYN (ryn_id)
)

CREATE DATABASE DemoDB;

USE DemoDB;

CREATE TABLE T_VOITURE_VTR (
    VTR_ID            INTEGER        NOT NULL PRIMARY KEY,
    VTR_IMMATRICUL    CHAR(10)       NOT NULL UNIQUE,
    VTR_CARBURANT     CHAR(2)        NOT NULL DEFAULT 'ES',
                                   CHECK (VTR_CARBURANT IN ('ES', 'GO', 'PL')),
    VTR_PUISSANCE_FISC INTEGER       NOT NULL
                                   CHECK (VTR_PUISSANCE_FISC BETWEEN 1 AND 20),
    VTR_NB_PLACES     INTEGER        NOT NULL
                                   CHECK (VTR_NB_PLACES BETWEEN 1 AND 7),
    VTR_MODELE        VARCHAR(20)    NOT NULL
                                   CHECK (RTRIM(LTRIM(VTR_MODELE)) NOT LIKE ''),
    VTR_CONSTRUCTEUR  VARCHAR(16)    NOT NULL
                                   CHECK (RTRIM(LTRIM(VTR_CONSTRUCTEUR)) NOT LIKE ''),
    VTR_NUMERO_SERIE  VARCHAR(25)    NOT NULL
                                   CHECK (RTRIM(LTRIM(VTR_NUMERO_SERIE)) NOT LIKE ''),

    CONSTRAINT CK_IMMATRICULATION CHECK (
        ((CONVERT(INTEGER, SUBSTRING(VTR_IMMATRICUL, 9, 1)) BETWEEN 0 AND 9)
         AND (SUBSTRING(VTR_IMMATRICUL, 10, 1) BETWEEN '0' AND '9')
         AND (SUBSTRING(VTR_IMMATRICUL, 9, 2) < '96'))
        OR
        ((CONVERT(INTEGER, SUBSTRING(VTR_IMMATRICUL, 9, 1)) = 2)
         AND (SUBSTRING(VTR_IMMATRICUL, 10, 1) IN ('A', 'B')))
    ),

    CONSTRAINT CK_PUISS_PLACE CHECK (VTR_NB_PLACES - 1 < VTR_PUISSANCE_FISC),

    CONSTRAINT UK_MDL_CTR_NSR UNIQUE (VTR_MODELE, VTR_CONSTRUCTEUR, VTR_NUMERO_SERIE)
);
