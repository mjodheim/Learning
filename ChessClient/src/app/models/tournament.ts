import { Category } from './category';
import { Match } from './match';
import { Player } from './player';

export interface Tournament {
    id: number;
    name: string;
    location: string;
    minPlayers: number;
    maxPlayers: number;
    minElo: number;
    maxElo: number;
    status: TournamentStatus;
    currentRound: number;
    womenOnly: boolean;
    registrationDeadline: string;
    createdAt: string;
    updatedAt: string;
    categories: Category[];
    registeredPlayers: Player[];
    currentRoundMatches: Match[];
}

export enum TournamentStatus {
    WaitingForPlayers = 0,
    InProgress = 1,
    Canceled = 2,
    Closed = 3
}