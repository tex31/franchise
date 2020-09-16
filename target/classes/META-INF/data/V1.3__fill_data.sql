--
-- TOC entry 2157 (class 0 OID 323781)
-- Dependencies: 176
-- Data for Name: agent; Type: TABLE DATA; Schema: public; Owner: -
--


--
-- TOC entry 2195 (class 0 OID 323945)
-- Dependencies: 214
-- Data for Name: useri; Type: TABLE DATA; Schema: public; Owner: -
--

--INSERT INTO useri VALUES (1, 'Administrateur', 'ROLE_ADMIN');

--
-- TOC entry 2164 (class 0 OID 324008)
-- Dependencies: 183
-- Data for Name: devise; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO devise VALUES (1, 'Tanindrazana - Fahafahana - Fandrosoana', '2018-01-01');
INSERT INTO devise VALUES (2, 'Fitiavana - Tanindrazana - Fandrosoana', '2018-01-02');

--
-- TOC entry 2165 (class 0 OID 324011)
-- Dependencies: 184
-- Data for Name: direction; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO direction VALUES (3, 'Direction Générale des Douanes', 'Inconnue', 'DGD', 'Inconnue', 'Antananarivo');
INSERT INTO direction VALUES (5, 'Direction des Ressources et de la Formation', 'Inconnue', 'DRF', 'Inconnue', 'Antananarivo');
INSERT INTO direction VALUES (7, 'Service de l''informatique', 'Inconnue', 'SI', 'Inconnue', 'Antananarivo');



--
-- TOC entry 2167 (class 0 OID 324020)
-- Dependencies: 186
-- Data for Name: directiontitlehist; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO directiontitlehist VALUES (4, '2018-08-27', 'Direction Générale des Douanes', 3);
INSERT INTO directiontitlehist VALUES (6, '2018-08-27', 'Direction des Ressources et de la Formation', 5);
INSERT INTO directiontitlehist VALUES (8, '2018-08-27', 'Service de l''informatique', 7);

--
-- TOC entry 2166 (class 0 OID 324017)
-- Dependencies: 185
-- Data for Name: directionhistor; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO directionhistor VALUES (3, 4);
INSERT INTO directionhistor VALUES (5, 6);
INSERT INTO directionhistor VALUES (7, 8);

--
-- TOC entry 2168 (class 0 OID 324023)
-- Dependencies: 187
-- Data for Name: etatmateriel; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO etatmateriel VALUES (9, 'Bon');
INSERT INTO etatmateriel VALUES (10, 'Mauvais');
INSERT INTO etatmateriel VALUES (11, 'Moyen');
INSERT INTO etatmateriel VALUES (12, 'Sur cale');
INSERT INTO etatmateriel VALUES (13, 'En panne');
INSERT INTO etatmateriel VALUES (14, 'A condamner');

--
-- TOC entry 2169 (class 0 OID 324026)
-- Dependencies: 188
-- Data for Name: financement; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO financement VALUES (15, 'RPI');
INSERT INTO financement VALUES (16, 'FASAD');
INSERT INTO financement VALUES (17, 'GASYNET');
INSERT INTO financement VALUES (18, 'PAGI');
INSERT INTO financement VALUES (19, 'BANQUE MONDIALE');
INSERT INTO financement VALUES (20, 'UNION EUROPEENE');
INSERT INTO financement VALUES (21, 'OMD');
INSERT INTO financement VALUES (22, 'Autres Partenaires techniques');

--
-- TOC entry 2175 (class 0 OID 324046)
-- Dependencies: 194
-- Data for Name: ma; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO ma VALUES (37, 'Achat');
INSERT INTO ma VALUES (38, 'Dons');
INSERT INTO ma VALUES (39, 'Affectation');
INSERT INTO ma VALUES (40, 'Recensement excédent');

--
-- TOC entry 2176 (class 0 OID 324049)
-- Dependencies: 195
-- Data for Name: marque; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO marque VALUES (23, 'Prolink');
INSERT INTO marque VALUES (24, 'Raffles');
INSERT INTO marque VALUES (25, 'Asus');
INSERT INTO marque VALUES (26, 'TOYOTA');
INSERT INTO marque VALUES (27, 'TOYOTA HILUX');
INSERT INTO marque VALUES (28, 'TOYOTA HIACE');
INSERT INTO marque VALUES (29, 'TOYOTA PRADO');
INSERT INTO marque VALUES (30, 'TOYOTA FORTUNER');
INSERT INTO marque VALUES (31, 'Nissan HARD BODY');
INSERT INTO marque VALUES (32, 'POLO CLASSIQUE');
INSERT INTO marque VALUES (33, 'ISUZU 4*4');
INSERT INTO marque VALUES (34, 'HP');
INSERT INTO marque VALUES (35, 'Inconnue');
INSERT INTO marque VALUES (36, 'Aucune');

