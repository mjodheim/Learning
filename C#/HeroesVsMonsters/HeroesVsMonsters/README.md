# 🗡️ Heroes vs Monsters — Console RPG (C# / OOP)

Bienvenue dans la forêt enchantée de **Shorewood**, située dans le royaume de **Stormwall**.  
Dans ce monde hostile, des héros affrontent des monstres dans une succession de combats jusqu’à la mort… ou la victoire finale.

Ce projet est un **jeu de rôle en console** développé en **C#**, conçu comme un **exercice récapitulatif de programmation orientée objet**.

---

## 🎯 Objectifs pédagogiques

Ce projet met en pratique :

- L’**héritage**
- Le **polymorphisme**
- L’**encapsulation**
- Les **propriétés en lecture seule**
- L’**abstraction**
- L’utilisation de la classe `Random`
- Une **architecture orientée domaine**
- (Bonus) La gestion d’un **plateau de jeu 2D**

---

## 🧙‍♂️ Univers du jeu

Deux grandes familles s’affrontent :

### 🔹 Héros
- **Humain**
- **Nain**

### 🔸 Monstres
- **Loup**
- **Orque**
- **Dragonnet**

Chaque combat oppose un héros à un monstre, jusqu’à ce que l’un des deux meure.

---

## 📊 Caractéristiques des personnages

Chaque personnage possède :

- **Force (For)**
- **Endurance (End)**
- **Points de Vie (PV)**

### 🎲 Calcul des caractéristiques

- **Force** et **Endurance** sont calculées à la création du personnage :
    - Lancer **4 dés à 6 faces**
    - Conserver **les 3 meilleurs résultats**
- Les **Points de Vie** sont calculés ainsi :

---

## ➕ Modificateur de caractéristique

Le modificateur dépend de la valeur de la caractéristique :

| Valeur de la caractéristique | Modificateur |
|------------------------------|--------------|
| < 5                          | -1           |
| < 10                         | 0            |
| < 15                         | +1           |
| ≥ 15                         | +2           |

---

## ⚔️ Système de combat

### Action : **Frappe**

Lorsqu’un personnage attaque :

1. Lancer **1 dé à 4 faces**
2. Ajouter le **modificateur basé sur la Force**
3. Retirer les dégâts aux PV de la cible

💀 **Un personnage meurt lorsque ses PV ≤ 0**

---

## 💰 Butin et récompenses

Lorsqu’un héros tue un monstre, il récupère son butin :

- **Or**
- Calculé avec **1 dé à 6 faces**
- **Cuir**
- Calculé avec **1 dé à 4 faces**

Le héros peut stocker **autant d’or et de cuir que voulu**.

---

## 🧬 Spécificités des classes

### 🧍 Humain
- +1 **Force**
- +1 **Endurance**

### ⛏️ Nain
- +2 **Endurance**

### 🐺 Loup
- Donne du **cuir**
- Pas d’or

### 👹 Orque
- +1 **Force**
- Possède de l’**or**

### 🐲 Dragonnet
- +1 **Endurance**
- Possède de l’**or**
- Donne du **cuir**

⚠️ Les bonus **ne modifient jamais la caractéristique de base**, ils sont appliqués séparément.

---

## 🧱 Contraintes techniques

- `Force` et `Endurance` sont des **propriétés en lecture seule**
- `PV` est :
- `private` en lecture/écriture (si les délégués sont vus)
- sinon en lecture seule
- La classe `De` contient :
- `Minimum` (lecture seule)
- `Maximum` (lecture seule)
- `Lance()` → retourne un entier aléatoire
- Utilisation obligatoire de `Random`

---

## 🗺️ Exercice supplémentaire — Plateau de jeu

### Plateau
- Grille **15 x 15**
- Environ **10 monstres**
- Chaque monstre est espacé d’au moins **2 cases** (horizontalement et verticalement)

### Positionnement
Chaque personnage possède :
- `X` : position horizontale
- `Y` : position verticale

Les positions sont définies **à la création**.

---

### 👀 Visibilité

- Le héros est visible : `H`
- Les monstres sont **cachés**
- Ils apparaissent uniquement quand le combat commence :
- `L` → Loup
- `O` → Orque
- `D` → Dragonnet

---

### ⚔️ Déclenchement des combats

Un combat démarre automatiquement lorsque le héros se place :
- **à côté** d’un monstre
- horizontalement ou verticalement

---

## 🏁 Fin du jeu

La partie se termine lorsque :

- Tous les monstres ont été vaincus 🏆
- **OU**
- Le héros meurt ☠️

---

## 🚀 Lancement du projet

```bash
dotnet run
