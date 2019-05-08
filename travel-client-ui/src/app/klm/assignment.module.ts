// Angular components imports
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { FileDropModule } from 'ngx-file-drop';


import { AssignmentRoutingModule } from './assignment-routing.module';
import { MaterialModule } from '../material.module';
import { CommonModule } from '@angular/common';
import { AssignmentComponent } from './assignment.component';

import { StoreModule } from '@ngrx/store';
import { AirportService } from './airports/airports.service';
import { FareComponent } from './fare/fare.component';
import { FareService } from './fare/fare.service';
import { AirportsComponent } from './airports/airports.component';
import { FareSearchComponent } from './fare/fare-search/fare-search.component';
import { FareOutputComponent } from './fare/fare-search/fare-output/fare-output.component';

@NgModule({
    declarations: [
        AssignmentComponent,
        AirportsComponent,
        FareComponent,
        FareSearchComponent,
        FareOutputComponent,
    ],
    imports: [
        ReactiveFormsModule,
        FormsModule,
        CommonModule,
        AngularFontAwesomeModule,
        FileDropModule,
        AssignmentRoutingModule,
        MaterialModule
    ],
    providers: [
        AirportService,
        FareService
    ],
})
export class AssignmentModule { }