--
-- TOC entry 2178 (class 0 OID 324058)
-- Dependencies: 197
-- Data for Name: motifdecharge; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO motifdecharge VALUES (41, 'Affectation');
INSERT INTO motifdecharge VALUES (42, 'Déces');
INSERT INTO motifdecharge VALUES (43, 'Licenciement');
INSERT INTO motifdecharge VALUES (44, 'Démission');
INSERT INTO motifdecharge VALUES (45, 'Disponibilité');
INSERT INTO motifdecharge VALUES (46, 'Détachement');
--
-- TOC entry 2179 (class 0 OID 324061)
-- Dependencies: 198
-- Data for Name: motifsortie; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO motifsortie VALUES (47, 'Affectation');
INSERT INTO motifsortie VALUES (48, 'Perte');
INSERT INTO motifsortie VALUES (49, 'Condamnation');
INSERT INTO motifsortie VALUES (50, 'Recensement ');
--
-- TOC entry 2180 (class 0 OID 324064)
-- Dependencies: 199
-- Data for Name: nomenclature; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO nomenclature VALUES (51, 'Combustibles et Luminaires', '1');
INSERT INTO nomenclature VALUES (52, 'Effets d''habillement, équipement, campement, harnachement, pansage', '2');
INSERT INTO nomenclature VALUES (53, 'Meubles et objets d''ammeubement, literie et couchage', '3');
INSERT INTO nomenclature VALUES (54, 'Outillage, instrument et appareils divers, matériel de transport, instruments de musiquen matériel sportif, matériel de guerre', '5');
INSERT INTO nomenclature VALUES (55, 'Ouvrages de bibliothèques, de sciences, et arts, Matériel d''enseignement, fournitures diverses', '10');

--
-- TOC entry 2190 (class 0 OID 324117)
-- Dependencies: 209
-- Data for Name: poste; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO poste VALUES (56, 'Receveur');
INSERT INTO poste VALUES (57, 'GAC');
INSERT INTO poste VALUES (58, 'Depositaire');
INSERT INTO poste VALUES (59, 'GAC suppléant');
INSERT INTO poste VALUES (60, 'Développeur');
INSERT INTO poste VALUES (61, 'Directeur Général');
INSERT INTO poste VALUES (62, 'Directeur');
INSERT INTO poste VALUES (63, 'Directeur des Ressources et de la Formation');
INSERT INTO poste VALUES (64, 'Chef SE');
INSERT INTO poste VALUES (65, 'Administrateur');

--
-- TOC entry 2193 (class 0 OID 324128)
-- Dependencies: 212
-- Data for Name: typemateriel; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO typemateriel VALUES (66, 'Lampe tube fluorescent', 'LAM1', 51);
INSERT INTO typemateriel VALUES (67, 'Réglettes LED', 'REG1', 51);
INSERT INTO typemateriel VALUES (68, 'Projecteur LED', 'PRO1', 51);
INSERT INTO typemateriel VALUES (69, 'Downlight LED', 'DOW1', 51);
INSERT INTO typemateriel VALUES (70, 'TUBE LED', 'TUB1', 51);
INSERT INTO typemateriel VALUES (71, 'Tenue de travail', 'TEN1', 52);
INSERT INTO typemateriel VALUES (72, 'Tenue de cérémonie', 'TEN2', 52);

--
-- TOC entry 2195 (class 0 OID 324140)
-- Dependencies: 214
-- Data for Name: useri; Type: TABLE DATA; Schema: public; Owner: -
--

INSERT INTO useri VALUES (2, 'Dépositaire Comptable', 'ROLE_DC');

INSERT INTO useri VALUES (1, 'Administrateur', 'ROLE_ADMIN');

INSERT INTO agent VALUES (1, true, 999999, 'Administrateur', '$2a$10$nVMDUP0w6Wji4FakfFNiBe2feKLK/oo4b9iFcKjXtqysCAAxN2/Py', NULL, NULL, NULL, 1);

--
-- TOC entry 2213 (class 0 OID 0)
-- Dependencies: 215
-- Name: useri_iduser_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('useri_iduser_seq', 1, true);




--
-- TOC entry 2212 (class 0 OID 0)
-- Dependencies: 210
-- Name: ref_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('ref_seq', 72, true);


--
-- TOC entry 2048 (class 2606 OID 346133)
-- Name: adiresy_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY adiresy
    ADD CONSTRAINT adiresy_pkey PRIMARY KEY (id);


--
-- TOC entry 2046 (class 2606 OID 345897)
-- Name: adresse_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY adresse
    ADD CONSTRAINT adresse_pkey PRIMARY KEY (id);


