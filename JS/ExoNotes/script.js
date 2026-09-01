const inputNote = document.getElementById("note");
const bouton = document.getElementById("btnAfficher");
const resultat = document.getElementById("resultat");

bouton.addEventListener("click", function () {

    let note;

    note = inputNote.value.trim() === "" ? null : Number(inputNote.value);

    if (note === null) {
        resultat.textContent = "Aucune note n'a été encodée.";
    } else if (note >= 18) {
        resultat.textContent = "Note : " + note + "/20 - Excellent";
    } else if (note >= 14) {
        resultat.textContent = "Note : " + note + "/20 - Bien";
    } else if (note >= 10) {
        resultat.textContent = "Note : " + note + "/20 - Suffisant";
    } else {
        resultat.textContent = "Note : " + note + "/20 - Insuffisant";
    }

});