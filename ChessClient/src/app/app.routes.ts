import { Routes } from '@angular/router';

export const routes: Routes = [
    {
        path: '',
        loadComponent: () => import('./features/home/home').then(m => m.Home)
    },
    {
        path: 'player/:id',
        loadComponent: () => import('./features/player/player-details/player-details').then(m => m.PlayerDetails)
    },
    {
        path: 'players',
        loadComponent: () => import('./features/player/players-list/players-list').then(m => m.PlayersList)
    },
    {
        path: 'tournament/:id',
        loadComponent: () => import('./features/tournament/tournament-details/tournament-details').then(m => m.TournamentDetails)
    },
    {
        path: 'tournaments',
        loadComponent: () => import('./features/tournament/tournaments-list/tournaments-list').then(m => m.TournamentsList)
    },
    // Route Wildcard for 404 - Not Found redirecting to Home
    {
        path: '**',
        redirectTo: ''
    }
];
