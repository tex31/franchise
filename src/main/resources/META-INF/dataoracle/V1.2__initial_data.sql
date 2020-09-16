--------------------------------------------------------
--  Fichier créé - mercredi-février-27-2019   
--------------------------------------------------------
--------------------------------------------------------
--  DDL for Sequence ACCOUNT_IDART_SEQ
--------------------------------------------------------

   CREATE SEQUENCE    "ACCOUNT_IDART_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence ACCOUNT_ID_SEQ
--------------------------------------------------------

   CREATE SEQUENCE    "ACCOUNT_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence AG_SEQ
--------------------------------------------------------

   CREATE SEQUENCE    "AG_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence DESIGNATION_ID_SEQ
--------------------------------------------------------

   CREATE SEQUENCE    "DESIGNATION_ID_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence ENTEST_SEQ
--------------------------------------------------------

   CREATE SEQUENCE    "ENTEST_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence FOUR_SEQ
--------------------------------------------------------

   CREATE SEQUENCE    "FOUR_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence HIST_SEQ
--------------------------------------------------------

   CREATE SEQUENCE    "HIST_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence OP_SEQ
--------------------------------------------------------

   CREATE SEQUENCE    "OP_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence REF_SEQ
--------------------------------------------------------

   CREATE SEQUENCE    "REF_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 231 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence USERI_SEQ
--------------------------------------------------------

   CREATE SEQUENCE    "USERI_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 21 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence USERROLE_SEQ
--------------------------------------------------------

   CREATE SEQUENCE    "USERROLE_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Sequence USER_SEQ
--------------------------------------------------------

   CREATE SEQUENCE    "USER_SEQ"  MINVALUE 1 MAXVALUE 9999999999999999999999999999 INCREMENT BY 1 START WITH 1 CACHE 20 NOORDER  NOCYCLE ;
--------------------------------------------------------
--  DDL for Table ADIRESY
--------------------------------------------------------

  CREATE TABLE   "ADIRESY" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR)
   ) ;
--------------------------------------------------------
--  DDL for Table AGENT
--------------------------------------------------------

  CREATE TABLE   "AGENT" 
   (	"IDAGENT" NUMBER(19,0), 
	"ACTIVE" NUMBER(1,0), 
	"IM" NUMBER(19,0), 
	"NOMAGENT" VARCHAR2(255 CHAR), 
	"PASSWORD" VARCHAR2(255 CHAR), 
	"PRENOMAGENT" VARCHAR2(255 CHAR), 
	"IDDIRECTION" NUMBER(19,0), 
	"IDPOSTENY" NUMBER(19,0), 
	"IDROLE" NUMBER(10,0)
   ) ;
--------------------------------------------------------
--  DDL for Table ARTICLE
--------------------------------------------------------

  CREATE TABLE   "ARTICLE" 
   (	"TYPEART" NUMBER(10,0), 
	"IDARTICLE" NUMBER(19,0), 
	"CARACTERISTIQUEARTICLE" VARCHAR2(255 CHAR), 
	"ESPECEUNIT" VARCHAR2(255 CHAR), 
	"NOMBRE" NUMBER(19,0), 
	"ORIGINE" VARCHAR2(255 CHAR), 
	"PRIX" FLOAT(126), 
	"REFERENCE" VARCHAR2(255 CHAR), 
	"VALIDATION" NUMBER(1,0), 
	"IMBENEFICIAIRE" NUMBER(19,0), 
	"IDCODE" NUMBER(19,0), 
	"IMDEPOSITAIRE" NUMBER(19,0), 
	"IDDIRECTIONART" NUMBER(19,0), 
	"IDMARQUEART" NUMBER(19,0), 
	"IDFIN" NUMBER(19,0), 
	"IDFOURNISSEUR" NUMBER(19,0), 
	"IDMODACQART" NUMBER(19,0)
   )    ;
--------------------------------------------------------
--  DDL for Table CATEGORIEMAT
--------------------------------------------------------

  CREATE TABLE   "CATEGORIEMAT" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR)
   )    ;
--------------------------------------------------------
--  DDL for Table CODEARTICLE
--------------------------------------------------------

  CREATE TABLE   "CODEARTICLE" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR), 
	"IDTYPEOBJ" NUMBER(19,0)
   )    ;