--
-- TOC entry 2050 (class 2606 OID 346141)
-- Name: agent_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY agent
    ADD CONSTRAINT agent_pkey PRIMARY KEY (idagent);


--
-- TOC entry 2054 (class 2606 OID 346149)
-- Name: article_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY article
    ADD CONSTRAINT article_pkey PRIMARY KEY (idarticle);


--
-- TOC entry 2056 (class 2606 OID 346154)
-- Name: categoriemat_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY categoriemat
    ADD CONSTRAINT categoriemat_pkey PRIMARY KEY (id);


--
-- TOC entry 2058 (class 2606 OID 346159)
-- Name: codearticle_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY codearticle
    ADD CONSTRAINT codearticle_pkey PRIMARY KEY (id);


--
-- TOC entry 2060 (class 2606 OID 346167)
-- Name: designation_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY designation
    ADD CONSTRAINT designation_pkey PRIMARY KEY (iddesignation);


--
-- TOC entry 2062 (class 2606 OID 346172)
-- Name: devise_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY devise
    ADD CONSTRAINT devise_pkey PRIMARY KEY (id);


--
-- TOC entry 2064 (class 2606 OID 346180)
-- Name: direction_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY direction
    ADD CONSTRAINT direction_pkey PRIMARY KEY (id);


--
-- TOC entry 2068 (class 2606 OID 346185)
-- Name: directiontitlehist_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY directiontitlehist
    ADD CONSTRAINT directiontitlehist_pkey PRIMARY KEY (idtitle);


--
-- TOC entry 2070 (class 2606 OID 346190)
-- Name: etatmateriel_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY etatmateriel
    ADD CONSTRAINT etatmateriel_pkey PRIMARY KEY (id);


--
-- TOC entry 2072 (class 2606 OID 346195)
-- Name: financement_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY financement
    ADD CONSTRAINT financement_pkey PRIMARY KEY (id);


--
-- TOC entry 2074 (class 2606 OID 346200)
-- Name: fournisseur_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY fournisseur
    ADD CONSTRAINT fournisseur_pkey PRIMARY KEY (id);


--
-- TOC entry 2076 (class 2606 OID 346208)
-- Name: fournisseurdetail_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY fournisseurdetail
    ADD CONSTRAINT fournisseurdetail_pkey PRIMARY KEY (idfourn);


--
-- TOC entry 2078 (class 2606 OID 346213)
-- Name: localite_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY localite
    ADD CONSTRAINT localite_pkey PRIMARY KEY (id);


--
-- TOC entry 2080 (class 2606 OID 346218)
-- Name: ma_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY ma
    ADD CONSTRAINT ma_pkey PRIMARY KEY (id);


--
-- TOC entry 2082 (class 2606 OID 346223)
-- Name: marque_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY marque
    ADD CONSTRAINT marque_pkey PRIMARY KEY (id);


--
-- TOC entry 2084 (class 2606 OID 346231)
-- Name: materiel_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT materiel_pkey PRIMARY KEY (idmateriel);


--
-- TOC entry 2088 (class 2606 OID 346236)
-- Name: motifdecharge_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY motifdecharge
    ADD CONSTRAINT motifdecharge_pkey PRIMARY KEY (id);


--
-- TOC entry 2090 (class 2606 OID 346241)
-- Name: motifsortie_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY motifsortie
    ADD CONSTRAINT motifsortie_pkey PRIMARY KEY (id);


--
-- TOC entry 2092 (class 2606 OID 346249)
-- Name: nomenclature_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY nomenclature
    ADD CONSTRAINT nomenclature_pkey PRIMARY KEY (id);


--
-- TOC entry 2120 (class 2606 OID 346329)
-- Name: opattribution_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY opattribution
    ADD CONSTRAINT opattribution_pkey PRIMARY KEY (id);


--
-- TOC entry 2096 (class 2606 OID 346257)
-- Name: opdettachement_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY opdettachement
    ADD CONSTRAINT opdettachement_pkey PRIMARY KEY (id);


--
-- TOC entry 2098 (class 2606 OID 346265)
-- Name: opentreearticle_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY opentreearticle
    ADD CONSTRAINT opentreearticle_pkey PRIMARY KEY (id);


--
-- TOC entry 2124 (class 2606 OID 346340)
-- Name: operationentree_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY operationentree
    ADD CONSTRAINT operationentree_pkey PRIMARY KEY (id);


--
-- TOC entry 2100 (class 2606 OID 346273)
-- Name: opsaisie_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY opsaisie
    ADD CONSTRAINT opsaisie_pkey PRIMARY KEY (id);


