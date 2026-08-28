const DOM = {
    listProducts: document.getElementById('listProducts'),
    addProduct: document.getElementById('addProduct'),
    addToList: document.getElementById('btnAdd'),
    clearList: document.getElementById('btnClear')
}

// Ajouter un produit
DOM.addToList.addEventListener('click', function(){
    const element = document.createElement('li');
    const btn = document.createElement('button');
    btn.textContent = '❌';
    element.textContent = DOM.addProduct.value;
    if(element.textContent === '') return;
    element.append(' ',btn);

    DOM.listProducts.append(element);
    DOM.addProduct.value = '';

    // supprimer ce produit
    btn.addEventListener('click',function(){
        element.remove();
    })
    
})

// Supprimer tous les produits
DOM.clearList.addEventListener('click', function(){
    DOM.listProducts.innerHTML = '';
})

// Supprimer un produit spécifique (pour la liste existante)
const count = DOM.listProducts.childElementCount
for (let index = 0; index < count; index++) {
    const element = DOM.listProducts.children[index];
    const button = element.getElementsByTagName('button')[0];
    button.addEventListener('click',function(){
        element.remove();
    })
}