USE AdventureWorks2019;
GO

--1. Module d’introduction

--1.1 Se connecter à SQL Server et créer une nouvelle requête.
--Créer une table ayant une colonne de type INTEGER, auto-incrémentée.
--Où cette table s’est-elle créée ? Comment pouvez-vous la visualiser ?

	CREATE TABLE Test (
		Id INT NOT NULL IDENTITY
	);
	-- Table dans la db actuelle, on utilise SELECT * FROM Test pour la visualiser

--1.2 Créer une nouvelle base de données (CREATE DATABASE nom_de_votre_bd)
--Créer une table contenant 2 colonnes dont une possède la contrainte UNIQUE, dans cette
--base de données. S’assurer que la table apparaît bien dans cette nouvelle base de données
--et pas dans la base de données « MASTER »

	CREATE DATABASE MyDB;
	GO

	USE MyDB;

	CREATE TABLE TableTest (
		Id INT IDENTITY,
		UserName NVARCHAR(50) UNIQUE NOT NULL
	);

--1.3 A l’aide de l’instruction « PRINT », afficher le message « Bonjour, et bienvenue dans le
--cours de Transact SQL ! »

	PRINT('Bonjour, et bienvenue dans le cours de Transact SQL !');

--1.4 Afficher le même message qu’à l’exercice 1.3. mais en utilisant cette fois une table
--temporaire

	CREATE TABLE #TableTemporaire(
		UserMessage NVARCHAR(250)
	);
	INSERT INTO #TableTemporaire VALUES(
		N'Bonjour, et bienvenue dans le cours de Transact SQL !'
	);

	SELECT * FROM #TableTemporaire;

--1.5 Insérer une ligne de valeurs dans la table créée au point 1.2.
--Insérer une seconde ligne identique. Un message d’erreur doit apparaître. A quelle ligne se
--situe le message ? Comment trouver cette ligne instantanément ?
--Comprenez-vous ce message ? Comment pouvez-vous faire pour le comprendre si
--l’anglais n’est pas votre fort ?

	INSERT INTO TableTest(UserName) VALUES
	('Toto'),
	('Toto');

	-- Msg 2627, Niveau 14, État 1, Ligne 53
	-- Violation of UNIQUE KEY constraint 'UQ__TableTes__C9F28456D2A05B3F'. Cannot insert duplicate key in object 'dbo.TableTest'. The duplicate key value is (Toto).
	-- -> L'erreur se situe à la ligne 53 car la valeur 'Toto' est utilisée edux fois pour l'INSERT avec une contrainte UNIQUE

--1.6 A l’aide d’une instruction de simple « SELECT », afficher les données contenues dans la
--table du point 1.2.
--Stocker à présent la requête SQL dans une variable que vous aurez préalablement déclarée
--pour afficher son contenu à l’aide de la commande « EXEC ».
--Référez-vous aux informations du cours théorique pour la déclaration de votre variable
--que nous n’avons pas encore vu à ce stade.

	SELECT * FROM TableTest;
	DECLARE @MyVariable NVARCHAR(MAX) = N'SELECT * FROM TableTest';
	EXEC (@MyVariable);

--2. Module sur les variables

--2.1 À l’aide de la commande « PRINT », affichez le message « Le T-SQL, c’est bien
--pratique ! »

	PRINT('Le T-SQL, c’est bien pratique !');

--2.2 Créer à présent une variable de type « chaine de caractères ». Cette variable contiendra
--la phrase affichée au point 2.1. Afficher le contenu de la variable via la commande
--« PRINT »

	DECLARE @MyVariableDeTypeNVarchar NVARCHAR(MAX) = N'Le T-SQL, c’est bien pratique !';
	PRINT(@MyVariableDeTypeNVarchar);

--2.3 Déclarer une variable qui contiendra le nombre d’employés de la table
--« Person.Person » de votre base de données AdventureWorks2008R2. Affichez le
--contenu de cette variable

	USE AdventureWorks2019;
	GO

	DECLARE @MyCountPersonFromAW NVARCHAR(MAX) = N'SELECT COUNT(*) FROM Person.Person';
	EXEC(@MyCountPersonFromAW);

--2.4 Déclarer une variable nommée « prenom_emp », du même type que les valeurs de la
--colonne « FirstName » de la table Person.Person. Remplir cette variable avec le
--prénom de M. Eminhizer et afficher le contenu de la variable. Vérifier que cette valeur
--est bien « Terry »

	EXEC sp_help 'Person.Person' -- Pour afficher les infos de la table Person.Person
		-- On voit ici que la colonne FirstName est de type NAME, un type personalisé dérivé de NVARCHAR(50)
	DECLARE @selector NVARCHAR(MAX) = 'Eminhizer';
	DECLARE @prenom_emp NVARCHAR(50) = 'SELECT FirstName FROM Person.Person WHERE LastName = @selector';
	EXEC(@prenom_emp);

--2.5 Soit le code suivant. Que doit-il donner ? Fonctionne-t-il et sinon, pourquoi ?

	-- -> Non il manque une virgule après la déclaration de @y, le SET a utilisé le double égal, pas de @ à z, pas de @ à x et y

--2.6 Et dans ce cas-ci ?

	-- -> Non, il manque le paramètre dans varchar(), := FAUX, y sans @, 

