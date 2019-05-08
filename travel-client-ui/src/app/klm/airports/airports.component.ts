import { Component, OnInit, ViewChild } from '@angular/core';
import { MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { AirportService } from './airports.service';
import { Airport } from './airports.model';

import { startWith, map } from 'rxjs/operators';
import { Observable, throwError } from 'rxjs';
import { FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-airports',
  templateUrl: './airports.component.html',
  styleUrls: ['./airports.component.css']
})
export class AirportsComponent implements OnInit {

  displayedColumns = [];
  airport: any[];
  dataSource: MatTableDataSource<any>;
  isHide = true;
  isPanelExpanded = true;
  code = '';

  hideValidation = false;

  airports: Airport[];
  sourceAirportCtrl = new FormControl('', Validators.required);
  filteredSourceAirport: Observable<Airport[]>;

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim().toLowerCase(); // Remove whitespace
    this.dataSource.filter = filterValue;

    this.dataSource.filterPredicate =
      (data: any, filterValue: string) => {
        const matchFilter = [];
        const filterArray = filterValue.split(',');
        const columns = [data.code];
        //  const columns = (<any>Object).values(data);

        //Main loop
        filterArray.forEach(filterValue => {
          const customFilter = [];
          columns.forEach(column => customFilter.push(column.toString().toLowerCase().includes(filterValue)));
          matchFilter.push(customFilter.some(Boolean)); // OR
        });
        return matchFilter.every(Boolean); // AND
      }
  }

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private airportService: AirportService) {
  }

  ngOnInit() {
    //----Code
    this.airport = [] //TABLE DATASOURCE
  }

  getAirports() {
    //----Code
    this.code = this.sourceAirportCtrl.value;
    this.airport = [] //TABLE DATASOURCE
    this.airportService.getAirportsByCode(this.code)
      .subscribe(
        data => {
          let locations = data['_embedded'];
          this.airport = locations['locations'];
          this.dataSource = new MatTableDataSource(this.airport);
          this.dataSource.sort = this.sort;
          this.dataSource.paginator = this.paginator;
          this.isHide = false;
          this.isPanelExpanded = false;

          //CREATE DISPLAYED COLUMNS DYNAMICALLY
          this.displayedColumns = [];
          for (let v in this.airport[0]) {
            if (v === 'code' || v === 'name' || v === 'description')
              this.displayedColumns.push(v);
          }
        })
  }

  reset() {
    //----Code
    this.airport = [] //TABLE DATASOURCE
    this.code = '';
  }

  onSearchChange(searchValue: string) {
    this.airportService.getAirportsByCode(searchValue)
      .subscribe(data => {
        let locations = data['_embedded'];
        this.airports = locations['locations'];
        this.filteredSourceAirport = this.sourceAirportCtrl.valueChanges
          .pipe(
            startWith(''),
            map(state => state ? this._filterStates(state) : this.airports.slice()),
        );
      },
        err => throwError('HTTP Error', err)
      );
  }

  private _filterStates(value: string): Airport[] {
    const filterValue = value.toLowerCase();
    return this.airports.filter(airport => airport.name.toLowerCase().indexOf(filterValue) === 0);
  }
}
