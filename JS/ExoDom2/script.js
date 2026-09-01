const inputFruit = document.getElementById("input-fruit");
const btnAjouter = document.getElementById("btn-ajouter");
const btnSupprimerTout = document.getElementById("btn-supprimer-tout");
const liste = document.getElementById("ma-liste");
const erreur = document.getElementById("erreur");

btnAjouter.addEventListener("click", () => {

    const fruit = inputFruit.value;

    erreur.textContent = fruit === "" ? "Veuillez entrer un fruit." : "";

    const li = document.createElement("li");

    li.textContent = fruit;

    const btnSupprimer = document.createElement("button");

    btnSupprimer.textContent = "Supprimer";

    btnSupprimer.addEventListener("click", () => {
        li.remove();
    });

    li.appendChild(btnSupprimer);

    liste.appendChild(li);

    inputFruit.value = "";
});


btnSupprimerTout.addEventListener("click", () => {
    liste.innerHTML = "";
});