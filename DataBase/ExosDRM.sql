USE dbslide

--Exercice 2.1.2 � Ecrire une requ�te pour pr�senter, pour chaque �tudiant, le nom de l��tudiant,
--la date de naissance, le login et le r�sultat pour l�ann�e de l�ensemble des �tudiants.

SELECT
	first_name,
	birth_date,
	[login],
	year_result
FROM student

--Exercice 2.1.3 � Ecrire une requ�te pour pr�senter, pour chaque �tudiant, son nom complet
--(nom et pr�nom s�par�s par un espace), son id et sa date de naissance.

SELECT
	last_name + ' ' + first_name AS full_name,
	student_id,
	birth_date
FROM student

--Exercice 2.1.4 � Ecrire une requ�te pour pr�senter, pour chaque �tudiant, dans une seule
--colonne (nomm�e � Info �tudiant �) l�ensemble des donn�es relatives � un �tudiant s�par�es par le
--symbole � | �. Sous SQL Server, il est n�cessaire d�avoir recours � la fonction de conversion
--CONVERT(type, champs).

SELECT
	CONCAT_WS(' | ', student_id, first_name, last_name, birth_date, [login], section_id, year_result, course_id) AS 'Info �tudiant'
FROM student

-- CONVERT uniquement dans le cas o� on n'utilise pas CONCAT_WS, qui lui g�re d�j� la conversion des champs en string
SELECT 
	CAST(student_id AS varchar) + ' | ' + first_name AS 'infos'
FROM student

--Exercice 2.2.1 � Ecrire une requ�te pour pr�senter le login et le r�sultat de tous les �tudiants
--ayant obtenu un r�sultat annuel sup�rieur � 16

SELECT
	login,
	year_result
FROM student
WHERE year_result > 16

--Exercice 2.2.2 � Ecrire une requ�te pour pr�senter le nom et l�id de section des �tudiants dont
--le pr�nom est Georges

SELECT
	first_name,
	student_id
FROM student
WHERE first_name = 'Georges'

--Exercice 2.2.3 � Ecrire une requ�te pour pr�senter le nom et le r�sultat annuel de tous les
--�tudiants ayant obtenu un r�sultat annuel compris entre 12 et 16

SELECT
	last_name,
	year_result
FROM student
WHERE year_result BETWEEN 12 AND 16

--Exercice 2.2.4 � Ecrire une requ�te pour pr�senter le nom, l�id de section et le r�sultat annuel
--de tous les �tudiants qui ne font pas partie des sections 1010, 1020 et 1110

SELECT
	last_name,
	student_id,
	year_result
FROM student
WHERE section_id NOT IN (1010,1020,1110)

--Exercice 2.2.5 � Ecrire une requ�te pour pr�senter le nom et l�id de section de tous les �tudiants
--qui ont un nom de famille qui termine par � r �

SELECT
	last_name,
	last_name,
	student_id
FROM student
WHERE last_name LIKE '%r'

--Exercice 2.2.6 � Ecrire une requ�te pour pr�senter le nom et le r�sultat annuel de tous les
--�tudiants qui ont un nom de famille pour lequel la troisi�me lettre est un � n � et qui ont obtenu
--un r�sultat annuel sup�rieur � 10

SELECT
	last_name,
	year_result
FROM student
WHERE last_name LIKE '__n%'
	AND year_result > 10

--Exercice 2.2.7 � Ecrire une requ�te pour pr�senter le nom et le r�sultat annuel class� par
--r�sultats annuels d�croissants de tous les �tudiants qui ont obtenu un r�sultat annuel inf�rieur ou
--�gal � 3

SELECT
	last_name,
	year_result
FROM student
WHERE year_result <= 3
ORDER BY year_result DESC 

--Exercice 2.2.8 � Ecrire une requ�te pour pr�senter le nom complet (nom et pr�nom s�par�s par
--un espace) et le r�sultat annuel class� par nom croissant sur le nom de tous les �tudiants
--appartenant � la section 1010

