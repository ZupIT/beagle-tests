import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ScreenAnalyticsLinkComponent } from './screen-analytics-link.component';
import { BeagleComponent } from './beagle.component';

const routes: Routes = [
  { path: '', component: BeagleComponent},
  { path: 'screen-analytics-link', component: ScreenAnalyticsLinkComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes, {
    initialNavigation: 'enabled'
})],
  exports: [RouterModule]
})
export class AppRoutingModule { }
