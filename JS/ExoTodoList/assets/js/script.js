document.addEventListener('DOMContentLoaded', () => {
    const todoForm = document.getElementById('todo-form');
    const taskNameInput = document.getElementById('task-name');
    const taskDescInput = document.getElementById('task-desc');
    const taskDeadlineInput = document.getElementById('task-deadline');
    const todoList = document.getElementById('todo-list');
    const clearAllBtn = document.getElementById('clear-all');
    const emptyState = document.getElementById('empty-state');
    const taskTemplate = document.getElementById('task-template');

    let tasks = [];

    // --- Fonctions ---

    function updateUI() {
        // Gérer l'état vide
        if (tasks.length === 0) {
            emptyState.classList.remove('hidden');
        } else {
            emptyState.classList.add('hidden');
        }

        // Vider la liste actuelle pour tout redessiner (ou on pourrait optimiser)
        todoList.innerHTML = '';
        
        tasks.forEach((task, index) => {
            const taskElement = createTaskElement(task, index);
            todoList.appendChild(taskElement);
        });
    }

    function createTaskElement(task, index) {
        const clone = taskTemplate.content.cloneNode(true);
        const li = clone.querySelector('.task-item');
        
        // Titre et Description
        li.querySelector('.task-title').textContent = task.name;
        li.querySelector('.task-description').textContent = task.description || 'Aucune description';
        
        // Date limite
        const dateSpan = li.querySelector('.task-date');
        if (task.deadline) {
            const date = new Date(task.deadline);
            dateSpan.textContent = `📅 ${date.toLocaleDateString('fr-FR')}`;
        } else {
            dateSpan.classList.add('hidden');
        }

        // État (Status)
        const statusSpan = li.querySelector('.task-status');
        statusSpan.textContent = task.completed ? 'Terminé' : 'En cours';
        if (task.completed) {
            li.classList.add('completed');
        }

        // Actions
        li.querySelector('.btn-complete').addEventListener('click', () => toggleTask(index));
        li.querySelector('.btn-delete').addEventListener('click', () => deleteTask(index));

        return li;
    }

    function addTask(name, description, deadline) {
        const newTask = {
            name: name,
            description: description,
            deadline: deadline,
            completed: false,
            createdAt: new Date()
        };
        tasks.push(newTask);
        updateUI();
    }

    function toggleTask(index) {
        tasks[index].completed = !tasks[index].completed;
        updateUI();
    }

    function deleteTask(index) {
        tasks.splice(index, 1);
        updateUI();
    }

    function clearAll() {
        if (tasks.length === 0) return;
        if (confirm('Voulez-vous vraiment effacer toutes les tâches ?')) {
            tasks = [];
            updateUI();
        }
    }

    // --- Event Listeners ---

    todoForm.addEventListener('submit', (e) => {
        e.preventDefault();
        
        const name = taskNameInput.value.trim();
        const desc = taskDescInput.value.trim();
        const deadline = taskDeadlineInput.value;

        if (name) {
            addTask(name, desc, deadline);
            todoForm.reset();
        }
    });

    clearAllBtn.addEventListener('click', clearAll);

    // Initialisation
    updateUI();
});
