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

import ConditionalPage from '../page-objects/ConditionalPage'

const conditionalPage = new ConditionalPage

  Given("the Beagle application did launch with the conditional screen url", () => {
    conditionalPage.init()
  })

  When(/I click in a conditional button with (.*) title/, (text) => {
    conditionalPage.clickButton(text)
  })

  Then("an Alert action should pop up with a TrueCondition message", () => {
    conditionalPage.checkAlertMessage("TrueCondition")
  })

  Then("an Alert action should pop up with a FalseCondition message", () => {
    conditionalPage.checkAlertMessage("FalseCondition")
  })