# 🏦 Gestion d'une Banque

## 📌 Objectif

L'objectif de cet exercice est de développer une petite application de gestion bancaire en utilisant les principes de la **programmation orientée objet**.

Le projet doit permettre de gérer :

* des personnes ;
* différents types de comptes bancaires ;
* une banque contenant plusieurs comptes ;
* les dépôts et retraits ;
* le calcul des avoirs d'un titulaire ;
* l'impression d'extraits de compte ;
* l'application d'intérêts selon le type et la situation du compte.

---

# 1. Classe `Personne`

Créer une classe représentant une personne.

## Propriétés

* `nom`
* `prenom`
* `birthdate`

Une personne pourra être utilisée comme **titulaire d'un compte bancaire**.

---

# 2. Classe `Compte`

Créer une classe représentant un compte bancaire générique.

## Propriétés

* `numeroCompte`
* `titulaire`
* `solde`

### Contraintes

Le `titulaire` est une instance de la classe `Personne`.

Le `solde` doit être accessible en lecture uniquement depuis l'extérieur :

```java
public double getSolde()
```

Il ne doit donc pas être possible de modifier directement le solde d'un compte avec un setter.

La modification du solde doit obligatoirement passer par les opérations prévues par le compte, notamment :

* dépôt ;
* retrait ;
* application des intérêts.

---

# 3. Classe `Courant`

Créer une classe `Courant` héritant de `Compte`.

Elle représente un compte courant pouvant disposer d'une ligne de crédit.

## Propriété supplémentaire

* `ligneDeCredit`

## Contraintes

La ligne de crédit doit être **positive ou nulle**.

Elle permet au compte courant de descendre sous zéro jusqu'à la limite autorisée.

Exemple :

```text
Solde : 100 €
Ligne de crédit : 500 €

Solde minimum autorisé : -500 €
```

Un retrait qui ferait passer le compte sous `-500 €` doit être refusé.

---

# 4. Classe `Epargne`

Créer une classe `Epargne` héritant également de `Compte`.

## Propriété supplémentaire

* `dateDernierRetrait`

Cette date doit être mise à jour lorsqu'un retrait est effectué sur le compte épargne.

---

# 5. Classe `Banque`

Créer une classe représentant une banque.

## Propriétés

* `nom`
* `comptes`

La banque doit pouvoir contenir plusieurs comptes bancaires.

Ces comptes peuvent être :

* des comptes courants ;
* des comptes épargne.

---

# 6. Fonctionnalités

## 💰 Déposer de l'argent

Chaque compte doit permettre d'effectuer un dépôt.

Exemple :

```java
compte.deposer(100);
```

Le montant déposé doit être ajouté au solde.

Un dépôt doit être effectué avec un montant valide.

---

## 💸 Retirer de l'argent

Chaque compte doit permettre d'effectuer un retrait.

Exemple :

```java
compte.retirer(50);
```

Les règles peuvent dépendre du type de compte.

### Compte épargne

Le retrait est possible si le solde est suffisant.

La date du dernier retrait doit ensuite être mise à jour.

### Compte courant

Le compte peut utiliser sa ligne de crédit.

Exemple :

```text
Solde : 200 €
Ligne de crédit : 500 €
Retrait demandé : 600 €

Nouveau solde : -400 €
```

Le retrait est autorisé car le solde reste supérieur à `-500 €`.

---

# 7. Vérification des avoirs d'une personne

La banque doit permettre de calculer la somme totale détenue par une personne sur l'ensemble de ses comptes.

Exemple :

```text
Anthony possède :

Compte courant : 1 000 €
Compte épargne : 3 000 €

Avoir total : 4 000 €
```

Les soldes négatifs éventuels doivent également être pris en compte dans le calcul.

Exemple :

```text
Compte courant : -200 €
Compte épargne : 3 000 €

Avoir total : 2 800 €
```

---

# 8. Impression d'un extrait de compte

Après chaque opération bancaire, un extrait de compte doit être affiché.

