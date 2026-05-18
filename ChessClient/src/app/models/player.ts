export enum Genre {
    Male = 0,
    Female = 1,
    NonBinary = 2,
    PreferNotToSay = 3
}

export interface Player {
  id: number;
  pseudo: string;
  email: string;
  birthDate: string;
  genre: Genre;
  elo: number;
}
