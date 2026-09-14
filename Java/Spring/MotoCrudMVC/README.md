# CRUD Motos — Spring Boot MVC

Exercice de formation consacré à la réalisation d'un **CRUD complet en Spring Boot MVC** autour d'un petit catalogue de motos.

Le projet part d'une version avec données en mémoire puis évolue vers une persistance réelle avec **Spring Data JPA, Hibernate et PostgreSQL**. L'objectif est surtout de pratiquer le cycle MVC, les formulaires Thymeleaf, les relations entre entités et l'utilisation des repositories.

## Notions travaillées

- Spring Boot MVC ;
- contrôleurs et routage GET / POST ;
- Thymeleaf ;
- Spring Data JPA et repositories ;
- Hibernate ;
- PostgreSQL ;
- entités JPA et relation `@ManyToOne` ;
- opérations Create, Read, Update et Delete ;
- filtres combinés par marque et catégorie ;
- initialisation de données avec `CommandLineRunner` ;
- Bootstrap pour une interface simple et responsive ;
- variables d'environnement pour la configuration de la base.

## Énoncé de l'exercice

Créer une application MVC permettant de gérer une collection de motos.

### Entité `Moto`

- `id` (`Long`)
- `brand` (`String`)
- `model` (`String`)
- `cc` (`int`)
- `imageUrl` (`String`)
- `description` (`String`)
- `category` (`Category`)

### Entité `Category`

- `id` (`Long`)
- `name` (`String`)

Une moto appartient à une catégorie via une relation `@ManyToOne`.

### Fonctionnalités

- afficher la liste des motos ;
- consulter le détail d'une moto ;
- ajouter une moto ;
- modifier une moto ;
- supprimer une moto ;
- filtrer la liste par marque et/ou catégorie ;
- charger quelques motos et catégories au démarrage.

> La connexion PostgreSQL est configurée avec `DB_URL`, `DB_USERNAME` et `DB_PASSWORD` afin de ne pas stocker les identifiants de base de données dans le dépôt.
