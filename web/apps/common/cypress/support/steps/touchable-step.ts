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

import TouchablePage from '../page-objects/TouchablePage'

const touchablePage = new TouchablePage

  Given("that I'm on the touchable screen", () => {
    touchablePage.init()
  })

  And(/I have a text with (.*) configured/, () => {
    touchablePage.checkTouchable()
  })
  
  When(/I click on touchable text (.*)/, () => {
    touchablePage.clickTouchableText()
  })

  And(/I have an image with (.*) configured/, () => {
    touchablePage.checkTouchable()
  })

  When(/I click on touchable image (.*)/, () => {
    touchablePage.clickTouchableImg()
  })
  
  Then("touchable screen should render all text attributes correctly", () => {
    touchablePage.checkNumberOfTouchables(3)
  })
  
  Then('component should render the action attribute correctly', () => {
    touchablePage.checkTouchableAction()
  })
