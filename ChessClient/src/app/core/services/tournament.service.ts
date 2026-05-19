import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Tournament } from '../../shared/models/tournament';

@Injectable({
  providedIn: 'root',
})
export class TournamentService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = environment.apiUrl;

}
