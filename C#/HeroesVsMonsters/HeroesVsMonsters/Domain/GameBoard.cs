using Domain.Entities;
using Domain.Entities.Heroes;
using Domain.Factories;

namespace Domain;

public class GameBoard
{
    // Définition de la taille du plateau de jeu
    private const int Size = 15;
    
    // Variable Random pour le placement des personnages
    private readonly Random _random = new Random();
    
    // Déclaration du héros et des monstres
    private Hero Hero { get; set; }
    private HeroType HeroType { get; set; }
    private List<Monster> Monsters { get; set; } = new();
    
    // Constructeur avec type de héros passé en paramètre et le nombre de monstres (10 par défaut)
    public GameBoard( HeroType heroType,  int monsterCount = 10)
    {
        HeroType = heroType;
        Hero = HeroFactory.CreateHero(HeroType);
        PlaceHero();
        PlaceMonsters(monsterCount);
    }

    // Constructeur générique avec type de héro aléatoire et le nombre de monstres (10 par défaut)
    public GameBoard(int monsterCount = 10)
    {
        Hero = HeroFactory.CreateRandomHero();
        PlaceHero();
        PlaceMonsters(monsterCount);
    }

    // Placement du héro
    private void PlaceHero()
    {
        var x = _random.Next(Size );
        var y = _random.Next(Size);
        Hero.SetPosition(x, y);
    }
    
    // Placement des monstres
    private void PlaceMonsters(int monsterCount)
    {
        // On va éviter de boucler à l'infini s'il est impossible de placer les monstres dans le plateau de jeu
        var counter = 0;
        var maxCounter = monsterCount * 1000;
        
        while (Monsters.Count < monsterCount && counter < maxCounter)
        {
            counter ++;
            
            var x = _random.Next(Size);
            var y = _random.Next(Size);
            
            // Création d'un monstre aléatoire avec sa position
            var monster = MonsterFactory.CreateRandomMonster();
            monster.SetPosition(x, y);
            
            // On vérifie si pour chaque monstre dans Monsters les coordonnées X et Y ne correspondent pas
            // au monstre nouvellement créé
            // Attention aux nombres négatifs
            // 'continue' → on relance la boucle
            var isValid = Monsters.All( m => 
                !(Math.Abs(m.X - x) <= 2 && Math.Abs(m.Y - y) <= 2)
            );
            if (!isValid) continue;
            
            // On vérifie que l'emplacement du monstre n'est pas sur notre héros
            if (x == Hero.X && y == Hero.Y) continue;
            
            Monsters.Add(monster);
        }

        if (counter == maxCounter) throw new Exception("Le plateau de jeu ne peut pas contenir autant de monstres");
    }
    
    // Lancement du projet
    public void Run()
    {
        Console.Clear();
        Console.WriteLine($"""
                           Bienvenue dans la forêt enchantée de Shorewood, {Hero.GetType().Name}.
                           Dans ce monde hostile, des héros affrontent des monstres dans
                           une succession de combats jusqu'à la mort... ou la victoire finale.
                           """);
        Console.WriteLine($"Appuyez sur une touche pour générer la carte...");
        Console.ReadKey(true);

        while (Hero.IsAlive && Monsters.Count > 0)
        {
            Console.Clear();
            
            // Génération du board à chaque événement
            DrawGameBoard();

            // Vérifier s'il y a un monstre à côté pour lancer le combat
            var monster = GetAdjacentMonster();
            if (monster != null)
            {
                Console.WriteLine("========== DEBUT DU COMBAT ==========");
                Console.WriteLine($"Vous affrontez un {monster.GetType().Name} !");
                Console.WriteLine($"Appuyez sur une touche pour frapper.");
                Console.ReadKey(true);
                Fight(monster);
            }
            
            // Capturer la touche enfoncée
            var key = Console.ReadKey(true).Key;

            // Déplacement du héros
            MoveHero(key);
        }
    }
    
