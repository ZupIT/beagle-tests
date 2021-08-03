import { Component } from '@angular/core';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'beagle-automated-tests-angular';

  route: string;
  private queryParams = new URLSearchParams(window.location.search);

  constructor() {
    this.route = this.queryParams.get('path') || '';
  }
}
