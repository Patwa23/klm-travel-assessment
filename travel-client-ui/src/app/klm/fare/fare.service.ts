import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { Fare } from './fare.model';
import { properties } from '../../shared/app.properties';
import { throwError } from 'rxjs';


@Injectable()
export class FareService {
  constructor(private httpClient: HttpClient) { }

  public getFares(origin: string, destination: string) {
    let url = properties.getFare + origin + "/" + destination;
    try {
      return this.httpClient.get<Fare>(url);
    } catch (error) {
      error => {
        return throwError(error);
      }
    }
  }
}