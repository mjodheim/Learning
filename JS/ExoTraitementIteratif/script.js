const btnGenerer = document.getElementById('btn-generer');
const btnEffacer = document.getElementById('btn-effacer');
const grille = document.getElementById('grille');

btnGenerer.addEventListener('click', () => {
    // Vider la grille avant de générer de nouvelles cases
    grille.innerHTML = '';

    // Générer 25 cases numérotées de 1 à 25
    for (let i = 1; i <= 25; i++) {
        // Création de l'élément à insérer dans la grille
        const caseDiv = document.createElement('div');

        caseDiv.classList.add('case');
        caseDiv.textContent = i;
        grille.appendChild(caseDiv);
    }

    // Parcourir les cases générées et colorer en rouge les multiples de 3, en vert les autres
    const cases = grille.querySelectorAll('.case');
    let i = 0;
    while (i < cases.length) {
        const caseDiv = cases[i];
        // Récupérer le numéro de la case à partir de son contenu textuel, en base 10
        const numero = parseInt(caseDiv.textContent, 10);

        if (numero % 3 === 0) {
            caseDiv.classList.add('case-rouge');
        } else {
            caseDiv.classList.add('case-verte');
        }

        i++;
    }
});

btnEffacer.addEventListener('click', () => {
    // Supprimer toutes les cases de la grille
    grille.innerHTML = "";
});