--
-- TOC entry 2102 (class 2606 OID 346281)
-- Name: opsortie_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY opsortie
    ADD CONSTRAINT opsortie_pkey PRIMARY KEY (id);


--
-- TOC entry 2104 (class 2606 OID 346289)
-- Name: opsortiearticle_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY opsortiearticle
    ADD CONSTRAINT opsortiearticle_pkey PRIMARY KEY (id);


--
-- TOC entry 2106 (class 2606 OID 346294)
-- Name: poste_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY poste
    ADD CONSTRAINT poste_pkey PRIMARY KEY (id);



--
-- TOC entry 2108 (class 2606 OID 346302)
-- Name: typemateriel_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY typemateriel
    ADD CONSTRAINT typemateriel_pkey PRIMARY KEY (id);


--
-- TOC entry 2112 (class 2606 OID 346310)
-- Name: typeobjet_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY typeobjet
    ADD CONSTRAINT typeobjet_pkey PRIMARY KEY (id);


--
-- TOC entry 2118 (class 2606 OID 346354)
-- Name: uk_7rinsri5ger9mpcujffk0kcwa; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY directionhistor
    ADD CONSTRAINT uk_7rinsri5ger9mpcujffk0kcwa UNIQUE (dirtitleid);


--
-- TOC entry 2086 (class 2606 OID 346346)
-- Name: uk_983enq5t3n1wndch8efiqydvg; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT uk_983enq5t3n1wndch8efiqydvg UNIQUE (code);


--
-- TOC entry 2066 (class 2606 OID 346344)
-- Name: uk_cc5kff3lccduct8um9ciab0on; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY direction
    ADD CONSTRAINT uk_cc5kff3lccduct8um9ciab0on UNIQUE (codedirection);


--
-- TOC entry 2094 (class 2606 OID 346348)
-- Name: uk_coe1ws0g9r615c2ak85e1fjwh; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY nomenclature
    ADD CONSTRAINT uk_coe1ws0g9r615c2ak85e1fjwh UNIQUE (nomenclature);


--
-- TOC entry 2122 (class 2606 OID 346356)
-- Name: uk_hal2htiljg342gmxtxcqrcro7; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY opentreemateriel
    ADD CONSTRAINT uk_hal2htiljg342gmxtxcqrcro7 UNIQUE (materielid);


--
-- TOC entry 2114 (class 2606 OID 346352)
-- Name: uk_iy7jsd0hvcxtqbisrxg0coyh5; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY useri
    ADD CONSTRAINT uk_iy7jsd0hvcxtqbisrxg0coyh5 UNIQUE (role);


--
-- TOC entry 2052 (class 2606 OID 346342)
-- Name: uk_m14fs3cet7rkfdvm535gc19ww; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY agent
    ADD CONSTRAINT uk_m14fs3cet7rkfdvm535gc19ww UNIQUE (im);


--
-- TOC entry 2110 (class 2606 OID 346350)
-- Name: uk_nbcgu0iesc6kp4jbl6l67cas4; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY typemateriel
    ADD CONSTRAINT uk_nbcgu0iesc6kp4jbl6l67cas4 UNIQUE (codetypemate);


--
-- TOC entry 2116 (class 2606 OID 346318)
-- Name: useri_pkey; Type: CONSTRAINT; Schema: public; Owner: -; Tablespace: 
--

ALTER TABLE ONLY useri
    ADD CONSTRAINT useri_pkey PRIMARY KEY (iduser);


--
-- TOC entry 2150 (class 2606 OID 346482)
-- Name: fk_1kfd3ltf9m8poish73osjufvx; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk_1kfd3ltf9m8poish73osjufvx FOREIGN KEY (idetat) REFERENCES etatmateriel(id);


--
-- TOC entry 2133 (class 2606 OID 346397)
-- Name: fk_2ftx9f7edrux42egi8p9stcw0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY article
    ADD CONSTRAINT fk_2ftx9f7edrux42egi8p9stcw0 FOREIGN KEY (idfin) REFERENCES financement(id);


--
-- TOC entry 2170 (class 2606 OID 346582)
-- Name: fk_2qmek8d2iffioepm4wvtd7q10; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsortie
    ADD CONSTRAINT fk_2qmek8d2iffioepm4wvtd7q10 FOREIGN KEY (idmat) REFERENCES materiel(idmateriel);


--
-- TOC entry 2173 (class 2606 OID 346597)
-- Name: fk_3704h5og1v9s8pq8ifqaos6g7; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsortiearticle
    ADD CONSTRAINT fk_3704h5og1v9s8pq8ifqaos6g7 FOREIGN KEY (idart) REFERENCES article(idarticle);


