--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.17
-- Dumped by pg_dump version 9.3.11
-- Started on 2018-09-25 11:24:11 EAT

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;


--
-- TOC entry 216 (class 1259 OID 346693)
-- Name: account_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE account_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 217 (class 1259 OID 346695)
-- Name: account_idart_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE account_idart_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


SET default_tablespace = '';

SET default_with_oids = false;

--
-- TOC entry 175 (class 1259 OID 346129)
-- Name: adiresy; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE adiresy (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 172 (class 1259 OID 345893)
-- Name: adresse; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE adresse (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 208 (class 1259 OID 346677)
-- Name: ag_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE ag_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 176 (class 1259 OID 346134)
-- Name: agent; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE agent (
    idagent bigint NOT NULL,
    active boolean NOT NULL,
    im bigint,
    nomagent character varying(255),
    password character varying(255) NOT NULL,
    prenomagent character varying(255),
    iddirection bigint,
    idposteny bigint,
    idrole integer
);


--
-- TOC entry 177 (class 1259 OID 346142)
-- Name: article; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE article (
    typeart integer NOT NULL,
    idarticle bigint NOT NULL,
    caracteristiquearticle character varying(255),
    especeunit character varying(255),
    nombre bigint,
    origine character varying(255),
    prix real,
    reference character varying(255),
    validation boolean NOT NULL,
    imbeneficiaire bigint,
    idcode bigint,
    imdepositaire bigint,
    iddirectionart bigint,
    idmarqueart bigint,
    idfin bigint,
    idfournisseur bigint,
    idmodacqart bigint
);


--
-- TOC entry 178 (class 1259 OID 346150)
-- Name: categoriemat; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE categoriemat (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 179 (class 1259 OID 346155)
-- Name: codearticle; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE codearticle (
    id bigint NOT NULL,
    designation character varying(255),
    idtypeobj bigint
);


--
-- TOC entry 180 (class 1259 OID 346160)
-- Name: designation; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE designation (
    iddesignation bigint NOT NULL,
    anneeacquisition character varying(255),
    autre character varying(255),
    documentpath character varying(255),
    especeunite character varying(255),
    image bytea,
    origine character varying(255),
    pu real,
    reffacture character varying(255),
    renseignement character varying(255),
    idcar integer,
    idfin bigint,
    idfourn bigint,
    idmarque bigint,
    idmodacq bigint,
    idnom bigint,
    idtypemateriel bigint
);


--
-- TOC entry 218 (class 1259 OID 346697)
-- Name: designation_id_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE designation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 181 (class 1259 OID 346168)
-- Name: devise; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE devise (
    id bigint NOT NULL,
    designation character varying(255),
    dateexpire date
);


--
-- TOC entry 182 (class 1259 OID 346173)
-- Name: direction; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE direction (
    id bigint NOT NULL,
    designation character varying(255),
    budget character varying(255),
    codedirection character varying(255),
    quatre character varying(255),
    trois character varying(255)
);


--
-- TOC entry 204 (class 1259 OID 346319)
-- Name: directionhistor; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE directionhistor (
    dirid bigint NOT NULL,
    dirtitleid bigint NOT NULL
);


--
-- TOC entry 183 (class 1259 OID 346181)
-- Name: directiontitlehist; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE directiontitlehist (
    idtitle bigint NOT NULL,
    datehist date,
    title character varying(255),
    iddirection bigint
);


--
-- TOC entry 184 (class 1259 OID 346186)
-- Name: etatmateriel; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE etatmateriel (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 185 (class 1259 OID 346191)
-- Name: financement; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE financement (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 209 (class 1259 OID 346679)
-- Name: four_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE four_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 186 (class 1259 OID 346196)
-- Name: fournisseur; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE fournisseur (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 187 (class 1259 OID 346201)
-- Name: fournisseurdetail; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE fournisseurdetail (
    idfourn bigint NOT NULL,
    adresse character varying(255),
    contact character varying(255),
    nif character varying(255),
    nomfourn character varying(255),
    observation character varying(255),
    stat character varying(255)
);


--
-- TOC entry 189 (class 1259 OID 321930)
-- Name: fournisseurdetail_idfourn_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE fournisseurdetail_idfourn_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2361 (class 0 OID 0)
-- Dependencies: 189
-- Name: fournisseurdetail_idfourn_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: -
--

ALTER SEQUENCE fournisseurdetail_idfourn_seq OWNED BY fournisseurdetail.idfourn;


--
-- TOC entry 190 (class 1259 OID 321932)
-- Name: hibernate_sequences; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE hibernate_sequences (
    sequence_name character varying(255),
    sequence_next_hi_value integer
);

--
-- TOC entry 210 (class 1259 OID 346681)
-- Name: hist_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE hist_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 188 (class 1259 OID 346209)
-- Name: localite; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE localite (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 189 (class 1259 OID 346214)
-- Name: ma; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE ma (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 190 (class 1259 OID 346219)
-- Name: marque; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE marque (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 191 (class 1259 OID 346224)
-- Name: materiel; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE materiel (
    typemateriels integer NOT NULL,
    idmateriel bigint NOT NULL,
    anneeacquisition character varying(255),
    autre character varying(255),
    code character varying(255),
    documentpath character varying(255),
    especeunite character varying(255),
    image bytea,
    numserie character varying(255),
    numerotype bigint,
    origine character varying(255),
    pu real,
    reference character varying(255),
    renseignement character varying(255),
    serienumero integer NOT NULL,
    validation boolean NOT NULL,
    montant_facture real,
    reffacture character varying(255),
    idcar integer,
    imdepositaire bigint,
    desingationid bigint,
    imdetenteur bigint,
    iddirection bigint,
    idetat bigint,
    idmarque bigint,
    opentreeid bigint,
    idnom bigint,
    idtypemateriel bigint,
    idfin bigint,
    idfourn bigint,
    idmodacq bigint,
    materielcount integer
);


--
-- TOC entry 192 (class 1259 OID 346232)
-- Name: motifdecharge; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE motifdecharge (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 193 (class 1259 OID 346237)
-- Name: motifsortie; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE motifsortie (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 194 (class 1259 OID 346242)
-- Name: nomenclature; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE nomenclature (
    id bigint NOT NULL,
    designation character varying(255),
    nomenclature character varying(255)
);


--
-- TOC entry 211 (class 1259 OID 346683)
-- Name: op_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE op_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 205 (class 1259 OID 346322)
-- Name: opattribution; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE opattribution (
    id bigint NOT NULL,
    dateop date,
    motifretour character varying(255),
    poste character varying(255),
    state character varying(255),
    timeop time without time zone,
    iddirection bigint,
    idoperateur bigint,
    detenteureffectif character varying(255),
    numerodet character varying(255),
    iddetenteur bigint,
    idmat bigint
);


--
-- TOC entry 195 (class 1259 OID 346250)
-- Name: opdettachement; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE opdettachement (
    id bigint NOT NULL,
    dateop date,
    motifretour character varying(255),
    poste character varying(255),
    state character varying(255),
    timeop time without time zone,
    iddirection bigint,
    idoperateur bigint,
    iddetenteur bigint,
    idmat bigint,
    idmotifdett bigint
);


--
-- TOC entry 196 (class 1259 OID 346258)
-- Name: opentreearticle; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE opentreearticle (
    id bigint NOT NULL,
    dateop date,
    motifretour character varying(255),
    poste character varying(255),
    state character varying(255),
    timeop time without time zone,
    iddirection bigint,
    idoperateur bigint,
    idart bigint
);


--
-- TOC entry 206 (class 1259 OID 346330)
-- Name: opentreemateriel; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE opentreemateriel (
    entreeid bigint NOT NULL,
    materielid bigint NOT NULL
);


--
-- TOC entry 207 (class 1259 OID 346333)
-- Name: operationentree; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE operationentree (
    id bigint NOT NULL,
    dateop date,
    motifretour character varying(255),
    poste character varying(255),
    state character varying(255),
    timeop time without time zone,
    iddirection bigint,
    idoperateur bigint,
    numoperation character varying(255),
    idmat bigint,
    pathdoc character varying(255),
    reffact character varying(255)
);


--
-- TOC entry 197 (class 1259 OID 346266)
-- Name: opsaisie; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE opsaisie (
    id bigint NOT NULL,
    dateop date,
    motifretour character varying(255),
    poste character varying(255),
    state character varying(255),
    timeop time without time zone,
    iddirection bigint,
    idoperateur bigint,
    designationref character varying(255),
    idrefer bigint
);


--
-- TOC entry 198 (class 1259 OID 346274)
-- Name: opsortie; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE opsortie (
    id bigint NOT NULL,
    dateop date,
    motifretour character varying(255),
    poste character varying(255),
    state character varying(255),
    timeop time without time zone,
    iddirection bigint,
    idoperateur bigint,
    numoperation character varying(255),
    idmat bigint,
    pj character varying(255),
    iddirect bigint,
    idmotif bigint
);


--
-- TOC entry 199 (class 1259 OID 346282)
-- Name: opsortiearticle; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE opsortiearticle (
    id bigint NOT NULL,
    dateop date,
    motifretour character varying(255),
    poste character varying(255),
    state character varying(255),
    timeop time without time zone,
    iddirection bigint,
    idoperateur bigint,
    decision character varying(255),
    nombretos bigint,
    idart bigint,
    idbenefi bigint
);


--
-- TOC entry 200 (class 1259 OID 346290)
-- Name: poste; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE poste (
    id bigint NOT NULL,
    designation character varying(255)
);


--
-- TOC entry 212 (class 1259 OID 346685)
-- Name: ref_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE ref_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;





--
-- TOC entry 201 (class 1259 OID 346295)
-- Name: typemateriel; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE typemateriel (
    id bigint NOT NULL,
    designation character varying(255),
    codetypemate character varying(255),
    idnomenclature bigint
);


--
-- TOC entry 202 (class 1259 OID 346303)
-- Name: typeobjet; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE typeobjet (
    id bigint NOT NULL,
    designation character varying(255),
    caracteristique character varying(255)
);


--
-- TOC entry 215 (class 1259 OID 346691)
-- Name: user_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE user_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 203 (class 1259 OID 346311)
-- Name: useri; Type: TABLE; Schema: public; Owner: -; Tablespace: 
--

CREATE TABLE useri (
    iduser integer NOT NULL,
    designation character varying(255),
    role character varying(255)
);


--
-- TOC entry 174 (class 1259 OID 346116)
-- Name: useri_iduser_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE useri_iduser_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 213 (class 1259 OID 346687)
-- Name: useri_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE useri_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 214 (class 1259 OID 346689)
-- Name: userrole_seq; Type: SEQUENCE; Schema: public; Owner: -
--

CREATE SEQUENCE userrole_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


--
-- TOC entry 2351 (class 0 OID 0)
-- Dependencies: 216
-- Name: account_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('account_id_seq', 1, false);


--
-- TOC entry 2352 (class 0 OID 0)
-- Dependencies: 217
-- Name: account_idart_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('account_idart_seq', 1, false);


--
-- TOC entry 2300 (class 0 OID 346129)
-- Dependencies: 175
-- Data for Name: adiresy; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2297 (class 0 OID 345893)
-- Dependencies: 172
-- Data for Name: adresse; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2353 (class 0 OID 0)
-- Dependencies: 208
-- Name: ag_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('ag_seq', 1, false);


--
-- TOC entry 2301 (class 0 OID 346134)
-- Dependencies: 176
-- Data for Name: agent; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2302 (class 0 OID 346142)
-- Dependencies: 177
-- Data for Name: article; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2303 (class 0 OID 346150)
-- Dependencies: 178
-- Data for Name: categoriemat; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2304 (class 0 OID 346155)
-- Dependencies: 179
-- Data for Name: codearticle; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2305 (class 0 OID 346160)
-- Dependencies: 180
-- Data for Name: designation; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2354 (class 0 OID 0)
-- Dependencies: 218
-- Name: designation_id_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('designation_id_seq', 1, false);


--
-- TOC entry 2306 (class 0 OID 346168)
-- Dependencies: 181
-- Data for Name: devise; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2307 (class 0 OID 346173)
-- Dependencies: 182
-- Data for Name: direction; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2329 (class 0 OID 346319)
-- Dependencies: 204
-- Data for Name: directionhistor; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2308 (class 0 OID 346181)
-- Dependencies: 183
-- Data for Name: directiontitlehist; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2309 (class 0 OID 346186)
-- Dependencies: 184
-- Data for Name: etatmateriel; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2310 (class 0 OID 346191)
-- Dependencies: 185
-- Data for Name: financement; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2355 (class 0 OID 0)
-- Dependencies: 209
-- Name: four_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('four_seq', 1, false);


--
-- TOC entry 2311 (class 0 OID 346196)
-- Dependencies: 186
-- Data for Name: fournisseur; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2312 (class 0 OID 346201)
-- Dependencies: 187
-- Data for Name: fournisseurdetail; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2298 (class 0 OID 345984)
-- Dependencies: 173
-- Data for Name: hibernate_sequences; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2356 (class 0 OID 0)
-- Dependencies: 210
-- Name: hist_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('hist_seq', 1, false);


--
-- TOC entry 2313 (class 0 OID 346209)
-- Dependencies: 188
-- Data for Name: localite; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2314 (class 0 OID 346214)
-- Dependencies: 189
-- Data for Name: ma; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2315 (class 0 OID 346219)
-- Dependencies: 190
-- Data for Name: marque; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2316 (class 0 OID 346224)
-- Dependencies: 191
-- Data for Name: materiel; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2317 (class 0 OID 346232)
-- Dependencies: 192
-- Data for Name: motifdecharge; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2318 (class 0 OID 346237)
-- Dependencies: 193
-- Data for Name: motifsortie; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2319 (class 0 OID 346242)
-- Dependencies: 194
-- Data for Name: nomenclature; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2357 (class 0 OID 0)
-- Dependencies: 211
-- Name: op_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('op_seq', 1, false);


--
-- TOC entry 2330 (class 0 OID 346322)
-- Dependencies: 205
-- Data for Name: opattribution; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2320 (class 0 OID 346250)
-- Dependencies: 195
-- Data for Name: opdettachement; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2321 (class 0 OID 346258)
-- Dependencies: 196
-- Data for Name: opentreearticle; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2331 (class 0 OID 346330)
-- Dependencies: 206
-- Data for Name: opentreemateriel; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2332 (class 0 OID 346333)
-- Dependencies: 207
-- Data for Name: operationentree; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2322 (class 0 OID 346266)
-- Dependencies: 197
-- Data for Name: opsaisie; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2323 (class 0 OID 346274)
-- Dependencies: 198
-- Data for Name: opsortie; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2324 (class 0 OID 346282)
-- Dependencies: 199
-- Data for Name: opsortiearticle; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2325 (class 0 OID 346290)
-- Dependencies: 200
-- Data for Name: poste; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2358 (class 0 OID 0)
-- Dependencies: 212
-- Name: ref_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('ref_seq', 1, false);

--
-- TOC entry 2359 (class 0 OID 0)
-- Dependencies: 215
-- Name: user_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('user_seq', 1, false);


--
-- TOC entry 2328 (class 0 OID 346311)
-- Dependencies: 203
-- Data for Name: useri; Type: TABLE DATA; Schema: public; Owner: -
--



--
-- TOC entry 2360 (class 0 OID 0)
-- Dependencies: 174
-- Name: useri_iduser_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('useri_iduser_seq', 1, true);


--
-- TOC entry 2361 (class 0 OID 0)
-- Dependencies: 213
-- Name: useri_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('useri_seq', 1, false);


--
-- TOC entry 2362 (class 0 OID 0)
-- Dependencies: 214
-- Name: userrole_seq; Type: SEQUENCE SET; Schema: public; Owner: -
--

SELECT pg_catalog.setval('userrole_seq', 1, false);