Cela concerne notamment :

* les dépôts ;
* les retraits ;
* l'application des intérêts.

L'impression doit être gérée par une **classe utilitaire statique**.

Par exemple :

```java
public final class AccountUtils {

    public static void imprimerExtrait(Compte compte) {
        // ...
    }
}
```

Exemple d'affichage :

```text
=============================
       EXTRAIT DE COMPTE
=============================
Compte : BE00123456789
Titulaire : Anthony Mets
Solde : 1 250.00 €
=============================
```

---

# 9. Application des intérêts

Chaque compte doit être capable de calculer et d'appliquer ses propres intérêts.

Le comportement dépend du type de compte.

---

## 🐷 Compte épargne

Le compte épargne possède un taux d'intérêt de :

```text
4,5 %
```

Calcul :

```text
Intérêt = solde × 4,5 %
```

Exemple :

```text
Solde avant intérêts : 1 000 €

Intérêt :
1 000 × 0,045 = 45 €

Solde après intérêts :
1 045 €
```

---

## 💳 Compte courant

Le taux dépend de la situation du compte.

### Solde positif

Taux :

```text
3 %
```

Exemple :

```text
Solde : 1 000 €

Intérêt :
1 000 × 0,03 = 30 €

Nouveau solde :
1 030 €
```

### Solde négatif

Taux :

```text
9,75 %
```

Dans ce cas, les intérêts représentent un coût supplémentaire.

Exemple :

```text
Solde : -1 000 €

Intérêt :
1 000 × 0,0975 = 97,50 €

Nouveau solde :
-1 097,50 €
```

---

# 10. Application des intérêts par la banque

La banque doit pouvoir appliquer les intérêts sur **tous les comptes qu'elle contient**.

Exemple :

```java
banque.appliquerInterets();
```

La banque parcourt alors l'ensemble de ses comptes et demande à chacun d'appliquer ses propres intérêts.

Le compte est responsable de savoir **comment ses intérêts doivent être calculés**.

---

# 🧱 Structure générale

Une architecture possible du projet est :

```text
src/
│
├── model/
│   ├── Personne.java
│   ├── Compte.java
│   ├── Courant.java
│   ├── Epargne.java
│   └── Banque.java
│
├── utils/
│   └── AccountUtils.java
│
└── Main.java
```

---

# 🔗 Relations entre les classes

```text
                Personne
                    │
                    │ titulaire
                    ▼
                 Compte
                /      \
               /        \
          Courant       Epargne
              ▲            ▲
              │            │
              └──────┬─────┘
                     │
                  Banque
                     │
                     │ contient
                     ▼
                 Comptes
```

---

# 🧠 Concepts orientés objet utilisés

Cet exercice permet notamment de travailler :

* l'encapsulation ;
* les getters et setters ;
* les constructeurs ;
* l'héritage ;
* le polymorphisme ;
* les classes abstraites ;
* les collections ;
* les relations entre objets ;
* la surcharge et la redéfinition de méthodes ;
* les méthodes statiques ;
* les classes utilitaires ;
* la responsabilité des classes.

---

# ✅ Résultat attendu

À la fin du projet, il doit être possible d'écrire quelque chose ressemblant à :

```java
Personne personne = new Personne(
        "Mets",
        "Anthony",
        LocalDate.of(1986, 8, 19)
);

Courant courant = new Courant(
        "BE001",
        personne,
        500
);

Epargne epargne = new Epargne(
        "BE002",
        personne
);

Banque banque = new Banque("Mjödheim Bank");

banque.ajouterCompte(courant);
banque.ajouterCompte(epargne);

courant.deposer(1000);
courant.retirer(300);

epargne.deposer(2000);
epargne.retirer(200);

double avoirs = banque.calculerAvoirs(personne);

banque.appliquerInterets();
```

Le projet doit respecter au maximum les principes de la programmation orientée objet : chaque classe doit être responsable de son propre comportement et les détails internes d'un compte ne doivent pas être modifiés directement depuis l'extérieur.