--2.7 Créer une variable nommée « date_du_jour » qui aura le format DATETIME et la
--valeur de la date du jour. Afficher cette date.

	DECLARE @date_du_jour DATETIME = getdate();
	PRINT(@date_du_jour);

--2.8 À l’aide de plusieurs variables, afficher la phrase « M. [Nom] [Prénom] est l’employé
--numéro [ID de l’employé], a été engagé le [date d’entrée en service de l’employé] et
--est un [homme/femme] »
--Les informations dont vous avez besoin se trouvent dans les tables « Person.Person »
--et « HumanResources.Employee »

	DECLARE
		@LastName NAME,
		@FirstName NAME,
		@BusinessEntityID INT,
		@HireDate DATE,
		@Gender CHAR(1),
		@output NVARCHAR(MAX);


	SELECT 
		@LastName = LastName,
		@FirstName = FirstName,
		@BusinessEntityID = Person.Person.BusinessEntityID,
		@HireDate = HireDate,
		@Gender = Gender
	FROM Person.Person, HumanResources.Employee
	WHERE 
		Person.Person.BusinessEntityID = HumanResources.Employee.BusinessEntityID

	SET @output = CONCAT(IIF(@Gender = 'M','Monsieur ','Madame '), @LastName, ' ', @FirstName, ' est l’employé numéro ', @BusinessEntityID, ',a été engagé le ', FORMAT(@HireDate,'dd MMMM yyyy','fr'), ' et est ', IIF(@Gender = 'M','un Homme.','un Femme.'));

	PRINT(@output);

--2.9 Créer une variable entière contenant votre âge. Créer une seconde variable de type
--chaine de caractères, contenant votre nom. Afficher maintenant la concaténation de ces
--variables.
--Cette opération pose-t-elle problème ? Avez-vous utilisé la fonction CONVERT dans
--ce cas ? Aurait-elle été utile ? Si vous ne l’avez pas utilisée, n’hésitez pas à la faire !
--Cela change-t-il la réponse ?

	DECLARE @MonAge INT = 39;
	DECLARE @MonNom NAME = 'Mets';

	PRINT(CONCAT(@MonAge,@MonNom));
	-- -> Aucun problème...

--2.10 Générer 3 variables entières. Afficher l’addition de ces trois variables dans une table
--temporaire. La colonne utilisée pour l’affichage aura pour nom « Résultat »

	DECLARE @MyVar1 INT = 1, @MyVar2 INT = 2, @MyVar3 INT = 3;
	CREATE TABLE #TempTableResult
	(
		[Résultat] INT NOT NULL
	);
	INSERT INTO #TempTableResult VALUES (@MyVar1 + @MyVar2 + @MyVar3);
	SELECT * FROM #TempTableResult;

--2.11 Créer des variables pour contenir les données des colonnes « BusinessEntityID »,
--« JobTitle » et « BirthDate » de la table HumanResources.Employee.
--Afficher [BusinessEntityID] + ‘ ‘ + [JobTitle] + ‘ ‘ + [BirthDate]
--Cela affiche-t-il le résultat attendu ? Comment résoudre ce problème ?

	DECLARE 
		@AllBusinessEntityID TABLE (Id INT);
	DECLARE
		@AllJobTitle TABLE (JobTitle NVARCHAR(50));
	DECLARE
		@AllBirthDate TABLE (BirthDate DATE);
	
	INSERT INTO @AllBusinessEntityID (id)
		SELECT BusinessEntityID
		FROM HumanResources.Employee

	

--2.12 Déclarer une table temporaire qui contiendra les données issues des colonnes Title,
--FirstName et LastName de la table Person.Person.
--Insérer les données dans la table temporaire. Afficher l’ensemble des données de la
--table.
--Déconnectez-vous de SQLServer et reconnectez-vous. La table temporaire existe-t-elle
--toujours ?
--A la fin de l’exercice, supprimer la table créée.



--2.13 Déclarer une variable temporaire de type table qui aura pour colonnes « TitreJob »,
--« DateEmbauche », « HeuresVacances » et « HeuresMaladie ». Remplir cette variable
--avec les données de tous les Techniciens de production WC60 de la table
--HumanResources.Employee. Afficher le contenu de cette table grâce à un select.



--2.14 Afficher maintenant les données de la variable de type table de l’exercice précédent
--dans un tableau, non plus via un select.



--2.15 Déclarer une table temporaire qui contiendra une colonne contenant le mois de
--naissance, une deuxième contenant le nom, une troisième le prénom et une dernière la
--ville où résident 5 de vos connaissances. Remplir cette table avec les données
--attendues.
--Déclarer une variable qui permettra de copier toutes les données de la table temporaire
--que vous venez de créer et y transférer les données qu’elle contient.
--Modifier les 2 premières lignes de votre table temporaire avec les données de deux
--autres personnes.
--Les données ont-elles été modifiées aux deux endroits simultanément ? Pourquoi ?
--Créer un nouveau script. Essayer d’afficher à nouveau les données des deux éléments
--créés (table et variable). Existent-ils toujours ?



--2.16 Quel est le nom de chacun des techniciens de productions WC60 ? Récupérer leur
--nom, prénom et leur job dans une variable temporaire dont on affiche le résultat dans
--une table temporaire.


--3. Module sur les conditionnelles

