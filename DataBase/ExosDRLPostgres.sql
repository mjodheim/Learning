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
WHERE CAST("section_id" AS VARCHAR(10)) LIKE '13%' AND "year_result" <= 60
-- WHERE "section_id"::varchar(10) LIKE '13%' AND "year_result" <= 60
ORDER BY "Résultat sur 100" DESC;

-- Exercice 2.3.7 – Donner le résultat annuel moyen pour l’ensemble des étudiants

SELECT AVG("year_result") AS "Résultat annuel moyen"
FROM "student";

-- Exercice 2.3.8 – Donner le plus haut résultat annuel obtenu par un étudiant

SELECT MAX("year_result") AS "Plus haut résultat annuel"
FROM "student";

-- Exercice 2.3.9 – Donner la somme des résultats annuels

SELECT SUM("year_result") AS "Somme des résultats annuels"
FROM "student";

-- Exercice 2.3.10 – Donner le résultat annuel le plus faible

SELECT MIN("year_result") AS "Résultat annuel le plus faible"
FROM "student";

-- Exercice 2.3.11 – Donner le nombre de lignes qui composent la table « STUDENT »

SELECT COUNT(*) AS "Nombre d'étudiants"
FROM "student";

-- Exercice 2.3.12 – Donner la liste des étudiants (login et année de naissance) nés après 1970

SELECT "login",
    EXTRACT(YEAR FROM "birth_date") AS "Année de naissance"
FROM "student"
WHERE EXTRACT(YEAR FROM "birth_date") > 1970;
-- YEAR("birth_date") > 1970 ne fonctionne pas sous PostgreSQL

-- Exercice 2.3.13 – Donner le login et le nom de tous les étudiants qui ont un nom composé d’au
-- moins 8 lettres

SELECT "login",
    "last_name"
FROM "student"
WHERE LENGTH("last_name") >= 8;

-- Exercice 2.3.14 – Donner la liste des étudiants ayant obtenu un résultat annuel supérieur ou
-- égal à 16. La liste présente le nom de l’étudiant en majuscules (nommer la colonne « Nom de
-- Famille ») et le prénom de l’étudiant dans l’ordre décroissant des résultats annuels obtenus

SELECT UPPER("last_name") AS "Nom de Famille",
    "first_name"
FROM "student"
WHERE "year_result" >= 16
ORDER BY "year_result" DESC;

-- Exercice 2.3.15 – Donner un nouveau login à chacun des étudiants ayant obtenu un résultat
-- annuel compris entre 6 et 10. Le login se compose des deux premières lettres du prénom de
-- l’étudiant suivi par les quatre premières lettres de son nom le tout en minuscule. Le résultat
-- reprend pour chaque étudiant, son nom, son prénom l’ancien et le nouveau login (colonne «
-- Nouveau login »)

SELECT "last_name",
    "first_name",  
    "login" AS "Ancien login",
    LOWER(CONCAT(SUBSTRING("first_name", 1, 2), SUBSTRING("last_name", 1, 4))) AS "Nouveau login"
FROM "student"
WHERE "year_result" BETWEEN 6 AND 10;

-- Exercice 2.3.16 – Donner un nouveau login à chacun des étudiants ayant obtenu un résultat
-- annuel égal à 10, 12 ou 14. Le login se compose des trois dernières lettres de son prénom suivi du
-- chiffre obtenu en faisant la différence entre l’année en cours et l’année de leur naissance. Le
-- résultat reprend pour chaque étudiant, son nom, son prénom l’ancien et le nouveau login (colonne
-- « Nouveau login »)

SELECT "last_name",
    "first_name",
    "login" AS "Ancien login",
    LOWER(CONCAT(SUBSTRING("first_name", LENGTH("first_name") - 2, 3), EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM "birth_date"))) AS "Nouveau login"
FROM "student"
WHERE "year_result" IN (10, 12, 14);

-- Exercice 2.3.17 – Donner la liste des étudiants (nom, login, résultat annuel) qui ont un nom
-- commençant par « D », « M » ou « S ». La liste doit présenter les données dans l’ordre croissant
-- des dates de naissance des étudiants

SELECT "last_name",
    "login",
    "year_result"
FROM "student"
WHERE "last_name" ~ '^[DMS]' -- LIKE '[DMS]%' ne fonctionne pas sous PostgreSQL
ORDER BY "birth_date" ASC;

-- Exercice 2.3.18 – Donner la liste des étudiants (nom, login, résultat annuel) qui ont obtenu un
-- résultat impair supérieur à 10. La liste doit être triée du plus grand résultat au plus petit

SELECT "last_name",
    "login",
    "year_result"
FROM "student"
WHERE "year_result" > 10 AND MOD("year_result", 2) != 0
ORDER BY "year_result" DESC;

-- Exercice 2.3.19 – Donner le nombre d’étudiants qui ont au moins 7 lettres dans leur nom de
-- famille

SELECT COUNT(*) AS "Nbre de noms de plus de 7 lettres"
FROM "student"
WHERE LENGTH("last_name") >= 7;

-- Exercice 2.3.20 – Pour chaque étudiant né avant 1955, donner le nom, le résultat annuel et le
-- statut. Le statut prend la valeur « OK » si l’étudiant à obtenu au moins 12 comme résultat annuel
-- et « KO » dans le cas contraire

