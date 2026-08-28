# 🃏 Jeu de Bataille — C# Console

Petit projet d’apprentissage en **C# / .NET** consistant à implémenter une version console du **jeu de cartes : Bataille**.

Le joueur affronte l’ordinateur.  
À chaque tour, chacun tire une carte et la **plus forte remporte le point**.

---

# 🎮 Règles du jeu

1. Deux **paquets de cartes** sont créés et mélangés.
2. Le joueur entre son **prénom**.
3. À chaque tour :
   - le joueur appuie sur une touche
   - une carte est tirée pour chaque joueur
4. La **valeur des cartes est comparée**
5. Le gagnant du tour marque **1 point**
6. Si les cartes ont la même valeur → **égalité**
7. La partie se termine lorsque le paquet est vide.

---

# 🏗 Structure du projet
```
Exo-JeuDeCartes
│
├── Enums
│ ├── Couleurs.cs
│ └── Valeurs.cs
│
├── Objets
│ ├── Carte.cs
│ ├── Paquet.cs
│ └── Personnes.cs
│
├── Program.cs
├── Exo-JeuDeCartes.csproj
└── README.md
```

---

# 🧩 Architecture des objets

## Carte

Représente une carte du jeu.

---

## Paquet

Gère les cartes.

Méthodes principales :
```
Creer() → génère les 52 cartes
Melanger() → mélange le paquet
Tirer() → tire une carte
```

---

## Personne

Représente un joueur.

Propriétés :
```
Nom
Prenom
Resultat
```

Méthodes :
```
AjouterPoints()
```

---

# 🔁 Logique du programme

1. Création des paquets
2. Mélange des cartes
3. Création du joueur et de l’ordinateur
4. Boucle de tirage des cartes
5. Comparaison des valeurs
6. Attribution des points
7. Affichage du résultat final

---

# 🧠 Concepts C# utilisés

- **Classes et objets**
- **Encapsulation**
- **Enum**
- **Struct**
- **List<T>**
- **switch expression**
- **pattern matching**
- **interpolation de chaînes**
- **fonctions locales**

---

# 📚 Projet d’apprentissage

Projet réalisé dans le cadre d’un apprentissage de :

- **C#**
- **.NET**
- **programmation orientée objet**