--3.1 Pour l’employé numéro 21 de la table HumanResources.Employee, examinez sa date
--d’arrivée dans l’entreprise. Si son ancienneté est de plus de 9 ans, afficher la phrase
--« L’employé 21 est un Senior ». Sinon, il faudra signaler qu’il s’agit d’un Junior.

	SELECT 
		IIF(
		DATEDIFF(YEAR, HireDate, GETDATE()) > 9, -- DATEDIFF prend en compte les mois et les jours
		'L’employé 21 est un Senior',
		'L’employé 21 est un Junior'
		) AS 'Ancienneté'
	FROM HumanResources.Employee
	WHERE BusinessEntityID = 21

--3.2 S’il existe dans la table Person.Person, quelqu’un du nom de « Zugelder », affichez
--son nom complet (Prénom, deuxième nom, nom de famille)
--Sinon, signaler qu’il n’existe personne portant ce nom !

	DECLARE @zugelderLastName NAME, @zugelderFirstName NAME, @zugelderMidleName NAME
	SELECT
		@zugelderLastName = LastName,
		@zugelderFirstName = FirstName,
		@zugelderMidleName = MiddleName
	FROM Person.Person
	WHERE LastName LIKE 'Zugelder'

	PRINT IIF(@zugelderLastName LIKE 'Zugelder',CONCAT(@zugelderFirstName,' ',@zugelderMidleName, ' ',@zugelderLastName),'Il n''existe pas !')

--3.3 Si le nombre de femmes est plus important que le nombre d’hommes, affichez, dans
--une table, « Les femmes domineront le monde ! » Sinon, indiquez « La guerre des
--sexes n’est pas finie… »
--Afficher le contenu de votre table

	DECLARE 
		@hommes INT,
		@femmes INT
	
	SELECT
		@hommes = COUNT(*)
	FROM HumanResources.Employee
	WHERE Gender LIKE 'M'

	SELECT
		@femmes = COUNT(*)
	FROM HumanResources.Employee
	WHERE Gender LIKE 'F'

	SELECT
		IIF(
		@femmes > @hommes,
		'Les femmes domineront le monde !',
		'La guerre des sexes n’est pas finie…'
		) AS [Résultat du sondage]

--3.4 Comparer le nombre d’heures d’absence des employés 21 et 27. Si le nombre d’heures
--de repos de l’un ET son nombre d’heures de vacances sont plus importants que ceux
--de l’autre, signalez-le par un message à l’écran ! Sinon, si le nombre d’heures de repos
--de l’un est plus grand que celui de l’autre, mais que son nombre d’heures de vacances
--est inférieur, signaler que tout va bien. Dans les autres cas, il n’y a rien à signaler.
--Choisissez vous-même du quel employé vous partirez pour faire la comparaison.

	DECLARE @Absences TABLE ([Id] INT NOT NULL, [Vacances] SMALLINT NOT NULL, [Maladies] SMALLINT NOT NULL)
	INSERT INTO @Absences SELECT BusinessEntityID, VacationHours, SickLeaveHours FROM HumanResources.Employee
	DECLARE @IdMax INT, @MaxAbsences INT

	IF ((SELECT [Vacances] + [Maladies] FROM @Absences WHERE Id = 21) NOT LIKE (SELECT [Vacances] + [Maladies] FROM @Absences WHERE Id = 27))
		BEGIN
			SELECT TOP 1
				@IdMax = Id,
				@MaxAbsences = [Vacances] + [Maladies]
			FROM @Absences
			WHERE Id = 21 OR Id = 27
			ORDER BY [Vacances] + [Maladies]
			PRINT CONCAT('L''employé ',@IdMax,' compte le plus d''heures d''absence avec ',@MaxAbsences,' heures au total.')
		END
	ELSE IF ((SELECT [Maladies] FROM @Absences WHERE Id = 21) > (SELECT [Maladies] FROM @Absences WHERE Id = 27)) AND 
			((SELECT [Vacances] FROM @Absences WHERE Id = 21) < (SELECT [Vacances] FROM @Absences WHERE Id = 27))
		PRINT 'Tout va bien !'
	ELSE PRINT 'Rien à signaler...'

--3.5 Afficher, dans une table temporaire dont le nom de la colonne sera « Suivi_employé »
--le statut d’un employé analysé. Selon le cas, si l’employé est né après l’an 2000, cela
--est vraisemblablement impossible. Dans le cas où l’employé est arrivé dans
--l’entreprise entre 1995 et 2005, il est un Junior. Entre 1990 et 1994 il est un Qualified.
--Entre 1985 et 1989, il est Confirmed, sinon, c’est un Vice President !
--Traitez un employé au hasard, de la table HumanResources.Employee.
--Un select vers votre table temporaire suffit !

	IF OBJECT_ID('tempdb..#TempTableFolEmp') IS NULL CREATE TABLE #TempTableFolEmp ([Suivi_employé] VARCHAR(50))
	GO

	DECLARE @IdEmployee INT = 44
	DECLARE @StatusEmployee VARCHAR(50)

	-- Dates modifiées pour avoir un résultat cohérent abec la DB actuelle
	INSERT INTO #TempTableFolEmp
		SELECT
			CASE
				WHEN YEAR(BirthDate) > 2020 THEN 'Vraisemblablement impossible'
				WHEN YEAR(HireDate) BETWEEN 2015 AND 2025 THEN 'Junior'
				WHEN YEAR(HireDate) BETWEEN 2010 AND 2014 THEN 'Qualified'
				WHEN YEAR(HireDate) BETWEEN 2005 AND 2009 THEN 'Confirmed'
				ELSE 'Vice President'
			END
		FROM HumanResources.Employee
		WHERE BusinessEntityID = @IdEmployee

	SELECT * FROM #TempTableFolEmp

	TRUNCATE TABLE #TempTableFolEmp