    // Génération du plateau en console
    private void DrawGameBoard()
    {
        Console.SetCursorPosition(0,0);
        Console.Clear();

        // Il convient de vérifier s'il y a un monstre adjacent afin de l'afficher
        var foundMonster = GetAdjacentMonster();
        
        // On vérifie case par case et on affiche le personnage
        for(int y = 0; y < Size; y++)
        {
            for (int x = 0; x < Size; x++)
            {
                // Affichage du héros
                if (x == Hero.X && y == Hero.Y)
                {
                    Console.Write("H ");
                    continue;
                }
                
                // Affichage du Monstre si adjacent au héros
                // Pour chaque monstre dans Monsters, on récupère celui qui correspond à l'emplacement actuel
                var monster = Monsters.FirstOrDefault(monster => monster.X == x && monster.Y == y);
                
                if (monster != null && foundMonster == monster)
                {
                    Console.Write($"{GetMonsterChar(monster)} ");
                    continue;
                }
                
                // On affiche un point si on ne trouve rien
                Console.Write(".  ");
            }
            
            // Retour à la ligne et on recommence
            Console.WriteLine();
        }
        
        // Un petit récap des stats actuelles
        Console.WriteLine("========== INFOS ==========");
        Console.WriteLine($"Points de vie actuels : {Hero.CurrentHp}");
        Console.WriteLine($"Monstres restants : {Monsters.Count}.");
        Console.WriteLine($"Sac : Or={Hero.HeroGold} | Cuir={Hero.HeroLeather}");
        Console.WriteLine("Déplacement : ZQSD ou flèches.");
    }
    
    // Récupérer le caractère d'un monstre : L, O, D
    private static char GetMonsterChar(Monster monster) => monster switch
    {
        Domain.Entities.Monsters.Loup => 'L',
        Domain.Entities.Monsters.Orque => 'O',
        Domain.Entities.Monsters.Dragonet => 'D',
        _ => '?'
    };

    private void MoveHero(ConsoleKey key)
    {
        // Variables de direction sur l'axe X et l'axe Y
        int dx = 0, dy = 0;

        switch (key)
        {
            case ConsoleKey.LeftArrow:
            case ConsoleKey.Q:
                dx = -1;
                break;
            case ConsoleKey.RightArrow:
            case ConsoleKey.D:
                dx = 1;
                break;
            case ConsoleKey.UpArrow:
            case ConsoleKey.Z:
                dy = -1;
                break;
            case ConsoleKey.DownArrow:
            case ConsoleKey.S:
                dy = 1;
                break;
            default:
                return; // on sort complètement de la méthode MoveHero
        }
        
        // Vérifier les bords du plateau
        int newX = Hero.X + dx;
        int newY = Hero.Y + dy;
        if(newX < 0 || newX  >= Size || newY < 0 || newY >= Size) return;
        
        // Attribution de la nouvelle position
        Hero.SetPosition(newX, newY);
    }
    
    private Monster? GetAdjacentMonster()
    {
        // Pour chaque monstre dans Monsters, on le récupère s'il est à côté du héros
        // Utilisation de Manhattan distance
        return Monsters.FirstOrDefault( monster => 
            Math.Abs(monster.X - Hero.X) + Math.Abs(monster.Y - Hero.Y) == 1);
    }

    private void Fight(Monster monster)
    {
        Console.Clear();
        
        while (Hero.IsAlive && monster.IsAlive)
        {
            var degats = 0;
            var hpBeforeHit = 0;
            // Le héros frappe en premier
            hpBeforeHit = monster.CurrentHp;
            Hero.Frapper(monster);
            degats = hpBeforeHit - monster.CurrentHp;
            Console.WriteLine($"Vous frappez votre cible et lui infligez {degats} points de dégât.");
            Console.WriteLine($"Points de vie restants du monstre : {monster.CurrentHp} / {monster.BaseHp}.\n");

            if (!monster.IsAlive) break;
            
            // Le monstre riposte
            hpBeforeHit = Hero.CurrentHp;
            monster.Frapper(Hero);
            degats = hpBeforeHit - Hero.CurrentHp;
            Console.WriteLine($"{monster.GetType().Name} vous touche et vous inflige {degats} points de dégât.");
            Console.WriteLine($"Vos points de vie restants : {Hero.CurrentHp} / {Hero.BaseHp}.\n");

            Console.WriteLine("Appuyez sur une touche pour continuer.\n");
            Console.ReadKey(true);
        }

        Console.Clear();
        
        if (Hero.IsAlive)
        {
            Console.WriteLine("Vous avez vaincu le monstre !");
            // Récupération du loot
            Hero.AddLoot(monster.MonsterGold, monster.MonsterLeather);
            // On n'oublie pas de faire disparaître le monstre de la collection
            Monsters.Remove(monster);
        }
        else
        {
            Console.WriteLine("Vous avez été vaincu !\n");
            Console.WriteLine("========== GAME OVER ==========");
            Console.WriteLine($"Sac : Or={Hero.HeroGold} | Cuir={Hero.HeroLeather}");
        }
        
        Console.WriteLine("Appuyez sur une touche pour continuer");
        Console.ReadKey(true);
    }
}