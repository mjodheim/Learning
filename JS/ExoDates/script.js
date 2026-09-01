const dateDuJour = new Date();
const dateNaissance = new Date(1986, 7, 19);

const affichageAge = document.getElementById("age");
const affichageAnniversaire = document.getElementById("prochain-anniversaire");
const affichageJours = document.getElementById("jours-restants");


// Calcul de l'âge
let age = dateDuJour.getFullYear() - dateNaissance.getFullYear();

if (
    dateDuJour.getMonth() < dateNaissance.getMonth() ||
    (
        dateDuJour.getMonth() === dateNaissance.getMonth() &&
        dateDuJour.getDate() < dateNaissance.getDate()
    )
) {
    age--;
}

affichageAge.textContent = "Âge : " + age + " ans";


// Prochain anniversaire

let prochainAnniversaire = new Date(
    dateDuJour.getFullYear(),
    dateNaissance.getMonth(),
    dateNaissance.getDate()
);

if (prochainAnniversaire < dateDuJour) {
    prochainAnniversaire.setFullYear(dateDuJour.getFullYear() + 1);
}


// Affichage de la date en toutes lettres

const options = {
    day: "numeric",
    month: "long",
    year: "numeric"
};

affichageAnniversaire.textContent =
    "Prochain anniversaire : " +
    prochainAnniversaire.toLocaleDateString("fr-BE", options);


// BONUS : nombre de jours restants

const difference = prochainAnniversaire - dateDuJour; // renvoie la différence en millisecondes

// Math.ceil() pour arrondir à l'entier supérieur : une journée commencée compte pour une journée entière
const joursRestants = Math.ceil(
    difference / (1000 * 60 * 60 * 24)
);

affichageJours.textContent =
    "Jours restants : " + joursRestants;