--
-- TOC entry 2171 (class 2606 OID 346587)
-- Name: fk_3gsdske3sk9q0vpolsefkawn8; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsortie
    ADD CONSTRAINT fk_3gsdske3sk9q0vpolsefkawn8 FOREIGN KEY (iddirection) REFERENCES direction(id);


--
-- TOC entry 2138 (class 2606 OID 346422)
-- Name: fk_41q8uyov72oj3rf2pbk7xtx8b; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY designation
    ADD CONSTRAINT fk_41q8uyov72oj3rf2pbk7xtx8b FOREIGN KEY (idfin) REFERENCES financement(id);


--
-- TOC entry 2146 (class 2606 OID 346462)
-- Name: fk_5bugq7t3xh225s002u3vfw1us; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk_5bugq7t3xh225s002u3vfw1us FOREIGN KEY (imdepositaire) REFERENCES agent(idagent);


--
-- TOC entry 2145 (class 2606 OID 346457)
-- Name: fk_63xrunvavfwavig1lksqx5gij; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk_63xrunvavfwavig1lksqx5gij FOREIGN KEY (idcar) REFERENCES typemateriel(id);


--
-- TOC entry 2178 (class 2606 OID 346622)
-- Name: fk_7rinsri5ger9mpcujffk0kcwa; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY directionhistor
    ADD CONSTRAINT fk_7rinsri5ger9mpcujffk0kcwa FOREIGN KEY (dirtitleid) REFERENCES directiontitlehist(idtitle);


--
-- TOC entry 2182 (class 2606 OID 346642)
-- Name: fk_8dpll1c9peh7tqqg4rw7a5q3k; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opattribution
    ADD CONSTRAINT fk_8dpll1c9peh7tqqg4rw7a5q3k FOREIGN KEY (iddirection) REFERENCES direction(id);


--
-- TOC entry 2162 (class 2606 OID 346542)
-- Name: fk_96gh25iaabmnw71mlo3l67hi1; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opdettachement
    ADD CONSTRAINT fk_96gh25iaabmnw71mlo3l67hi1 FOREIGN KEY (idoperateur) REFERENCES agent(idagent);


--
-- TOC entry 2180 (class 2606 OID 346632)
-- Name: fk_97lmo48sk98wjajbpj79dtrcl; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opattribution
    ADD CONSTRAINT fk_97lmo48sk98wjajbpj79dtrcl FOREIGN KEY (iddetenteur) REFERENCES agent(idagent);


--
-- TOC entry 2164 (class 2606 OID 346552)
-- Name: fk_a13a3ji8dwj6tv5qbn0mw224k; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opentreearticle
    ADD CONSTRAINT fk_a13a3ji8dwj6tv5qbn0mw224k FOREIGN KEY (iddirection) REFERENCES direction(id);


--
-- TOC entry 2177 (class 2606 OID 346617)
-- Name: fk_abtv5k232s1nhkx0140e3rj9f; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY typemateriel
    ADD CONSTRAINT fk_abtv5k232s1nhkx0140e3rj9f FOREIGN KEY (idnomenclature) REFERENCES nomenclature(id);


--
-- TOC entry 2147 (class 2606 OID 346467)
-- Name: fk_aoe0utlrxp6huevprwjbcg6; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk_aoe0utlrxp6huevprwjbcg6 FOREIGN KEY (desingationid) REFERENCES designation(iddesignation);


--
-- TOC entry 2129 (class 2606 OID 346377)
-- Name: fk_b2n61s2p8as0i9xt1aa5mfece; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY article
    ADD CONSTRAINT fk_b2n61s2p8as0i9xt1aa5mfece FOREIGN KEY (idcode) REFERENCES codearticle(id);


--
-- TOC entry 2179 (class 2606 OID 346627)
-- Name: fk_b6hkvadijtio2w9hhwvd1p7l5; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY directionhistor
    ADD CONSTRAINT fk_b6hkvadijtio2w9hhwvd1p7l5 FOREIGN KEY (dirid) REFERENCES direction(id);


--
-- TOC entry 2141 (class 2606 OID 346437)
-- Name: fk_ban4iw8wxykh1qgey0vnfqbq0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY designation
    ADD CONSTRAINT fk_ban4iw8wxykh1qgey0vnfqbq0 FOREIGN KEY (idmodacq) REFERENCES ma(id);


--
-- TOC entry 2139 (class 2606 OID 346427)
-- Name: fk_bk80xvlpmkkpf8estrwv6g7i6; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY designation
    ADD CONSTRAINT fk_bk80xvlpmkkpf8estrwv6g7i6 FOREIGN KEY (idfourn) REFERENCES fournisseur(id);


