-- Exercice 2.1.2 – Ecrire une requête pour présenter, pour chaque étudiant, le nom de l’étudiant,
-- la date de naissance, le login et le résultat pour l’année de l’ensemble des étudiants.

SELECT "last_name", "birth_date", "login", "year_result"
FROM "student";

-- Exercice 2.1.3 – Ecrire une requête pour présenter, pour chaque étudiant, son nom complet
-- (nom et prénom séparés par un espace), son id et sa date de naissance.

SELECT CONCAT("last_name", ' ', "first_name") AS full_name, "student_id", "birth_date"
FROM "student";

-- Exercice 2.1.4 – Ecrire une requête pour présenter, pour chaque étudiant, dans une seule
-- colonne (nommée « Info Étudiant ») l’ensemble des données relatives à un étudiant séparées par
-- le symbole « | ». Sous SQL Server, il est nécessaire d’avoir recours à la fonction de conversion
-- CONVERT(type, champs).

SELECT CONCAT_WS(' | ', "last_name", "first_name", "birth_date", "login", "year_result") AS "Info Étudiant"
FROM "student";

-- Exercice 2.2.1 – Ecrire une requête pour présenter le login et le résultat de tous les étudiants
-- ayant obtenu un résultat annuel supérieur à 16

SELECT "login", "year_result"
FROM "student"
WHERE "year_result" > 16;

-- Exercice 2.2.2 – Ecrire une requête pour présenter le nom et l’id de section des étudiants dont
-- le prénom est Georges

SELECT "last_name", "section_id"
FROM "student"
WHERE "first_name" LIKE 'Georges';

-- Exercice 2.2.3 – Ecrire une requête pour présenter le nom et le résultat annuel de tous les
-- étudiants ayant obtenu un résultat annuel compris entre 12 et 16

SELECT "last_name", "year_result"
FROM "student"
WHERE "year_result" BETWEEN 12 AND 16;

-- Exercice 2.2.4 – Ecrire une requête pour présenter le nom, l’id de section et le résultat annuel
-- de tous les étudiants qui ne font pas partie des sections 1010, 1020 et 1110

SELECT "last_name", "section_id", "year_result"
FROM "student"
WHERE "section_id" NOT IN (1010, 1020, 1110);

-- Exercice 2.2.5 – Ecrire une requête pour présenter le nom et l’id de section de tous les
-- étudiants qui ont un nom de famille qui termine par « r »

SELECT "last_name", "section_id"
FROM "student"
WHERE "last_name" LIKE '%r';

-- Exercice 2.2.6 – Ecrire une requête pour présenter le nom et le résultat annuel de tous les
-- étudiants qui ont un nom de famille pour lequel la troisième lettre est un « n » et qui ont obtenu
-- un résultat annuel supérieur à 10

SELECT "last_name", "year_result"
FROM "student"
WHERE "last_name" LIKE '__n%' AND "year_result" > 10;

-- Exercice 2.2.7 – Ecrire une requête pour présenter le nom et le résultat annuel classé par
-- résultats annuels décroissants de tous les étudiants qui ont obtenu un résultat annuel inférieur ou
-- égal à 3

SELECT "last_name", "year_result"
FROM "student"
WHERE "year_result" <= 3
ORDER BY "year_result" DESC;

-- Exercice 2.2.8 – Ecrire une requête pour présenter le nom complet (nom et prénom séparés par
-- un espace) et le résultat annuel classé par nom croissant sur le nom de tous les étudiants
-- appartenant à la section 1010

SELECT CONCAT("last_name", ' ', "first_name") AS full_name, "year_result"
FROM "student"
WHERE "section_id" = 1010
ORDER BY "last_name" ASC;

-- Exercice 2.2.9 – Ecrire une requête pour présenter le nom, l’id de section et le résultat annuel
-- classé par ordre croissant sur la section de tous les étudiants appartenant aux sections 1010 et
-- 1020 ayant un résultat annuel qui n’est pas compris entre 12 et 18

SELECT "last_name", "section_id", "year_result"
FROM "student"
WHERE "section_id" IN (1010, 1020) AND "year_result" NOT BETWEEN 12 AND 18;

-- Exercice 2.2.10 – Ecrire une requête pour présenter le nom, l’id de section et le résultat annuel
-- sur 100 (nommer la colonne « Résultat sur 100 ») classé par ordre décroissant du résultat de tous
-- les étudiants appartenant aux sections commençant par 13 et ayant un résultat annuel sur 100
-- inférieur ou égal à 60

SELECT "last_name", "section_id", "year_result" * 5 AS "Résultat sur 100"
FROM "student"
WHERE CAST("section_id" AS TEXT) LIKE '13%' AND "year_result" <= 60
ORDER BY "Résultat sur 100" DESC;

