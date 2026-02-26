# Tri instantané d'un tableau d'entiers que l'utilisateur doit remplir

# Déclaration du tableau
tab = []

print("Entrez la taille du tableau :")
taille = int(input())

while taille <= 0:
    print("Veuillez entrer un nombre entier positif")
    print("Entrez la taille du tableau :")
    taille = int(input())

for i in range(taille):
    print(f"Entrez l'élément {i + 1} :")
    number = int(input())
    
    # On récupère la position où insérer le nombre
    j = i - 1
    while j >= 0 and tab[j] > number:
        j -= 1
    
    # On insère le nombre à la position trouvée (après le plus petit que le nombre actuel),
    # en décalant les éléments plus grands vers la droite
    tab.insert(j + 1, number)

print("Tableau trié :")
print(tab) #Affiche directement le tableau, c'est pas beau ça ?!