Console.WriteLine("=== Addition de deux nombres ===");

int nb1 = AskWithIntTryParse("Veuillez entrer le premier nombre entier : ");
int nb2 = AskWithIntTryParse("Veuillez entrer le deuxième nombre entier : ");

Console.WriteLine($"L'addition nombres {nb1} et {nb2} vaut {nb1 + nb2}");

    void Parite()
{
    int i = AskWithIntTryParse("Veuillez entrer un nombre entier : ");
    int result = (i / 2) * 2;

    Console.WriteLine(i == result ? "Le nombre est pair" : "Le nombre est impair");
}

int AskWithIntTryParse(string question)
{
    int result;
    do
    {
        Console.Write(question);
    } while (!int.TryParse(Console.ReadLine(), out result));
    return result;
}