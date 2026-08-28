use dbslide

SELECT TOP 3
	student_id,
	first_name,
	last_name,
	year_result,
	year_result * 5 AS [year_result * 5],
	CASE
		WHEN year_result >= 15 THEN 'Excellent'
		WHEN year_result >= 10 THEN 'Satisfaisant'
		ELSE 'Faible'
	END AS [Appréciation],
	-- CONCAT(first_name,' ',last_name) AS full_name,
	CONCAT_WS(' - ',first_name, last_name) AS full_name,
	LEFT(first_name, 2), -- Prend les deux premiers caractères
	RIGHT(last_name,2), -- Prend les deux derniers caractères
	SUBSTRING(first_name, 2, 3), -- Récupère les 3 caractères à partir du 2ème
	TRIM(last_name), -- Retire les espaces au début et la la fin
	UPPER(last_name), -- Met la chaine de caractères en majuscule
	LOWER(first_name), -- Met ka chaine de caractères en minuscule
	REPLACE(first_name,'é','e'), -- Remplace la lettre é par e
	LEN(first_name), -- Renvoie la longueur de la chaine de caractères, espaces y compris
	CHARINDEX('a',last_name), -- Renvoie l'index de la lettre a, en commençant à 1
	GETDATE() AS today,
	YEAR(GETDATE()) AS this_year,
	CONCAT(DATEDIFF(YEAR,birth_date,GETDATE()),' ans'),
	DATEFROMPARTS(YEAR(birth_date),MONTH(birth_date),day(birth_date)), -- Affiche la date au format universel yyyy-mm-dd
	FORMAT(birth_date,'dddd, dd MMMM yyyy', 'fr'), -- Affiche la date au format désiré
	course_name
FROM student -- JOIN jusqu'a la table course pour récupérer le course_name
	JOIN section ON student.section_id = section.section_id
	JOIN professor ON section.section_id = professor.section_id
	JOIN course ON course.professor_id = professor.professor_id
WHERE year_result BETWEEN 10 AND 20
	AND first_name LIKE '%a%' -- contient un a
	-- L'utilisation d'un ALIAS dans la clause WHERE est interdite
	-- => ici on ne peut pas utiliser WHERE nom_complet IS NOT NULL par exemple
	-- MAIS on peut l'utiliser dans ORDER BY ou GROUP BY
ORDER BY year_result DESC, first_name ASC


-- COALESCE remplace les NULL par un paramètre passé
SELECT
	section_id,
	COALESCE ([login],CONCAT('.',first_name, last_name), 'Pas de login disponible') AS [Coalesce] -- si first_name ou last_name n'existe pas, on prend le 3ème paramètre
FROM student

SELECT
	first_name,
	year_result,
	NULLIF (year_result, 12) AS [Résultat NULL] -- Le NULLIF ne prend qu'une seule valeur, in utilisera le CASE pour tester les plages
FROM student