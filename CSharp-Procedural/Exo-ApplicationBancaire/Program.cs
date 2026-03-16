int limiteQuotidienne = 1500,
    montantDecouvert = 200,
    montantDecouvertMax = 1250,
    retraitDuJour = 0,
    choix = 0,
    montant;
decimal soldeCompte = 917;
DateTime dateDernierRetrait = DateTime.Today;

while (choix != 5)
{
    Console.Clear();
    Console.WriteLine("""
        ==================== APPLICATION BANCAIRE ====================
        1. Ajouter | 2. Retirer | 3. Découvert | 4. Solde | 5. Quitter
        """);

    if (!int.TryParse(Console.ReadLine(), out choix)) continue;

    switch (choix)
    {
        case 1: AjouterArgent(); break;
        case 2: RetirerArgent(); break;
        case 3: ChangerMontantDecouvert(); break;
        case 4: ConsulterCredit(); break;
        case 5: Console.WriteLine("Merci de votre visite, passez une belle journée !"); break;
    }
}

void AjouterArgent()
{
    do
    {
        Console.Clear();
        Console.WriteLine("Combien voulez-vous ajouter à votre compte ?");
    } while (!int.TryParse(Console.ReadLine(), out montant));
    if (montant <= 0)
    {
        Console.WriteLine("""
        Pour les retraits, merci de sélectionner le choix adéquat dans le menu principal.
        Appuyez sur une touche pour revenir au menu principal.
        """);
        Console.ReadKey();
    }
    soldeCompte += montant;
    Console.WriteLine($"""
    Votre solde a été crédité d'un montant de {montant} euros.
    Appuyez sur une touche pour revenir au menu principal.
    """);
    Console.ReadKey();
}

void RetirerArgent()
{
    do
    {
        Console.Clear();
        Console.WriteLine("""
        Combien voulez-vous retirer de votre compte ?
        1. 20€          4. 200€
        2. 50€          5. Autre montant
        3. 100€         6. Revenir au menu principal
        """);
    } while (!int.TryParse(Console.ReadLine(), out choix));
    switch (choix)
    {
        case 1:
            montant = 20;
            break;
        case 2:
            montant = 50;
            break;
        case 3:
            montant = 100;
            break;
        case 4:
            montant = 200;
            break;
        case 5:
            do
            {
                Console.WriteLine("Choisissez le montant désiré (un nombre entier)");
            } while (!int.TryParse(Console.ReadLine(), out montant));
            if (montant <= 0)
            {
                Console.WriteLine("""
                Bien tenté petit malin, mais pour les crédits de compte ce n'est pas le bon endroit !
                Appuyez sur une touche pour revenir au menu principal.
                """);
                Console.ReadKey();
                return;
            }
            break;
        case 6:
            return;
        default:
            Console.WriteLine("""
            Choix incorrect.
            Appuyez sur une touche pour revenir au menu principal.
            """);
            Console.ReadKey();
            return;
    }
    if (!ProcederRetrait()) return;
    Console.WriteLine($"""
    Voici votre argent.
    Votre solde a été débité d'un montant de {montant} euros.
    Appuyez sur une touche pour revenir au menu principal.
    """);
    Console.ReadKey();
}

bool ProcederRetrait()
{
    if (montant > soldeCompte + montantDecouvert)
    {
        Console.WriteLine("""
        Solde insuffisant.
        Appuyez sur une touche pour modifier le montant.
        """);
        Console.ReadKey();
        return false;
    }
    if (VerifierLimiteQuotidienne(montant))
    {
        soldeCompte -= montant;
        retraitDuJour += montant;
        dateDernierRetrait = DateTime.Today;
        return true;
    }
    else
    {
        Console.WriteLine($"""
        Vous avez atteint la limite quotidienne de retrait de {limiteQuotidienne} euros : {retraitDuJour} retirés aujourd'hui.
        Appuyez sur une touche pour modifier le montant.
        """);
        Console.ReadKey();
        return false;
    }
}

void ChangerMontantDecouvert()
{
    do
    {
        Console.Clear();
        Console.Write("Indiquez le montant du découvert souhaité : ");
    } while (!int.TryParse(Console.ReadLine(), out montant));
    if (montant < 0)
    {
        Console.WriteLine("""
        Désolé, mais le montant doit être une valeure positive.
        Appuyez sur une touche pour revenir au menu principal.
        """);
        Console.ReadKey();
        return;
    }
    if (montant > montantDecouvertMax)
    {
        Console.WriteLine($"""
        Vous ne pouvez pas dépasser votre limite de découvert maximale de {montantDecouvertMax} euros.
        Appuyez sur une touche pour revenir au menu principal.
        """);
        Console.ReadKey();
        return;
    }
    montantDecouvert = montant;
    Console.WriteLine($"""
    Vous avez modifié votre découvert à {montantDecouvert} euros.
    Appuyez sur une touche pour revenir au menu principal.
    """);
    Console.ReadKey();
    return;
}

void ConsulterCredit()
{
    Console.Clear();
    if (soldeCompte < 0)
    {
        Console.WriteLine($"""
        Votre compte est en négatif : {soldeCompte} euros.
        Veuillez régulariser la situation rapidement afin d'éviter des frais supplémentaires.
        Appuyez sur une touche pour revenir au menu principal.
        """);
        Console.ReadKey();
        return;
    }
    else
    {
        Console.WriteLine($"""
        Votre compte présente un solde de {soldeCompte} euros.
        Appuyez sur une touche pour revenir au menu principal.
        """);
        Console.ReadKey();
        return;
    }
}

bool VerifierLimiteQuotidienne(int montant)
{
    if (dateDernierRetrait != DateTime.Today)
    {
        retraitDuJour = 0;
    }
    return retraitDuJour + montant <= limiteQuotidienne;
}