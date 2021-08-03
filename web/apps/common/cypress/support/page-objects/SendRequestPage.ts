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

import SendRequestElements from '../elements/send-request-elements'
import BeaglePage from './BeaglePage'

class SendRequestPage extends BeaglePage {
  lastAlertMessage = ''
  constructor() {
    super('send-request')
  }
  
  init() {
    return super.init().then(() => {
      cy.on('window:alert', message => this.lastAlertMessage = message)
    })
  }

  clickButton(text: string) {
    SendRequestElements.buttonWithText(text).click()
  }

  checkAlert(message: String){
    expect(this.lastAlertMessage).to.equal(message)
  }
  
  checkButton(){
    SendRequestElements.buttonWithText('didFinish').should('exist')
  }
}

export default SendRequestPage
