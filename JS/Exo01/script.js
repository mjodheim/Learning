let prenom = prompt("Quel est ton prénom ?");

alert(`Bonjour, ${prenom} !`);

const age = prompt("Quel âge as-tu ?");

// prompt renvoie toujours un string
console.log(typeof age);

alert(`Tu t'appelles ${prenom} et tu as ${age} ans.`);