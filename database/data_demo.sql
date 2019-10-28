--Ajout des rôles utilisateurs
INSERT INTO public."role"
("role")
values
('admin'),
('user');

--Ajout des département
INSERT INTO public.departement
(nom, code)
values
('Ain', '1'),
('Aisne', '2'),
('Allier', '3'),
('Alpes-de-Haute-Provence', '4'),
('Hautes-Alpes', '5'),
('Alpes-Maritimes', '6'),
('Ardèche', '7'),
('Ardennes', '8'),
('Ariège', '9'),
('Aube', '10'),
('Aude', '11'),
('Aveyron', '12'),
('Bouches-du-Rhône', '13'),
('Calvados', '14'),
('Cantal', '15'),
('Charente', '16'),
('Charente-Maritime', '17'),
('Cher', '18'),
('Corrèze', '19'),
('Corse-du-Sud', '2A'),
('Haute-Corse', '2B'),
('Côte-d''Or', '21'),
('Côtes d''Armor', '22'),
('Creuse', '23'),
('Dordogne', '24'),
('Doubs', '25'),
('Drôme', '26'),
('Eure', '27'),
('Eure-et-Loir', '28'),
('Finistère', '29'),
('Gard', '30'),
('Haute-Garonne', '31'),
('Gers', '32'),
('Gironde', '33'),
('Hérault', '34'),
('Ille-et-Vilaine', '35'),
('Indre', '36'),
('Indre-et-Loire', '37'),
('Isère', '38'),
('Jura', '39'),
('Landes', '40'),
('Loir-et-Cher', '41'),
('Loire', '42'),
('Haute-Loire', '43'),
('Loire-Atlantique', '44'),
('Loiret', '45'),
('Lot', '46'),
('Lot-et-Garonne', '47'),
('Lozère', '48'),
('Maine-et-Loire', '49'),
('Manche', '50'),
('Marne', '51'),
('Haute-Marne', '52'),
('Mayenne', '53'),
('Meurthe-et-Moselle', '54'),
('Meuse', '55'),
('Morbihan', '56'),
('Moselle', '57'),
('Nièvre', '58'),
('Nord', '59'),
('Oise', '60'),
('Orne', '61'),
('Pas-de-Calais', '62'),
('Puy-de-Dôme', '63'),
('Pyrénées-Atlantiques', '64'),
('Hautes-Pyrénées', '65'),
('Pyrénées-Orientales', '66'),
('Bas-Rhin', '67'),
('Haut-Rhin', '68'),
('Rhône', '69'),
('Haute-Saône', '70'),
('Saône-et-Loire', '71'),
('Sarthe', '72'),
('Savoie', '73'),
('Haute-Savoie', '74'),
('Paris', '75'),
('Seine-Maritime', '76'),
('Seine-et-Marne', '77'),
('Yvelines', '78'),
('Deux-Sèvres', '79'),
('Somme', '80'),
('Tarn', '81'),
('Tarn-et-Garonne', '82'),
('Var', '83'),
('Vaucluse', '84'),
('Vandée', '85'),
('Vienne', '86'),
('Haute-Vienne', '87'),
('Vosges', '88'),
('Yonne', '89'),
('Territoire de Belfort', '90'),
('Essonne', '91'),
('Hauts-de-Seine', '92'),
('Seine-St-Denis', '93'),
('Val-de-Marne', '94'),
('Val-D''Oise', '95'),
('Guadeloupe', '971'),
('Martinique', '972'),
('Guyane', '973'),
('La Réunion', '974'),
('Mayotte', '976');

--Ajout des cotations
INSERT INTO public.cotation
(cotation)
values
('3a'),
('3b'),
('3c'),
('4a'),
('4b'),
('4c'),
('5a'),
('5b'),
('5c'),
('6a'),
('6b'),
('6c'),
('7a'),
('7b'),
('7c'),
('8a'),
('8b'),
('8c'),
('9a'),
('9b'),
('9c');

--Ajout tag site
INSERT INTO public.tag
(tag)
values
('none'),
('officiel les amis de l''escalade');

--Ajout Sites
INSERT INTO public.site
(nom, description, departement_id, tag_id, picture)
values
-- id 1
('Rocher de la fraîche', 'Belle falaise situé en plein coeur du village. Altitude 1500 m. Praticable de juin à octobre.', 74, 1, null),

-- id 2
('Falaise du lac Besson', 'Cette longue barre alterne des voies en plusieurs longueurs, des voies plus courtes et sportives et des secteurs enfants. Dans l''ensemble, il s''agit de dalles et de murs assez raides munis de prises franches. Compétences et équipements appropriés (port du casque recommandé car les chutes de pierres sont courantes).', 38, 1, null),

-- id 3
('Les gorges du loup', 'Le site compte 70 voies, de 6a à 9a (en incluant les variantes et connexions). L''escalade est un mélange de colonnettes infernales, de bidoigts et de prises taillées, où tout va très vite, en l’absence de repos dignes de ce nom. Les voies déversantes et très physiques permettent d’enchaîner les passages car on a plus affaire à de la continuité qu''à des problèmes de bloc. L''autre alternative à l''enchaînement, c''est la chute, mais les surplombs sont très bienveillants et le trou dans l''air n''a jamais occasionné trop d''égratignures. L''équipement est bon et la majorité des dégaines restent à demeure. Le pied de falaise est plutôt convivial.', 06, 1, null);