SELECT
	last_name + ' ' + first_name AS [full_name],
	year_result
FROM student
WHERE section_id = 1010
ORDER BY last_name

--Exercice 2.2.9 � Ecrire une requ�te pour pr�senter le nom, l�id de section et le r�sultat annuel
--class� par ordre croissant sur la section de tous les �tudiants appartenant aux sections 1010 et
--1020 ayant un r�sultat annuel qui n�est pas compris entre 12 et 18

SELECT
	last_name,
	section_id,
	year_result
FROM student
WHERE section_id IN (1010, 1020)
	AND year_result NOT BETWEEN 12 AND 18
ORDER BY section_id

--Exercice 2.2.10 � Ecrire une requ�te pour pr�senter le nom, l�id de section et le r�sultat annuel
--sur 100 (nommer la colonne � R�sultat sur 100 �) class� par ordre d�croissant du r�sultat de tous
--les �tudiants appartenant aux sections commen�ant par 13 et ayant un r�sultat annuel sur 100
--inf�rieur ou �gal � 60

SELECT
	first_name,
	section_id,
	year_result,
	year_result * 5 AS [R�sultat sur 100]
FROM student
WHERE section_id LIKE '13%'
	AND year_result <= 60
ORDER BY [R�sultat sur 100] DESC

--Exercice 2.3.1 � Pourquoi lorsque l�on utilise la fonction � MAX � ou � MIN � les valeurs
--� NULL � sont-elles ignor�es ?

	-- Parce que MIN et MAX doivent retourner une valeur -> impossible d�s lors qu'il y a un NULL

--Exercice 2.3.2 � Pourquoi le type des donn�es n�a-t-il pas d�importance lorsque l�on utilise la
--fonction � COUNT � ?

	-- Peu importe les valeurs, COUNT ne se sert que du nombre de lignes dans la table ou la colonne

--Exercice 2.3.3 � La fonction � AVG � renvoie la moyenne de toutes les lignes r�sultantes d�une
--requ�te SELECT sur une colonne incluant toutes les valeurs � NULL �. (Vrai/Faux ?)

	-- Faux, la fonction AVG ne prend pas en compte les NULL pour la m�me raison que MIN et MAX

--Exercice 2.3.4 � La fonction � SUM � est utilis�e pour ajouter des totaux aux colonnes.
--(Vrai/Faux ?)

	-- FAUX, elle retourne la somme des valeurs d'une colonne

--Exercice 2.3.5 � La fonction � COUNT(*) � compte toutes les lignes d�une table. (Vrai/Faux ?)

	-- VRAI

--Exercice 2.3.6 � Les requ�tes suivantes sont-elles valides ?

	-- SELECT COUNT * FROM student
		-- => Non, il manque les parenth�ses
	-- SELECT COUNT(student_id), login FROM student 
		-- => Non, car on demande d'afficher [login] qui n'est pas agr�g�e
	-- SELECT MIN(year_result), MAX(birth_date) FROM student WHERE year_result > 12
		-- => Oui

--Exercice 2.3.7 � Donner le r�sultat annuel moyen pour l�ensemble des �tudiants

	SELECT 
		AVG(year_result) AS [R�sultat annuel moyen] 
	FROM student

--Exercice 2.3.8 � Donner le plus haut r�sultat annuel obtenu par un �tudiant

	SELECT
		MAX(year_result) AS [Meilleur r�sultat annuel]
	FROM student

--Exercice 2.3.9 � Donner la somme des r�sultats annuels

	SELECT
		SUM(year_result) AS [Somme des r�sultats annuels]
	FROM student

--Exercice 2.3.10 � Donner le r�sultat annuel le plus faible

	SELECT
		MIN(year_result) AS [R�sultat annuel le plus faible]
	FROM student

--Exercice 2.3.11 � Donner le nombre de lignes qui composent la table � STUDENT �

	SELECT
		COUNT(*) AS [Nombre de lignes de la table STUDENT]
	FROM student

