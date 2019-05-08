// Angular components imports
import { Title, BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
// Maincontainer component imports
import { AppComponent } from './app.component';
// Technical components import
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { AngularFontAwesomeModule } from 'angular-font-awesome';
import { FileDropModule } from 'ngx-file-drop';
import { AgGridModule } from 'ag-grid-angular';
import { _ } from 'underscore';
import more from 'highcharts/highcharts-more.src';
import exporting from 'highcharts/modules/exporting.src';
import { NgxPermissionsModule } from 'ngx-permissions';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';
import { Store, StoreModule } from '@ngrx/store'
import { CoreModule } from './core/core.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { ToastrModule } from 'ngx-toastr';
import { reducers } from './store/app.reducer';
import { OAuthModule } from 'angular-oauth2-oidc';
import { BASE_URL } from './auth/auth.token';

@NgModule({
    declarations: [
        AppComponent,
        PageNotFoundComponent,
    ],
    imports: [
        CoreModule,
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        AngularFontAwesomeModule,
        FileDropModule,
        AgGridModule.withComponents([]),
        NgxPermissionsModule.forRoot(),
        NgbModule.forRoot(),
        StoreModule.forRoot(reducers),
        BrowserAnimationsModule,
        ToastrModule.forRoot({
            timeOut: 5000,
            positionClass: 'toast-bottom-left',
            preventDuplicates: false
        }),

        //     OAuthModule.forRoot({
        //         resourceServer: {
        //           allowedUrls: ['http://localhost:8080/oauth/token?grant_type=password'],
        //           sendAccessToken: true
        //         }
        //   })
    ],
    providers: [
        Title,
        // { provide: BASE_URL, useValue: 'http://localhost:8080' }
    ],
    bootstrap: [
        AppComponent,
    ]
})
export class AppModule { }