import { Component } from '@angular/core';

@Component({
  selector: 'beagle',
  templateUrl: './beagle.component.html'
})
export class BeagleComponent {
 
  route: string;
  private queryParams = new URLSearchParams(window.location.search);

  constructor() {
    this.route = this.queryParams.get('path') || '';
  }
}
