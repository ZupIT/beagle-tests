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
import 'cypress-wait-until';
import buttonElements from '../elements/button-elements'
import BeaglePage from './BeaglePage'

class ButtonPage extends BeaglePage {
  constructor() {
    super('button')
  }

  checkNumberOfButtons(quantity: number) {
    cy.waitUntil(() => buttonElements.buttons().should('have.length', quantity))
  }

  checkDisabledButtons(text: string) {
    cy.waitUntil(() => buttonElements.buttonWithText(text).should('be.disabled'))
  }

  checkButton(text: string) {
    cy.waitUntil(() => buttonElements.buttonWithText(text).should('exist'))
  }

  clickButton(text: string) {
    buttonElements.buttonWithText(text).click()
  }

  checkButtonAction() {
    buttonElements.paragraphWithText('You clicked right').should('exist')
  }
}

export default ButtonPage