--3.6 En fonction de l’âge de l’employé traité, prévenez-nous s’il sera bientôt à la retraite ou
--pas via une phrase affichée à l’écran « Attention, retraite imminente pour
--[nom_employé] ! » ou justement, « [nom_employé] a encore de longue années à faire
--chez nous ! »
--Utilisez ici un CASE pour fournir la variable qui contiendra le nom de votre employé.

	DECLARE @AgeRetraite INT = 67
	DECLARE @IdEmployeeRetraite INT = 84
	DECLARE @Retraite VARCHAR(MAX)

	SELECT
		@Retraite = 
		CASE
			WHEN DATEDIFF(YEAR,GETDATE(),BirthDate) > @AgeRetraite-5 THEN 'Attention, retraite imminente pour ' + LastName
			ELSE LastName + ' a encore de longue années à faire chez nous !'
		END
	FROM Person.Person pp JOIN HumanResources.Employee he ON pp.BusinessEntityID = he.BusinessEntityID
	WHERE pp.BusinessEntityID = @IdEmployeeRetraite

	PRINT @Retraite

--3.7 Enregistrez dans une variable de type TABLE, le nombre d’occurrence des noms
--Coleman, Powell, Suarez et Vance. Vous trouverez ces noms dans la table
--Person.Person.
--Il est possible de faire l’opération en une seule requête, cependant faites le également
--en créant pour chaque élément à transférer, une variable supplémentaire.

	DECLARE @Occurence TABLE ( [Nom] NAME, [Occurence] INT )

	INSERT INTO @Occurence SELECT LastName, COUNT(*) FROM Person.Person WHERE LastName LIKE 'Coleman' GROUP BY LastName
	INSERT INTO @Occurence SELECT LastName, COUNT(*) FROM Person.Person WHERE LastName LIKE 'Powell' GROUP BY LastName
	INSERT INTO @Occurence SELECT LastName, COUNT(*) FROM Person.Person WHERE LastName LIKE 'Suarez' GROUP BY LastName
	INSERT INTO @Occurence SELECT LastName, COUNT(*) FROM Person.Person WHERE LastName LIKE 'Vance' GROUP BY LastName

	SELECT * FROM @Occurence

--3.8 S’il existe plus de 20 employés nés avant 1975, alors dans le cas où ils ont plus de 80
--heures d’absence totale (vacances et maladie), afficher dans une table temporaire,
--qu’ils sont en excédent. Dans le cas où ce nombre est entre 60 et 80, ils sont dans la
--norme, dans le cas où ils sont entre 40 et 60 heures d’absence, alors ils sont de bons
--éléments !
--Faites l’exercice également s’il existe plus de 20 employés nés entre 1980 et 1990.

IF OBJECT_ID('tempdb..#TempEmployeeAbences') IS NULL CREATE TABLE #TempEmployeeAbences ([Name] NAME, [Excédent] VARCHAR(50))
GO

DECLARE @EmployeeAbsences TABLE ([Maladie] SMALLINT, [Vacances] SMALLINT)
--INSERT INTO @EmployeeAbsences SELECT SickLeaveHours, VacationHours FROM HumanResources.Employee WHERE YEAR(BirthDate) < 1975
INSERT INTO @EmployeeAbsences SELECT SickLeaveHours, VacationHours FROM HumanResources.Employee WHERE YEAR(BirthDate) BETWEEN 1980 AND 1990

IF((SELECT COUNT(*) FROM @EmployeeAbsences) > 20)
BEGIN
	INSERT INTO #TempEmployeeAbences 
		SELECT 
			LastName,
			CASE
				WHEN (SickLeaveHours + VacationHours) < 60 THEN 'Bon élément !'
				WHEN (SickLeaveHours + VacationHours) BETWEEN 60 AND 80 THEN 'Dans la norme !'
				ELSE 'Excédent de ' + CAST(((SickLeaveHours + VacationHours) - 80) AS VARCHAR(50)) + ' heures.'
			END
		FROM HumanResources.Employee he JOIN Person.Person pp ON pp.BusinessEntityID = he.BusinessEntityID
END

SELECT * FROM #TempEmployeeAbences

TRUNCATE TABLE #TempEmployeeAbences


--4. Boucle et Curseurs

--4.1 Est-il possible de sortir d’une boucle WHILE en T-SQL ? Si oui comment ?
--Testez cette possibilité avec une boucle qui affiche le carré des nombres de 1 à 20 mais
--qui sort de la boucle si le nombre vaut 12.

	-- -> Avec un break
	DECLARE @i INT = 1
	WHILE @i <= 20 AND @i != 12
		BEGIN
			PRINT CONCAT('Le carré de ',@i,' vaut ',@i*@i)
			SET @i += 1
		END

--4.2 Comment déterminer la fin d’une boucle en T-SQL ? Quels sont les 2 choses les plus
--importantes ?
--Créez une boucle WHILE infinie. Cela fait-il planter SQLServer ?

	--> Dans les paramètres du WHILE
	--> BEGIN END et une porte de sortie
	WHILE 1 = 1
		BEGIN
			PRINT '1 !'
		END
	--> Obligé d'arrêter le processus, SQLServer reste en processus ouvert

