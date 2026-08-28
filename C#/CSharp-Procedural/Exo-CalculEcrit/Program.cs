// Contraintes d'utilisation :
// Utiliser des Collections de type string

Console.WriteLine("===== Calcul écrit de deux nombres =====");
Console.WriteLine("Veuillez entrer le premier nombre");
int nb1;
while (!int.TryParse(Console.ReadLine(), out nb1))
{
    Console.WriteLine("Veuillez entrer un nombre entier : ");
}
string number1 = nb1.ToString();
Console.WriteLine("Veuillez entrer le deuxième nombre");
int nb2;
while (!int.TryParse(Console.ReadLine(), out nb2))
{
    Console.WriteLine("Veuillez entrer un nombre entier : ");
}
string number2 = nb2.ToString();

Stack<char> stackResult = new Stack<char>();
Stack<char> stackNumber1 = new Stack<char>(number1.ToCharArray());
Stack<char> stackNumber2 = new Stack<char>(number2.ToCharArray());

int maxLength = Math.Max(number1.Length, number2.Length);
int report = 0;

for (int i = 0; i < maxLength; i++)
{
    int val1 = stackNumber1.Count > 0 ? int.Parse(stackNumber1.Pop().ToString()) : 0;
    int val2 = stackNumber2.Count > 0 ? int.Parse(stackNumber2.Pop().ToString()) : 0;
    int result = val1 + val2 + report;

    if (result < 10 && i < maxLength - 1)
    {
        stackResult.Push((result.ToString()).ToCharArray()[0]);
        report = 0;
    }
    if (result > 10 && i < maxLength - 1)
    {
        stackResult.Push((result.ToString()).ToCharArray()[1]);
        report = 1;
    }
    else
    {
        stackResult.Push((result.ToString()).ToCharArray()[1]);
        stackResult.Push('1');
    }
}

foreach (char chiffre in stackResult)
{
    Console.Write(chiffre);
}