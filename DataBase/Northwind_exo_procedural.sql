CREATE TABLE MyTable (
    id INT UNIQUE,
    message TEXT
)

-- 1.3 À l'aide de l'instruction RAISE NOTICE, afficher le message « Bonjour, et bienvenue dans le cours
-- de PL/pgSQL ! »
-- Syntaxe : DO $$ BEGIN RAISE NOTICE 'votre message'; END; $$ LANGUAGE plpgsql;

DO $$
    BEGIN
        RAISE NOTICE 'Bonjour, et bienvenue dans le cours de PL/pgSQL !';
    END;
$$ LANGUAGE plpgsql;

-- 1.4 Afficher le même message qu'en 1.3, mais en stockant d'abord le message dans une table
-- temporaire (CREATE TEMP TABLE), puis en le lisant via un SELECT.

CREATE TEMP TABLE Message (
    message TEXT
);

INSERT INTO Message VALUES (
    'Bonjour, et bienvenue dans le cours de PL/pgSQL !'
)

DO $$
    DECLARE 
        msg TEXT;
    BEGIN
        SELECT message INTO msg 
        FROM Message;

        RAISE NOTICE '%',msg;
    END;
$$ LANGUAGE plpgsql;

-- 1.5 Insérer une ligne de valeurs dans la table créée en 1.2. Insérer une seconde ligne identique. Un
-- message d'erreur doit apparaître. Sur quelle ligne se situe l'erreur ? Comprenez-vous le message
-- d'erreur PostgreSQL ?
-- En PostgreSQL, les erreurs indiquent le code SQLSTATE (ex: 23505 = unique_violation).

INSERT INTO MyTable (id, message) VALUES
(1,'message'),
(1,'message');

-- ERREUR :
-- duplicate key value violates unique constraint "mytable_id_key"
-- DETAIL: Key (id)=(1) already exists.

-- 1.6 À l'aide d'un SELECT simple, afficher les données de la table créée en 1.2. Stocker ensuite la
-- requête SQL dans une variable TEXT et l'exécuter via EXECUTE dans un bloc DO.
-- DO $$ DECLARE v_sql TEXT; BEGIN v_sql := 'SELECT * FROM ma_table'; EXECUTE v_sql; END; $$
-- LANGUAGE plpgsql;

DO $$
    DECLARE
        v_sql TEXT;
    BEGIN
        v_sql := 'SELECT * FROM MyTable;';
        EXECUTE v_sql;
    END;
$$ LANGUAGE plpgsql;

-- 2.1 À l'aide de RAISE NOTICE, affichez le message « Le PL/pgSQL, c'est bien pratique ! »

DO $$
    BEGIN
        RAISE NOTICE 'Le PL/pgSQL, c''est bien pratique !';
    END;
$$ LANGUAGE plpgsql;

-- 2.2 Créer une variable de type TEXT contenant la phrase du point 2.1. Afficher le contenu de la
-- variable via RAISE NOTICE.

DO $$
    DECLARE
        v_phrase TEXT := 'Le PL/pgSQL, c''est bien pratique !';
    BEGIN
        RAISE NOTICE '%', v_phrase;
    END;
$$ LANGUAGE plpgsql;

-- 2.3 Déclarer une variable qui contiendra le nombre d'employés de la table employees de Northwind.
-- Afficher le contenu de cette variable.
-- Utiliser SELECT COUNT(*) INTO v_nb FROM employees;

DO $$
    DECLARE
        v_nb INT;
    BEGIN
        SELECT COUNT(*) INTO v_nb
        FROM employees;

        RAISE NOTICE 'Nombre d''employés: %', v_nb;
    END;
$$ LANGUAGE plpgsql;

-- 2.4 Déclarer une variable v_prenom du même type que la colonne first_name de la table employees.
-- Remplir cette variable avec le prénom de l'employée nommée Davolio et afficher son contenu.

DO $$
    DECLARE
        v_prenom employees.first_name%type;
    BEGIN
        SELECT first_name INTO v_prenom
        FROM employees
        WHERE lower(last_name) = lower('Davolio');

        RAISE NOTICE 'Prénom de Mme Davolio: %', v_prenom;
    END;
$$ LANGUAGE plpgsql;

-- 2.5 Soit le code suivant. Que doit-il donner ? Fonctionne-t-il ? Si non, pourquoi ?
-- DO $$
-- DECLARE
-- x INT;
-- y INT;
-- z VARCHAR;
-- BEGIN
-- z := x + y;
-- RAISE NOTICE '%', z;
-- END;
-- $$ LANGUAGE plpgsql;

-- Affichera NULL -> x et y ne sont pas convertis donc z ne change pas, reste à la valeur null

-- 2.6 Et dans ce cas-ci ?
-- DO $$
-- DECLARE
-- x VARCHAR;
-- y VARCHAR(50);
-- z INT;
-- BEGIN
-- x := 'La valeur de : ';
-- y := x || z::TEXT || ' est ';
-- RAISE NOTICE '% %', y, z;
-- END;
-- $$ LANGUAGE plpgsql;

-- null null -> y n'est pas de type TEXT, sa valeur ne changera donc pas non plus

-- 2.7 Créer une variable v_date_du_jour de type TIMESTAMP avec la valeur de la date et heure
-- courante. Afficher cette date.

DO $$
    DECLARE
        v_date_du_jour TIMESTAMP := CURRENT_DATE;
    BEGIN
        RAISE NOTICE 'Date du jour: %', TO_CHAR(v_date_du_jour,'DD/MM/YYYY');
    END;
$$ LANGUAGE plpgsql;

