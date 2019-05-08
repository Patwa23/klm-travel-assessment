import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { throwError } from "rxjs";
import { take, catchError } from "rxjs/operators";
import { Airport } from './airports.model';
import { properties } from '../../shared/app.properties';

@Injectable()
export class AirportService {

  constructor(private httpClient: HttpClient) {
  }

  public getAirports() {
    return this.httpClient.get<any[]>(properties.getAirports, { responseType: 'json' });
  }

  public getAirportsByCode(searchValue: string) {
    const params = {
      term: searchValue,
    };
    return this.httpClient.get<any[]>(properties.getAirports, { responseType: 'json', params })
      .pipe(
        take(1),
        catchError(err => {
          console.log('Handling error locally and rethrowing it...', err);
          return throwError(err);
        }
        ));
  }

}