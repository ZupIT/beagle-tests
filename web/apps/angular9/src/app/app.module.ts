import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { Beagle } from './beagle.module';
import { ScreenAnalyticsLinkComponent } from './screen-analytics-link.component';
import { BeagleComponent } from './beagle.component';

@NgModule({
  declarations: [
    AppComponent,
    ScreenAnalyticsLinkComponent,
    BeagleComponent
  ],
  imports: [
    BrowserModule.withServerTransition({ appId: 'serverApp' }),
    AppRoutingModule,
    Beagle
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
