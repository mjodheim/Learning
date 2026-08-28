Console.WriteLine("===== Les 5 premières tables de multiplication jusque 20 =====");
for (int i = 1; i < 6; i++)
{
    string result = "";
    for (int j = 1; j < 21; j++)
    {
        result += $"{i} * {j} = {j * i} ; ";
    }
    Console.WriteLine(result.Remove(result.Length - 3, 3)); // On retire le ; de la fin avec les espaces
}

