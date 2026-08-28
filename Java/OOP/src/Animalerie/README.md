# Exercice — Animalerie

## Héritage, classe abstraite et polymorphisme

**Prérequis : chapitres 0 à 6**

---

## L'énoncé

Nous désirons effectuer la gestion d'une **animalerie**. Cette dernière s'occupe de plusieurs types d'animaux : chiens, chats, oiseaux… (laissez libre cours à votre imagination !)

Pour **chaque animal**, l'animalerie doit connaître : son **nom**, son **poids**, sa **taille**, son **sexe**, son **âge**, son **âge humain équivalent** et sa **date d'arrivée** à l'animalerie. Tous les animaux possèdent le comportement `crier()`.

| Espèce | Caractéristiques propres | Probabilité de décès |
|---|---|---|
| **Chat** | son caractère (énergique, farouche, câlin…), si ses griffes ont été coupées, s'il est à poil long ou non | **0,5 %** |
| **Chien** | la couleur de son collier, s'il a été dressé, sa race | **1 %** |
| **Oiseau** | sa couleur, s'il doit vivre en volière ou en petite cage | **3 %** |

Le programme de gestion doit :

1. **Encoder** des animaux (chiens, chats, oiseaux)
2. **Lister** les caractéristiques de tous les animaux encodés
3. **Afficher le nombre** de chats, de chiens et d'oiseaux
4. **Vérifier** si certains animaux ne sont pas décédés durant la nuit

---

## Ce que vous devez créer

```
animaux/Animal.java              (abstraite)
animaux/Chat.java                (extends Animal)
animaux/Chien.java               (extends Animal)
animaux/Oiseau.java              (extends Animal)
animaux/Sexe.java                (énumération)
animaux/Caractere.java           (énumération, propre au chat)
animaux/Logement.java            (énumération, propre à l'oiseau)
Pension.java                     (le registre : UNE liste pour les trois espèces)
```

Le reste — `Animalerie`, `Encodage/`, `Consultation/`, `Nuit`, `FicheAnimal` — n'est que la couche des menus, sur le modèle de la salle de cinéma.

---

## Partie A — La classe mère

1. Placez dans `Animal` **tout ce que les trois espèces partagent**, et rien d'autre. Le caractère d'un chat ou la race d'un chien n'y ont pas leur place.
2. `Animal` est **abstraite** : on n'accueille jamais « un animal » à l'animalerie, toujours un chat, un chien ou un oiseau.
3. Son constructeur est `protected` : seules les sous-classes l'appellent, via `super(...)`.
4. Déclarez comme **abstraits** les comportements qu'aucune formule commune ne peut couvrir :

   | Méthode | Pourquoi elle ne peut pas être écrite dans `Animal` |
   |---|---|
   | `crier()` | chaque espèce a sa voix |
   | `getEspece()` | le libellé affiché dans la fiche |
   | `getAgeHumain()` | le barème diffère d'une espèce à l'autre |
   | `getProbabiliteDeDeces()` | 0,5 % / 1 % / 3 % |
   | `detailsSpecifiques()` | la dernière ligne de la fiche |

5. `decrire()`, elle, est **`final`** : la fiche a toujours la même structure, seule sa dernière ligne change — et cette ligne est déléguée à `detailsSpecifiques()`. Si vous réécrivez la mise en forme dans chaque sous-classe, c'est raté.

---

## Partie B — Le décès nocturne

`passerLaNuit(Random hasard)` est écrite **une seule fois**, dans `Animal` :

```java
if (hasard.nextDouble() >= getProbabiliteDeDeces()) {
    return false;
}
```

C'est le seul endroit du programme où le décès est tiré au sort. Le pourcentage, lui, vient de la sous-classe : la méthode ne sait pas — et n'a pas à savoir — si elle travaille sur un chat ou sur un oiseau. **Un animal déjà décédé ne meurt pas une seconde fois.**

Un seul `Random` pour toute l'animalerie : il appartient à `Pension`, qui le passe à chaque animal.

---

## Partie C — Le registre

`Pension` ne connaît qu'**une seule liste**, de type `List<Animal>` : c'est le bénéfice direct de la classe mère commune.

| Méthode | Contrainte |
|---|---|
| `accueillir(Animal)` | le seul moyen d'ajouter un animal |
| `getAnimaux()` | consultable, mais **non modifiable** de l'extérieur |
| `compter(Class<? extends Animal>)` | une seule méthode pour les trois espèces, pas trois |
| `passerLaNuit()` | renvoie la liste de ceux qui n'ont pas survécu |

---

## Auto-contrôle

L'héritage est réussi si le code d'affichage **ne teste jamais l'espèce**.

Relisez `Inventaire`, `Concert` et `Nuit` : ils parcourent des `Animal` et appellent `decrire()`, `crier()`, `getProbabiliteDeDeces()`. **Aucun `instanceof`, aucun `switch` sur l'espèce.** C'est le type réel de l'objet qui choisit la bonne version à l'exécution.

Si vous avez besoin d'un `instanceof` pour afficher une fiche, c'est que la caractéristique concernée est mal placée : elle devrait être derrière une méthode de la classe mère.

Seul `Recensement` nomme les trois classes — et pour cause : c'est précisément ce que l'énoncé demande d'y compter.

---

## Pour lancer

Depuis `Main` : **2. Animalerie**. Trois pensionnaires sont déjà encodés au démarrage pour que les menus aient de la matière.

> Avec 0,5 % / 1 % / 3 %, une nuit tranquille est le cas normal. Enchaînez les nuits — ou encodez une volière entière — pour voir la différence de risque entre les espèces.
