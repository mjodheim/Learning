Console.WriteLine("===== Calcul de nombres premiers =====");
Console.WriteLine("Combien de nombres premiers à afficher ?");
int number;
while (true)
{
    if (int.TryParse(Console.ReadLine(), out number) && number > 0)
        break;
    Console.Write("\nVeuillez entrer un nombre entier positif : ");
    Console.ReadLine();
}

string result = NombresPremiers(number);
Console.WriteLine($"Les {number} premiers nombres entiers sont {result}");

string NombresPremiers(int recurrence)
{
    string result = "2"; // Considéré comme le premier nombre premier
    int premier = 3;
    for (int i = 1; i < recurrence; i++)
    {
        while (!EstPremier(premier))
        {
            premier++;
        }
        result += $" | {premier}";
        premier++;
    }
    return result;
}
bool EstPremier(int nombre)
{
    for (int i = 2; i <= Math.Sqrt(nombre); i++)
    {
        if (nombre % i == 0)
            return false;
    }
    return true;
}