--4.3 Afficher le carré des nombres impairs allant de 1 à 50 sans prendre les nombres
--compris entre 20 et 30.

	DECLARE @impaire INT = 2
	WHILE @impaire <= 50 
		BEGIN
			IF @impaire NOT BETWEEN 20 AND 30
				PRINT CONCAT('Le carré de ',@impaire,' vaut ',@impaire*@impaire)
			SET @impaire += 2
		END

--4.4 Ecrire une boucle WHILE qui affiche la phrase « Ceci est un nombre divisible par 3 :
--[valeur_divisible_par_3] » pour tous les nombres divisibles par 3 entre 1 et 30

	DECLARE @estDivisiblePar3 INT = 1
	WHILE @estDivisiblePar3 <= 30
		BEGIN
			IF @estDivisiblePar3 % 3 = 0
				PRINT CONCAT('Ceci est un nombre divisible par 3 : ',@estDivisiblePar3)
			SET @estDivisiblePar3 += 1
		END

--4.5 Ecrire une boucle WHILE qui affiche le décompte des années depuis aujourd’hui
--jusqu’à 1983. Incrémenter également un compteur à afficher en fin de décompte dans
--la phrase « [compteur] années ont été décomptées depuis [annee_en_cours] ».

	DECLARE @decompte INT = 0
	WHILE YEAR(GETDATE()) - @decompte >= 1983
		BEGIN
			PRINT YEAR(GETDATE()) - @decompte
			SET @decompte += 1
		END

--4.6 Écrire une boucle qui, pour chaque itération, enregistre la date, sous 5 formats
--différents (vous avez le choix des formats) dans une variable de type TABLE. Afficher
--les données récoltées à l’écran
--Pensez ici aux formats de dates pour CONVERT ainsi qu’à la fonction RAND()…

	DECLARE @TableDates TABLE ([Dates] DATETIME)
	DECLARE @compteurDate INT = 0
	DECLARE @random INT

	WHILE @compteurDate < 5
		BEGIN
			SET @random = FLOOR(RAND()*(112-101+1))+101
			INSERT INTO @TableDates SELECT CONVERT(VARCHAR,GETDATE(),@random)
			SET @compteurDate += 1
		END
	SELECT * FROM @TableDates

--4.7 Ecrire une boucle simple qui permette d’afficher la phrase « [LAST_NAME de
--l’employé] est l’employé dont l’id est [ID de l’employé] » pour les 100 premiers
--employés de la table « Person.Person ».
--Faites l’exercice en déclarant d’abord 2 variables distinctes de type table qui
--contiendront les valeurs pour LastName et BusinessEntityId (sans utiliser de curseur).
--Refaire ensuite l’exercice en stockant d’abord les valeurs récupérées dans un curseur.

	DECLARE @indiceEmploye INT = 0
	DECLARE @LastName NAME
	DECLARE @IdEmployee INT
	
	DECLARE @LastNames TABLE (
		IdLastName INT IDENTITY,
		LastName NAME
	)
	DECLARE @IdEmployees TABLE (
		IdEmployee INT IDENTITY,
		BusinessEntityId INT
	)

	INSERT INTO @LastNames (LastName)
		SELECT TOP 100 LastName
		FROM Person.Person
		ORDER BY BusinessEntityID

	INSERT INTO @IdEmployees (BusinessEntityId)
		SELECT TOP 100 BusinessEntityID
		FROM Person.Person
		ORDER BY BusinessEntityID

	WHILE @indiceEmploye < 100
		BEGIN
			SELECT @LastName = LastName
			FROM @LastNames
			WHERE IdLastName = @indiceEmploye

			SELECT @IdEmployee = BusinessEntityId
			FROM @IdEmployees
			WHERE IdEmployee = @indiceEmploye

			PRINT CONCAT(@LastName,' est l’employé dont l’id est ',@IdEmployee)

			SET @indiceEmploye += 1
		END

	------------ AVEC CURSEUR ------------
	DECLARE @LastName_CR NAME
	DECLARE @IdEmployee_CR INT
	-- Création du curseur en indiquant ce qu'on veut parcourir (ORDRE DES COLONNES)
	DECLARE CR_Employee CURSOR FOR
		SELECT TOP 100 LastName, BusinessEntityID
		FROM Person.Person
		ORDER BY BusinessEntityID
	-- Positionnement du curseur juste avant la première ligne
	OPEN CR_Employee
	-- Placer le curseur sur la prochaine ligne et attribuer les variables
	-- ATTENTION à l'ordre des colonnes
	FETCH NEXT FROM CR_Employee
	INTO @LastName_CR, @IdEmployee_CR

	-- Boucle tant qu'on a des lignes
	WHILE @@FETCH_STATUS = 0
		BEGIN
			PRINT CONCAT(@LastName_CR,' est l’employé dont l’id est ',@IdEmployee_CR)
			FETCH NEXT FROM CR_Employee
			INTO @LastName_CR, @IdEmployee_CR
		END
	-- Fermeture du curseur
	CLOSE CR_Employee
	-- Destruction du curseur => plus en RAM
	DEALLOCATE CR_Employee