--
-- TOC entry 2165 (class 2606 OID 346557)
-- Name: fk_brf7c5dvg7pn4aik8unxydybn; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opentreearticle
    ADD CONSTRAINT fk_brf7c5dvg7pn4aik8unxydybn FOREIGN KEY (idoperateur) REFERENCES agent(idagent);


--
-- TOC entry 2132 (class 2606 OID 346392)
-- Name: fk_btp963qjg0oqcxutifo9xwj8n; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY article
    ADD CONSTRAINT fk_btp963qjg0oqcxutifo9xwj8n FOREIGN KEY (idmarqueart) REFERENCES marque(id);


--
-- TOC entry 2188 (class 2606 OID 346672)
-- Name: fk_bwi2wesv4moinne8m49qoqd2g; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY operationentree
    ADD CONSTRAINT fk_bwi2wesv4moinne8m49qoqd2g FOREIGN KEY (idoperateur) REFERENCES agent(idagent);


--
-- TOC entry 2168 (class 2606 OID 346572)
-- Name: fk_c023jk8io7hcj73y8kcm8u1j7; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsortie
    ADD CONSTRAINT fk_c023jk8io7hcj73y8kcm8u1j7 FOREIGN KEY (iddirect) REFERENCES direction(id);


--
-- TOC entry 2140 (class 2606 OID 346432)
-- Name: fk_ckrclnxlp2j9hjlg53y13i478; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY designation
    ADD CONSTRAINT fk_ckrclnxlp2j9hjlg53y13i478 FOREIGN KEY (idmarque) REFERENCES marque(id);


--
-- TOC entry 2136 (class 2606 OID 346412)
-- Name: fk_cr7ouopgwx0wssx2c1ql4n9od; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY codearticle
    ADD CONSTRAINT fk_cr7ouopgwx0wssx2c1ql4n9od FOREIGN KEY (idtypeobj) REFERENCES typeobjet(id);


--
-- TOC entry 2169 (class 2606 OID 346577)
-- Name: fk_dmpuxsn6brpktgyfbs9t260fb; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsortie
    ADD CONSTRAINT fk_dmpuxsn6brpktgyfbs9t260fb FOREIGN KEY (idmotif) REFERENCES motifsortie(id);


--
-- TOC entry 2172 (class 2606 OID 346592)
-- Name: fk_dod7cy4pw817nlkjr1fa70ml8; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsortie
    ADD CONSTRAINT fk_dod7cy4pw817nlkjr1fa70ml8 FOREIGN KEY (idoperateur) REFERENCES agent(idagent);


--
-- TOC entry 2134 (class 2606 OID 346402)
-- Name: fk_e5j8x010a91pma929bq46fk7k; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY article
    ADD CONSTRAINT fk_e5j8x010a91pma929bq46fk7k FOREIGN KEY (idfournisseur) REFERENCES fournisseur(id);


--
-- TOC entry 2161 (class 2606 OID 346537)
-- Name: fk_efspmxw039473c7ay04qj773c; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opdettachement
    ADD CONSTRAINT fk_efspmxw039473c7ay04qj773c FOREIGN KEY (iddirection) REFERENCES direction(id);


--
-- TOC entry 2163 (class 2606 OID 346547)
-- Name: fk_epdju6822rfq4jnxcx3uqg00; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opentreearticle
    ADD CONSTRAINT fk_epdju6822rfq4jnxcx3uqg00 FOREIGN KEY (idart) REFERENCES article(idarticle);


--
-- TOC entry 2187 (class 2606 OID 346667)
-- Name: fk_eq418uba8siqhj80cl16xbsg3; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY operationentree
    ADD CONSTRAINT fk_eq418uba8siqhj80cl16xbsg3 FOREIGN KEY (iddirection) REFERENCES direction(id);


--
-- TOC entry 2175 (class 2606 OID 346607)
-- Name: fk_et3kolbb4lf3142mx4u0sygwi; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsortiearticle
    ADD CONSTRAINT fk_et3kolbb4lf3142mx4u0sygwi FOREIGN KEY (iddirection) REFERENCES direction(id);


--
-- TOC entry 2186 (class 2606 OID 346662)
-- Name: fk_fi9r4deqgxgf02se7cmkhwnn0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY operationentree
    ADD CONSTRAINT fk_fi9r4deqgxgf02se7cmkhwnn0 FOREIGN KEY (idmat) REFERENCES materiel(idmateriel);


--
-- TOC entry 2152 (class 2606 OID 346492)
-- Name: fk_fu77na9at6mcas0m3fgqillb9; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk_fu77na9at6mcas0m3fgqillb9 FOREIGN KEY (opentreeid) REFERENCES operationentree(id);


