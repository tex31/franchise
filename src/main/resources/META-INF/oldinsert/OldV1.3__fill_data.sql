-- INSERTING into  USERI
SET DEFINE OFF;
Insert into USERI(IDUSER,DESIGNATION,ROLE) values (2, 'Dépositaire Comptable', 'ROLE_DC');
Insert into USERI(IDUSER,DESIGNATION,ROLE) values (1, 'Administrateur', 'ROLE_ADMIN');
-- INSERTING into  ADIRESY;
SET DEFINE OFF;
-- INSERTING into  AGENT
Insert into AGENT(IDAGENT,ACTIVE,IM,NOMAGENT,PASSWORD,PRENOMAGENT,IDDIRECTION,IDPOSTENY,IDROLE) values (1, 1, 999999, 'Administrateur', '$2a$10$nVMDUP0w6Wji4FakfFNiBe2feKLK/oo4b9iFcKjXtqysCAAxN2/Py', NULL, NULL, NULL, 1);
SET DEFINE OFF;
-- INSERTING into  ARTICLE
SET DEFINE OFF;
-- INSERTING into  CATEGORIEMAT
SET DEFINE OFF;
-- INSERTING into  CODEARTICLE
SET DEFINE OFF;
-- INSERTING into  DEVISE
SET DEFINE OFF;
Insert into DEVISE(ID,DESIGNATION,DATEEXPIRE) values (1, 'Tanindrazana - Fahafahana - Fandrosoana', to_date('2018-01-01','RRRR-MM-DD'));
Insert into DEVISE(ID,DESIGNATION,DATEEXPIRE) values (2, 'Fitiavana - Tanindrazana - Fandrosoana', to_date('2018-01-02','RRRR-MM-DD'));
-- INSERTING into  DIRECTION
SET DEFINE OFF;
Insert into DIRECTION(ID,DESIGNATION,BUDGET,CODEDIRECTION,QUATRE,TROIS) values (3, 'DIRECTION Générale des Douanes', 'Inconnue', 'DGD', 'Inconnue', 'Antananarivo');
Insert into DIRECTION(ID,DESIGNATION,BUDGET,CODEDIRECTION,QUATRE,TROIS) values (5, 'DIRECTION des Ressources et de la ForMAtion', 'Inconnue', 'DRF', 'Inconnue', 'Antananarivo');
Insert into DIRECTION(ID,DESIGNATION,BUDGET,CODEDIRECTION,QUATRE,TROIS) values (7, 'Service de l''informatique', 'Inconnue', 'SI', 'Inconnue', 'Antananarivo');
-- INSERTING into  DIRECTIONTITLEHIST
SET DEFINE OFF;
Insert into DIRECTIONTITLEHIST(IDTITLE,DATEHIST,TITLE,IDDIRECTION) values (4, to_date('2018-08-27','RRRR-MM-DD'), 'DIRECTION Générale des Douanes', 3);
Insert into DIRECTIONTITLEHIST(IDTITLE,DATEHIST,TITLE,IDDIRECTION) values (6, to_date('2018-08-27','RRRR-MM-DD'), 'DIRECTION des Ressources et de la ForMAtion', 5);
Insert into DIRECTIONTITLEHIST(IDTITLE,DATEHIST,TITLE,IDDIRECTION) values (8, to_date('2018-08-27','RRRR-MM-DD'), 'Service de l''inforMAtique', 7);
-- INSERTING into  DIRECTIONHISTOR
SET DEFINE OFF;
Insert into DIRECTIONHISTOR(DIRID,DIRTITLEID) values (3, 4);
Insert into DIRECTIONHISTOR(DIRID,DIRTITLEID) values (5, 6);
Insert into DIRECTIONHISTOR(DIRID,DIRTITLEID) values (7, 8);
-- INSERTING into  ENTITYTEST
SET DEFINE OFF;
-- INSERTING into  ETATMATERIEL
SET DEFINE OFF;
Insert into ETATMATERIEL(ID,DESIGNATION) values (9, 'Bon');
Insert into ETATMATERIEL(ID,DESIGNATION) values (10, 'Mauvais');
Insert into ETATMATERIEL(ID,DESIGNATION) values (11, 'Moyen');
Insert into ETATMATERIEL(ID,DESIGNATION) values (12, 'Sur cale');
Insert into ETATMATERIEL(ID,DESIGNATION) values (13, 'En panne');
Insert into ETATMATERIEL(ID,DESIGNATION) values (14, 'A condamner');
-- INSERTING into  FINANCEMENT
SET DEFINE OFF;
Insert into FINANCEMENT(ID,DESIGNATION) values (15, 'RPI');
Insert into FINANCEMENT(ID,DESIGNATION) values (16, 'FASAD');
Insert into FINANCEMENT(ID,DESIGNATION) values (17, 'GASYNET');
Insert into FINANCEMENT(ID,DESIGNATION) values (18, 'PAGI');
Insert into FINANCEMENT(ID,DESIGNATION) values (19, 'BANQUE MONDIALE');
Insert into FINANCEMENT(ID,DESIGNATION) values (20, 'UNION EUROPEENE');
Insert into FINANCEMENT(ID,DESIGNATION) values (21, 'OMD');
Insert into FINANCEMENT(ID,DESIGNATION) values (22, 'Autres Partenaires techniques');
-- INSERTING into  FOURNISSEUR
SET DEFINE OFF;
-- INSERTING into  FOURNISSEURDETAIL
SET DEFINE OFF;
-- INSERTING into  LOCALITE
SET DEFINE OFF;
-- INSERTING into  MA
SET DEFINE OFF;
Insert into MA(ID,DESIGNATION) values (37, 'Achat');
Insert into MA(ID,DESIGNATION) values (38, 'Dons');
Insert into MA(ID,DESIGNATION) values (39, 'Affectation');
Insert into MA(ID,DESIGNATION) values (40, 'Recensement excédent');
-- INSERTING into  MARQUE
SET DEFINE OFF;
Insert into MARQUE(ID,DESIGNATION) values (23, 'Prolink');
Insert into MARQUE(ID,DESIGNATION) values (24, 'Raffles');
Insert into MARQUE(ID,DESIGNATION) values (25, 'Asus');
Insert into MARQUE(ID,DESIGNATION) values (26, 'TOYOTA');
Insert into MARQUE(ID,DESIGNATION) values (27, 'TOYOTA HILUX');
Insert into MARQUE(ID,DESIGNATION) values (28, 'TOYOTA HIACE');
Insert into MARQUE(ID,DESIGNATION) values (29, 'TOYOTA PRADO');
Insert into MARQUE(ID,DESIGNATION) values (30, 'TOYOTA FORTUNER');
Insert into MARQUE(ID,DESIGNATION) values (31, 'Nissan HARD BODY');
Insert into MARQUE(ID,DESIGNATION) values (32, 'POLO CLASSIQUE');
Insert into MARQUE(ID,DESIGNATION) values (33, 'ISUZU 4*4');
Insert into MARQUE(ID,DESIGNATION) values (34, 'HP');
Insert into MARQUE(ID,DESIGNATION) values (35, 'Inconnue');
Insert into MARQUE(ID,DESIGNATION) values (36, 'Aucune');
-- INSERTING into  MATERIEL
SET DEFINE OFF;
-- INSERTING into  MOTIFDECHARGE
SET DEFINE OFF;
Insert into MOTIFDECHARGE(ID,DESIGNATION) values (41, 'Affectation');
Insert into MOTIFDECHARGE(ID,DESIGNATION) values (42, 'Déces');
Insert into MOTIFDECHARGE(ID,DESIGNATION) values (43, 'Licenciement');
Insert into MOTIFDECHARGE(ID,DESIGNATION) values (44, 'Démission');
Insert into MOTIFDECHARGE(ID,DESIGNATION) values (45, 'Disponibilité');
Insert into MOTIFDECHARGE(ID,DESIGNATION) values (46, 'Détachement');
-- INSERTING into  MOTIFSORTIE
SET DEFINE OFF;
Insert into MOTIFSORTIE(ID,DESIGNATION) values (47, 'Affectation');
Insert into MOTIFSORTIE(ID,DESIGNATION) values (48, 'Perte');
Insert into MOTIFSORTIE(ID,DESIGNATION) values (49, 'Condamnation');
Insert into MOTIFSORTIE(ID,DESIGNATION) values (50, 'Recensement ');
-- INSERTING into  NOMENCLATURE
SET DEFINE OFF;
Insert into NOMENCLATURE(ID,DESIGNATION,NOMENCLATURE) values (51, 'Combustibles et Luminaires', '1');
Insert into NOMENCLATURE(ID,DESIGNATION,NOMENCLATURE) values (52, 'Effets d''habillement, équipement, campement, harnachement, pansage', '2');
Insert into NOMENCLATURE(ID,DESIGNATION,NOMENCLATURE) values (53, 'Meubles et objets d''ammeubement, literie et couchage', '3');
Insert into NOMENCLATURE(ID,DESIGNATION,NOMENCLATURE) values (54, 'Outillage, instrument et appareils divers, matériel de transport, instruments de musiquen matériel sportif, matériel de guerre', '5');
Insert into NOMENCLATURE(ID,DESIGNATION,NOMENCLATURE) values (55, 'Ouvrages de bibliothèques, de sciences, et arts, matériel d''enseignement, fournitures diverses', '10');
-- INSERTING into  OPATTRIBUTION
SET DEFINE OFF;
-- INSERTING into  OPDETTACHEMENT
SET DEFINE OFF;
-- INSERTING into  OPENTREEARTICLE
SET DEFINE OFF;
-- INSERTING into  OPENTREEMATERIEL
SET DEFINE OFF;
-- INSERTING into  OPERATIONENTREE
SET DEFINE OFF;
-- INSERTING into  OPSAISIE
SET DEFINE OFF;
-- INSERTING into  OPSORTIE
SET DEFINE OFF;
-- INSERTING into  OPSORTIEARTICLE
SET DEFINE OFF;
-- INSERTING into  POSTE
SET DEFINE OFF;
Insert into POSTE(ID,DESIGNATION) values (56, 'Receveur');
Insert into POSTE(ID,DESIGNATION) values (57, 'GAC');
Insert into POSTE(ID,DESIGNATION) values (58, 'Depositaire');
Insert into POSTE(ID,DESIGNATION) values (59, 'GAC suppléant');
Insert into POSTE(ID,DESIGNATION) values (60, 'Développeur');
Insert into POSTE(ID,DESIGNATION) values (61, 'Directeur Général');
Insert into POSTE(ID,DESIGNATION) values (62, 'Directeur');
Insert into POSTE(ID,DESIGNATION) values (63, 'Directeur des Ressources et de la Formation');
Insert into POSTE(ID,DESIGNATION) values (64, 'Chef SE');
Insert into POSTE(ID,DESIGNATION) values (65, 'Administrateur');
-- INSERTING into  TYPEMATERIEL
SET DEFINE OFF;
Insert into TYPEMATERIEL(ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values (66, 'Lampe tube fluorescent', 'LAM1', 51);
Insert into TYPEMATERIEL(ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values (67, 'Réglettes LED', 'REG1', 51);
Insert into TYPEMATERIEL(ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values (68, 'Projecteur LED', 'PRO1', 51);
Insert into TYPEMATERIEL(ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values (69, 'Downlight LED', 'DOW1', 51);
Insert into TYPEMATERIEL(ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values (70, 'TUBE LED', 'TUB1', 51);
Insert into TYPEMATERIEL(ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values (71, 'Tenue de travail', 'TEN1', 52);
Insert into TYPEMATERIEL(ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values (72, 'Tenue de cérémonie', 'TEN2', 52);
-- INSERTING into  TYPEOBJET
SET DEFINE OFF;
-- SET SEQUENCE VALUE
ALTER SEQUENCE REF_SEQ INCREMENT BY 60;
SELECT REF_SEQ.nextval from DUAL;
ALTER SEQUENCE REF_SEQ INCREMENT BY 1;

--Insert into TYPEMATERIEL(ID,DESIGNATION,CODETYPEMATE,IDNOMENCLATURE) values (MY_SEQ.nextval, 'Tenue de cérémonie', 'TEN2', 52);
