/*
 * Copyright 2020 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import { waitForDebugger } from 'inspector'
import analyticsElements from '../elements/analytics-elements'
import BeaglePage from './BeaglePage'
import SimpleFormPage from './SimpleFormPage'

class AnalyticsPage extends BeaglePage {
  lastAlertMessage = ''
  lastConfirmMessage = ''

  constructor() {
    super('analytics2')
  }

  init() {
    return super.init().then(() => {
      cy.on('window:alert', message => this.lastAlertMessage = message)
      // @ts-ignore using untyped cypress extension
      cy.on('window:confirm', (message) => this.lastConfirmMessage = message)
    })
  }

  verifyLocalStorage(key: string) {
    cy.clearLocalStorage(key).should((ls) => {
      expect(ls.getItem(key)).to.be.null
    })
  }

  clickButtonByText(text: string) {
    analyticsElements.buttonWithText(text).click()
  }

  checkAlertAction() {
    expect(this.lastAlertMessage).to.equal('AlertMessage')
  }

  checkConfirmAction() {
    expect(this.lastConfirmMessage).to.equal('Confirm Message')
  }

  verifyIfAnalyticsNotCreated() {
    analyticsElements.getAnalytics().should('have.length', 1)
  }

  verifyIfAnalyticsIsCreated(analyticsRecord: string) {
    analyticsElements.getAnalytics().then(($div) => {
      expect($div.text()).to.have.string(analyticsRecord)
    })
  }

}

export default AnalyticsPage