--
-- TOC entry 2144 (class 2606 OID 346452)
-- Name: fk_g7l5wt1t2j2fupom91oymhenm; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY directiontitlehist
    ADD CONSTRAINT fk_g7l5wt1t2j2fupom91oymhenm FOREIGN KEY (iddirection) REFERENCES direction(id);


--
-- TOC entry 2128 (class 2606 OID 346372)
-- Name: fk_g7w6pfq4kqlpf0c5hdm43tb31; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY article
    ADD CONSTRAINT fk_g7w6pfq4kqlpf0c5hdm43tb31 FOREIGN KEY (imbeneficiaire) REFERENCES agent(idagent);


--
-- TOC entry 2151 (class 2606 OID 346487)
-- Name: fk_gbkf6seno6bda3ru2b7rir6ic; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk_gbkf6seno6bda3ru2b7rir6ic FOREIGN KEY (idmarque) REFERENCES marque(id);


--
-- TOC entry 2127 (class 2606 OID 346367)
-- Name: fk_gkco9yx7753xuju4l5fv7x0wc; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY agent
    ADD CONSTRAINT fk_gkco9yx7753xuju4l5fv7x0wc FOREIGN KEY (idrole) REFERENCES useri(iduser);


--
-- TOC entry 2184 (class 2606 OID 346652)
-- Name: fk_hal2htiljg342gmxtxcqrcro7; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opentreemateriel
    ADD CONSTRAINT fk_hal2htiljg342gmxtxcqrcro7 FOREIGN KEY (materielid) REFERENCES materiel(idmateriel);


--
-- TOC entry 2135 (class 2606 OID 346407)
-- Name: fk_hpfkdrseflf6i97gel3lgo42k; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY article
    ADD CONSTRAINT fk_hpfkdrseflf6i97gel3lgo42k FOREIGN KEY (idmodacqart) REFERENCES ma(id);


--
-- TOC entry 2130 (class 2606 OID 346382)
-- Name: fk_iextl9k5i24wfo1q1hsg2c3ha; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY article
    ADD CONSTRAINT fk_iextl9k5i24wfo1q1hsg2c3ha FOREIGN KEY (imdepositaire) REFERENCES agent(idagent);


--
-- TOC entry 2158 (class 2606 OID 346522)
-- Name: fk_j43ba6yryfip7dbw7cvr1uuao; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opdettachement
    ADD CONSTRAINT fk_j43ba6yryfip7dbw7cvr1uuao FOREIGN KEY (iddetenteur) REFERENCES agent(idagent);


--
-- TOC entry 2160 (class 2606 OID 346532)
-- Name: fk_j6q293lk4tbioi6tgv6hkr9x0; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opdettachement
    ADD CONSTRAINT fk_j6q293lk4tbioi6tgv6hkr9x0 FOREIGN KEY (idmotifdett) REFERENCES motifdecharge(id);


--
-- TOC entry 2143 (class 2606 OID 346447)
-- Name: fk_jn9fhdf4a7dx9wfm10srwgwgy; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY designation
    ADD CONSTRAINT fk_jn9fhdf4a7dx9wfm10srwgwgy FOREIGN KEY (idtypemateriel) REFERENCES typemateriel(id);


--
-- TOC entry 2185 (class 2606 OID 346657)
-- Name: fk_jqtuvqv8qoi5y9p7u48l3nym4; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opentreemateriel
    ADD CONSTRAINT fk_jqtuvqv8qoi5y9p7u48l3nym4 FOREIGN KEY (entreeid) REFERENCES operationentree(id);


--
-- TOC entry 2149 (class 2606 OID 346477)
-- Name: fk_k069nwtgggqvdhpcf5dbfvuhm; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk_k069nwtgggqvdhpcf5dbfvuhm FOREIGN KEY (iddirection) REFERENCES direction(id);


--
-- TOC entry 2148 (class 2606 OID 346472)
-- Name: fk_ld53t3uo6gm3tmoujlxidx6y; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk_ld53t3uo6gm3tmoujlxidx6y FOREIGN KEY (imdetenteur) REFERENCES agent(idagent);


--
-- TOC entry 2167 (class 2606 OID 346567)
-- Name: fk_mnwve0uf1vjv90xb1y9cw3glr; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsaisie
    ADD CONSTRAINT fk_mnwve0uf1vjv90xb1y9cw3glr FOREIGN KEY (idoperateur) REFERENCES agent(idagent);


--
-- TOC entry 2157 (class 2606 OID 346517)
-- Name: fk_mxu71stfosta9uhtq19ob6yq2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk_mxu71stfosta9uhtq19ob6yq2 FOREIGN KEY (idmodacq) REFERENCES ma(id);


