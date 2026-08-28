// 1. Demander un prix

let prix = prompt("Entrez un prix :");

// Conversion en nombre réel
prix = parseFloat(prix);

console.log("Prix :", prix);

// Formatage avec 2 chiffres après la virgule
console.log("Prix formaté :", prix.toFixed(2) + " €");


// 2. Avec plusieurs variables

let prenom = prompt("Entrez votre prénom :");
let nom = prompt("Entrez votre nom :");

// Concaténation
let nomComplet = prenom + " " + nom;

console.log("Nom complet :", nomComplet);


// 3. Avec un objet

let utilisateur = {
    prenom: prompt("Entrez votre prénom :"),
    nom: prompt("Entrez votre nom :")
};

// Concaténation
let nomCompletObjet = utilisateur.prenom + " " + utilisateur.nom;

console.log("Nom complet avec objet :", nomCompletObjet);


// 4. Objet personne

let personne = {
    nom: "Mets",
    prenom: "Anthony",
    age: 40,
    ville: "Beaumont"
};


// Ajouter une propriété dynamiquement
personne.email = "anthony@email.com";


// Afficher chaque propriété
console.log("Nom :", personne.nom);
console.log("Prénom :", personne.prenom);
console.log("Âge :", personne.age);
console.log("Ville :", personne.ville);
console.log("Email :", personne.email);


// Supprimer la propriété email
delete personne.email;


// Tenter de l'afficher
console.log("Email après suppression :", personne.email);


// 5. Création d'un identifiant
// substring ([index début, index fin[)

// 3e et 4e caractères du prénom
let partiePrenom = personne.prenom.substring(2, 4);

// 2 premiers caractères du nom
let partieNom = personne.nom.substring(0, 2);

// Création de l'identifiant
let identifiant = partiePrenom + partieNom + personne.age;

console.log("Identifiant :", identifiant);