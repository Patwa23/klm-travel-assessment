import { Routes, RouterModule } from "@angular/router";
import { NgModule } from "@angular/core";

import { AssignmentComponent } from "./assignment.component";

import { AuthGuard } from "../auth/auth-guard.service";
import { AirportsComponent } from "./airports/airports.component";
import { FareComponent } from "./fare/fare.component";


const assignmentRoutes: Routes = [
    { path: '',component: AssignmentComponent},
    { path: 'airports', component: AirportsComponent },
    { path: 'fare', component: FareComponent },

];


@NgModule({
    imports:[RouterModule.forChild(assignmentRoutes)],
    exports:[RouterModule]
})

export class AssignmentRoutingModule{
}