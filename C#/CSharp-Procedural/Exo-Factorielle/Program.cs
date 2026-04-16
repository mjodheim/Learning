int number;
Console.WriteLine("===== Calcul de Factorielle =====");
Console.Write("Entrer un nombre : ");
while (true)
{
    if (int.TryParse(Console.ReadLine(), out number))
        break;
    Console.Write("\nEntrez un nombre valide : ");
    Console.ReadLine();
}

int result = Factoriel(number);
Console.WriteLine($"Le factoriel de {number} vaut {result}.");

int Factoriel(int nombre)
{
    int result = nombre;
    while (nombre > 1)
    {
        result *= --nombre;
    }

    return result;
}