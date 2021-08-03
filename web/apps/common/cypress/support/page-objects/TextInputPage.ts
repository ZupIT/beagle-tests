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

import textInputElements from '../elements/text-input-elements'
import BeaglePage from './BeaglePage'

class TextInputPage extends BeaglePage {
  constructor() {
    super('textinput')
  }

  checkInputByValue(value: string){
    textInputElements.inputByValue(value).should('exist')
  }

  checkInputByPlaceholder(placeholder: string){
    textInputElements.inputByPlaceholder(placeholder).should('exist')
  }

  checkHaveDisableInput(placeholder: string){
    textInputElements.inputByPlaceholder(placeholder).should('exist')
  }

  checkIfDisabled(placeholder: string){
    textInputElements.inputByPlaceholder(placeholder).should('be.disabled')
  }

  checkReadOnlyInput(value: string){
    textInputElements.inputByValue(value).should('exist')
  }

  checkIfReadOnly(value: string){
    textInputElements.inputByValue(value).should('have.attr','readonly')
  }

  checkInputType(placeholder: string, type: string){
    textInputElements.inputByPlaceholder(placeholder).should('have.attr','type', `${type}`) 
  }

  clickInputByPlaceholder(placeholder: string){
    textInputElements.inputByPlaceholder(placeholder).click()
  }

  checkEventByClick(placeholder: string, event: string){
    textInputElements.inputByPlaceholder(placeholder).should('have.value',`${event}`) 
  }

  typeInputByPlaceholder(placeholder: string, value: string){
    textInputElements.inputByPlaceholder(placeholder).type(`${value}`)
  }
  
}

export default TextInputPage