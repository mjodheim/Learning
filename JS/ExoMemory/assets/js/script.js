// EventListener sur le DOM : on récupère tous les éléments du DOM dont on a besoin.
// DOMContentLoaded : On attend que tout le HTML soit chargé avant de lancer le script.
document.addEventListener('DOMContentLoaded', () => {
    const gameGrid = document.getElementById('game-grid');
    const movesDisplay = document.getElementById('moves-count');
    const pairsDisplay = document.getElementById('pairs-count');
    const resetBtn = document.getElementById('reset-btn');
    const modalResetBtn = document.getElementById('modal-reset-btn');
    const winModal = document.getElementById('win-modal');
    const finalMovesDisplay = document.getElementById('final-moves');

    // Tableau d'icônes représente les paires à retrouver. On duplique le tableau pour avoir 16 cartes.
    const icons = ['🔥', '⚡', '🌈', '💎', '🎨', '🚀', '🍕', '🎮'];
    let cards = [...icons, ...icons];
    let flippedCards = [];
    let matchedPairs = 0;
    let moves = 0;
    // On bloque le clic sur une carte si on est en train de vérifier une paire.
    let isProcessing = false;

    // Initialisation du jeu / reset
    function initGame() {
        gameGrid.innerHTML = '';
        flippedCards = [];
        matchedPairs = 0;
        moves = 0;
        isProcessing = false;
        movesDisplay.textContent = '0';
        pairsDisplay.textContent = `0/${icons.length}`;
        winModal.classList.add('hidden');

        // On mélange le tableau des cartes 
        cards.sort(() => Math.random() - 0.5);

        // On place les cartes dans le grid
        cards.forEach((icon, index) => {
            const card = createCard(icon, index);
            gameGrid.appendChild(card);
        });
    }

    function createCard(icon, index) {
        const card = document.createElement('div');
        card.classList.add('card');
        card.dataset.icon = icon;
        card.dataset.index = index;

        card.innerHTML = `
            <div class="card-inner">
                <div class="card-front"></div>
                <div class="card-back">${icon}</div>
            </div>
        `;

        card.addEventListener('click', () => handleCardClick(card));
        return card;
    }

    function handleCardClick(card) {
        // Ignorer si déjà retournée, déjà matchée ou si on attend un délai
        if (isProcessing || card.classList.contains('flipped') || card.classList.contains('matched')) {
            return;
        }

        flipCard(card);
        flippedCards.push(card);

        if (flippedCards.length === 2) {
            checkMatch();
        }
    }

    function flipCard(card) {
        card.classList.add('flipped');
    }

    function unflipCard(card) {
        card.classList.remove('flipped');
    }

    function checkMatch() {
        isProcessing = true;
        moves++;
        movesDisplay.textContent = moves;

        const [card1, card2] = flippedCards;
        const isMatch = card1.dataset.icon === card2.dataset.icon;

        if (isMatch) {
            handleMatch(card1, card2);
        } else {
            handleNoMatch(card1, card2);
        }
    }

    function handleMatch(card1, card2) {
        card1.classList.add('matched');
        card2.classList.add('matched');
        matchedPairs++;
        pairsDisplay.textContent = `${matchedPairs}/${icons.length}`;

        flippedCards = [];
        isProcessing = false;

        if (matchedPairs === icons.length) {
            showWinModal();
        }
    }

    function handleNoMatch(card1, card2) {
        setTimeout(() => {
            unflipCard(card1);
            unflipCard(card2);
            flippedCards = [];
            isProcessing = false;
        }, 1000);
    }

    function showWinModal() {
        finalMovesDisplay.textContent = moves;
        winModal.classList.remove('hidden');
    }

    resetBtn.addEventListener('click', initGame);
    modalResetBtn.addEventListener('click', initGame);

    // Démarrer le jeu
    initGame();
});
