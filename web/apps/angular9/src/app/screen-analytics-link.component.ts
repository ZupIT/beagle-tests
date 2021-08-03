import { Component } from '@angular/core';
import StorageService from './storage.service'

@Component({
  selector: 'screen-analytics-link',
  templateUrl: './screen-analytics-link.component.html'
})
export class ScreenAnalyticsLinkComponent {
  analyticsJson = '[]'

  constructor() {
    this.analyticsJson = JSON.stringify(StorageService.getData('Analytics_data'))
  }
}