--Exercice 2.3.12 � Donner la liste des �tudiants (login et ann�e de naissance) n�s apr�s 1970

	SELECT
		[login],
		YEAR(birth_date)
	FROM student
	WHERE YEAR(birth_date) > 1970

--Exercice 2.3.13 � Donner le login et le nom de tous les �tudiants qui ont un nom compos� d�au
--moins 8 lettres

	SELECT
		[login],
		last_name
	FROM student
	WHERE LEN(last_name) >= 8

--Exercice 2.3.14 � Donner la liste des �tudiants ayant obtenu un r�sultat annuel sup�rieur ou
--�gal � 16. La liste pr�sente le nom de l��tudiant en majuscules (nommer la colonne � Nom de
--Famille �) et le pr�nom de l��tudiant dans l�ordre d�croissant des r�sultats annuels obtenus

	SELECT
		UPPER(last_name) AS [Nom de famille],
		first_name
	FROM student
	WHERE year_result > 16
	ORDER BY year_result DESC

--Exercice 2.3.15 � Donner un nouveau login � chacun des �tudiants ayant obtenu un r�sultat
--annuel compris entre 6 et 10. Le login se compose des deux premi�res lettres du pr�nom de
--l��tudiant suivi par les quatre premi�res lettres de son nom le tout en minuscule. Le r�sultat
--reprend pour chaque �tudiant, son nom, son pr�nom l�ancien et le nouveau login (colonne �
--Nouveau login �)

	SELECT
		last_name,
		first_name,
		[login],
		LOWER(CONCAT(LEFT(first_name,2),LEFT(last_name,4))) AS [Nouveau login]
	FROM student
	WHERE year_result BETWEEN 6 AND 10

--Exercice 2.3.16 � Donner un nouveau login � chacun des �tudiants ayant obtenu un r�sultat
--annuel �gal � 10, 12 ou 14. Le login se compose des trois derni�res lettres de son pr�nom suivi du
--chiffre obtenu en faisant la diff�rence entre l�ann�e en cours et l�ann�e de leur naissance. Le
--r�sultat reprend pour chaque �tudiant, son nom, son pr�nom l�ancien et le nouveau login (colonne
--� Nouveau login �)

	SELECT
		last_name,
		first_name,
		[login],
		CONCAT(RIGHT(first_name,3),YEAR(GETDATE()) - YEAR(birth_date)) AS [Nouveau login]
	FROM student
	WHERE year_result IN (10,12,14)

--Exercice 2.3.17 � Donner la liste des �tudiants (nom, login, r�sultat annuel) qui ont un nom
--commen�ant par � D �, � M � ou � S �. La liste doit pr�senter les donn�es dans l�ordre croissant des
--dates de naissance des �tudiants

	SELECT
		last_name,
		[login],
		year_result
	FROM student
	WHERE last_name like '[DMS]%'
	ORDER BY birth_date

--Exercice 2.3.18 � Donner la liste des �tudiants (nom, login, r�sultat annuel) qui ont obtenu un
--r�sultat impair sup�rieur � 10. La liste doit �tre tri�e du plus grand r�sultat au plus petit

	SELECT
		last_name,
		[login],
		year_result
	FROM student
	WHERE (year_result % 2) != 0
		AND year_result > 10
	ORDER BY year_result DESC

