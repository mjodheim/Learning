/*
    VARIABLES
*/
const titre = document.getElementById("titre");
const paragraphe = document.querySelector(".paragraphe");

const btnCouleur = document.getElementById("btnCouleur");
const btnPolice = document.getElementById("btnPolice");

const elementsListe = document.querySelectorAll("li");

/*
    INTERRACTION AVEC LE DOM
*/

// Modifier le contenu textuel du titre et du paragraphe
titre.textContent = "Titre modifié avec JavaScript";
paragraphe.textContent = "Paragraphe modifié avec JavaScript";

// Ajouter une classe CSS au paragraphe et l'afficher en console
// J'utilise toggle au lieu de add pour pouvoir retirer la classe si on reclique sur le bouton
btnCouleur.addEventListener("click", () => {
    paragraphe.classList.toggle("couleur");
    console.log("Classe active : couleur");
});

// Ajouter une autre classe CSS au paragraphe
btnPolice.addEventListener("click", () => {
    paragraphe.classList.toggle("police");
});

/*
    BONUS: Au clic d'un <li>, modifier son texte pour indiquer qu'il a été sélectionné, et
    retirer cette indication si on clique de nouveau dessus.
*/

for (const element of elementsListe) {
    element.addEventListener("click", () => {
        element.classList.toggle("selectionne");
    });
}