--Ajout Secteurs
INSERT INTO public.secteur
(nom, description, site_id)
values
-- id 1
('Grande Falaise', '', 1),
-- id 2
('Dièbre Bleu', '', 1),
-- id 3
('Haute Tension', '', 1),
-- id 4
('L''Anneau', '', 1),
-- id 5
('Cocaine', '', 2),
-- id 6
('La menace', '', 2),
-- id 7
('Cayenne', '', 3),
-- id 8
('Les balcons', '', 3);



--Ajout de voies
INSERT INTO public.voie
(numero, nom, secteur_id)
values
--Secteur id 1 - Grande Falaise
-- id 1
(2,'La Belle en cuisse', 1),
-- id 2
(12, 'Macadam cowboy', 1),
--Secteur id 2 - Dièbre Bleu
-- id 3
(3, 'La Valise', 2),
-- id 4
(10, 'Herminator', 2),
--Secteur id 3 - Dièbre Bleu
--id 5
(2, 'No Man’s land', 3),
-- id 6
(6, 'Jolie Gazelle', 3),
--Secteur id 4 - L'Anneau
-- id 7
(3, 'Les barbares', 4),
-- id 8
(9, 'Que demande le peuple', 4),
--Secteur id 5 - Cocaine
-- id 9
(3, 'Amy dalle', 5),
-- id 10
(9, 'La centrale', 5),
--Secteur id 6 - La menace
-- id 11
(14, 'Coeur de pierre', 6),
-- id 12
(16, 'Poumon d''acier', 6),
-- id 13
(14, 'Zitounette', 7),
-- id 14
(16, 'Welcome to the jungle', 7),
-- id 15
(2, 'Monsaigneur', 8),
-- id 16
(8, 'Full metal casquette', 8);




--Ajout des longueurs
INSERT INTO public.longueur
(numero, cotation_id, voie_id)
values
-- La Belle en cuisse
(1, 10, 1),
-- Macadam Cowboy
(1, 10, 2),
(2, 9, 2),
-- La Valise
(1, 10, 3),
-- Herminator
(1, 10, 4),
-- No man's land
(1, 14, 5),
-- Jolie Gazelle
(1,9, 6),
-- Les barbares
(1, 10, 7),
-- Que demande le peuple
(1, 8, 8),
-- amy dalle
(1, 8, 9),
-- la centrale
(1, 8, 10),
(2, 10, 10),
-- coeur de pierre
(1, 10, 11),
(2, 10, 11),
-- poumon d'acier
(1, 10, 12),
-- Zitounette
(1, 14, 13),
-- Welcome to the jungle
(4, 16, 14),
-- Monsaigneur
(4, 13, 15),
-- Full metal casquette
(4, 14, 16);

--ajout des utilisateurs
INSERT INTO public.utilisateur
(nom, email, "password", role_id)
values
('Christophe', 'christophe@gmail.com', '$2a$10$xhkmsSS4o4ETUCDjhYyqvuC9j7Q.bjYUmejU3HJEnFtCqn5pc88xa', 1),
('Pascale', 'pascale@gmail.com', '$2a$10$xhkmsSS4o4ETUCDjhYyqvuC9j7Q.bjYUmejU3HJEnFtCqn5pc88xa', 1),
('Alice', 'alice@gmail.com', '$2a$10$xhkmsSS4o4ETUCDjhYyqvuC9j7Q.bjYUmejU3HJEnFtCqn5pc88xa', 1),
('Cécile', 'cecile@gmail.com', '$2a$10$xhkmsSS4o4ETUCDjhYyqvuC9j7Q.bjYUmejU3HJEnFtCqn5pc88xa', 1),
('Charles', 'cathala@gmail.com', '$2a$10$xhkmsSS4o4ETUCDjhYyqvuC9j7Q.bjYUmejU3HJEnFtCqn5pc88xa', 2);

--ajout status commentaire
INSERT INTO public.status_commentaire
(status)
values
('non modifié'),
('modifié'),
('supprimé');

--Ajout des commentaires
INSERT INTO public.commentaire
(texte, "date", status_id, utilisateur_id, site_id)
VALUES('Bon site pour debuter', current_timestamp(0), 1, 1, 1);

--Ajout des topos
INSERT INTO public.topo
(nom, description, date_parution, site_id)
values
('Topo du rocher de la Fraîche', 'Receuil des voies des 4 secteurs du site, contient aussi des infos pratiques.', '12-03-1992', 1),
('Topo des falaises du lac besson', 'Receuil des voies des 2 secteurs du site, contient aussi des infos pratiques.', '20-03-1988', 2),
('Topo des gorges du loup', 'Receuil des voies des 2 secteurs du site, contient aussi des infos pratiques.', '16-12-1996', 3);

--Ajout des topos possédés
INSERT INTO public.possesseur_topo
(topo_id, utilisateur_id, disponible, shared)
values
(1, 1, true, false),
(2, 1, true, false),
(3, 1, true, false),
(2, 2, true, false),
(3, 2, true, false),
(3, 3, true, false);

--Ajout des status de réservation
INSERT INTO public.status_demande_reservation
(status)
values
('En attente'),
('Acceptée - Topo en prêt'),
('Refusée'),
('Annulée'),
('Terminée - Topo retourné');

