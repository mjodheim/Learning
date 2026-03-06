using System;
Console.Clear();
#region Exercices
void Ex01_Addition()
{
    WriteLine("=== Addition de deux nombres ===");

    int nb1 = AskWithIntTryParse("Veuillez entrer le premier nombre entier : ");
    int nb2 = AskWithIntTryParse("Veuillez entrer le deuxième nombre entier : ");

    WriteLine($"L'addition nombres {nb1} et {nb2} vaut {nb1 + nb2}");
}
void Ex02_Parite()
{
    int i = AskWithIntTryParse("Veuillez entrer un nombre entier : ");
    int result = (i/2)*2;

    Console.WriteLine(i == result ? "Le nombre est pair" : "Le nombre est impair");
}
void Ex03_()
{
    
}
#endregion
#region functions
int AskWithIntTryParse(string question)
{
    int result;
    do
    {
        Console.Write(question);
    } while (!int.TryParse(Console.ReadLine(),out result)); 
    return result;
}
#endregion

Ex02_Parite();