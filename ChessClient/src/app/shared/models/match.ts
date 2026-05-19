export interface Match {
    id: number;
    player1Id: number;
    player2Id: number;
    tournamentId: number;
    round: number;
    result: MatchResult;
}

export enum MatchResult {
    Pending = 0,
    WhiteWin = 1,
    BlackWin = 2,
    Draw = 3
}