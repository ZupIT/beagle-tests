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

import TextInputPage from '../page-objects/TextInputPage'

const textInputPage = new TextInputPage

Given("the Beagle application did launch with the textInput on screen", () => {
    textInputPage.init()
})

Then(/I must check if the textInput value (.*) appears on the screen/, (Value) => {
   textInputPage.checkInputByValue(Value)
})

Then(/I must check if the textInput placeholder (.*) appears on the screen/, (placeholder) => {
    textInputPage.checkInputByPlaceholder(placeholder)
})

When(/the placeholder (.*) of the disabled field is on the screen/, (placeholder) => {
    textInputPage.checkHaveDisableInput(placeholder)
})

Then(/verify if the field with the placeholder (.*) is disabled/, (placeholder) => {
    textInputPage.checkIfDisabled(placeholder)
})

When(/the value (.*) of the readOnly field is on the screen/, (placeholder) => {
    textInputPage.checkReadOnlyInput(placeholder)
})

Then(/verify if the field with the value (.*) is read only/, (placeholder) => {
    textInputPage.checkIfReadOnly(placeholder)
})

Then(/validate textInput component of type number with hint (.*)/, (placeholder) => {
    textInputPage.checkInputType(placeholder, 'NUMBER')  
})

When(/I click the textInput with the placeholder (.*)/, (placeholder) => {
    textInputPage.clickInputByPlaceholder(placeholder)
})

And(/I type anything on textInput with the placeholder (.*)/, (placeholder) => {
    textInputPage.typeInputByPlaceholder(placeholder, 'teste')
})

Then(/the textInput with the placeholder (.*) will change its value to (.*)/, (placeholder, event) => {
    textInputPage.checkEventByClick(placeholder, event)  
})

Then(/the textInput with the placeholder (.*) should have value (.*)/, (placeholder, event) => {
    textInputPage.checkEventByClick(placeholder, event)  
})