--------------------------------------------------------
--  DDL for Table DESIGNATION
--------------------------------------------------------

  CREATE TABLE   "DESIGNATION" 
   (	"IDDESIGNATION" NUMBER(19,0), 
	"ANNEEACQUISITION" VARCHAR2(255 CHAR), 
	"AUTRE" VARCHAR2(255 CHAR), 
	"DOCUMENTPATH" VARCHAR2(255 CHAR), 
	"ESPECEUNITE" VARCHAR2(255 CHAR), 
	"IMAGE" BLOB, 
	"ORIGINE" VARCHAR2(255 CHAR), 
	"PU" FLOAT(126), 
	"REFFACTURE" VARCHAR2(255 CHAR), 
	"RENSEIGNEMENT" VARCHAR2(255 CHAR), 
	"IDCAR" NUMBER(*,0), 
	"IDFIN" NUMBER(19,0), 
	"IDFOURN" NUMBER(19,0), 
	"IDMARQUE" NUMBER(19,0), 
	"IDMODACQ" NUMBER(19,0), 
	"IDNOM" NUMBER(19,0), 
	"IDTYPEMATERIEL" NUMBER(19,0)
   )    
  ;
--------------------------------------------------------
--  DDL for Table DEVISE
--------------------------------------------------------

  CREATE TABLE   "DEVISE" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR), 
	"DATEEXPIRE" DATE
   ) ;
--------------------------------------------------------
--  DDL for Table DIRECTION
--------------------------------------------------------

  CREATE TABLE   "DIRECTION" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR), 
	"BUDGET" VARCHAR2(255 CHAR), 
	"CODEDIRECTION" VARCHAR2(255 CHAR), 
	"QUATRE" VARCHAR2(255 CHAR), 
	"TROIS" VARCHAR2(255 CHAR)
   )  ;
--------------------------------------------------------
--  DDL for Table DIRECTIONHISTOR
--------------------------------------------------------

  CREATE TABLE   "DIRECTIONHISTOR" 
   (	"DIRID" NUMBER(19,0), 
	"DIRTITLEID" NUMBER(19,0)
   )  ;
--------------------------------------------------------
--  DDL for Table DIRECTIONTITLEHIST
--------------------------------------------------------

  CREATE TABLE   "DIRECTIONTITLEHIST" 
   (	"IDTITLE" NUMBER(19,0), 
	"DATEHIST" DATE, 
	"TITLE" VARCHAR2(255 CHAR), 
	"IDDIRECTION" NUMBER(19,0)
   ) ;
--------------------------------------------------------
--  DDL for Table ENTITYTEST
--------------------------------------------------------

  CREATE TABLE   "ENTITYTEST" 
   (	"IDENTIT" NUMBER(19,0), 
	"MYATTR" VARCHAR2(255 CHAR), 
	"MYDATE" DATE, 
	"TITLE" VARCHAR2(255 CHAR), 
	"IDDIRECTION" NUMBER(19,0)
   )    ;
--------------------------------------------------------
--  DDL for Table ETATMATERIEL
--------------------------------------------------------

  CREATE TABLE   "ETATMATERIEL" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR)
   ) ;
--------------------------------------------------------
--  DDL for Table FINANCEMENT
--------------------------------------------------------

  CREATE TABLE   "FINANCEMENT" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR)
   ) ;
--------------------------------------------------------
--  DDL for Table FOURNISSEUR
--------------------------------------------------------

  CREATE TABLE   "FOURNISSEUR" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR)
   )    ;
--------------------------------------------------------
--  DDL for Table FOURNISSEURDETAIL
--------------------------------------------------------

  CREATE TABLE   "FOURNISSEURDETAIL" 
   (	"IDFOURN" NUMBER(19,0), 
	"ADRESSE" VARCHAR2(255 CHAR), 
	"CONTACT" VARCHAR2(255 CHAR), 
	"NIF" VARCHAR2(255 CHAR), 
	"NOMFOURN" VARCHAR2(255 CHAR), 
	"OBSERVATION" VARCHAR2(255 CHAR), 
	"STAT" VARCHAR2(255 CHAR)
   )    ;
--------------------------------------------------------
--  DDL for Table LOCALITE
--------------------------------------------------------

  CREATE TABLE   "LOCALITE" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR)
   )    ;
--------------------------------------------------------
--  DDL for Table MA
--------------------------------------------------------

  CREATE TABLE   "MA" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR)
   )  ;
--------------------------------------------------------
--  DDL for Table MARQUE
--------------------------------------------------------

  CREATE TABLE   "MARQUE" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR)
   ) ;
