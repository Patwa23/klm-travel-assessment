import { Routes, RouterModule, PreloadAllModules } from "@angular/router";
import { NgModule } from "@angular/core";

import { ChartComponent } from "./technicalcomponents/charts/chart.component";
import { PageNotFoundComponent } from "./page-not-found/page-not-found.component";
import { DashboardComponent } from "./core/dashboard/dashboard.component";
import { AuthGuard } from "./auth/auth-guard.service";

const appRoutes: Routes = [
    { path: 'dashboard', component: DashboardComponent },
    { path: 'assignment', loadChildren: './klm/assignment.module#AssignmentModule', canActivate: [AuthGuard] },
    { path: 'login', loadChildren: './auth/auth.module#AuthModule', canActivate: [AuthGuard] },
    { path: 'chart', component: ChartComponent },
    { path: '', redirectTo: '/dashboard', pathMatch: 'full' },
    { path: 'not-found', component: PageNotFoundComponent },
    { path: '**', redirectTo: 'not-found' }
];


@NgModule({
    imports: [RouterModule.forRoot(appRoutes, {
        enableTracing: false,
        preloadingStrategy: PreloadAllModules
    })],
    exports: [RouterModule]
})

export class AppRoutingModule {
}