SELECT "last_name",
    "year_result",
    CASE 
        WHEN "year_result" >= 12 THEN 'OK' ELSE 'KO' 
    END AS "Statut"
FROM "student"
WHERE EXTRACT(YEAR FROM "birth_date") < 1955;

-- Exercice 2.3.21 – Donner pour chaque étudiant né entre 1955 et 1965 le nom, le résultat
-- annuel et la catégorie à laquelle il appartient. La catégorie est fonction du résultat annuel obtenu :
-- un résultat inférieur à 10 appartient à la catégorie « inférieure », un résultat égal à 10 appartient à
-- la catégorie « neutre », un résultat autre appartient à la catégorie « supérieure »

SELECT "last_name",
    "year_result",
    CASE 
        WHEN "year_result" < 10 THEN 'inférieure'
        WHEN "year_result" = 10 THEN 'neutre'
        ELSE 'supérieure' 
    END AS "Catégorie"
FROM "student"
WHERE EXTRACT(YEAR FROM "birth_date") BETWEEN 1955 AND 1965;


-- Avec la fonction SIGN, on peut simplifier la requête précédente en utilisant le résultat de la soustraction
-- entre le résultat annuel et 10. Le résultat de cette soustraction sera négatif si le résultat annuel est inférieur
-- à 10, nul si le résultat annuel est égal à 10 et positif si le résultat annuel est supérieur à 10. On peut donc
-- utiliser la fonction SIGN pour déterminer la catégorie de chaque étudiant.

SELECT "last_name",
    "year_result",
    CASE SIGN("year_result" - 10)
        WHEN -1 THEN 'inférieure'
        WHEN 0 THEN 'neutre'
        ELSE 'supérieure' 
    END AS "Catégorie"
FROM "student"
WHERE EXTRACT(YEAR FROM "birth_date") BETWEEN 1955 AND 1965;

-- Exercice 2.3.22 – Donner pour chaque étudiant né entre 1975 et 1985, son nom, son résultat
-- annuel et sa date de naissance sous la forme: jours en chiffre, mois en lettre et années en quatre
-- chiffres (ex : 11 juin 2005)

-- Si la locale le permet on peut : SET lc_time = 'fr_FR.utf8' pour avoir le mois en français. 
-- -> Pas recommandé à faire depuis la db, plutôt dans le front.
SELECT "last_name",
    "year_result",
    TO_CHAR("birth_date", 'DD month YYYY') AS "Date de naissance"
FROM "student"
WHERE EXTRACT(YEAR FROM "birth_date") BETWEEN 1975 AND 1985;

-- Exercice 2.3.23 – Donner pour chaque étudiant né en dehors des mois d’hiver et ayant obtenu
-- un résultat inférieur à 7, son nom, le mois de sa naissance (en chiffre) son résultat annuel et son
-- résultat annuel corrigé (« Nouveau résultat ») tel que si le résultat annuel est égal à 4, le valeur
-- proposée est « NULL »

SELECT "last_name", 
    EXTRACT(MONTH FROM "birth_date") AS "Mois de naissance",
    "year_result",
    NULLIF("year_result", 4) AS "Nouveau résultat"
FROM "student"
WHERE EXTRACT(MONTH FROM "birth_date") NOT IN (12, 1, 2, 3) 
    AND "year_result" < 7;

-- Exercice 2.4.7 – Donner pour chaque section, le résultat maximum (dans une colonne appelée
-- « Résultat maximum ») obtenu par les étudiants

SELECT "section_id",
    MAX("year_result") AS "Résultat maximum"
FROM "student"
GROUP BY "section_id";

-- Exercice 2.4.8 – Donner pour toutes les sections commençant par 10, le résultat annuel moyen
-- PRÉCIS (dans une colonne appelée « Moyenne ») obtenu par les étudiants

SELECT "section_id",
    AVG("year_result") AS "Moyenne"
FROM "student"
WHERE CAST("section_id" AS VARCHAR(10)) LIKE '10%'
GROUP BY "section_id";

-- Exercice 2.4.9 – Donner le résultat moyen (dans une colonne appelée « Moyenne ») et le mois
-- en chiffre (dans une colonne appelée « Mois de naissance ») pour les étudiants nés le même mois
-- entre 1970 et 1985

SELECT EXTRACT(MONTH FROM "birth_date") AS "Mois de naissance",
    CAST(AVG("year_result") AS INTEGER) AS "Moyenne"
FROM "student"
WHERE EXTRACT(YEAR FROM "birth_date") BETWEEN 1970 AND 1985
GROUP BY EXTRACT(MONTH FROM "birth_date");

-- Exercice 2.4.10 – Donner pour toutes les sections qui comptent plus de 3 étudiants, la
-- moyenne PRÉCISE des résultats annuels (dans une colonne appelée « Moyenne »)

SELECT "section_id",
    AVG("year_result") AS "Moyenne"
FROM "student"
GROUP BY "section_id"
HAVING COUNT(*) > 3;

-- Exercice 2.4.11 – Donner le résultat maximum obtenu par les étudiants appartenant aux
-- sections dont le résultat moyen est supérieur à 8

SELECT "section_id",
    CAST(AVG("year_result") AS INTEGER) AS "Moyenne",
    MAX("year_result") AS "Résultat maximum"
FROM "student"
GROUP BY "section_id"
HAVING AVG("year_result") > 8;