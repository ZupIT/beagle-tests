import { BeagleModule } from '@zup-it/beagle-angular';
import { environment } from './../environments/environment';
// import all the components you wish to use with Beagle.

@BeagleModule({
  baseUrl: environment.baseUrl || 'http://localhost:4200/public',
  module: {
    path: './beagle-components.module',
    name: 'BeagleComponentsModule',
  },
  components: {
    // Associate every beagle component to your angular component.
  },
})
export class Beagle {}
