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


import textlements from '../elements/text-elements'
import BeaglePage from './BeaglePage'

class TextPage extends BeaglePage {
  constructor() {
    super('text')
  }

  checkTextIsOnScreen(text: string){  
    textlements.textByValue(text).should('exist')
  }

  checkTextColor(text: string, color: string){
    textlements.textByValue(text).should('have.css', 'color').and('equal', `${color}`)
  }
 
  checkTextAlignment(text: string, alignment: string){
    let align = alignment.toLowerCase()
    textlements.textByValue(text).should('have.css', 'text-align').and('equal', `${align}`)
  }
  
} 

export default TextPage