# Exercice — Chapitres 4 & 5

## Modificateurs d'accès et méthodes : la salle de cinéma

**Durée : 45 min — Prérequis : chapitres 0 à 5**

---

## Ce que vous devez créer

Trois classes réparties en **deux packages**, plus un `main` :

```
cinema/Salle.java
cinema/AgentDEntretien.java
cinema/premium/SalleVIP.java     (extends Salle)
Main.java                        (hors de ces deux packages)
```

Cette répartition n'est pas décorative : c'est elle qui rendra visibles les différences entre les quatre niveaux d'accès.

---

## Partie A — Les modificateurs d'accès

Dans `Salle`, déclarez les membres suivants avec la visibilité **la plus restrictive possible** qui permette encore au projet de compiler. Ajoutez `final` là où c'est justifié.

| Membre | Contrainte à respecter |
|---|---|
| `PLACES_MAX = 200` | Commune à toutes les salles, jamais modifiée, lisible depuis `Main` |
| `nom` | Fixé à la construction, ne change plus jamais |
| `placesVendues`, `recette` | Invisibles depuis l'extérieur de la classe |
| `niveauSonore` | Doit être lu par `SalleVIP` — autre package, mais sous-classe |
| `codeNettoyage` | Doit être lu par `AgentDEntretien` — même package — mais **pas** par `SalleVIP` |

Les deux dernières lignes sont le cœur de l'exercice.

---

## Partie B — Les méthodes

1. `reserver()` → réserve une place plein tarif
2. `reserver(int nb)` → réserve `nb` places plein tarif
3. `reserver(int nb, double reduction)` → **la seule qui travaille vraiment**

> Les deux premières doivent **déléguer** à la troisième. Si vous copiez-collez du code entre elles, c'est raté.

4. Dans la méthode (3), écrivez **trois gardes** en début de méthode, chacune avec un `return false` immédiat :
    - `nb <= 0`
    - réduction hors de l'intervalle `[0 ; 1]`
    - places insuffisantes

   Le chemin nominal doit ensuite se lire d'un seul trait, sans aucun `if` imbriqué.

5. `placesDisponibles(int nb)` → outil interne, invisible depuis l'extérieur
6. `encaisser(double... billets)` → accepte zéro, un ou plusieurs billets, renvoie le total
7. `prixTTC(double ht)` → ne dépend d'aucune salle en particulier
8. `getNom()` → une sous-classe ne doit **pas** pouvoir la redéfinir

---

## Auto-contrôle

Restreindre **trop** provoque une erreur de compilation : le problème se voit tout de suite. Restreindre **trop peu** ne provoque rien. D'où cette vérification obligatoire.

Depuis `Main`, ces trois lignes doivent **refuser de compiler** :

```java
salle.placesVendues;
salle.codeNettoyage;
salle.niveauSonore;
```

Dans `SalleVIP` :

| Accès | Résultat attendu |
|---|---|
| `niveauSonore` | doit **passer** |
| `codeNettoyage` | doit **échouer** |

Si l'un de ces tests passe alors qu'il devrait échouer, votre visibilité est trop large.