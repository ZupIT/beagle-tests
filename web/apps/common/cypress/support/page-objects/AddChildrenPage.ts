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

import addChildrenElements from '../elements/add-children-elements'
import BeaglePage from './BeaglePage'

class ChildrenPage extends BeaglePage {
  constructor() {
    super('add-children')
  }

  checkButton(text: string) {
    addChildrenElements.buttonWithText(text).should('exist')
  }

  clickButton(text: string) {
    addChildrenElements.buttonWithText(text).click()
  }

  checkAddChildrenAfter() {
    addChildrenElements.paragraphWithText('New text added').should('exist')
    addChildrenElements.paragraph().should('have.length', 2)
    addChildrenElements.paragraph().eq(1).contains('New text added')
  }

  checkAddChildrenBefore() {
    addChildrenElements.paragraphWithText('New text added').should('exist')
    addChildrenElements.paragraph().should('have.length', 2)
    addChildrenElements.paragraph().eq(0).contains('New text added')
  }

  checkOnlyOneParagraph() {
    addChildrenElements.paragraphWithText("I'm the single text on screen").should('exist')
    addChildrenElements.paragraph().should('have.length', 1)
  }

  checkOnlyOneParagraphAdded() {
    addChildrenElements.paragraphWithText('New text added').should('exist')
    addChildrenElements.paragraph().should('have.length', 1)
  }
}

export default ChildrenPage
