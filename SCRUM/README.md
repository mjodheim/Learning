# Agile & Scrum — Résumé

Ce document présente les principales **méthodologies de gestion de projet logiciel**, en opposant notamment l'approche **Waterfall** à la philosophie **Agile**, puis détaille le fonctionnement du framework **Scrum**.

## 1. Waterfall vs Agile

### Waterfall
La méthode en cascade organise le projet en phases successives :

**Analyse → Conception → Développement → Tests → Déploiement → Maintenance**

Elle est adaptée lorsque :
- les besoins sont clairs et stables ;
- la technologie est connue ;
- le projet est prévisible et relativement court.

Ses principales limites sont sa **rigidité**, l'**effet tunnel**, le feedback client tardif, la difficulté à modifier le projet en cours de route et la découverte tardive des risques.

### Agile
Agile repose sur une approche **itérative et incrémentale** : on livre régulièrement une partie fonctionnelle du produit, on récupère du feedback et on adapte la suite du travail.

Les 3 piliers de l'empirisme sont :
- **Inspection**
- **Adaptation**
- **Transparence**

L'objectif est de réduire les risques, améliorer la communication et produire de la valeur rapidement.

## 2. Le Manifeste Agile

Les 4 valeurs fondamentales privilégient :

1. **Les individus et leurs interactions** plutôt que les processus et les outils.
2. **Un logiciel opérationnel** plutôt qu'une documentation excessive.
3. **La collaboration avec le client** plutôt que la négociation contractuelle.
4. **L'adaptation au changement** plutôt que le suivi strict d'un plan.

Agile est avant tout une **philosophie de travail** : elle donne une direction et des principes, sans imposer une manière unique de travailler.

## 3. Scrum

**Scrum** est un framework Agile permettant de produire une version fonctionnelle du produit au cours de cycles courts appelés **Sprints**, généralement de **1 à 4 semaines**.

Un projet Scrum se déroule en trois grandes phases :

1. **Phase initiale**
   - analyse ;
   - création du Product Backlog ;
   - estimation globale des risques, coûts et délais ;
   - constitution de l'équipe.

2. **Phase de Sprints**
   - sélection des éléments à réaliser ;
   - développement ;
   - tests ;
   - feedback ;
   - adaptation.

3. **Phase de clôture**
   - préparation du produit pour sa livraison.

L'équipe Scrum est **autonome et auto-organisée**.

## 4. Les rôles Scrum

### Product Owner
Il porte la **vision du produit** et gère les priorités du **Product Backlog**.  
Il représente les besoins des utilisateurs et cherche à maximiser la valeur produite.

### Scrum Master
Il veille au bon fonctionnement de Scrum :
- facilite les réunions ;
- aide l'équipe à travailler de manière autonome ;
- élimine les obstacles ;
- protège l'équipe des perturbations extérieures ;
- favorise l'amélioration continue.

Il agit comme un **Servant Leader**, pas comme un chef d'équipe.

### Scrum Team
L'équipe réalise concrètement le produit. Elle décide elle-même **comment** accomplir le travail sélectionné pour le Sprint.

## 5. Les événements Scrum

### Sprint Planning
Permet de définir :
- les objectifs du Sprint ;
- les User Stories à réaliser ;
- la manière d'organiser le travail.

### Daily Scrum
Courte réunion quotidienne permettant de synchroniser l'équipe et d'identifier les obstacles.

### Sprint Review
Présentation de l'incrément réalisé afin de recueillir le feedback des parties prenantes et d'adapter la suite du produit.

### Sprint Retrospective
L'équipe analyse sa manière de travailler afin d'identifier ce qui a bien fonctionné, ce qui doit être amélioré et les actions à appliquer au Sprint suivant.

## 6. Product Backlog et User Stories

Le **Product Backlog** contient l'ensemble des éléments nécessaires au produit. Il évolue tout au long du projet.

Un bon Product Backlog est **DEEP** :

- **D**etailed Appropriately
- **E**stimated
- **E**mergent
- **P**rioritized

Les besoins sont souvent exprimés sous forme de **User Stories** :

> **En tant que** ...  
> **Je veux** ...  
> **Afin de** ...

Une bonne User Story respecte le principe **INVEST** :

- **I**ndependent
- **N**egotiable
- **V**aluable
- **E**stimable
- **S**mall
- **T**estable

Une tâche n'est réellement terminée que lorsqu'elle respecte la **Definition of Done**, par exemple : implémentée, testée, intégrée et documentée.

## 7. Estimation et planification

L'effort est estimé en **Story Points**. Ils représentent une valeur **relative**, et non un nombre d'heures ou de jours.

L'estimation prend notamment en compte :
- la complexité ;
- l'incertitude ;
- le risque ;
- l'ampleur ;
- l'effort.

Le cours utilise notamment la suite de **Fibonacci** pour les estimations :  
`1, 2, 3, 5, 8, 13, 21...`

Le **Planning Poker** permet à l'équipe de comparer les estimations et d'arriver à un consensus.

La **vélocité** correspond à la quantité de Story Points qu'une équipe peut réaliser pendant un Sprint. Elle devient plus fiable au fil des itérations.

Un **Burndown Chart** permet de visualiser l'évolution du travail restant dans le temps.

## 8. Vision produit

Avant de développer, l'équipe doit comprendre :
- **pour qui** le produit est créé ;
- **quel problème** il résout ;
- **quelle valeur** il apporte ;
- **comment** il se différencie ;
- **quels objectifs business** il poursuit.

La vision doit être partagée, concise et suffisamment large pour laisser de la place à l'adaptation.

Le cours insiste également sur le **Minimum Viable Product (MVP)** : commencer par la version la plus simple répondant réellement aux besoins essentiels, puis apprendre grâce aux retours utilisateurs.

## 9. Agile à grande échelle

Scrum fonctionne particulièrement bien avec de petites équipes. Pour de grandes organisations, plusieurs équipes peuvent être coordonnées grâce au **Scaling Agile**, avec différents niveaux :

**Équipe → Programme → Solution → Portfolio**

L'objectif reste cependant de conserver l'autonomie des équipes et d'ajouter uniquement les couches de coordination nécessaires.

---

## À retenir

> **Waterfall cherche à prévoir le projet. Agile cherche à apprendre du projet.**

Scrum applique cette philosophie en travaillant par **Sprints courts**, avec une **équipe autonome**, un **feedback fréquent**, une **priorisation continue** et une amélioration progressive du produit et de la manière de travailler.
