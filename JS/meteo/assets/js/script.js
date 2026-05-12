//#region Configuration

const CLE_API = "ca07e8cf1510366dff47e0d1a8e1066e"

//#endregion

//#region Sélection des éléments

const formulaire = document.getElementById("formulaire-meteo");
const bouttonRecherche = document.getElementById("boutton-recherche");
const textBoutton = document.getElementById("text-boutton");
const carteResultat = document.getElementById("carte-resultat");
const contenuResultat = document.getElementById("contenu-resultat");
const alerteErreur = document.getElementById("alerte-erreur");

//#endregion

//#region écoute du formulaire

formulaire.addEventListener("submit", function (evenement) {
	evenement.preventDefault();

	let ville = document.getElementById('ville').value.trim();
	let pays = document.getElementById('pays').value.trim().toUpperCase();

	if (!ville) return;

	let requete = ville;
	if (pays) {
		requete = requete + `,${pays}`;
	}

	// spinner
	textBoutton.innerHTML = `<span class="spinner-border text-light"></span>`
	bouttonRecherche.disabled = true;

	const url = `https://api.openweathermap.org/data/2.5/weather?q=${encodeURIComponent(requete)}&appid=${CLE_API}&units=metric&lang=fr`

	fetch(url)
		.then(response => {
			if (!response.ok) throw new Error("Ville non trouvé ou erreur venant du serveur...")
			return response.json();
		})
		.then(donnees => {
			afficherMeteo(donnees)
		})
		.catch(erreur => {
			alerteErreur.textContent = erreur.message;
			alerteErreur.classList.remove('d-none');
		})
		.finally(() => {
			textBoutton.textContent = 'Rechercher';
			bouttonRecherche.disabled = false;
		})
})

//#endregion

//#region Fonctions / Procédure

function afficherMeteo(donnees){

	const codeIcone = donnees.weather[0].icon;
	const temperature = Math.round(donnees.main.temp);
	const description = donnees.weather[0].description;
	const nomVille = donnees.name;
	const codePays = donnees.sys.country;

	// conteneur
	const conteneur = document.createElement('div');
	conteneur.classList.add('text-center');

	// Icône
	const imgIcone = document.createElement('img');
	imgIcone.src = `https://openweathermap.org/img/wn/${codeIcone}@4x.png`;
	imgIcone.alt = 'Icône de météo';
	imgIcone.style.width = '140px';
	imgIcone.classList.add('mb-4');

	conteneur.appendChild(imgIcone);

	// Température
	const h2Temp = document.createElement('h2');
	h2Temp.classList.add('display-1','fw-bold', 'mb-0');
	h2Temp.textContent = temperature;

	const spanCelsius = document.createElement('span');
	spanCelsius.classList.add('fs-2', 'fw-normal');
	spanCelsius.textContent = '°C';

	h2Temp.appendChild(spanCelsius);
	conteneur.appendChild(h2Temp);

	// Description
	const pDescription = document.createElement('p');
	pDescription.classList.add('fs-3', 'text-capitalize', 'text-muted', 'mb-4');
	pDescription.textContent = description;
	conteneur.appendChild(pDescription);

	// Ville + pays
	const divVille = document.createElement('div');
	divVille.classList.add('d-flex', 'align-items-center', 'justify-content-center', 'gap-2', 'mb-5');
	const h4Ville = document.createElement('h4');
	h4Ville.classList.add('mb-0')
	h4Ville.textContent = nomVille;
	divVille.appendChild(h4Ville);

	if (codePays){
		const badgePays = document.createElement('span');
		badgePays.classList.add('badge', 'bg-primary', 'fs-6', 'px-3', 'py-2');
		badgePays.textContent = codePays;
		divVille.appendChild(badgePays);
	}

	conteneur.appendChild(divVille);

	// Détails (ressentis / humidité / Vent)
	const rowDetails = document.createElement('div');
	rowDetails.classList.add('row', 'g-4', 'text-center');

	const col1 = creerColonneDetail('Ressentie', `${Math.round(donnees.main.feels_like)} °C`)
	const col2 = creerColonneDetail('Humidité', `${donnees.main.humidity}%`)
	const col3 = creerColonneDetail('Vent', `${donnees.wind.speed} m/s`)

	rowDetails.appendChild(col1);
	rowDetails.appendChild(col2);
	rowDetails.appendChild(col3);

	conteneur.appendChild(rowDetails);

	contenuResultat.appendChild(conteneur);
	carteResultat.classList.remove('d-none');
}


function creerColonneDetail(titre, valeur){
	const col = document.createElement('div');
	col.classList.add('col-4');

	const small = document.createElement('small');
	small.classList.add('text-muted', 'd-block');
	small.textContent = titre

	const strong = document.createElement('strong');
	strong.classList.add('fs-5');
	strong.textContent = valeur;

	col.appendChild(small);
	col.appendChild(strong);
	return col;
}

//#endregion