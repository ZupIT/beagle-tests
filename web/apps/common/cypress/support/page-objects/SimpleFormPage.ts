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

import simpleFormElements from '../elements/simple-form-elements'
import BeaglePage from './BeaglePage'

class SimpleFormPage extends BeaglePage {
  lastAlertMessage = ''
  constructor() {
    super('simpleform')
  }

  init() {
    return super.init().then(() => {
      cy.on('window:alert', message => this.lastAlertMessage = message)
    })
  }

  checkInputByPlaceholder(placeholder: string){
    simpleFormElements.inputByPlaceholder(placeholder).should('exist')
  }
  
  checkButtonByText(text: string){
    simpleFormElements.buttonWithText(text).should('exist')
  }

  clickButtonByText(text: string){
    simpleFormElements.buttonWithText(text).click()
  }

  typeInputByPlaceholder(placeholder: string, value: string){
    simpleFormElements.inputByPlaceholder(placeholder).type(value)
  }

  checkAlertMessage(message: String){
    expect(this.lastAlertMessage).to.equal(message)
  }

}

export default SimpleFormPage
