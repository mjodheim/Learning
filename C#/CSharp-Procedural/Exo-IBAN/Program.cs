void ValidationBban()
{
    long bban;
    string stringBban;
    Console.WriteLine("Entrer les 12 chiffres de votre compte BBAN");
    while (true)
    {
        if (long.TryParse(Console.ReadLine(), out bban) && bban.ToString().Length == 12)
            break;
        Console.Write("Entrez un numéro BBAN valide : ");
        stringBban = Console.ReadLine();
    }
    long baseNumber = long.Parse(bban.ToString().Substring(0, 10));
    int check = int.Parse(bban.ToString().Substring(10, 2));
    long modulo97 = baseNumber % 97;
    bool isValide = modulo97 == check || (modulo97 == 0 && check == 97);
    Console.WriteLine(isValide ? "OK" : "KO");
}
void BbanToIban()
{
    long bbanBelge;
    do
    {
        Console.Write("Entrer les 12 chiffres de votre compte BBAN Belge : ");
        bbanBelge = ValidateBban(Console.ReadLine());
        if (bbanBelge == 0)
            Console.WriteLine("Compte non valide.");
    } while (bbanBelge == 0);
    string key = ((int)(98 - bbanBelge % 97)).ToString("D2"); // Si jamais le résultat est <10 on garde un 0
    Console.WriteLine($"Voici votre IBAN : BE{key}{bbanBelge}");
}

long ValidateBban(string stringBban)
{
    long bban;
    while (true)
    {
        if (long.TryParse(stringBban, out bban) && bban.ToString().Length == 12)
            break;
        Console.Write("Entrez un numéro BBAN valide : ");
        stringBban = Console.ReadLine();
    }
    long baseNumber = long.Parse(bban.ToString().Substring(0, 10));
    int check = int.Parse(bban.ToString().Substring(10, 2));
    long modulo97 = baseNumber % 97;
    bool isValide = modulo97 == check || (modulo97 == 0 && check == 97);
    return isValide ? bban : 0;
}