--------------------------------------------------------
--  DDL for Table MATERIEL
--------------------------------------------------------

  CREATE TABLE   "MATERIEL" 
   (	"TYPEMATERIELS" NUMBER(10,0), 
	"IDMATERIEL" NUMBER(19,0), 
	"ANNEEACQUISITION" VARCHAR2(255 CHAR), 
	"AUTRE" VARCHAR2(255 CHAR), 
	"CODE" VARCHAR2(255 CHAR), 
	"DOCUMENTPATH" VARCHAR2(255 CHAR), 
	"ESPECEUNITE" VARCHAR2(255 CHAR), 
	"IMAGE" RAW(255), 
	"NUMSERIE" VARCHAR2(255 CHAR), 
	"NUMEROTYPE" NUMBER(19,0), 
	"ORIGINE" VARCHAR2(255 CHAR), 
	"PU" FLOAT(126), 
	"REFERENCE" VARCHAR2(255 CHAR), 
	"RENSEIGNEMENT" VARCHAR2(255 CHAR), 
	"SERIENUMERO" NUMBER(10,0), 
	"VALIDATION" NUMBER(1,0), 
	"MONTANT_FACTURE" FLOAT(126), 
	"REFFACTURE" VARCHAR2(255 CHAR), 
	"IDCAR" NUMBER(*,0), 
	"IMDEPOSITAIRE" NUMBER(19,0), 
	"DESINGATIONID" NUMBER(19,0), 
	"IMDETENTEUR" NUMBER(19,0), 
	"IDDIRECTION" NUMBER(19,0), 
	"IDETAT" NUMBER(19,0), 
	"IDMARQUE" NUMBER(19,0), 
	"OPENTREEID" NUMBER(19,0), 
	"IDNOM" NUMBER(19,0), 
	"IDTYPEMATERIEL" NUMBER(19,0), 
	"IDFIN" NUMBER(19,0), 
	"IDFOURN" NUMBER(19,0), 
	"IDMODACQ" NUMBER(19,0), 
	"MATERIELCOUNT" NUMBER(10,0)
   )    ;
--------------------------------------------------------
--  DDL for Table MOTIFDECHARGE
--------------------------------------------------------

  CREATE TABLE   "MOTIFDECHARGE" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR)
   )    ;
--------------------------------------------------------
--  DDL for Table MOTIFSORTIE
--------------------------------------------------------

  CREATE TABLE   "MOTIFSORTIE" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR)
   )    ;
--------------------------------------------------------
--  DDL for Table NOMENCLATURE
--------------------------------------------------------

  CREATE TABLE   "NOMENCLATURE" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR), 
	"NOMENCLATURE" VARCHAR2(255 CHAR)
   )    ;
--------------------------------------------------------
--  DDL for Table OPATTRIBUTION
--------------------------------------------------------

  CREATE TABLE   "OPATTRIBUTION" 
   (	"ID" NUMBER(19,0), 
	"DATEOP" DATE, 
	"MOTIFRETOUR" VARCHAR2(255 CHAR), 
	"POSTE" VARCHAR2(255 CHAR), 
	"STATE" VARCHAR2(255 CHAR), 
	"TIMEOP" DATE, 
	"IDDIRECTION" NUMBER(19,0), 
	"IDOPERATEUR" NUMBER(19,0), 
	"DETENTEUREFFECTIF" VARCHAR2(255 CHAR), 
	"NUMERODET" VARCHAR2(255 CHAR), 
	"IDDETENTEUR" NUMBER(19,0), 
	"IDMAT" NUMBER(19,0)
   )    ;
--------------------------------------------------------
--  DDL for Table OPDETTACHEMENT
--------------------------------------------------------

  CREATE TABLE   "OPDETTACHEMENT" 
   (	"ID" NUMBER(19,0), 
	"DATEOP" DATE, 
	"MOTIFRETOUR" VARCHAR2(255 CHAR), 
	"POSTE" VARCHAR2(255 CHAR), 
	"STATE" VARCHAR2(255 CHAR), 
	"TIMEOP" DATE, 
	"IDDIRECTION" NUMBER(19,0), 
	"IDOPERATEUR" NUMBER(19,0), 
	"IDDETENTEUR" NUMBER(19,0), 
	"IDMAT" NUMBER(19,0), 
	"IDMOTIFDETT" NUMBER(19,0)
   )    ;
--------------------------------------------------------
--  DDL for Table OPENTREEARTICLE
--------------------------------------------------------

  CREATE TABLE   "OPENTREEARTICLE" 
   (	"ID" NUMBER(19,0), 
	"DATEOP" DATE, 
	"MOTIFRETOUR" VARCHAR2(255 CHAR), 
	"POSTE" VARCHAR2(255 CHAR), 
	"STATE" VARCHAR2(255 CHAR), 
	"TIMEOP" DATE, 
	"IDDIRECTION" NUMBER(19,0), 
	"IDOPERATEUR" NUMBER(19,0), 
	"IDART" NUMBER(19,0)
   )    ;