-- 2.8 À l'aide de plusieurs variables, afficher la phrase : « M. [Nom] [Prénom] est l'employé numéro [ID],
-- a été engagé le [date d'embauche] et est un [homme/femme] ». Les données proviennent de la table
-- employees de Northwind.
-- Pour le genre : la table employees ne contient pas cette info, utilisez la colonne title_of_courtesy
-- (Mr./Ms./Mrs./Dr.) pour déduire le genre.

DO $$
    DECLARE
        v_sexe VARCHAR(10);
        rec RECORD;
    BEGIN
        FOR rec IN
            SELECT 
                last_name,
                first_name,
                employee_id,
                hire_date,
                title_of_courtesy
            FROM employees
        LOOP
            IF rec.title_of_courtesy like '_r.' THEN
                v_sexe := 'un homme';
            ELSE
                v_sexe := 'une femme';
            END IF;

            RAISE NOTICE '% % % est l''employé numéro %, a été engagé le % et est %',
            rec.title_of_courtesy, rec.last_name, rec.first_name, rec.employee_id, TO_CHAR(rec.hire_date,'DD/MM/YYYY'), v_sexe;
        END LOOP;
    END;
$$ LANGUAGE plpgsql;

-- 2.9 Créer une variable entière contenant votre âge. Créer une seconde variable de type TEXT
-- contenant votre nom. Afficher la concaténation de ces deux variables. La conversion pose-t-elle
-- problème ? Utilisez ::TEXT ou CAST().

DO $$
    DECLARE
        v_age INT := 40;
        v_nom TEXT := 'Mets';
    BEGIN
        RAISE NOTICE '%', CONCAT(v_nom,v_age::TEXT);
    END;
$$ LANGUAGE plpgsql;

-- 2.10 Générer 3 variables entières. Afficher leur somme dans une table temporaire avec une colonne
-- nommée Résultat.

CREATE TEMP TABLE Result (
    "Résultat" INT
);

DO $$
    DECLARE
        v_a INT;
        v_b INT;
        v_c INT; -- Les 3 variables sont générées mais pas initialisées => valent NULL
    BEGIN
        INSERT INTO Result VALUES(
            v_a + v_b + v_c
        );
    END;
$$ LANGUAGE plpgsql;

SELECT * FROM Result;

DROP TABLE Result;

-- 2.11 Créer des variables pour contenir les colonnes employee_id, title et birth_date de la table
-- employees. Afficher leur concaténation. Cela affiche-t-il le résultat attendu ? Comment résoudre le
-- problème de conversion de types ?
-- birth_date est un DATE — utilisez TO_CHAR(birth_date, 'DD/MM/YYYY') ou birth_date::TEXT.

DO $$
    DECLARE
        v_id employees.employee_id%type;
        v_title employees.title%type;
        v_birth_date employees.birth_date%type;
        rec RECORD;
    BEGIN
        FOR rec IN
            SELECT
                employee_id,
                title,
                birth_date
            FROM employees
        LOOP
            RAISE NOTICE '%', CONCAT_WS('_', rec.employee_id::TEXT, rec.title::TEXT, TO_CHAR(rec.birth_date,'DD/MM/YYYY')::TEXT);
        END LOOP;
    END;
$$ LANGUAGE plpgsql;

-- 2.12 Déclarer une table temporaire qui contiendra les colonnes title_of_courtesy, first_name et
-- last_name de la table employees. Insérer les données. Afficher l'ensemble. Se déconnecter et se
-- reconnecter à PostgreSQL : la table temporaire existe-t-elle toujours ? Supprimer la table à la fin.

-- Création de la table temporaire basée sur la table employees et sans données
CREATE TEMP TABLE MaTable AS
    SELECT
        title_of_courtesy,
        first_name,
        last_name
    FROM employees
WITH NO DATA;

DO $$
    DECLARE
        rec RECORD;
    BEGIN
        FOR rec IN
           SELECT
                title_of_courtesy,
                first_name,
                last_name
            FROM employees
        LOOP
            INSERT INTO MaTable VALUES (
                rec.title_of_courtesy,
                rec.first_name,
                rec.last_name
            );
        END LOOP;
    END;
$$ LANGUAGE plpgsql;

SELECT * FROM MaTable;

DROP TABLE MaTable;

-- 2.13 Déclarer une table temporaire avec les colonnes titre_job, date_embauche, heures_vacances
-- (simulé par 0), ville. Remplir cette table avec les données des employés dont le titre (title) contient
-- 'Sales'. Afficher le contenu.
-- La table Northwind employees n'a pas de colonne vacation_hours comme AdventureWorks. Utilisez la colonne
-- notes ou simulez avec une valeur fixe.

CREATE TEMP TABLE MaTable AS
    SELECT
        title AS titre_job,
        TO_CHAR(hire_date,'DD/MM/YYYY') AS date_embauche,
        0::INT AS heures_vacances,
        city AS ville
    FROM employees
WITH NO DATA;

DO $$
    DECLARE
        rec RECORD;
    BEGIN
        FOR rec IN
           SELECT
                title,
                hire_date,
                city
            FROM employees
            WHERE lower(title) LIKE lower('%sales%')
        LOOP
            INSERT INTO MaTable (titre_job, date_embauche, heures_vacances, ville) VALUES (
                rec.title,
                TO_CHAR(rec.hire_date,'DD/MM/YYYY'),
                0,
                rec.city
            );
        END LOOP;
    END;
$$ LANGUAGE plpgsql;

SELECT * FROM MaTable;

-- 2.14 Afficher maintenant les données de la table temporaire de l'exercice 2.13 dans une nouvelle table
-- temporaire (copie via INSERT INTO … SELECT).

CREATE TEMP TABLE MaTable2 AS
    SELECT * FROM MaTable;

SELECT * FROM MaTable2;

DROP TABLE MaTable, MaTable2;

-- 2.15 Déclarer une table temporaire avec colonnes mois_naissance, nom, prenom, ville. Insérer les
-- données de 5 connaissances. Copier ces données dans une seconde table temporaire. Modifier les 2
-- premières lignes de la première table. Les modifications se reflètent-elles dans la deuxième ? Pourquoi
-- Recréer un nouveau script — les tables existent-elles encore ?

CREATE TEMP TABLE MaTable (
    mois_naissance INT,
    nom VARCHAR(50),
    prenom VARCHAR (50),
    ville VARCHAR(50)
);

INSERT INTO MaTable VALUES
    (8,'riri','duck','canardville'),
    (9,'fifi','duck','canardville'),
    (10,'loulou','duck','canardville'),
    (12,'donald','duck','canardville'),
    (4,'picsou','duck','canardville');

CREATE TEMP TABLE MaTable2 AS
    SELECT * FROM MaTable;

UPDATE MaTable
    SET mois_naissance = 3
    WHERE mois_naissance IN (8,9);

SELECT * FROM MaTable2;

DROP TABLE MaTable, MaTable2;

-- Aucun impact car MaTable2 est une copie simple de MaTable, elle ne copie pas sa référence.

-- 2.16 Quels sont les noms de tous les employés dont le titre contient 'Sales' ? Récupérer leur nom,
-- prénom et titre dans une table temporaire et afficher le résultat.

CREATE TEMP TABLE MaTable AS
    SELECT 
        last_name,
        first_name,
        title
    FROM employees
    WHERE lower(title) LIKE lower('%sales%');

SELECT * FROM MaTable;

-- 3.1 Pour l'employé dont l'employee_id vaut 5 dans la table employees, examinez sa date d'embauche
-- (hire_date) et sa date de naissance (birth_date). Si son ancienneté est de plus de 30 ans, afficher «
-- L'employé 5 est un Senior ». Sinon, signaler qu'il s'agit d'un Junior.
-- n EXTRACT(YEAR FROM AGE(hire_date)) pour calculer l'ancienneté.

DO $$
    DECLARE
        v_hire_date employees.hire_date%TYPE;
        v_birth_date employees.birth_date%TYPE;
        v_anciennete INT;
    BEGIN
        SELECT
            hire_date,
            birth_date
        INTO
            v_hire_date,
            v_birth_date
        FROM employees
        WHERE employee_id = 5;

        v_anciennete := EXTRACT(YEAR FROM AGE(CURRENT_DATE, v_hire_date))::INT;

        RAISE NOTICE 'Date de naissance : % - Date d''embauche : %',
            TO_CHAR(v_birth_date, 'DD/MM/YYYY'),
            TO_CHAR(v_hire_date, 'DD/MM/YYYY');

        IF v_anciennete > 30 THEN
            RAISE NOTICE 'L''employé 5 est un Senior';
        ELSE
            RAISE NOTICE 'L''employé 5 est un Junior';
        END IF;
    END;
$$ LANGUAGE plpgsql;

-- 3.2 S'il existe dans la table customers quelqu'un dont le contact_name contient 'Ana', affichez son
-- nom complet et l'entreprise. Sinon, signaler qu'aucune personne ne porte ce nom.

DO $$
    DECLARE
        rec RECORD;
    BEGIN
        IF EXISTS (
            SELECT 1
            FROM customers
            WHERE lower(contact_name) LIKE lower('%Ana%')
        ) THEN

            FOR rec IN
                SELECT
                    contact_name,
                    company_name
                FROM customers
                WHERE lower(contact_name) LIKE lower('%Ana%')
            LOOP
                RAISE NOTICE '% travaille pour %',
                    rec.contact_name,
                    rec.company_name;
            END LOOP;

        ELSE
            RAISE NOTICE 'Aucune personne ne porte ce nom';
        END IF;
    END;
$$ LANGUAGE plpgsql;

-- 3.3 En Northwind, les employés ont un champ title_of_courtesy (Mr., Ms., Mrs., Dr.). Si le nombre
-- d'employées femmes (Ms./Mrs.) est plus important que le nombre d'hommes (Mr.), afficher dans une
-- table temporaire « Les femmes domineront le monde ! ». Sinon, indiquer « La guerre des sexes n'est
-- pas finie… ».

CREATE TEMP TABLE MaTable (
    message TEXT
);

DO $$
    DECLARE
        v_femmes INT;
        v_hommes INT;
    BEGIN
        SELECT COUNT(*) INTO v_femmes
        FROM employees
        WHERE title_of_courtesy IN ('Ms.', 'Mrs.');

        SELECT COUNT(*) INTO v_hommes
        FROM employees
        WHERE title_of_courtesy = 'Mr.';

        IF v_femmes > v_hommes THEN
            INSERT INTO MaTable VALUES (
                'Les femmes domineront le monde !'
            );
        ELSE
            INSERT INTO MaTable VALUES (
                'La guerre des sexes n''est pas finie…'
            );
        END IF;
    END;
$$ LANGUAGE plpgsql;

SELECT * FROM MaTable;

DROP TABLE MaTable;

-- 3.4 Comparer l'ancienneté des employés dont l'employee_id est 1 et 3. Si l'un a plus d'ancienneté ET
-- plus de commandes traitées que l'autre (via la table orders), signalez-le. Sinon, si l'un a plus
-- d'ancienneté mais moins de commandes, signalez-le différemment. Dans les autres cas, rien à
-- signaler.

DO $$
    DECLARE
        v_hire_1 employees.hire_date%TYPE;
        v_hire_3 employees.hire_date%TYPE;
        v_orders_1 INT;
        v_orders_3 INT;
    BEGIN
        SELECT hire_date INTO v_hire_1
        FROM employees
        WHERE employee_id = 1;

        SELECT hire_date INTO v_hire_3
        FROM employees
        WHERE employee_id = 3;

        SELECT COUNT(*) INTO v_orders_1
        FROM orders
        WHERE employee_id = 1;

        SELECT COUNT(*) INTO v_orders_3
        FROM orders
        WHERE employee_id = 3;

        -- Plus la date d'embauche est ancienne, plus l'employé a d'ancienneté

        IF v_hire_1 < v_hire_3 AND v_orders_1 > v_orders_3 THEN
            RAISE NOTICE 'L''employé 1 a plus d''ancienneté et a traité plus de commandes que l''employé 3';

        ELSIF v_hire_3 < v_hire_1 AND v_orders_3 > v_orders_1 THEN
            RAISE NOTICE 'L''employé 3 a plus d''ancienneté et a traité plus de commandes que l''employé 1';

        ELSIF v_hire_1 < v_hire_3 AND v_orders_1 < v_orders_3 THEN
            RAISE NOTICE 'L''employé 1 a plus d''ancienneté mais a traité moins de commandes que l''employé 3';

        ELSIF v_hire_3 < v_hire_1 AND v_orders_3 < v_orders_1 THEN
            RAISE NOTICE 'L''employé 3 a plus d''ancienneté mais a traité moins de commandes que l''employé 1';

        ELSE
            RAISE NOTICE 'Rien à signaler';
        END IF;
    END;
$$ LANGUAGE plpgsql;

-- 3.5 Afficher dans une table temporaire (colonne statut_employe) le statut d'un employé au hasard
-- selon la date d'embauche : avant 1993 = Président, 1993-1998 = Confirmed, 1998-2003 = Qualified,
-- après 2003 = Junior. Si l'employé est né après 2000, afficher Impossible.
-- n Utilisez RANDOM() * COUNT(*) ou ORDER BY RANDOM() LIMIT 1 pour sélectionner un employé aléatoire.

CREATE TEMP TABLE MaTable (
    statut_employe TEXT
);

DO $$
    DECLARE
        rec RECORD;
        v_statut TEXT;
    BEGIN
        SELECT
            employee_id,
            hire_date,
            birth_date
        INTO rec
        FROM employees
        ORDER BY RANDOM()
        LIMIT 1;

        IF rec.birth_date >= DATE '2000-01-01' THEN
            v_statut := 'Impossible';

        ELSIF rec.hire_date < DATE '1993-01-01' THEN
            v_statut := 'Président';

        ELSIF rec.hire_date < DATE '1998-01-01' THEN
            v_statut := 'Confirmed';

        ELSIF rec.hire_date < DATE '2003-01-01' THEN
            v_statut := 'Qualified';

        ELSE
            v_statut := 'Junior';
        END IF;

        INSERT INTO MaTable VALUES (
            v_statut
        );

        RAISE NOTICE 'Employé sélectionné : %', rec.employee_id;
    END;
$$ LANGUAGE plpgsql;

SELECT * FROM MaTable;

DROP TABLE MaTable;

-- 3.6 En fonction de l'âge de l'employé traité (ex. employee_id = 2), prévenez-nous s'il sera bientôt à la
-- retraite (âge > 60) via une phrase affichée à l'écran. Utilisez une expression CASE pour construire la
-- phrase.
-- n EXTRACT(YEAR FROM AGE(birth_date)) pour calculer l'âge.

DO $$
    DECLARE
        v_age INT;
        v_phrase TEXT;
    BEGIN
        SELECT
            EXTRACT(YEAR FROM AGE(CURRENT_DATE, birth_date))::INT
        INTO v_age
        FROM employees
        WHERE employee_id = 2;

        v_phrase := CASE
            WHEN v_age > 60
                THEN 'L''employé 2 sera bientôt à la retraite.'
            ELSE
                'L''employé 2 n''est pas encore proche de la retraite.'
        END;

        RAISE NOTICE '%', v_phrase;
    END;
$$ LANGUAGE plpgsql;

-- 3.7 Enregistrer dans une table temporaire le nombre d'occurrences des contact_name contenant les
-- chaînes 'Ana', 'Maria', 'Thomas' et 'John' dans la table customers. Faire l'opération en une seule
-- requête avec CASE ou GROUP BY. Puis faire une version avec variables séparées pour chaque
-- comptage.


CREATE TEMP TABLE Occurrences (
    ana INT,
    maria INT,
    thomas INT,
    john INT
);

INSERT INTO Occurrences
SELECT
    SUM(
        CASE
            WHEN lower(contact_name) LIKE '%ana%' THEN 1
            ELSE 0
        END
    )::INT,

    SUM(
        CASE
            WHEN lower(contact_name) LIKE '%maria%' THEN 1
            ELSE 0
        END
    )::INT,

    SUM(
        CASE
            WHEN lower(contact_name) LIKE '%thomas%' THEN 1
            ELSE 0
        END
    )::INT,

    SUM(
        CASE
            WHEN lower(contact_name) LIKE '%john%' THEN 1
            ELSE 0
        END
    )::INT
FROM customers;

SELECT * FROM Occurrences;

DROP TABLE Occurrences;


-- Version avec une variable pour chaque nom

DO $$
    DECLARE
        v_ana INT;
        v_maria INT;
        v_thomas INT;
        v_john INT;
    BEGIN
        SELECT COUNT(*) INTO v_ana
        FROM customers
        WHERE lower(contact_name) LIKE '%ana%';

        SELECT COUNT(*) INTO v_maria
        FROM customers
        WHERE lower(contact_name) LIKE '%maria%';

        SELECT COUNT(*) INTO v_thomas
        FROM customers
        WHERE lower(contact_name) LIKE '%thomas%';

        SELECT COUNT(*) INTO v_john
        FROM customers
        WHERE lower(contact_name) LIKE '%john%';

        RAISE NOTICE 'Ana : %', v_ana;
        RAISE NOTICE 'Maria : %', v_maria;
        RAISE NOTICE 'Thomas : %', v_thomas;
        RAISE NOTICE 'John : %', v_john;
    END;
$$ LANGUAGE plpgsql;

-- 3.8 S'il existe plus de 3 employés embauchés avant 1994, afficher dans une table temporaire leur
-- statut d'absence simulé : si leur employee_id est pair, ils sont en excédant ; si divisible par 3, ils sont
-- dans la norme ; sinon ils sont de bons éléments. Faire l'exercice également pour les employés
-- embauchés entre 1994 et 1998.

CREATE TEMP TABLE MaTable (
    employee_id INT,
    periode TEXT,
    statut_absence TEXT
);

DO $$
    DECLARE
        v_avant_1994 INT;
        v_1994_1998 INT;
    BEGIN
        SELECT COUNT(*) INTO v_avant_1994
        FROM employees
        WHERE hire_date < DATE '1994-01-01';

        IF v_avant_1994 > 3 THEN

            INSERT INTO MaTable
            SELECT
                employee_id,
                'Avant 1994',
                CASE
                    WHEN employee_id % 2 = 0
                        THEN 'En excédant'
                    WHEN employee_id % 3 = 0
                        THEN 'Dans la norme'
                    ELSE
                        'Bon élément'
                END
            FROM employees
            WHERE hire_date < DATE '1994-01-01';

        ELSE
            RAISE NOTICE 'Il n''y a pas plus de 3 employés embauchés avant 1994';
        END IF;


        SELECT COUNT(*) INTO v_1994_1998
        FROM employees
        WHERE hire_date >= DATE '1994-01-01'
        AND hire_date < DATE '1999-01-01';

        IF v_1994_1998 > 3 THEN

            INSERT INTO MaTable
            SELECT
                employee_id,
                '1994 - 1998',
                CASE
                    WHEN employee_id % 2 = 0
                        THEN 'En excédant'
                    WHEN employee_id % 3 = 0
                        THEN 'Dans la norme'
                    ELSE
                        'Bon élément'
                END
            FROM employees
            WHERE hire_date >= DATE '1994-01-01'
            AND hire_date < DATE '1999-01-01';

        ELSE
            RAISE NOTICE 'Il n''y a pas plus de 3 employés embauchés entre 1994 et 1998';
        END IF;
    END;
$$ LANGUAGE plpgsql;

SELECT * FROM MaTable;

DROP TABLE MaTable;

-- 4.1 Est-il possible de sortir d'une boucle WHILE en PL/pgSQL ? Si oui, comment ? Testez avec une
-- boucle qui affiche le carré des nombres de 1 à 20 mais sort de la boucle si la valeur vaut 12.
-- Utilisez EXIT WHEN v_i = 12; ou IF v_i = 12 THEN EXIT; END IF;

-- On sort avec la condition du WHILE, EXIT WHEN ou IF... THEN EXIT
DO $$
    DECLARE
        v_i INT := 1;
    BEGIN
        WHILE v_i <= 20 LOOP
            EXIT WHEN v_i = 12;
            RAISE NOTICE '% ^2 = %', v_i, v_i*v_i;
            v_i := v_i + 1;
        END LOOP;
    END;
$$ LANGUAGE plpgsql;

-- 4.2 Comment mettre fin à une boucle en PL/pgSQL ? Quelles sont les 2 choses les plus importantes ?
-- Créez une boucle LOOP sans condition et ajoutez un EXIT WHEN. Que se passe-t-il si vous supprimez
-- la condition de sortie ?
-- Une boucle infinie bloque le backend PostgreSQL. Utilisez pg_cancel_backend(pg_backend_pid()) pour
-- l'annuler.



-- 4.3 Afficher le carré des nombres impairs allant de 1 à 50, sans inclure les nombres compris entre 20 et
-- 30.
-- Utilisez CONTINUE WHEN v_i BETWEEN 20 AND 30; ou la boucle FOR i IN 1..50 BY 2.

DO $$
    DECLARE
        v_i INT := 1;
    BEGIN
        FOR v_i IN 1..50 BY 2 LOOP
            CONTINUE WHEN v_i BETWEEN 20 AND 30;
            RAISE NOTICE '% ^2 = %', v_i, v_i*v_i;
        END LOOP;
    END;
$$ LANGUAGE plpgsql;

-- 4.4 Écrire une boucle WHILE qui affiche la phrase « Ceci est un nombre divisible par 3 : [valeur] » pour
-- tous les multiples de 3 entre 1 et 30.

DO $$
    DECLARE
        v_i INT := 1;
    BEGIN
        FOR v_i IN 1..30 LOOP
            IF v_i % 3 = 0 THEN
                RAISE NOTICE 'Ceci est un nombre divisible par 3 : %', v_i;
            END IF;
        END LOOP;
    END;
$$ LANGUAGE plpgsql;

-- 4.5 Écrire une boucle WHILE qui affiche le décompte des années depuis aujourd'hui jusqu'en 1983.
-- Incrémenter un compteur à afficher en fin de décompte dans la phrase « [compteur] années ont été
-- décomptées depuis [annee_en_cours] ».
-- EXTRACT(YEAR FROM CURRENT_DATE) pour obtenir l'année courante.

DO $$
    DECLARE
        v_year INT := EXTRACT(YEAR FROM CURRENT_DATE);
        v_i INT := 0;
    BEGIN
        WHILE v_year >= 1983 LOOP
            RAISE NOTICE '% : % années ont été décomptées depuis %', v_year, v_i, EXTRACT(YEAR FROM CURRENT_DATE);
            v_year := v_year - 1;
            v_i := v_i + 1;
        END LOOP;
    END;
$$ LANGUAGE plpgsql;

-- 4.6 Écrire une boucle qui, pour chaque itération (5 itérations), enregistre la date sous 5 formats
-- différents dans une table temporaire. Afficher les données récoltées.
-- Utilisez TO_CHAR(NOW(), 'DD/MM/YYYY'), TO_CHAR(NOW(), 'YYYY-MM-DD'), TO_CHAR(NOW(), 'Day DD
-- Month YYYY'), etc. et RANDOM() pour varier.

CREATE TEMP TABLE MaTable (
    iteration INT,
    format_1 TEXT,
    format_2 TEXT,
    format_3 TEXT,
    format_4 TEXT,
    format_5 TEXT
);

DO $$
    DECLARE
        v_i INT;
        v_date TIMESTAMP;
    BEGIN
        FOR i IN 1..5
        LOOP
            -- RANDOM permet de légèrement faire varier la date
            v_date := NOW() + (RANDOM() * INTERVAL '10 days');

            INSERT INTO MaTable VALUES (
                v_i,
                TO_CHAR(v_date, 'DD/MM/YYYY'),
                TO_CHAR(v_date, 'YYYY-MM-DD'),
                TO_CHAR(v_date, 'Day DD Month YYYY'),
                TO_CHAR(v_date, 'DD-MM-YY HH24:MI'),
                TO_CHAR(v_date, 'FMDay, DD FMMonth YYYY HH24:MI:SS')
            );
        END LOOP;
    END;
$$ LANGUAGE plpgsql;

SELECT * FROM MaTable;

DROP TABLE MaTable;

-- 4.7 Écrire une boucle qui affiche « [last_name] est l'employé dont l'id est [employee_id] » pour les 9
-- employés de la table employees. Faites d'abord l'exercice avec 2 tables temporaires distinctes (une
-- pour les noms, une pour les IDs) et une jointure. Refaites-le ensuite avec un curseur explicite.

CREATE TEMP TABLE NomsEmployes (
    employee_id INT,
    last_name TEXT
);

CREATE TEMP TABLE IdEmployes (
    employee_id INT
);

INSERT INTO NomsEmployes
SELECT
    employee_id,
    last_name
FROM employees;

INSERT INTO IdEmployes
SELECT employee_id
FROM employees;

DO $$
    DECLARE
        rec RECORD;
    BEGIN
        FOR rec IN
            SELECT
                n.last_name,
                i.employee_id
            FROM NomsEmployes n
            JOIN IdEmployes i
                ON n.employee_id = i.employee_id
        LOOP
            RAISE NOTICE '% est l''employé dont l''id est %',
                rec.last_name,
                rec.employee_id;
        END LOOP;
    END;
$$ LANGUAGE plpgsql;

DROP TABLE NomsEmployes, IdEmployes;

-- Avec un curseur

DO $$
    DECLARE
        rec RECORD;
        cur_employees CURSOR FOR
            SELECT
                employee_id,
                last_name
            FROM employees;
    BEGIN
        OPEN cur_employees;

        LOOP
            FETCH cur_employees INTO rec;

            EXIT WHEN NOT FOUND;

            RAISE NOTICE '% est l''employé dont l''id est %',
                rec.last_name,
                rec.employee_id;
        END LOOP;

        CLOSE cur_employees;
    END;
$$ LANGUAGE plpgsql;

-- 4.8 Récupérer les noms, prénoms, ID et titre (title) de tous les employés dans un curseur. Afficher dans
-- une table temporaire uniquement ceux dont le titre contient 'Sales'. Le filtre se fait après récupération
-- complète des données dans le curseur.

CREATE TEMP TABLE MaTable (
    employee_id INT,
    nom TEXT,
    prenom TEXT,
    titre TEXT
);

DO $$
    DECLARE
        rec RECORD;

        cur_employees CURSOR FOR
            SELECT
                employee_id,
                last_name,
                first_name,
                title
            FROM employees;

    BEGIN
        OPEN cur_employees;

        LOOP
            FETCH cur_employees INTO rec;

            EXIT WHEN NOT FOUND;

            IF lower(rec.title) LIKE lower('%Sales%') THEN
                INSERT INTO MaTable VALUES (
                    rec.employee_id,
                    rec.last_name,
                    rec.first_name,
                    rec.title
                );
            END IF;
        END LOOP;

        CLOSE cur_employees;
    END;
$$ LANGUAGE plpgsql;

SELECT * FROM MaTable;

DROP TABLE MaTable;


-- 4.9 Afficher les données des employés (Nom, Prénom, Date de naissance) rattachés au titre 'Sales
-- Representative'. Assurez-vous qu'il ne faille changer qu'une seule variable pour basculer vers un autre
-- titre (ex: 'Vice President, Sales').

DO $$
    DECLARE
        v_titre employees.title%TYPE := 'Sales Representative';

        rec RECORD;

        cur_employees CURSOR FOR
            SELECT
                last_name,
                first_name,
                birth_date,
                title
            FROM employees;

    BEGIN
        OPEN cur_employees;

        LOOP
            FETCH cur_employees INTO rec;

            EXIT WHEN NOT FOUND;

            IF rec.title = v_titre THEN
                RAISE NOTICE '% % - né(e) le %',
                    rec.last_name,
                    rec.first_name,
                    TO_CHAR(rec.birth_date, 'DD/MM/YYYY');
            END IF;
        END LOOP;

        CLOSE cur_employees;
    END;
$$ LANGUAGE plpgsql;

-- 4.10 Récupérer la liste des employee_id dans un curseur. Récupérer les commandes de la table
-- orders dans un second curseur. Pour chaque employé, si son ID est présent dans le second curseur,
-- afficher le montant total de ses commandes (via order_details). Tout doit se faire via les curseurs et
-- boucles, sans jointure SQL directe.

DO $$
    DECLARE
        rec_employee RECORD;
        rec_order RECORD;
        rec_detail RECORD;

        v_total NUMERIC;
        v_employe_total NUMERIC;

        cur_employees CURSOR FOR
            SELECT employee_id
            FROM employees;

        cur_orders CURSOR FOR
            SELECT
                order_id,
                employee_id
            FROM orders;

    BEGIN
        OPEN cur_employees;

        LOOP
            FETCH cur_employees INTO rec_employee;

            EXIT WHEN NOT FOUND;

            v_employe_total := 0;

            OPEN cur_orders;

            LOOP
                FETCH cur_orders INTO rec_order;

                EXIT WHEN NOT FOUND;

                IF rec_order.employee_id = rec_employee.employee_id THEN

                    -- On récupère les lignes de la commande
                    FOR rec_detail IN
                        SELECT
                            unit_price,
                            quantity,
                            discount
                        FROM order_details
                        WHERE order_id = rec_order.order_id
                    LOOP

                        v_total :=
                            rec_detail.unit_price
                            * rec_detail.quantity
                            * (1 - rec_detail.discount);

                        v_employe_total :=
                            v_employe_total + v_total;

                    END LOOP;

                END IF;

            END LOOP;

            CLOSE cur_orders;

            IF v_employe_total > 0 THEN
                RAISE NOTICE 'Employé % : montant total des commandes = %',
                    rec_employee.employee_id,
                    ROUND(v_employe_total, 2);
            END IF;

        END LOOP;

        CLOSE cur_employees;
    END;
$$ LANGUAGE plpgsql;

-- 4.11 Récupérer les produits et leurs noms dans la table products dans un premier curseur. Récupérer
-- les quantités commandées dans la table order_details dans un second curseur. Pour chaque produit
-- existant, calculer et afficher la quantité totale commandée. Sans SUM dans un SELECT — tout dans
-- les boucles !
-- Limitez à 50 produits pour les tests.

DO $$
    DECLARE
        rec_product RECORD;
        rec_detail RECORD;

        v_quantite_totale INT;

        cur_products CURSOR FOR
            SELECT
                product_id,
                product_name
            FROM products
            ORDER BY product_id
            LIMIT 50;

        cur_details CURSOR FOR
            SELECT
                product_id,
                quantity
            FROM order_details;

    BEGIN
        OPEN cur_products;

        LOOP
            FETCH cur_products INTO rec_product;

            EXIT WHEN NOT FOUND;

            v_quantite_totale := 0;

            OPEN cur_details;

            LOOP
                FETCH cur_details INTO rec_detail;

                EXIT WHEN NOT FOUND;

                IF rec_detail.product_id = rec_product.product_id THEN
                    v_quantite_totale :=
                        v_quantite_totale + rec_detail.quantity;
                END IF;

            END LOOP;

            CLOSE cur_details;

            RAISE NOTICE '% : % unités commandées',
                rec_product.product_name,
                v_quantite_totale;

        END LOOP;

        CLOSE cur_products;
    END;
$$ LANGUAGE plpgsql;

-- 4.12 Récupérer le prix unitaire actuel (unit_price) de chaque produit dans products. Dans un second
-- curseur, récupérer les données des commandes récentes (order_date la plus récente) de orders. Pour
-- chaque produit, insérer dans une table temporaire son nom, son prix et la date de la dernière
-- commande le concernant.

CREATE TEMP TABLE MaTable (
    nom_produit TEXT,
    prix NUMERIC,
    derniere_commande DATE
);

DO $$
    DECLARE
        rec_product RECORD;
        rec_order RECORD;
        rec_detail RECORD;

        v_derniere_date DATE;

        cur_products CURSOR FOR
            SELECT
                product_id,
                product_name,
                unit_price
            FROM products;

        cur_orders CURSOR FOR
            SELECT
                order_id,
                order_date
            FROM orders
            ORDER BY order_date DESC;

    BEGIN
        OPEN cur_products;

        LOOP
            FETCH cur_products INTO rec_product;

            EXIT WHEN NOT FOUND;

            v_derniere_date := NULL;

            OPEN cur_orders;

            LOOP
                FETCH cur_orders INTO rec_order;

                EXIT WHEN NOT FOUND;

                -- On regarde si le produit apparaît dans cette commande
                FOR rec_detail IN
                    SELECT product_id
                    FROM order_details
                    WHERE order_id = rec_order.order_id
                LOOP

                    IF rec_detail.product_id = rec_product.product_id THEN
                        v_derniere_date := rec_order.order_date;
                        EXIT;
                    END IF;

                END LOOP;

                -- Dès qu'une commande contenant le produit est trouvée,
                -- inutile de continuer à parcourir les anciennes.
                IF v_derniere_date IS NOT NULL THEN
                    EXIT;
                END IF;

            END LOOP;

            CLOSE cur_orders;

            INSERT INTO MaTable VALUES (
                rec_product.product_name,
                rec_product.unit_price,
                v_derniere_date
            );

        END LOOP;

        CLOSE cur_products;
    END;
$$ LANGUAGE plpgsql;

SELECT
    nom_produit,
    prix,
    TO_CHAR(derniere_commande, 'DD/MM/YYYY') AS derniere_commande
FROM MaTable;

DROP TABLE MaTable;

-- 5.1 Créer une procédure qui affiche directement la date et l'heure du système. L'appel de cette
-- procédure permet d'un seul coup de récupérer la date et l'heure sans utiliser NOW() ou
-- CURRENT_TIMESTAMP directement dans le script.

CREATE OR REPLACE PROCEDURE afficher_date_heure()
LANGUAGE plpgsql
AS $$
    BEGIN
        RAISE NOTICE 'Date et heure : %', CURRENT_TIMESTAMP;
    END;
$$;

CALL afficher_date_heure();

-- 5.2 Créer une procédure qui affiche la phrase « Nous sommes le [Date_du_jour] et il est actuellement
-- [heure_du_moment] ».

CREATE OR REPLACE PROCEDURE afficher_phrase_date_heure()
LANGUAGE plpgsql
AS $$
    BEGIN
        RAISE NOTICE 'Nous sommes le % et il est actuellement %',
            TO_CHAR(CURRENT_DATE, 'DD/MM/YYYY'),
            TO_CHAR(NOW(), 'HH24:MI:SS'); -- NOW pour le TO_CHAR qui ne fonctionne pas sur un CURRENT_TIME
    END;
$$;

CALL afficher_phrase_date_heure();

-- 5.3 Créer une procédure qui insère dans une table temporaire (créée si elle n'existe pas) les données
-- vous concernant passées en paramètres (nom, prénom, âge, ville).

CREATE OR REPLACE PROCEDURE ajouter_donnees(
    p_nom TEXT,
    p_prenom TEXT,
    p_age INT,
    p_ville TEXT
)
LANGUAGE plpgsql
AS $$
    BEGIN

        CREATE TEMP TABLE IF NOT EXISTS tmp_donnees (
            nom TEXT,
            prenom TEXT,
            age INT,
            ville TEXT
        );

        INSERT INTO tmp_donnees VALUES (
            p_nom,
            p_prenom,
            p_age,
            p_ville
        );

    END;
$$;

CALL ajouter_donnees('Mets', 'Anthony', 40, 'Beaumont');

SELECT * FROM tmp_donnees;

-- 5.4 Créer une procédure qui, en parcourant la table employees, insère les données dans deux tables
-- temporaires tmp_anciens et tmp_jeunes. Si l'employé a été embauché avant 1994 et est né avant
-- 1960, il va dans tmp_anciens. S'il a été embauché après 1995 et est né après 1965, il va dans
-- tmp_jeunes.

CREATE OR REPLACE PROCEDURE classer_employes()
LANGUAGE plpgsql
AS $$
    DECLARE
        rec RECORD;
    BEGIN

        CREATE TEMP TABLE IF NOT EXISTS tmp_anciens (
            employee_id INT,
            last_name TEXT,
            first_name TEXT,
            hire_date DATE,
            birth_date DATE
        );

        CREATE TEMP TABLE IF NOT EXISTS tmp_jeunes (
            employee_id INT,
            last_name TEXT,
            first_name TEXT,
            hire_date DATE,
            birth_date DATE
        );

        FOR rec IN
            SELECT
                employee_id,
                last_name,
                first_name,
                hire_date,
                birth_date
            FROM employees
        LOOP

            IF rec.hire_date < DATE '1994-01-01'
                AND rec.birth_date < DATE '1960-01-01'
            THEN

                INSERT INTO tmp_anciens VALUES (
                    rec.employee_id,
                    rec.last_name,
                    rec.first_name,
                    rec.hire_date,
                    rec.birth_date
                );

            ELSIF rec.hire_date > DATE '1995-12-31'
                AND rec.birth_date > DATE '1965-12-31'
            THEN

                INSERT INTO tmp_jeunes VALUES (
                    rec.employee_id,
                    rec.last_name,
                    rec.first_name,
                    rec.hire_date,
                    rec.birth_date
                );

            END IF;

        END LOOP;

    END;
$$;

CALL classer_employes();

SELECT * FROM tmp_anciens;
SELECT * FROM tmp_jeunes;

-- 5.5 Créer une fonction scalaire qui retourne le nombre de lignes contenues dans la table employees.

CREATE OR REPLACE FUNCTION nb_employees()
RETURNS INT
LANGUAGE plpgsql
AS $$
    DECLARE
        v_nb INT;
    BEGIN

        SELECT COUNT(*) INTO v_nb
        FROM employees;

        RETURN v_nb;

    END;
$$;

SELECT nb_employees();

-- 5.6 Créer une fonction qui retourne le nom du produit (product_name de products) dont le prix
-- unitaire (unit_price) a été le plus souvent commandé (apparaît le plus souvent dans order_details). S'il
-- y a ex-aequo, retourner une phrase concaténée de tous les noms.

CREATE OR REPLACE FUNCTION produit_plus_commande()
RETURNS TEXT
LANGUAGE plpgsql
AS $$
    DECLARE
        v_resultat TEXT;
    BEGIN

        SELECT STRING_AGG(product_name, ', ')
        INTO v_resultat
        FROM products
        WHERE product_id IN (

            SELECT product_id
            FROM order_details
            GROUP BY product_id, unit_price
            HAVING COUNT(*) = (

                SELECT MAX(nb)
                FROM (
                    SELECT COUNT(*) AS nb
                    FROM order_details
                    GROUP BY product_id, unit_price
                ) t

            )

        );

        RETURN v_resultat;

    END;
$$;

SELECT produit_plus_commande();

-- 5.7 Créer une procédure avec un paramètre INOUT (équivalent OUTPUT) qui met à jour la colonne
-- d'une table (ex: ajouter une colonne last_update à employees si elle n'existe pas, et la remplir avec
-- CURRENT_DATE pour les employés dont hire_date n'est pas le 31 juillet 1994). La procédure retourne
-- via le paramètre INOUT le nombre de lignes mises à jour. Faire une version avec UPDATE simple et
-- une version avec curseur.

CREATE OR REPLACE PROCEDURE maj_employees_simple(
    INOUT p_nb INT
)
LANGUAGE plpgsql
AS $$
    BEGIN

        ALTER TABLE employees
        ADD COLUMN IF NOT EXISTS last_update DATE;

        UPDATE employees
        SET last_update = CURRENT_DATE
        WHERE hire_date::DATE <> DATE '1994-07-31';

        GET DIAGNOSTICS p_nb = ROW_COUNT;

    END;
$$;

CALL maj_employees_simple(0);

-- Avec un curseur

CREATE OR REPLACE PROCEDURE maj_employees_curseur(
    INOUT p_nb INT
)
LANGUAGE plpgsql
AS $$
    DECLARE
        rec RECORD;

        cur_employee CURSOR FOR
            SELECT employee_id, hire_date
            FROM employees;

    BEGIN

        ALTER TABLE employees
        ADD COLUMN IF NOT EXISTS last_update DATE;

        p_nb := 0;

        OPEN cur_employee;

        LOOP

            FETCH cur_employee INTO rec;

            EXIT WHEN NOT FOUND;

            IF rec.hire_date::DATE <> DATE '1994-07-31' THEN

                UPDATE employees
                SET last_update = CURRENT_DATE
                WHERE employee_id = rec.employee_id;

                p_nb := p_nb + 1;

            END IF;

        END LOOP;

        CLOSE cur_employee;

    END;
$$;

CALL maj_employees_curseur(0);

-- 5.8 Créer une procédure qui parcourt via un curseur l'ensemble des produits et leurs prix de
-- products. Si le prix est inférieur à 15, insérer dans une table non-temporaire (créée via CREATE
-- TABLE IF NOT EXISTS). Si le prix est supérieur à 15, insérer dans une table temporaire le nom du
-- produit et une appréciation ('prix bien trop élevé' si > 50, 'prix raisonnable' entre 15 et 50). Retourner
-- via un paramètre INOUT le nombre de produits bon marché insérés.

CREATE OR REPLACE PROCEDURE classer_produits(
    INOUT p_nb INT
)
LANGUAGE plpgsql
AS $$
    DECLARE
        rec RECORD;

        cur_product CURSOR FOR
            SELECT
                product_id,
                product_name,
                unit_price
            FROM products;

    BEGIN

        CREATE TABLE IF NOT EXISTS produits_bon_marche (
            product_id INT,
            product_name TEXT,
            unit_price NUMERIC
        );

        CREATE TEMP TABLE IF NOT EXISTS tmp_produits_chers (
            product_name TEXT,
            appreciation TEXT
        );

        p_nb := 0;

        OPEN cur_product;

        LOOP

            FETCH cur_product INTO rec;

            EXIT WHEN NOT FOUND;

            IF rec.unit_price < 15 THEN

                INSERT INTO produits_bon_marche VALUES (
                    rec.product_id,
                    rec.product_name,
                    rec.unit_price
                );

                p_nb := p_nb + 1;

            ELSIF rec.unit_price > 50 THEN

                INSERT INTO tmp_produits_chers VALUES (
                    rec.product_name,
                    'prix bien trop élevé'
                );

            ELSIF rec.unit_price > 15 THEN

                INSERT INTO tmp_produits_chers VALUES (
                    rec.product_name,
                    'prix raisonnable'
                );

            END IF;

        END LOOP;

        CLOSE cur_product;

    END;
$$;

CALL classer_produits(0);

-- 5.9 Créer une procédure qui affiche « X employés travaillent au poste de [title] ». Le nombre X sera
-- retourné par une fonction créée au préalable qui prend le titre en paramètre. Tester pour plusieurs
-- titres différents.

CREATE OR REPLACE FUNCTION nb_employes_par_titre(
    p_title TEXT
)
RETURNS INT
LANGUAGE plpgsql
AS $$
    DECLARE
        v_nb INT;
    BEGIN

        SELECT COUNT(*) INTO v_nb
        FROM employees
        WHERE title = p_title;

        RETURN v_nb;

    END;
$$;

-- Procédure utilisant la fonction précédente.

CREATE OR REPLACE PROCEDURE afficher_nb_employes(
    p_title TEXT
)
LANGUAGE plpgsql
AS $$
    DECLARE
        v_nb INT;
    BEGIN

        v_nb := nb_employes_par_titre(p_title);

        RAISE NOTICE '% employés travaillent au poste de %',
            v_nb,
            p_title;

    END;
$$;

CALL afficher_nb_employes('Sales Representative');

CALL afficher_nb_employes('Vice President, Sales');

CALL afficher_nb_employes('Sales Manager');

-- 5.10 Créer une procédure qui affiche les noms de produits (product_name) et leur prix (unit_price)
-- appartenant à la catégorie passée en paramètre et dont le prix est inférieur à un seuil également passé
-- en paramètre. Tester avec tous les produits de la catégorie 'Beverages' à moins de 20€. Retourner
-- aussi via INOUT le nombre de produits non affichés.

CREATE OR REPLACE PROCEDURE afficher_produits_categorie(
    p_categorie TEXT,
    p_seuil NUMERIC,
    INOUT p_non_affiches INT
)
LANGUAGE plpgsql
AS $$
    DECLARE
        rec RECORD;

        cur_product CURSOR FOR
            SELECT
                p.product_name,
                p.unit_price
            FROM products p
            JOIN categories c
                ON p.category_id = c.category_id
            WHERE c.category_name = p_categorie;

    BEGIN

        p_non_affiches := 0;

        OPEN cur_product;

        LOOP

            FETCH cur_product INTO rec;

            EXIT WHEN NOT FOUND;

            IF rec.unit_price < p_seuil THEN

                RAISE NOTICE '% : % €',
                    rec.product_name,
                    rec.unit_price;

            ELSE

                p_non_affiches := p_non_affiches + 1;

            END IF;

        END LOOP;

        CLOSE cur_product;

    END;
$$;

CALL afficher_produits_categorie(
    'Beverages',
    20,
    0
);

-- 5.11 Créer une table mes_employes pouvant stocker ID, nom, prénom, date d'embauche et date de
-- naissance. Créer une procédure qui remplit cette table en utilisant un type composite (ou tableau)
-- passé en paramètre, en complétant automatiquement la date d'embauche avec CURRENT_DATE.

CREATE TABLE mes_employes (
    employee_id INT,
    nom TEXT,
    prenom TEXT,
    date_embauche DATE,
    date_naissance DATE
);

CREATE TYPE type_employe AS (
    employee_id INT,
    nom TEXT,
    prenom TEXT,
    date_naissance DATE
);

CREATE OR REPLACE PROCEDURE ajouter_employe(
    p_employe type_employe
)
LANGUAGE plpgsql
AS $$
    BEGIN

        INSERT INTO mes_employes VALUES (
            p_employe.employee_id,
            p_employe.nom,
            p_employe.prenom,
            CURRENT_DATE,
            p_employe.date_naissance
        );

    END;
$$;

CALL ajouter_employe(
    ROW(
        10,
        'Duck',
        'Donald',
        DATE '1980-01-01'
    )::type_employe
);

SELECT * FROM mes_employes;

-- 5.12 Récupérer les données de téléphone des employés (home_phone de employees) dans une table
-- temporaire. Créer une fonction qui ajoute la colonne tel_num à la table mes_employes (créée en 5.11)
-- et la remplit à partir des données de téléphone. La fonction retourne le nombre de lignes mises à jour.

CREATE TEMP TABLE tmp_telephones AS
    SELECT
        employee_id,
        home_phone
    FROM employees;

CREATE OR REPLACE FUNCTION ajouter_telephones()
RETURNS INT
LANGUAGE plpgsql
AS $$
    DECLARE
        v_nb INT;
    BEGIN

        ALTER TABLE mes_employes
        ADD COLUMN IF NOT EXISTS tel_num TEXT;

        UPDATE mes_employes m
        SET tel_num = t.home_phone
        FROM tmp_telephones t
        WHERE m.employee_id = t.employee_id;

        GET DIAGNOSTICS v_nb = ROW_COUNT;

        RETURN v_nb;

    END;
$$;

SELECT ajouter_telephones();

-- 5.13 Créer une procédure qui ajoute une colonne pays à la table mes_employes et la remplit en
-- recoupant les données de la table employees (country). Pour chaque employé dont le téléphone est
-- connu, ajouter le pays.

CREATE OR REPLACE PROCEDURE ajouter_pays()
LANGUAGE plpgsql
AS $$
    BEGIN

        ALTER TABLE mes_employes
        ADD COLUMN IF NOT EXISTS pays TEXT;

        UPDATE mes_employes m
        SET pays = e.country
        FROM employees e
        WHERE m.employee_id = e.employee_id
        AND e.home_phone IS NOT NULL;

    END;
$$;

CALL ajouter_pays();

SELECT * FROM mes_employes;

-- 5.14 Supprimer tout ce que vous avez créé : procédures, fonctions, types, tables.
-- -- Pour supprimer :
-- DROP FUNCTION IF EXISTS nom_fonction(types_params);
-- DROP PROCEDURE IF EXISTS nom_procedure(types_params);
-- DROP TYPE IF EXISTS nom_type;
-- DROP TABLE IF EXISTS nom_table;

DROP PROCEDURE IF EXISTS afficher_date_heure();

DROP PROCEDURE IF EXISTS afficher_phrase_date_heure();

DROP PROCEDURE IF EXISTS ajouter_donnees(
    TEXT,
    TEXT,
    INT,
    TEXT
);

DROP PROCEDURE IF EXISTS classer_employes();

DROP FUNCTION IF EXISTS nb_employees();

DROP FUNCTION IF EXISTS produit_plus_commande();

DROP PROCEDURE IF EXISTS maj_employees_simple(INT);

DROP PROCEDURE IF EXISTS maj_employees_curseur(INT);

DROP PROCEDURE IF EXISTS classer_produits(INT);

DROP FUNCTION IF EXISTS nb_employes_par_titre(TEXT);

DROP PROCEDURE IF EXISTS afficher_nb_employes(TEXT);

DROP PROCEDURE IF EXISTS afficher_produits_categorie(
    TEXT,
    NUMERIC,
    INT
);

DROP FUNCTION IF EXISTS ajouter_telephones();

DROP PROCEDURE IF EXISTS ajouter_pays();

DROP PROCEDURE IF EXISTS ajouter_employe(type_employe);

DROP TYPE IF EXISTS type_employe;

DROP TABLE IF EXISTS tmp_donnees;

DROP TABLE IF EXISTS tmp_anciens;

DROP TABLE IF EXISTS tmp_jeunes;

DROP TABLE IF EXISTS produits_bon_marche;

DROP TABLE IF EXISTS tmp_produits_chers;

DROP TABLE IF EXISTS tmp_telephones;

DROP TABLE IF EXISTS mes_employes;

ALTER TABLE employees
DROP COLUMN IF EXISTS last_update;