<<<<<<< HEAD
﻿Console.WriteLine("===== Suite de Fibonacci =====");
Console.Write("Combien de nombres voulez-vous afficher ? ");
int number;
while (true)
{
    if (int.TryParse(Console.ReadLine(), out number) && number > 0)
        break;
    Console.Write("\nEntrez un nombre valide : ");
    Console.ReadLine();
}
=======
﻿int number;

Console.WriteLine("===== Suite de Fibonacci =====");
Console.Write("Combien de nombres voulez-vous afficher ? ");


while (!int.TryParse(Console.ReadLine(), out number) || number <= 0)
{
    Console.Write("\nEntrez un nombre valide : ");
}

>>>>>>> baeca2c2875911c40f649850402c1864857a144c
Fibonacci(number);

void Fibonacci(int recurence)
{
    // On initialise les deux premiers chiffres de la suite
    string output = "0 | 1 ";
<<<<<<< HEAD
    int[] tab = new int[recurence];
    tab[0] = 0;
    tab[1] = 1;
    for (int i = 2; i < recurence; i++)
    {
        tab[i] += tab[i - 2] + tab[i - 1];
        output += $"| {tab[i]} ";
    }

=======
    if (recurence > 2)
    {
        int[] tab = new int[recurence];
        tab[0] = 0;
        tab[1] = 1;
        for (int i = 2; i < recurence; i++)
        {
            tab[i] += tab[i - 2] + tab[i - 1];
            output += $"| {tab[i]} ";
        }
    }
>>>>>>> baeca2c2875911c40f649850402c1864857a144c
    Console.WriteLine(output);
}