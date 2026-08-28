int[][] notesTab;
int nbClasses, nbStudents, maxNoteByClass = 0, maxGlobalNote = 0, nbGlobalStudents = 0;
decimal averageNote = 0;
Random random = new Random();

do
{
    System.Console.Write("Combien de classes y a-t-il ? ");
} while (!int.TryParse(Console.ReadLine(), out nbClasses));

notesTab = new int[nbClasses][];

for (int i = 0; i < nbClasses; i++)
{
    do
    {
        System.Console.Write($"Nombre d'élèves dans la classe {i + 1} ? ");
    } while (!int.TryParse(Console.ReadLine(), out nbStudents));

    notesTab[i] = new int[nbStudents];
    nbGlobalStudents += nbStudents;

    // On rempli avec des notes aléatoires
    for (int j = 0; j < nbStudents; j++)
    {
        notesTab[i][j] = random.Next(21);
        averageNote += notesTab[i][j];
        if (notesTab[i][j] > maxNoteByClass)
        {
            maxNoteByClass = notesTab[i][j];
        }
    }
    if (maxNoteByClass > maxGlobalNote)
    {
        maxGlobalNote = maxNoteByClass;
    }
    System.Console.WriteLine($"Note maximale pour la classe {i + 1} : {maxNoteByClass}");
}

if (maxGlobalNote != 0)
{
    averageNote /= nbGlobalStudents;
}

System.Console.WriteLine($"""
     Note maximale globale : {maxGlobalNote}
     Note moyenne globale : {averageNote}
 """);