--Exercice 2.3.19 � Donner le nombre d��tudiants qui ont au moins 7 lettres dans leur nom de
--famille

	SELECT
		COUNT(*) AS [Nombre d'�tudiants ayant au moins 7 lettres dans leur nom de famille]
	FROM student
	WHERE LEN(last_name) >= 7

--Exercice 2.3.20 � Pour chaque �tudiant n� avant 1955, donner le nom, le r�sultat annuel et le
--statut. Le statut prend la valeur � OK � si l��tudiant � obtenu au moins 12 comme r�sultat annuel et
--� KO � dans le cas contrair.

	SELECT
		last_name,
		year_result,
		CASE
			WHEN year_result >= 12 THEN 'OK'
			ELSE 'KO'
		END AS [Statut]
	FROM student
	WHERE YEAR(birth_date) < 1955

--Exercice 2.3.21 � Donner pour chaque �tudiant n� entre 1955 et 1965 le nom, le r�sultat annuel
--et la cat�gorie � laquelle il appartient. La cat�gorie est fonction du r�sultat annuel obtenu : un
--r�sultat inf�rieur � 10 appartient � la cat�gorie � inf�rieure �, un r�sultat �gal � 10 appartient � la
--cat�gorie � neutre �, un r�sultat autre appartient � la cat�gorie � sup�rieure �

	SELECT
		last_name,
		year_result,
		CASE
			WHEN year_result > 10 THEN 'sup�rieure'
			WHEN year_result = 10 THEN 'neutre'
			ELSE 'inf�rieure'
		END AS [Cat�gorie]
	FROM student
	WHERE YEAR(birth_date) BETWEEN 1955 AND 1965

--Exercice 2.3.22 � Donner pour chaque �tudiant n� entre 1975 et 1985, son nom, son r�sultat
--annuel et sa date de naissance sous la forme: jours en chiffre, mois en lettre et ann�es en quatre
--chiffres (ex : 11 juin 2005)

	SELECT
		last_name,
		year_result,
		FORMAT(birth_date,'dd MMMM yyyy','fr') AS [Date de naissance]
	FROM student
	WHERE YEAR(birth_date) BETWEEN 1975 AND 1986

--Exercice 2.3.23 � Donner pour chaque �tudiant n� en dehors des mois d�hiver et ayant obtenu
--un r�sultat inf�rieur � 7, son nom, le mois de sa naissance (en chiffre) son r�sultat annuel et son
--r�sultat annuel corrig� (� Nouveau r�sultat �) tel que si le r�sultat annuel est �gal � 4, le valeur
--propos�e est � NULL �

	SELECT
		last_name,
		MONTH(birth_date) AS [Mois de naissance (en chiffre)],
		year_result,
		NULLIF(year_result, 4) AS [Nouveau r�sultat]
	FROM student
	WHERE ((MONTH(birth_date) NOT IN (1, 2, 3, 12))
		AND (year_result < 7))

	SELECT
		last_name,
		MONTH(birth_date) AS [Mois de naissance (en chiffre)],
		year_result,
		NULLIF(year_result, 4) AS [Nouveau r�sultat]
	FROM student
	WHERE ((MONTH(birth_date) NOT LIKE '[1-3,12]')
		AND (year_result < 7))

--Exercice 2.4.1 � L�utilisation de � GROUP BY � peut �tre consid�r�e comme une forme de
--boucle dans une requ�te SQL ? (Vrai/Faux)

	-- FAUX

--Exercice 2.4.2 � La r�partition en groupe se fait avant de prendre en compte les restrictions
--impos�es par un � WHERE � ? (Vrai/Faux)

	-- FAUX

--Exercice 2.4.3 � Un � GROUP BY � doit imp�rativement porter sur une colonne non alliac�e ?

	-- Vrai, le SELECT est "lu" apr�s le GROUP BY

--Exercice 2.4.4 � L�utilisation d�un � GROUP BY � a pour effet de trier les r�sultats dans l�ordre
--croissant de la colonne incluse dans le � GROUP BY � ? (Vrai/Faux)

	-- Faux, GROUP BY ne trie pas les r�sultats, il les regroupe. Il faut utiliser ORDER BY pour trier les r�sultats

--Exercice 2.4.5 � La colonne sur laquelle porte le � GROUP BY � doit imp�rativement �tre
--pr�sente dans la clause � SELECT � ? (Vrai/Faux)

	-- Faux, il est possible de faire un GROUP BY sur une colonne qui n'est pas pr�sente dans le SELECT, mais il faut alors utiliser une fonction d'agr�gation pour les autres colonnes du SELECT

--Exercice 2.4.6 � Les requ�tes suivantes sont-elles valides ?



--Exercice 2.4.7 � Donner pour chaque section, le r�sultat maximum (dans une colonne appel�e
--� R�sultat maximum �) obtenu par les �tudiants

	SELECT
		section_id,
		MAX(year_result) AS [R�sultat maximum]
	FROM student
	GROUP BY section_id

--Exercice 2.4.8 � Donner pour toutes les sections commen�ant par 10, le r�sultat annuel moyen
--PR�CIS (dans une colonne appel�e � Moyenne �) obtenu par les �tudiants

	SELECT
		section_id,
		AVG(CONVERT(FLOAT,year_result)) AS [Moyenne]
	FROM student
	WHERE CONVERT(VARCHAR,section_id) LIKE '10%'
	GROUP BY section_id

--Exercice 2.4.9 � Donner le r�sultat moyen (dans une colonne appel�e � Moyenne �) et le mois
--en chiffre (dans une colonne appel�e � Mois de naissance �) pour les �tudiants n�s le m�me mois
--entre 1970 et 1985

	SELECT
		MONTH(birth_date) AS [Mois de naissance],
		AVG(year_result) AS [Moyenne]
	FROM student
	WHERE YEAR(birth_date) BETWEEN 1970 AND 1985
	GROUP BY MONTH(birth_date)

--Exercice 2.4.10 � Donner pour toutes les sections qui comptent plus de 3 �tudiants, la
--moyenne PR�CISE des r�sultats annuels (dans une colonne appel�e � Moyenne �)

	SELECT
		section_id,
		AVG(CAST(year_result AS FLOAT)) AS [Moyenne]
	FROM student
	GROUP BY section_id
	HAVING COUNT(first_name) > 3
	

--Exercice 2.4.11 � Donner le r�sultat maximum obtenu par les �tudiants appartenant aux
--sections dont le r�sultat moyen est sup�rieur � 8

	SELECT
		section_id,
		MAX(year_result) AS [R�sultat maximum],
		AVG(year_result) AS [Moyenne]
	FROM student
	GROUP BY section_id
	HAVING AVG(section_id) > 8

--Exercice 2.5.1 � L�utilisation de � ROLLUP � cr�e des groupes de donn�es en se d�pla�ant dans
--une seule direction, partant de la gauche vers la droite par rapport aux colonnes s�lectionn�es ?
--(Vrai/Faux)

	-- Vrai

--Exercice 2.5.2 � Le r�sultat produit par un � ROLLUP � pr�sente les r�sultats du plus agr�g� au
--moins agr�g� ? (Vrai/Faux)

	-- Faux, le r�sultat est pr�sent� dans l'ordre des colonnes donn�es en param�tre.

--Exercice 2.5.3 � L�op�rateur � CUBE � permet de produire moins de sous-totaux qu�avec
--l�op�rateur � ROLLUP � ? (Vrai/Faux)

	-- Faux, CUBE g�n�re des totaux pour toutes les combinaisons possibles en fonction des colonnes pass�es en param�tre

--Exercice 2.5.4 � Avec l�op�rateur � CUBE �, le nombre de groupes dans le r�sultat est
--ind�pendant du nombre de colonnes s�lectionn�es dans le � GROUP BY � ? (Vrai/Faux)

	-- Faux, le nombre de groupes g�n�r�s par CUBE est fonction du nombre de colonnes pass�es en param�tre

--Exercice 2.5.5 � L�op�rateur � CUBE � ne peut pas �tre appliqu� � la fonction d�agr�gation
--� SUM � ? (Vrai/Faux)

	-- Faux, CUBE peut �ter appliqu� � toutes les fonctions d'agr�gation, comme ROLLUP d'ailleurs

--Exercice 2.5.6 � Donner la moyenne exacte des r�sultats obtenus par les �tudiants par section
--et par cours, ainsi que la moyenne par section uniquement et enfin, la moyenne g�n�rale. La liste
--ainsi produite reprend l�id de section, de cours le r�sultat moyen (dans une colonne appel�e �
--Moyenne �). Se baser uniquement sur les sections 1010 et 1320

	SELECT
		section_id,
		course_id,
		AVG(CAST(year_result AS FLOAT)) AS [Moyenne]
	FROM student
	WHERE section_id IN (1010, 1320)
	GROUP BY ROLLUP (section_id, course_id)

--Exercice 2.5.7 � Donner la moyenne exacte des r�sultats obtenus par les �tudiants par cours et
--par section, ainsi que la moyenne par cours uniquement, puis par section uniquement et enfin, la
--moyenne g�n�rale. La liste ainsi produite reprend l�id de section, de cours le r�sultat moyen (dans
--une colonne appel�e � Moyenne �). Se baser uniquement sur les sections 1010 et 1320

	SELECT 
		course_id,
		section_id,
		AVG(CAST(year_result AS FLOAT)) AS [Moyenne]
	FROM student
	WHERE section_id IN (1010, 1320)
	GROUP BY CUBE (course_id, section_id)

--Exercice 2.5.8 � Ceci cl�ture la troisi�me partie DRL du cours. Avant de passer � la suite
--de la mati�re, nous vous invitons � prendre un peu de temps afin d��valuer
--personnellement votre niveau de compr�hension de la mati�re en vous r�f�rant aux
--derniers slides du module (slides d�auto-�valuation)

--Exercice 2.6.1 � Donner pour chaque cours le nom du professeur responsable ainsi que la
--section dont le professeur fait partie

	SELECT
		c.course_name,
		s.section_name,
		p.professor_name
	FROM course c
		JOIN professor p ON p.professor_id = c.professor_id
		JOIN section s ON s.section_id = p.section_id

--Exercice 2.6.2 � Donner pour chaque section, l�id, le nom et le nom de son d�l�gu�. Classer les
--sections dans l�ordre inverse des id de section. Un d�l�gu� est un �tudiant de la table � STUDENT �

	SELECT
		s.section_id,
		s.section_name,
		student.last_name
	FROM section s
		JOIN student ON s.delegate_id = student.student_id
	ORDER BY s.section_id DESC

--Exercice 2.6.3 � Donner pour chaque section, le nom des professeurs qui en sont membre

	SELECT
		s.section_id,
		s.section_name,
		p.professor_name
	FROM section s
		LEFT JOIN professor p ON p.section_id = s.section_id
	ORDER BY s.section_id DESC

--Exercice 2.6.4 � M�me objectif que la question 3 mais seuls les sections comportant au moins
--un professeur doivent �tre reprises

	SELECT
		s.section_id,
		s.section_name,
		p.professor_name
	FROM section s
		JOIN professor p ON p.section_id = s.section_id
	ORDER BY s.section_id DESC

--Exercice 2.6.5 � Donner � chaque �tudiant ayant obtenu un r�sultat annuel sup�rieur ou �gal �
--12 son grade en fonction de son r�sultat annuel et sur base de la table grade. La liste doit �tre
--class�e dans l�ordre alphab�tique des grades attribu�s

	SELECT
		last_name,
		year_result,
		grade
	FROM student
		JOIN grade ON year_result BETWEEN lower_bound AND upper_bound
	WHERE year_result >= 12
	ORDER BY grade

--Exercice 2.6.6 � Donner la liste des professeurs et la section � laquelle ils se rapportent ainsi
--que le(s) cour(s) (nom du cours et cr�dits) dont le professeur est responsable. La liste est tri�e par
--ordre d�croissant des cr�dits attribu�s � un cours

	SELECT
		professor_name,
		section_name,
		course_name,
		course_ects
	FROM professor
		LEFT JOIN section ON section.section_id = professor.section_id
		LEFT JOIN course ON course.professor_id = professor.professor_id
	ORDER BY course_ects DESC

--Exercice 2.6.7 � Donner pour chaque professeur son id et le total des cr�dits ECTS
--(� ECTS_TOT �) qui lui sont attribu�s. La liste propos�e est tri�e par ordre d�croissant de la somme
--des cr�dits allou�s

	SELECT
		professor.professor_id,
		SUM(course.course_ects) AS [ECTS_TOT]
	FROM professor
		LEFT JOIN course ON course.professor_id = professor.professor_id
	GROUP BY professor.professor_id
	ORDER BY [ECTS_TOT] DESC

--Exercice 2.6.8 � Donner la liste (nom et pr�nom) de l�ensemble des professeurs et des �tudiants
--dont le nom est compos� de plus de 8 lettres. Ajouter une colonne pour pr�ciser la cat�gorie (S
--pour � STUDENT �, P pour � PROFESSOR �) � laquelle appartient l�individu

	SELECT
		first_name,
		last_name,
		'S' AS [Cat�gorie]
	FROM student
	WHERE LEN(student.last_name) > 8

	UNION

	SELECT
		professor_surname,
		professor_name,
		'P' AS [Cat�gorie]
	FROM professor
	WHERE LEN(professor.professor_name) > 8

--Exercice 2.6.9 � Donner l�id de chacune des sections qui n�ont pas de professeur attitr�

	SELECT
		section_id
	FROM section
	WHERE section_id NOT IN
	(
		SELECT DISTINCT section_id
		FROM professor
	)

--Exercice 2.6.10 � Ceci cl�ture la quatri�me partie DRL du cours. Avant de passer � la suite
--de la mati�re, nous vous invitons � prendre un peu de temps afin d��valuer
--personnellement votre niveau de compr�hension de la mati�re en vous r�f�rant aux
--derniers slides du module (slides d�auto-�valuation)

	
--Exercice 2.7.1 � Donner la liste des �tudiants (nom et pr�nom) qui font partie de la m�me
--section que mademoiselle � Roberts �. La liste doit �tre class�e par ordre alphab�tique sur le nom
--et mademoiselle � Roberts � ne doit pas apparaitre dans la liste
	
	SELECT 
		first_name,
		last_name
	FROM student
	WHERE section_id IN
	(
		SELECT section_id
		FROM student
		WHERE last_name = 'Roberts'
	)
		AND last_name != 'Roberts'
	ORDER BY last_name

--Exercice 2.7.2 � Donner la liste des �tudiants (nom, pr�nom et r�sultat) de l�ensemble des
--�tudiants ayant obtenu un r�sultat annuel sup�rieur au double du r�sultat moyen pour l�ensemble
--des �tudiants

	SELECT
		last_name,
		first_name,
		year_result AS [R�sultat annuel]
	FROM student
	WHERE year_result >
	(
		SELECT AVG(year_result) * 2
		FROM student
	)

--Exercice 2.7.3 � Donner la liste de toutes les sections qui n�ont pas de professeur

	SELECT
		section_name
	FROM section
	WHERE section_id NOT IN
	(
			SELECT section_id
			FROM professor
		)

--Exercice 2.7.4 � Donner la liste des �tudiants qui ont comme mois de naissance le mois
--correspondant � la date d�engagement du professeur � Giot �. Classer les �tudiants par ordre de
--r�sultat annuel d�croissant

	SELECT
		last_name,
		first_name,
		FORMAT(birth_date,'MM/dd/yyyy','fr') AS [Date de naissance],
		year_result
	FROM student
	WHERE MONTH(birth_date) IN
	(
		SELECT MONTH(professor_hire_date)
		FROM professor
		WHERE professor_name = 'Giot'
	)
	ORDER BY year_result DESC

--Exercice 2.7.5 � Donner la liste des �tudiants qui ont obtenu le grade � TB � pour leur r�sultat
--annuel

	SELECT
		last_name,
		first_name,
		year_result
	FROM student
	WHERE year_result BETWEEN
	(
		SELECT lower_bound
		FROM grade
		WHERE grade = 'TB'
	)
	AND
	(
		SELECT upper_bound
		FROM grade
		WHERE grade = 'TB'
	)

--Exercice 2.7.6 � Donner la liste des �tudiants qui appartienne � la section pour laquelle
--Mademoiselle � Marceau � est d�l�gu�e

	SELECT
		last_name,
		first_name,
		section_id
	FROM student
	WHERE section_id IN
	(
		SELECT
			section.section_id
		FROM section JOIN student ON student.student_id = section.delegate_id
		WHERE student.last_name = 'Marceau'
	)

--Exercice 2.7.7 � Donner la liste des sections qui se composent de plus de quatre �tudiants

	SELECT
		section_id,
		section_name
	FROM section
	WHERE section_id IN
	(
		SELECT
			section_id
		FROM student
		GROUP BY section_id
		HAVING COUNT(student_id) > 4
	)

--Exercice 2.7.8 � Donner la liste des �tudiants premiers de leur section en terme de r�sultat
--annuel et qui n�appartiennent pas aux sections dont le r�sultat moyen est inf�rieure � 10

	SELECT
		last_name,
		first_name,
		section_id
	FROM student
	WHERE year_result IN
	(
		SELECT
			MAX(year_result)
		FROM student
		GROUP BY section_id
	)
	AND section_id NOT IN
	(
		SELECT
			section_id
		FROM student
		GROUP BY section_id
		HAVING AVG(year_result) < 10
	)
	ORDER BY section_id DESC

--Exercice 2.7.9 � Donner la section qui poss�de la moyenne la plus �lev�e. Le r�sultat pr�sente
--le num�ro de section ainsi que sa moyenne
		
-- En utilisant une CTE (table temporaire)
	WITH 
	[MoyenneCTE] AS (
		SELECT
			section_id,
			AVG(year_result) AS [Moyenne]
		FROM student
		GROUP BY section_id
	)
	SELECT
		section_id,
		[Moyenne] AS [Moyenne la plus �lev�e]
	FROM [MoyenneCTE]
	WHERE [Moyenne] = 
	(
		SELECT MAX([Moyenne]) 
		FROM [MoyenneCTE]
	)

-- En ne prenant que le 1er

	SELECT TOP 1
		section_id,
		AVG(year_result) AS [Moyenne la plus �lev�e]
	FROM student
	GROUP BY section_id
	ORDER BY [Moyenne la plus �lev�e] DESC

--Exercice 2.7.10 � Ceci cl�ture la cinqui�me partie DRL du cours. Avant de passer � la
--suite de la mati�re, nous vous invitons � prendre un peu de temps afin d��valuer
--personnellement votre niveau de compr�hension de la mati�re en vous r�f�rant aux
--derniers slides du module (slides d�auto-�valuation)

CREATE DATABASE MARVEL
USE MARVEL
GO

CREATE TABLE Employe
(
    Id INT NOT NULL IDENTITY,
    LastName NVARCHAR(50) NOT NULL,
    FirstName NVARCHAR(50) NOT NULL,
    ManagerId INT NULL,
);


SET IDENTITY_INSERT Employe ON;

INSERT INTO Employe (Id, LastName, FirstName, ManagerId)
VALUES
(1, 'Fury', 'Nick', NULL),
(2, 'Rogers', 'Steve', 1),
(3, 'Stark', 'Tony', 2),
(4, 'Romanov', 'Natasha', 1),
(5, 'Banner', 'Bruce', 4);

SET IDENTITY_INSERT Employe OFF;

SELECT * FROM Employe;

WITH Records
AS
(
    SELECT Id, LastName, FirstName, ManagerId, 1 AS Niv -- 1 est le niveau de r�f�rence
    FROM Employe
    WHERE ManagerId IS NULL

    UNION ALL
    
	SELECT e.Id, e.LastName, e.FirstName, e.ManagerId, Niv + 1
    FROM Employe AS e
    JOIN Records AS r ON e.ManagerId = r.Id
)
SELECT *
FROM Records