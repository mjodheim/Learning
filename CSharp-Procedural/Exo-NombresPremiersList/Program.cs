List<int> myList = new List<int>();

Console.WriteLine("===== Nombres premiers =====");
Console.WriteLine("Inférieurs à quel nombre ?");
int number;
while (true)
{
    if (int.TryParse(Console.ReadLine(), out number) && number > 0)
        break;
    Console.WriteLine("Veuillez entrer un nombre entier positif : ");
    Console.ReadLine();
}

int premier = 2;
do
{
    if (EstPremier(premier))
    {
        myList.Add(premier);
    }
    premier++;
} while (premier < number);

foreach (int nbPremier in myList)
{
    Console.WriteLine($"{nbPremier}");
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