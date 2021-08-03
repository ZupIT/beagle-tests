import React from 'react';
import { BeagleProvider, BeagleRemoteView } from '@zup-it/beagle-react';
import BeagleService from './beagle/beagle-service';

function App() {
  const windowUrl = window.location.search;
  const queryParams = new URLSearchParams(windowUrl);
  const route = queryParams.get("path") || '/payload.json'

  return (
    <BeagleProvider value={BeagleService}>
      <BeagleRemoteView route={route} />
    </BeagleProvider>
  );
}

export default App;