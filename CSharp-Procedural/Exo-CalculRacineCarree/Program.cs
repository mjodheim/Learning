Console.WriteLine("===== Calcul d'une racine carrée =====");
Console.Write("Veuillez choisir votre nombre : ");
double number;
while (true)
{
    if (double.TryParse(Console.ReadLine(), out number) && number >= 0)
        break;
    Console.WriteLine("Veuillez entrer un nombre positif");
}

double result = CalculerRacineCarree(number);
Console.WriteLine($"La racine carée de {number} vaut {result:F10}.");

double CalculerRacineCarree(double number)
{
    // On applique la formule de Newton
    // On cherche en premier le point de départ
    double x = 0;
    for (double k = 1; (k * k) <= number; k++)
    {
        x = k;
    }
    // On applique la formule de Newton si on n'a pas d'égalité
    double result = x;
    if ((x * x) != number)
    {
        for (int i = 0; i < x; i++)
        {
            result = 0.5 * (result + (number / result));
        }
    }
    return result;
}