
CREATE SEQUENCE public.status_demande_reservation_id_seq;

CREATE TABLE public.status_demande_reservation (
                id INTEGER NOT NULL DEFAULT nextval('public.status_demande_reservation_id_seq'),
                status VARCHAR NOT NULL,
                CONSTRAINT status_demande_reservation_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.status_demande_reservation_id_seq OWNED BY public.status_demande_reservation.id;

CREATE SEQUENCE public.cotation_id_seq;

CREATE TABLE public.cotation (
                id INTEGER NOT NULL DEFAULT nextval('public.cotation_id_seq'),
                cotation VARCHAR(2) NOT NULL,
                CONSTRAINT cotation_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.cotation_id_seq OWNED BY public.cotation.id;

CREATE SEQUENCE public.status_commentaire_id_seq;

CREATE TABLE public.status_commentaire (
                id INTEGER NOT NULL DEFAULT nextval('public.status_commentaire_id_seq'),
                status VARCHAR(25) NOT NULL,
                CONSTRAINT status_commentaire_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.status_commentaire_id_seq OWNED BY public.status_commentaire.id;

CREATE SEQUENCE public.role_id_seq;

CREATE TABLE public.role (
                id INTEGER NOT NULL DEFAULT nextval('public.role_id_seq'),
                role VARCHAR(25) NOT NULL,
                CONSTRAINT role_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.role_id_seq OWNED BY public.role.id;

CREATE SEQUENCE public.tag_id_seq;

CREATE TABLE public.tag (
                id INTEGER NOT NULL DEFAULT nextval('public.tag_id_seq'),
                tag VARCHAR(50) NOT NULL,
                CONSTRAINT tag_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.tag_id_seq OWNED BY public.tag.id;

CREATE SEQUENCE public.departement_id_seq;

CREATE TABLE public.departement (
                id INTEGER NOT NULL DEFAULT nextval('public.departement_id_seq'),
                nom VARCHAR(50) NOT NULL,
                code VARCHAR(3) NOT NULL,
                CONSTRAINT departement_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.departement_id_seq OWNED BY public.departement.id;

CREATE SEQUENCE public.utilisateur_id_seq;

CREATE TABLE public.utilisateur (
                id INTEGER NOT NULL DEFAULT nextval('public.utilisateur_id_seq'),
                nom VARCHAR(50) NOT NULL,
                email VARCHAR(100) NOT NULL,
                password VARCHAR(100) NOT NULL,
                role_id INTEGER NOT NULL,
                CONSTRAINT utilisateur_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.utilisateur_id_seq OWNED BY public.utilisateur.id;

CREATE UNIQUE INDEX utilisateur_idx
 ON public.utilisateur
 ( email, nom );

CREATE SEQUENCE public.site_id_seq;

CREATE TABLE public.site (
                id INTEGER NOT NULL DEFAULT nextval('public.site_id_seq'),
                nom VARCHAR(100) NOT NULL,
                description VARCHAR(1000) NOT NULL,
                departement_id INTEGER NOT NULL,
                tag_id INTEGER NOT NULL,
                CONSTRAINT site_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.site_id_seq OWNED BY public.site.id;

CREATE UNIQUE INDEX site_idx
 ON public.site
 ( nom );

CREATE SEQUENCE public.commentaire_id_seq;

CREATE TABLE public.commentaire (
                id INTEGER NOT NULL DEFAULT nextval('public.commentaire_id_seq'),
                texte VARCHAR(500) NOT NULL,
                date TIMESTAMP NOT NULL,
                status_id INTEGER NOT NULL,
                utilisateur_id INTEGER NOT NULL,
                site_id INTEGER NOT NULL,
                CONSTRAINT commentaire_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.commentaire_id_seq OWNED BY public.commentaire.id;

CREATE SEQUENCE public.secteur_id_seq;

CREATE TABLE public.secteur (
                id INTEGER NOT NULL DEFAULT nextval('public.secteur_id_seq'),
                nom VARCHAR(100) NOT NULL,
                description VARCHAR(250) NOT NULL,
                site_id INTEGER NOT NULL,
                CONSTRAINT secteur_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.secteur_id_seq OWNED BY public.secteur.id;

CREATE UNIQUE INDEX secteur_idx
 ON public.secteur
 ( nom );

CREATE SEQUENCE public.voie_id_seq;

CREATE TABLE public.voie (
                id INTEGER NOT NULL DEFAULT nextval('public.voie_id_seq'),
                numero INTEGER NOT NULL,
                nom VARCHAR(250) NOT NULL,
                secteur_id INTEGER NOT NULL,
                CONSTRAINT voie_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.voie_id_seq OWNED BY public.voie.id;

CREATE SEQUENCE public.longueur_id_seq;

CREATE TABLE public.longueur (
                id INTEGER NOT NULL DEFAULT nextval('public.longueur_id_seq'),
                numero INTEGER NOT NULL,
                cotation_id INTEGER NOT NULL,
                voie_id INTEGER NOT NULL,
                CONSTRAINT longueur_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.longueur_id_seq OWNED BY public.longueur.id;

CREATE SEQUENCE public.topo_id_seq;

CREATE TABLE public.topo (
                id INTEGER NOT NULL DEFAULT nextval('public.topo_id_seq'),
                nom VARCHAR(100) NOT NULL,
                description VARCHAR(250) NOT NULL,
                date_parution DATE NOT NULL,
                site_id INTEGER NOT NULL,
                CONSTRAINT topo_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.topo_id_seq OWNED BY public.topo.id;

CREATE TABLE public.possesseur_topo (
                topo_id INTEGER NOT NULL,
                utilisateur_id INTEGER NOT NULL,
                disponible BOOLEAN NOT NULL,
                CONSTRAINT possesseur_topo_pk PRIMARY KEY (topo_id, utilisateur_id)
);


CREATE SEQUENCE public.reservation_topo_id_seq;

CREATE TABLE public.reservation_topo (
                id INTEGER NOT NULL DEFAULT nextval('public.reservation_topo_id_seq'),
                reservation_topo_id INTEGER NOT NULL,
                possesseur_id INTEGER NOT NULL,
                demandeur_id INTEGER NOT NULL,
                status_id INTEGER NOT NULL,
                CONSTRAINT reservation_topo_pk PRIMARY KEY (id)
);


ALTER SEQUENCE public.reservation_topo_id_seq OWNED BY public.reservation_topo.id;

ALTER TABLE public.reservation_topo ADD CONSTRAINT status_demande_reservation_reservation_topo_fk
FOREIGN KEY (status_id)
REFERENCES public.status_demande_reservation (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.longueur ADD CONSTRAINT cotation_longueur_fk
FOREIGN KEY (cotation_id)
REFERENCES public.cotation (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commentaire ADD CONSTRAINT status_commentaire_fk
FOREIGN KEY (status_id)
REFERENCES public.status_commentaire (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.utilisateur ADD CONSTRAINT role_utilisateur_fk
FOREIGN KEY (role_id)
REFERENCES public.role (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.site ADD CONSTRAINT tag_site_fk
FOREIGN KEY (tag_id)
REFERENCES public.tag (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.site ADD CONSTRAINT departement_site_fk
FOREIGN KEY (departement_id)
REFERENCES public.departement (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commentaire ADD CONSTRAINT utilisateur_commentaire_fk
FOREIGN KEY (utilisateur_id)
REFERENCES public.utilisateur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.possesseur_topo ADD CONSTRAINT utilisateur_possesseur_topo_fk
FOREIGN KEY (utilisateur_id)
REFERENCES public.utilisateur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.reservation_topo ADD CONSTRAINT utilisateur_reservation_topo_fk
FOREIGN KEY (demandeur_id)
REFERENCES public.utilisateur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.topo ADD CONSTRAINT site_topo_fk
FOREIGN KEY (site_id)
REFERENCES public.site (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.secteur ADD CONSTRAINT site_secteur_fk
FOREIGN KEY (site_id)
REFERENCES public.site (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.commentaire ADD CONSTRAINT site_commentaire_fk
FOREIGN KEY (site_id)
REFERENCES public.site (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.voie ADD CONSTRAINT secteur_voie_fk
FOREIGN KEY (secteur_id)
REFERENCES public.secteur (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.longueur ADD CONSTRAINT voie_longueur_fk
FOREIGN KEY (voie_id)
REFERENCES public.voie (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.possesseur_topo ADD CONSTRAINT topo_possesseur_topo_fk
FOREIGN KEY (topo_id)
REFERENCES public.topo (id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.reservation_topo ADD CONSTRAINT possesseur_topo_reservation_topo_fk
FOREIGN KEY (reservation_topo_id, possesseur_id)
REFERENCES public.possesseur_topo (topo_id, utilisateur_id)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;