--4.8 Récupérer les 200 premiers noms, prénoms et ID d’employés de la table Person.Person
--ainsi que leur Job à partir de la table HumanResources.Employees. Stocker ces valeurs
--dans un curseur. Afficher Les données du curseur dans une table temporaire, mais
--uniquement si ces valeurs correspondent aux employés techniciens de productions
--WC60. Le tri se fera après récupération de l’ensemble des données dans le curseur.

	CREATE TABLE #TempTableTop200 (
		LastName NAME,
		FirstName NAME,
		Id INT,
		JobTitle NVARCHAR(50)
	)
	GO
	DECLARE @LastNameTop200 NAME, @FirstNameTop200 NAME, @IdTop200 INT, @JobTitleTop200 NVARCHAR(50)

	DECLARE CR_Top200Employees CURSOR FOR
		SELECT TOP 200 LastName, FirstName, pp.BusinessEntityID, JobTitle
		FROM Person.Person pp JOIN HumanResources.Employee he ON pp.BusinessEntityID = he.BusinessEntityID
		ORDER BY BusinessEntityID

	OPEN CR_Top200Employees

	FETCH NEXT FROM CR_Top200Employees
	INTO @LastNameTop200, @FirstNameTop200, @IdTop200, @JobTitleTop200

	WHILE @@FETCH_STATUS = 0
		BEGIN
			IF (@JobTitleTop200 LIKE '%WC60%')
				BEGIN
					INSERT INTO #TempTableTop200 VALUES (
						@LastNameTop200, @FirstNameTop200, @IdTop200, @JobTitleTop200
					)
				END
			FETCH NEXT FROM CR_Top200Employees
			INTO @LastNameTop200, @FirstNameTop200, @IdTop200, @JobTitleTop200
		END

	CLOSE CR_Top200Employees

	DEALLOCATE CR_Top200Employees

	SELECT * FROM #TempTableTop200

	DROP TABLE #TempTableTop200

	GO

--4.9 Afficher les données des employés (Nom, Prénom, Date de naissance) rattachés à l’un
--des Jobs. Assurez-vous qu’il ne faille changer les données que d’une seule variable
--afin d’afficher les données des employés rattachés à un autre Job.

	

--4.10 Récupérer la liste des employés dans un curseur.
--Récupérer la liste des ventes de la table Sales.SalesPerson dans un autre curseur.
--Pour chaque employé, si son id (BusinessEntityId) est reprise dans le second curseur,
--afficher le montant de la dernière commission qui le concerne et la date à laquelle elle
--a été modifiée.
--Attention, tout se passe avec les curseurs et les boucles, pas question de faire de
--jointure dans ce cas !



--4.11 Récupérer les produits et leurs noms dans la table Production.Product dans un premier
--curseur.
--Récupérer la quantité commandée des produits dans la table Production.Workorder
--dans un second curseur.
--Pour chaque produit existant, afficher la quantité commandée. Attention, pas de
--COUNT via un SELECT dans ce cas ! De nouveau, tout se passe dans les curseurs et
--les boucles !
--Ne tester la requête que pour les 2000 premières entrées de la table
--Production.Workorder



--4.12 Récupérer le prix le plus récent de chacun des produits dans la table
--Production.productCostHistory.
--Récupérer dans un autre curseur les données concernant chaque produit.
--Pour chaque produit, insérer dans une table temporaire son nom, son prix et la date de
--sa dernière mis à jour.



--5. Procédures et fonctions

--5.1 Créer une procédure qui remplace la fonction de récupération de la date en l’affichant
--directement à l’écran. L’appel de cette procédure permet donc d’un seul coups de
--récupérer la date et l’heure du système, sans passer par « getDate() » ou
--« CURRENT_TIMESTAMP »

	CREATE OR ALTER PROCEDURE sp_AfficherDateSysteme
	AS
	BEGIN
		DECLARE @dateSysteme DATETIME = SYSDATETIME()

		PRINT CONCAT('Date et heure système : ', @dateSysteme)
	END
	GO

	EXEC sp_AfficherDateSysteme
	GO

--5.2 Créer une procédure qui insère dans une table temporaire les données vous concernant.
--Nom, prénom, date de naissance.

	CREATE OR ALTER PROCEDURE sp_InsertMesInfos
	AS
	BEGIN
		CREATE TABLE #MesInfos
		(
			Nom NVARCHAR(50),
			Prenom NVARCHAR(50),
			DateNaissance DATE
		)

		INSERT INTO #MesInfos
		VALUES
		('Mets','Anthony','1986-08-19')

		SELECT * FROM #MesInfos
	END

	EXEC sp_InsertMesInfos
	DROP TABLE #MesInfos
	GO

--5.3 Créer une procédure qui insère des données dans deux tables temporaires distinctes. Si
--l’employé est né avant 1970 et qu’il a été engagé dans l’entreprise avant 2002, il rentre
--dans la première table. S’il est né avant 1980 et après 1970, et qu’il a été engagé avant
--2002 il se retrouve dans la deuxième table

	CREATE OR ALTER PROCEDURE sp_RepartitionEmployes
	AS
	BEGIN
		CREATE TABLE #Employes1
		(
			Id INT,
			BirthDate DATE,
			HireDate DATE
		)

		CREATE TABLE #Employes2
		(
			Id INT,
			BirthDate DATE,
			HireDate DATE
		)

		INSERT INTO #Employes1
		SELECT BusinessEntityID, BirthDate, HireDate
		FROM HumanResources.Employee
		WHERE BirthDate < '1970'
		AND HireDate < '2002'

		INSERT INTO #Employes2
		SELECT BusinessEntityID, BirthDate, HireDate
		FROM HumanResources.Employee
		WHERE BirthDate BETWEEN '1970' AND '1980'
		AND HireDate < '2002'

		SELECT * FROM #Employes1
		SELECT * FROM #Employes2

	END

	EXEC sp_RepartitionEmployes

	DROP TABLE #Employes1
	DROP TABLE #Employes2
	GO

