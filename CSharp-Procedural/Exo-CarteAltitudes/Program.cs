decimal[,] carte;
int x, y;
Random random = new Random();
decimal maxValue = 0, averageValue = 0;

do
{
    Console.Write("Donnez la valeur de x : ");
} while (!int.TryParse(Console.ReadLine(), out x));

do
{
    Console.Write("Donnez la valeur de y : ");
} while (!int.TryParse(Console.ReadLine(), out y));

carte = new decimal[x, y];

// On rempli la carte avec des nombres aléatoires et on cherche la valeur la plus haute
for (int i = 0; i < y; i++)
{
    for (int j = 0; j < x; j++)
    {
        carte[j, i] = (decimal)random.Next(25000) / 3;
        if (carte[j, i] > maxValue)
        {
            maxValue = carte[j, i];
        }
        averageValue += carte[j, i];
    }
}

averageValue /= (x * y);

Console.WriteLine($"Après génération des altitudes, la valeur maximale et de {maxValue} mètres et la moyenne est de {averageValue} mètres.");