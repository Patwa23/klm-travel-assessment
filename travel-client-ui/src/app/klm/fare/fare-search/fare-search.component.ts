import { Component, OnInit } from '@angular/core';
import { Fare } from '../fare.model';
import { FareService } from '../fare.service';
import { ToastrService } from 'ngx-toastr';
import { HttpErrorResponse } from '@angular/common/http';

import { FormControl, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { map, startWith, filter } from 'rxjs/operators';
import { AirportService } from '../../airports/airports.service';
import { Airport } from '../../airports/airports.model';

@Component({
  selector: 'app-fare-search',
  templateUrl: './fare-search.component.html',
  styleUrls: ['./fare-search.component.css']
})
export class FareSearchComponent {
  fare: Fare;
  isCardHide = true;
  isPanelExpanded = true;
  isSpinnerHide = true;
  airports: Airport[];

  sourceAirportCtrl = new FormControl('', Validators.required);
  filteredSourceAirport: Observable<Airport[]>;

  destinationAirportCtrl = new FormControl('', Validators.required);
  filteredDestinationAirport: Observable<Airport[]>;

  constructor(private fareService: FareService,
    private toastr: ToastrService,
    private airportService: AirportService) {

    this.airportService.getAirports()
      .subscribe(
        data => {
          let locations = data['_embedded'];
          this.airports = locations['locations'];

          this.filteredSourceAirport = this.sourceAirportCtrl.valueChanges
            .pipe(
              startWith(''),
              map(state => state ? this._filterStates(state) : this.airports.slice()),
          );

          this.filteredDestinationAirport = this.destinationAirportCtrl.valueChanges
            .pipe(
              startWith(''),
              map(state => state ? this._filterStates(state) : this.airports.slice()),
          );
        });
  }

  public getFare() {
    this.isSpinnerHide = false;
    this.isCardHide = true;
    this.fareService.getFares(this.sourceAirportCtrl.value, this.destinationAirportCtrl.value)
      .subscribe(
        data => {
          this.fare = data;
          this.isCardHide = false;
          this.isPanelExpanded = false;
          this.isSpinnerHide = true;
        })
      , (error: HttpErrorResponse) => {
        this.isSpinnerHide = true;
        this.isCardHide = true;
        this.toastr.error("Fare", "Server Error" + error.message);
      }
  }

  public reset() {
    this.isCardHide = true;
    this.isSpinnerHide = true;
    this.sourceAirportCtrl.reset();
    this.destinationAirportCtrl.reset();
  }

  private _filterStates(value: string): Airport[] {
    const filterValue = value.toLowerCase();
    return this.airports.filter(airport => airport.name.toLowerCase().indexOf(filterValue) === 0);
  }

}