--------------------------------------------------------
--  DDL for Table OPENTREEMATERIEL
--------------------------------------------------------

  CREATE TABLE   "OPENTREEMATERIEL" 
   (	"ENTREEID" NUMBER(19,0), 
	"MATERIELID" NUMBER(19,0)
   )    ;
--------------------------------------------------------
--  DDL for Table OPERATIONENTREE
--------------------------------------------------------

  CREATE TABLE   "OPERATIONENTREE" 
   (	"ID" NUMBER(19,0), 
	"DATEOP" DATE, 
	"MOTIFRETOUR" VARCHAR2(255 CHAR), 
	"POSTE" VARCHAR2(255 CHAR), 
	"STATE" VARCHAR2(255 CHAR), 
	"TIMEOP" DATE, 
	"IDDIRECTION" NUMBER(19,0), 
	"IDOPERATEUR" NUMBER(19,0), 
	"NUMOPERATION" VARCHAR2(255 CHAR), 
	"IDMAT" NUMBER(19,0), 
	"PATHDOC" VARCHAR2(255 CHAR), 
	"REFFACT" VARCHAR2(255 CHAR)
   )    ;
--------------------------------------------------------
--  DDL for Table OPSAISIE
--------------------------------------------------------

  CREATE TABLE   "OPSAISIE" 
   (	"ID" NUMBER(19,0), 
	"DATEOP" DATE, 
	"MOTIFRETOUR" VARCHAR2(255 CHAR), 
	"POSTE" VARCHAR2(255 CHAR), 
	"STATE" VARCHAR2(255 CHAR), 
	"TIMEOP" DATE, 
	"IDDIRECTION" NUMBER(19,0), 
	"IDOPERATEUR" NUMBER(19,0), 
	"DESIGNATIONREF" VARCHAR2(255 CHAR), 
	"IDREFER" NUMBER(19,0)
   )    ;
--------------------------------------------------------
--  DDL for Table OPSORTIE
--------------------------------------------------------

  CREATE TABLE   "OPSORTIE" 
   (	"ID" NUMBER(19,0), 
	"DATEOP" DATE, 
	"MOTIFRETOUR" VARCHAR2(255 CHAR), 
	"POSTE" VARCHAR2(255 CHAR), 
	"STATE" VARCHAR2(255 CHAR), 
	"TIMEOP" DATE, 
	"IDDIRECTION" NUMBER(19,0), 
	"IDOPERATEUR" NUMBER(19,0), 
	"NUMOPERATION" VARCHAR2(255 CHAR), 
	"IDMAT" NUMBER(19,0), 
	"PJ" VARCHAR2(255 CHAR), 
	"IDDIRECT" NUMBER(19,0), 
	"IDMOTIF" NUMBER(19,0)
   )    ;
--------------------------------------------------------
--  DDL for Table OPSORTIEARTICLE
--------------------------------------------------------

  CREATE TABLE   "OPSORTIEARTICLE" 
   (	"ID" NUMBER(19,0), 
	"DATEOP" DATE, 
	"MOTIFRETOUR" VARCHAR2(255 CHAR), 
	"POSTE" VARCHAR2(255 CHAR), 
	"STATE" VARCHAR2(255 CHAR), 
	"TIMEOP" DATE, 
	"IDDIRECTION" NUMBER(19,0), 
	"IDOPERATEUR" NUMBER(19,0), 
	"DECISION" VARCHAR2(255 CHAR), 
	"NOMBRETOS" NUMBER(19,0), 
	"IDART" NUMBER(19,0), 
	"IDBENEFI" NUMBER(19,0)
   )    ;
--------------------------------------------------------
--  DDL for Table POSTE
--------------------------------------------------------

  CREATE TABLE   "POSTE" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR)
   )    ;

--------------------------------------------------------
--  DDL for Table TYPEMATERIEL
--------------------------------------------------------

  CREATE TABLE   "TYPEMATERIEL" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR), 
	"CODETYPEMATE" VARCHAR2(255 CHAR), 
	"IDNOMENCLATURE" NUMBER(19,0)
   )    ;
--------------------------------------------------------
--  DDL for Table TYPEOBJET
--------------------------------------------------------

  CREATE TABLE   "TYPEOBJET" 
   (	"ID" NUMBER(19,0), 
	"DESIGNATION" VARCHAR2(255 CHAR), 
	"CARACTERISTIQUE" VARCHAR2(255 CHAR)
   )    ;
--------------------------------------------------------
--  DDL for Table USERI
--------------------------------------------------------

  CREATE TABLE   "USERI" 
   (	"IDUSER" NUMBER(10,0), 
	"DESIGNATION" VARCHAR2(255 CHAR), 
	"ROLE" VARCHAR2(255 CHAR)
   )    ;