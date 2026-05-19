import { inject, Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { Player } from '../../shared/models/player';

@Injectable({
  providedIn: 'root',
})
export class PlayerService {
  private readonly http = inject(HttpClient);
  private readonly apiUrl = environment.apiUrl;

}