--5.4 Créer une fonction qui renvoi le nombre de ligne contenu dans la table
--HumanResources.Employee.

CREATE OR ALTER FUNCTION fn_NombreEmployes()
RETURNS INT
AS
BEGIN

	DECLARE @nb INT

	SELECT @nb = COUNT(*)
	FROM HumanResources.Employee

	RETURN @nb

END
GO

PRINT CONCAT('Nombre d''employés : ',dbo.fn_NombreEmployes())
GO

--5.5 Créer une fonction qui renvoie le nom du produit (Name de Production.Product) dont
--le prix (StandardCost de Production.ProductCostHistory) a été modifié le plus grand
--nombre de fois. S’il y a ex-aequo, renvoyez celui qui à été modifié en dernier !

CREATE OR ALTER FUNCTION fn_ProduitPlusModifie()
RETURNS NVARCHAR(50)
AS
BEGIN

	DECLARE @NomProduit NVARCHAR(50)

	SELECT TOP 1
		@NomProduit = p.Name
	FROM Production.ProductCostHistory pch
	JOIN Production.Product p
		ON p.ProductID = pch.ProductID
	GROUP BY p.Name
	ORDER BY COUNT(*) DESC, MAX(pch.ModifiedDate) DESC

	RETURN @NomProduit

END
GO

SELECT dbo.fn_ProduitPlusModifie()
GO

--5.6 Créer une procédure ayant un paramètre en mode OUTPUT qui permet de modifier les
--lignes de la table HumanResources.Employee et de mettre la date « ModifiedDate » à
--la date du jour, si cette date n’est pas égale au 31 juillet 2008. La procédure renverra le
--nombre de lignes réellement mises à jour, via son paramètre OUTPUT.

CREATE OR ALTER PROCEDURE sp_UpdateModifiedDate
	@NbLignes INT OUTPUT
AS
BEGIN

	UPDATE HumanResources.Employee
	SET ModifiedDate = GETDATE()
	WHERE ModifiedDate <> '2008-07-31'

	SET @NbLignes = @@ROWCOUNT

END
GO

DECLARE @result INT

EXEC sp_UpdateModifiedDate @NbLignes = @result OUTPUT

PRINT CONCAT('Nombre de lignes modifiées : ', @result)
GO

--5.7 Créer une procédure qui récupère dans un curseur l’ensemble des produits (Name de
--Production.Product) et leur prix (StandardCost de Production.ProductCostHistory)
--dont la valeur a été modifiée le plus récemment uniquement.
--Si le prix est de moins de 15, alors il faut inscrire ce produit dans une table
--non-temporaire que la procédure créera SI ELLE N’EXISTE PAS (référez-vous aux
--exemples du cours pour trouver comment faire !)
--Si le prix est plus grand que 15, alors il faudra insérer dans une table temporaire le
--nom du produit ainsi qu’une phrase associée qui sera soit « prix bien trop élevé » si le
--prix est de plus de 50, soit « prix raisonnable » si le prix est compris entre 15 et 50.
--La procédure renvoi le nombre de valeurs insérées dans la table non-temporaire dans
--un paramètre passé en mode OUTPUT.



--5.8 Créer une procédure qui insère une nouvelle ligne dans la table PERSON.PERSON.
--Les données que vous insérerez seront les vôtres mais elles seront passées en
--paramètre lors de l’appelle de la procédure.



--5.9 Créer une procédure qui permet d’afficher la phrase « X employés travaillent au poste
--de [JobTitle de HumanResources.Employees] »
--Ce nombre X sera renvoyé par une fonction que vous aurez préalablement créée et qui
--demande en paramètre de quel Job il s’agit, paramètre passé par la procédure
--appelante.
--Tester la procédure pour plusieurs JobTitle différents au sein de la procédure.



--5.10 Créer une procédure qui affiche le nom des produits (Name de Production.Product) et
--le prix (ListPrice de Production.Product) des produits appartenant à la même
--sous-catégorie que le nom de catégorie (Name de Production.ProductSubcategory)
--passé en paramètres à la procédure et ayant un prix (ListPrice de Production.Product)
--inférieur à un prix également passé en paramètres.
--Tester la procédure dans le cas de tous les articles ayant un rapport avec « %Bikes% »
--et un prix inférieur à 700.
--La procédure renverra également une valeur en mode OUTPUT qui informera du
--nombre totale de lignes non-affichées par la procédure.



--5.11 Créer une table pouvant récupérer les ID, noms, prénoms, dates d’embauche et dates
-- de naissance des employés de la base de données AdventureWorks.
--Créer une procédure capable de remplir votre table d’un seul coup en utilisant les
--données passées en paramètres. Les données passées seront contenues dans une
--variable de type table qui contiendra les ID, noms, prénoms et dates de naissance des
--employés. La date d’embauche sera la date du jour que vous complèterez vous-même
--à partir de la procédure.



