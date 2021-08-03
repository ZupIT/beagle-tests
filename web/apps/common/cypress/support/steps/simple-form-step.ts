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

import SimpleFormPage from '../page-objects/SimpleFormPage'

const simpleFormPage = new SimpleFormPage

Given("that I'm on the simple form screen", () => {
  simpleFormPage.init()
})


Then(/checks that the textInput with the placeholder (.*) is on the screen/, (placeholder) => {
  simpleFormPage.checkInputByPlaceholder(placeholder)
})


Then(/checks that the button with the title (.*) is on the screen/, (text) => {
  simpleFormPage.checkButtonByText(text)
})



When(/I type on textInput with the placeholder (.*) and insert (.*)/, (placeholder, value) => {
  simpleFormPage.typeInputByPlaceholder(placeholder, value)
})

When(/I click to button (.*)/, (text) => {
  simpleFormPage.clickButtonByText(text)
})

Then(/verify if (.*) is appear correctly/, (message) => {
  simpleFormPage.checkAlertMessage(message)
})