--
-- TOC entry 2174 (class 2606 OID 346602)
-- Name: fk_ndg3qdkn7y7uro6877hbqdetx; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsortiearticle
    ADD CONSTRAINT fk_ndg3qdkn7y7uro6877hbqdetx FOREIGN KEY (idbenefi) REFERENCES agent(idagent);


--
-- TOC entry 2154 (class 2606 OID 346502)
-- Name: fk_niuufl4c4ffbcb3jqeiof9bi2; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk_niuufl4c4ffbcb3jqeiof9bi2 FOREIGN KEY (idtypemateriel) REFERENCES typemateriel(id);


--
-- TOC entry 2142 (class 2606 OID 346442)
-- Name: fk_nu5v8mjcqq3jshvhdsxmmfvge; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY designation
    ADD CONSTRAINT fk_nu5v8mjcqq3jshvhdsxmmfvge FOREIGN KEY (idnom) REFERENCES nomenclature(id);


--
-- TOC entry 2156 (class 2606 OID 346512)
-- Name: fk_ptpfkxr6g5eddgs0gqyed65aw; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk_ptpfkxr6g5eddgs0gqyed65aw FOREIGN KEY (idfourn) REFERENCES fournisseur(id);


--
-- TOC entry 2137 (class 2606 OID 346417)
-- Name: fk_pu91ry2rkvt1uxietf394t353; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY designation
    ADD CONSTRAINT fk_pu91ry2rkvt1uxietf394t353 FOREIGN KEY (idcar) REFERENCES typemateriel(id);


--
-- TOC entry 2155 (class 2606 OID 346507)
-- Name: fk_qgp53rk76q65rlw5jy381m0kn; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk_qgp53rk76q65rlw5jy381m0kn FOREIGN KEY (idfin) REFERENCES financement(id);


--
-- TOC entry 2166 (class 2606 OID 346562)
-- Name: fk_r5okyp62difod2xfvlyyx3hew; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsaisie
    ADD CONSTRAINT fk_r5okyp62difod2xfvlyyx3hew FOREIGN KEY (iddirection) REFERENCES direction(id);


--
-- TOC entry 2131 (class 2606 OID 346387)
-- Name: fk_r9yt94j6orlv39wvrxs0x80gj; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY article
    ADD CONSTRAINT fk_r9yt94j6orlv39wvrxs0x80gj FOREIGN KEY (iddirectionart) REFERENCES direction(id);


--
-- TOC entry 2181 (class 2606 OID 346637)
-- Name: fk_rdpkaeco02sovqcxsyak77sir; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opattribution
    ADD CONSTRAINT fk_rdpkaeco02sovqcxsyak77sir FOREIGN KEY (idmat) REFERENCES materiel(idmateriel);


--
-- TOC entry 2176 (class 2606 OID 346612)
-- Name: fk_re4d3ws1k37oci5dutefl7y6v; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opsortiearticle
    ADD CONSTRAINT fk_re4d3ws1k37oci5dutefl7y6v FOREIGN KEY (idoperateur) REFERENCES agent(idagent);


--
-- TOC entry 2125 (class 2606 OID 346357)
-- Name: fk_rpcqqsbi9vyg5qebukamxmphh; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY agent
    ADD CONSTRAINT fk_rpcqqsbi9vyg5qebukamxmphh FOREIGN KEY (iddirection) REFERENCES direction(id);


--
-- TOC entry 2159 (class 2606 OID 346527)
-- Name: fk_s1ec3jb4pl769vi9wybeyf2ea; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opdettachement
    ADD CONSTRAINT fk_s1ec3jb4pl769vi9wybeyf2ea FOREIGN KEY (idmat) REFERENCES materiel(idmateriel);


--
-- TOC entry 2153 (class 2606 OID 346497)
-- Name: fk_s60c8l417u3dvlpsxjnndavcn; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY materiel
    ADD CONSTRAINT fk_s60c8l417u3dvlpsxjnndavcn FOREIGN KEY (idnom) REFERENCES nomenclature(id);


--
-- TOC entry 2126 (class 2606 OID 346362)
-- Name: fk_tbhd06qf1w66e0qkic6ppkke4; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY agent
    ADD CONSTRAINT fk_tbhd06qf1w66e0qkic6ppkke4 FOREIGN KEY (idposteny) REFERENCES poste(id);


--
-- TOC entry 2183 (class 2606 OID 346647)
-- Name: fk_te4tc5e28v67s3ko7q28qgrqj; Type: FK CONSTRAINT; Schema: public; Owner: -
--

ALTER TABLE ONLY opattribution
    ADD CONSTRAINT fk_te4tc5e28v67s3ko7q28qgrqj FOREIGN KEY (idoperateur) REFERENCES agent(idagent);


-- Completed on 2018-09-25 11:24:12 EAT

--
-- PostgreSQL database dump complete
--