--5.12 Récupérer les données de la table Person.PersonPhone dans une variable de type table.
--Passer la variable fournie à une fonction qui vous permettra de rajouter les numéros de
--téléphones correspondants aux employés que vous avez entrés au point 5.12. Par
--contre, on ne retiendra dans notre table personnelle les numéros que s’ils sont de type
--3 (PhoneNumberTypeID).
--La fonction renvoie le nombre de lignes mises à jour.
--Il faudra bien entendu ajouter la colonne « TelNum » à la table créée au point 5.12



--5.13 Récolter les informations StatePronvinceID et Name de Person.StateProvince que
--vous recouperez avec les informations de Person.Address afin de mettre en relation le
--nom de la province où réside chaque employé dans une variable de type table.
--Créer une procédure qui rajoutera à la table créée au point 5.12, pour chaque employé
--dont le numéro de téléphone est fourni, le nom de la province dans laquelle il réside.
--La colonne supplémentaire devra être rajoutée, bien entendu.



--6. Triggers

--6.1 Sur la base de données DBSlides, remplacer chacune des contraintes (unique, check,
--FK) par des triggers. Rajouter également un trigger qui vérifie bien qu’un délégué de
--cours de la table SECTION est bien un étudiant de la table STUDENT existante
--Il faudra lever des erreurs lorsque les contraintes ne sont pas respectées dans vos
--triggers et afficher des messages à l’utilisateur.



--6.2 Créer un trigger qui permette de vérifier si, lorsqu’on modifie les données de la
--colonne year_result de la table STUDENT, cette valeur est non seulement comprise
--entre 0 et 20 mais également que la modification n’excède pas 30% de sa cote
--précédente.



--6.3 Lorsque l’on supprime des données dans les tables de la base de données
--AdventureWorks, ces informations doivent être récupérées dans des tables d’archivage
--qui permettent de garder une trace des anciennes données pour back-ups éventuels…
--Les tables d’archivage sont créées via le trigger si elles n’existent pas et les insertions
--dans ces tables se feront via des procédures préalablement stockées.
--Faites l’exercice pour 2 tables de la BD.



--6.4 Créer un trigger sur la base de données AdventureWorks qui empêche de supprimer
--une table.



--6.5 Pour chaque étudiant de la table STUDENT de DBSlides, si sa date de naissance est
--inférieure à 1965 et que son résultat annuel n’est toujours pas plus grand que 12, alors
--s’il fait partie de la section « BSc Economics » ou de la section « BSc Management »,
--il faut que lors de la prochaine modification de l’une de ses données, cet étudiant soit
--transféré dans une table « VétéransBSc » que vous devrez créer. Cette nouvelle table
--aura bien entendu les mêmes liens avec la table SECTION que la table STUDENT et
--les étudiants resteront bien entendu délégués de leur section, même en tant que
--VétéransBSc ! Ce qui signifie qu’il faudra faire une modification du trigger qui vérifie
--l’existence d’un délégué dans les tables correspondantes…



--6.6 Créer une nouvelle table INSCRIPTIONS dans la base de données DBSlides, qui
--reprend le nom, prénom, la section où l’étudiant est inscrit et sa date de naissance.
--Une personne inscrite dans la table inscription doit avoir choisi une section
--particulière. Par contre, cette personne ne peut pas être un professeur ou un
--VétéranBSc, ni un étudiant de la table STUDENT qui aurait déjà un résultat annuel.
--Cependant, pour être dans la table inscriptions, il faudra aussi qu’il apparaisse dans la
--table STUDENT. Si ce n’est pas le cas, il faudra d’abord le faire avant de valider
--l’insertion dans votre nouvelle table INSCRIPTIONS



--6.7 Créer un trigger dans la table PROFESSEURS de la BD DBSlides, qui permet de créer
--directement un cours pour ce professeur dans la table cours et qui fera les liens
--nécessaire entre les deux éléments. Le nom du cours sera du type [Cours_ProfName]
--en attendant que la modification de ce nom soit effectuée.
--Dans la table cours, lorsque l’on fait une nouvelle insertion, il faudra qu’un trigger
--lance une procédure permettant d’afficher quels cours sont donnés par quels
--professeurs dans quels sections.



--6.8 Lorsque l’on valide l’inscription d’un étudiant dans la table INSCRIPTION (rajout
--d’un champ de validation dans la table) cet étudiant devra être supprimé de la table
--inscriptions et obtenir la cote de base 8 dans la table STUDENT.
--Mais lorsque l’on modifie cette cote, un trigger de la table STUDENT devra vérifier
--que la personne n’existe plus dans la table INSCRIPTIONS OU que son champ
--validation est bien passé à validé.



--6.9 Créer une procédure qui permet de rajouter une section à la table SECTION de la BD
--DBSlides. Lors de l’insertion d’une donnée dans cette table, un trigger devra se
--déclencher pour vérifier que la valeur de la section est bien conforme à ce qui est
--attendu (chiffre compris entre 1000 et 3000). Si c’est le cas, le trigger lancera une
--procédure qui permettra d’afficher une phrase de retour « Il y a maintenant X sections
--dans la table section. Leurs valeurs sont : … », avec X étant le nombre de sections et la
--liste de ces sections après les deux points.



--6.10 Exercice récapitulatif sur la vente de voitures entre « personnes »

