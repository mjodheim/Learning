using Domain;
using System.Data;
using System.Reflection;

namespace ExercicesLinq;

internal class Program
{
    static void Main(string[] args)
    {
        #region exos
        DataContext dc = new DataContext();

        // Exercice 2.1 Ecrire une requête pour présenter, pour chaque étudiant, le nom de l’étudiant, la
        // date de naissance, le login et le résultat pour l’année de l’ensemble des étudiants.
        void Exo2_1()
        {
            var result = dc.Students.Select(s => new { s.First_Name, s.BirthDate, s.Login, s.Year_Result });

            Print(result);
        }

        // Exercice 2.2 Ecrire une requête pour présenter, pour chaque étudiant, son nom complet(nom
        // et prénom séparés par un espace), son id et sa date de naissance.
        void Exo2_2()
        {
            var result = dc.Students.Select(s => new { Prenom = s.First_Name, Nom = s.Last_Name, DateDeNaissance = s.BirthDate, Id = s.Student_ID });

            foreach (var student in result)
            {
                Console.WriteLine($"{student.Nom} {student.Prenom} a l'id {student.Id} et est né(e) le {student.DateDeNaissance}");
            }
        }

        // Exercice 2.3 Ecrire une requête pour présenter, pour chaque étudiant, dans une seule chaine de
        // caractère l’ensemble des données relatives à un étudiant séparées par le symbole |.
        void Exo2_3()
        {
            Type studentType = typeof(Student);
            IEnumerable<PropertyInfo> properties = studentType.GetProperties().ToArray();

            var result = dc.Students.Select(s => string.Join(" | ", properties.Select(p => p.GetValue(s))));

            Print(result);
        }

        // Exercice 3.1 Pour chaque étudiant né avant 1955, donner le nom, le résultat annuel et le statut.
        // Le statut prend la valeur « OK » si l’étudiant à obtenu au moins 12 comme résultat annuel
        // et « KO » dans le cas contraire. 
        void Exo3_1()
        {
            var result = dc.Students.Where(s => s.BirthDate.Year < 1955).Select(s => new { s.Last_Name, s.Year_Result, status = s.Year_Result > 12 ? "OK" : "KO" });

            Print(result);
        }

        // Exercice 3.2 Donner pour chaque étudiant entre 1955 et 1965 le nom, le résultat annuel et la
        // catégorie à laquelle il appartient.La catégorie est fonction du résultat annuel obtenu; un
        // résultat inférieur à 10 appartient à la catégorie « inférieure », un résultat égal à 10 appartient
        // à la catégorie « neutre », un résultat autre appartient à la catégorie « supérieure ».
        void Exo3_2()
        {
            var result = dc.Students.Where(s => s.BirthDate.Year > 1955 && s.BirthDate.Year > 1965).Select(s => new { s.Last_Name, s.Year_Result,
                categorie = s.Year_Result switch
                {
                    < 10 => "inférieur",
                    > 10 => "supérieur",
                    _ => "neutre"
                }
            });

            Print(result);
        }

        // Exercice 3.3 Ecrire une requête pour présenter le nom, l’id de section et de tous les étudiants
        // qui ont un nom de famille qui termine par r.
        void Exo3_3()
        {
            var result = dc.Students.Where(s => s.Last_Name.EndsWith('r')).Select(s => new { s.Last_Name, s.Section_ID });

            Print(result);
        }

        // Exercice 3.4 Ecrire une requête pour présenter le nom et le résultat annuel classé par résultats
        // annuels décroissant de tous les étudiants qui ont obtenu un résultat annuel inférieur ou égal
        // à 3.
        void Exo3_4()
        {
            var result = dc.Students.Where(s => s.Year_Result <= 3).Select(s => new { s.Last_Name, s.Year_Result }).OrderByDescending(s => s.Year_Result);

            Print(result);
        }

        // Exercice 3.5 Ecrire une requête pour présenter le nom complet(nom et prénom séparés par un
        // espace) et le résultat annuel classé par nom croissant sur le nom de tous les étudiants
        // appartenant à la section 1110
        void Exo3_5()
        {
            var result = dc.Students.Where(s => s.Section_ID == 1110).Select(s => s.Last_Name + " " + s.First_Name + " | Résultat annuel : " + s.Year_Result );

            Print(result);
        }

        // Exercice 3.6 Ecrire une requête pour présenter le nom, l’id de section et le résultat annuel
        // classé par ordre croissant sur la section de tous les étudiants appartenant aux sections 1010
        // et 1020 ayant un résultat annuel qui n’est pas compris entre 12 et 18.
        void Exo3_6()
        {
            var result = dc.Students.Where(s => (s.Section_ID == 1010 || s.Section_ID == 1020) && (s.Year_Result < 12 || s.Year_Result > 18))
                .Select(s => new { s.Last_Name, s.Section_ID, s.Year_Result })
                .OrderBy(s => s.Section_ID);

            Print(result);
        }

        // Exercice 3.7 Ecrire une requête pour présenter le nom, l’id de section et le résultat annuel sur
        // 100(nommer la colonne ‘result_100’) classé par ordre décroissant du résultat de tous les
        // étudiants appartenant aux sections commençant par 13 et ayant un résultat annuel sur 100
        // inférieur ou égal à 60.
        void Exo3_7()
        {
            Print(
                dc.Students.Where(s => s.Section_ID.ToString().StartsWith("13") && (s.Year_Result * 5 <= 60))
                .Select(s => new {s.Last_Name, s.Section_ID, result_100 = s.Year_Result * 5 })
                .OrderByDescending(s => s.result_100)
            );
        }

        // Exercice 4.1 Donner le résultat annuel moyen pour l’ensemble des étudiants.
        void Exo4_1()
        {
            Console.WriteLine("Résultat annuel moyen : {0}", dc.Students.Average(s => (float)s.Year_Result)); 
        }

        // Exercice 4.2 Donner le plus haut résultat annuel obtenu par un étudiant.
        void Exo4_2()
        {
            Console.WriteLine("Plus haut résultat annuel : {0}", dc.Students.Max(s => s.Year_Result));
        }

        // Exercice 4.3 Donner la somme des résultats annuels.
        void Exo4_3()
        {
            Console.WriteLine("Somme des résultats annuels : {0}", dc.Students.Sum(s => s.Year_Result));
        }

        // Exercice 4.4 Donner le résultat annuel le plus faible.
        void Exo4_4()
        {
            Console.WriteLine("Résultat annuel le plus faible : {0}", dc.Students.Min(s => s.Year_Result));
        }

        // Exercice 4.5 Donner le nombre de lignes qui composent la séquence « Students » ayant obtenu
        // un résultat annuel impair.
        void Exo4_5()
        {
            Console.WriteLine("Nombre de lignes : {0}", 
                dc.Students.Count(s => s.Year_Result % 2 is not 0)
                );
        }

        // Exercice 5.1 Donner pour chaque section, le résultat maximum (« Max_Result ») obtenu par les
        // étudiants
        void Exo5_1()
        {
            IEnumerable<IGrouping<int,Student>> groups = dc.Students.GroupBy(s => s.Section_ID);

            foreach (IGrouping<int,Student> group in groups)
            {
                Console.WriteLine("Groupe {0} | Max_Result : {1}", group.Key, group.Max(s => s.Year_Result));
            }
        }

        // Exercice 5.2 Donner pour toutes les sections commençant par 10, le résultat annuel moyen
        // (« AVGResult ») obtenu par les étudiants.
        void Exo5_2()
        {
            IEnumerable<IGrouping<int, Student>> groups = dc.Students.Where(s => s.Section_ID.ToString().StartsWith("10")).GroupBy(s => s.Section_ID);

            foreach (IGrouping<int, Student> group in groups)
            {
                Console.WriteLine($"Groupe {group.Key} | AVGResult : {group.Average(s => s.Year_Result)}");
            }
        }

        // Exercice 5.3 Donner le résultat moyen (« AVGResult ») et le mois en chiffre (« BirthMonth »)
        // pour les étudiants né le même mois entre 1970 et 1985.

        void Exo5_3()
        {
            var groups = dc.Students
                .Where(s => s.BirthDate.Year >= 1970 && s.BirthDate.Year <= 1985)
                .GroupBy(s => s.BirthDate.Month)
                .Select(g => new
                {
                    BirthMonth = g.Key,
                    AVGResult = g.Average(s => s.Year_Result)
                });

            foreach (var group in groups)
            {
                Console.WriteLine($"Mois : {group.BirthMonth} | Moyenne : {group.AVGResult}");
            }
        }

        // Exercice 5.4 Donner pour toutes les sections qui compte plus de 3 étudiants, la moyenne des
        // résultats annuels(« AVGResult »).
        void Exo5_4()
        {
            var groups = dc.Students
            .GroupBy(s => s.Section_ID)
            .Where(g => g.Count() > 3)
            .Select(g => new
            {
                section = g.Key,
                AVGResult = g.Average(s => s.Year_Result)
            });

            foreach (var group in groups)
            {
                Console.WriteLine($"Section : {group.section} | Moyenne : {group.AVGResult}");
            }
        }

        // Exercice 5.5 Donner pour chaque cours, le nom du professeur responsable ainsi que la section
        // dont le professeur fait partie.
        void Exo5_5()
        {
            var result = dc.Courses
                .Join(dc.Professors,
                    c => c.Professor_ID,
                    p => p.Professor_ID,
                    (c,p) => new {c,p})
                .Join(dc.Sections,
                    cp => cp.p.Section_ID,
                    s => s.Section_ID,
                    (cp, s) => new
                    {
                        courseName = cp.c.Course_Name,
                        professorName = cp.p.Professor_Name,
                        sectionName = s.Section_Name
                    });

            Console.WriteLine($"{"Cours", -50} {"Section", -30} {"Professeur", -20}");
            Console.WriteLine(new string('-', 100));

            foreach (var course in result)
            {
                Console.WriteLine($"{course.courseName, -50} {course.sectionName, -30} {course.professorName, -20}");
            }
        }

        // Exercice 5.6 Donner pour chaque section, l’id, le nom et le nom de son délégué. Classer les
        // sections dans l’ordre inverse des id de section
        void Exo5_6()
        {
            var result = dc.Sections
                .Join(dc.Students,
                    se => se.Delegate_ID,
                    st => st.Student_ID,
                    (se, st) => new
                    {
                        sectionId = se.Section_ID,
                        sectionName = se.Section_Name,
                        delegateName = st.Last_Name
                    })
                .OrderByDescending(se_st => se_st.sectionId);

            Console.WriteLine($"{"ID section",-15} {"Nom section",-20} {"Nom délégué",-20}");
            Console.WriteLine(new string('-', 55));

            foreach (var item in result)
            {
                Console.WriteLine($"{item.sectionId,-15} {item.sectionName,-20} {item.delegateName,-20}");
            }
        }

        // Exercice 5.7 Donner, pour toutes les sections, le nom des professeurs qui en sont membres
        void Exo5_7()
        {
            var result = from se in dc.Sections
                         join p in dc.Professors on se.Section_ID equals p.Section_ID into se_p
                         from p in se_p.DefaultIfEmpty() // Pour chaque prof dans la jointure, par ligne, si une section est vide on la prend quand-même avec null
                         select new
                         {
                             sectionName = se.Section_Name,
                             professors = p is null ? "N/A" : p.Professor_Name
                         };

            Console.WriteLine($"{"Nom section",-20} {"Nom des professeurs membres",-30}");
            Console.WriteLine(new string('-', 50));

            foreach (var item in result)
            {
                Console.WriteLine($"{item.sectionName,-20} {item.professors,-20}");
            }
        }

        // Exercice 5.8 Même objectif que la question 5.7, mais seules les sections comportant au moins
        // un professeur doivent être reprises.
        void Exo5_8()
        {
            var result = dc.Sections
                .Join(dc.Professors,
                    se => se.Section_ID,
                    p => p.Section_ID,
                    (se,p) => new
                    {
                        sectionName = se.Section_Name,
                        professorName = p.Professor_Name
                    });

            Console.WriteLine($"{"Nom section",-20} {"Nom des professeurs membres",-30}");
            Console.WriteLine(new string('-', 50));

            foreach (var item in result)
            {
                Console.WriteLine($"{item.sectionName,-20} {item.professorName,-20}");
            }
        }

        // Exercice 5.9 Donner à chaque étudiant ayant obtenu un résultat annuel supérieur ou égal à 12
        // son grade en fonction de son résultat annuel et sur base de la table grade. La liste doit être
        // classée dans l’ordre alphabétique des grades attribués.
        void Exo5_9()
        {
            var result = dc.Students
                .Where(st => st.Year_Result >= 12)
                .Join(dc.Grades,
                    st => true,
                    gr => true,
                    (st, gr) => new { st, gr})
                .Where(r => r.st.Year_Result >= r.gr.Lower_Bound && r.st.Year_Result <= r.gr.Upper_Bound)
                .Select(g => new
                {
                    studentName = g.st.Last_Name,
                    result = g.st.Year_Result,
                    grade = g.gr.GradeName
                })
                .OrderBy(l => l.grade);

            Console.WriteLine($"{"Nom de l'étudiant",-20} {"Résultat annuel",-20} {"Grade",-15}");
            Console.WriteLine(new string('-', 55));

            foreach (var item in result)
            {
                Console.WriteLine($"{item.studentName,-20} {item.result,-20} {item.grade,-15}");
            }
        }

        // Exercice 5.10 Donner la liste des professeurs et la section à laquelle ils se rapportent ainsi que
        // le(s) cour(s)(nom du cours et crédits) dont le professeur est responsable.La liste est triée
        // par ordre décroissant des crédits attribués à un cours.
        void Exo5_10()
        {
            var result = dc.Professors
                .Join(dc.Sections,
                   p => p.Section_ID,
                   se => se.Section_ID,
                   (p, se) => new {p, se })
                .Join(dc.Courses,
                    p_se => p_se.p.Professor_ID,
                    c => c.Professor_ID,
                    (p_se, c) => new
                   {
                       professorName = p_se.p.Professor_Name,
                       sectionName = p_se.se.Section_Name,
                       courseName = c.Course_Name,
                       credits = c.Course_Ects
                   })
                .OrderByDescending(c => c.credits);

            Console.WriteLine($"{"Nom du professeur",-20} {"Nom de la section",-20} {"Cours",-40} {"Crédits",-15}");
            Console.WriteLine(new string('-', 95));

            foreach (var item in result)
            {
                Console.WriteLine($"{item.professorName,-20} {item.sectionName,-20} {item.courseName,-40} {item.credits,-15}");
            }
        }

        // Exercice 5.11 Donner pour chaque professeur son id et le total des crédits ECTS
        // (« ECTSTOT ») qui lui sont attribués. La liste proposée est triée par ordre décroissant de la
        // somme des crédits alloués.
        void Exo5_11()
        {
            var result = dc.Professors
                .Join(dc.Courses,
                    p => p.Professor_ID,
                    c => c.Professor_ID,
                    (p,c) => new
                    {
                        p.Professor_ID,
                        c.Course_Ects
                    })
                .GroupBy(p => p.Professor_ID)
                .Select(t => new
                {
                    professorId = t.Key,
                    ECTSTOT = t.Sum(c => c.Course_Ects)
                })
                .OrderByDescending(r => r.ECTSTOT);

            Console.WriteLine($"{"Id du professeur",-20} {"Crédits",-15}");
            Console.WriteLine(new string('-', 95));

            foreach (var item in result)
            {
                Console.WriteLine($"{item.professorId,-20} {item.ECTSTOT,-20}");
            }
        }

        #endregion

        // Lanceur
        Exo5_11();

        #region Functions

        void Print<T>(IEnumerable<T> result)
        {
            foreach (var item in result)
            {
                Console.WriteLine(item);
            }
        }

        #endregion
    }
}
