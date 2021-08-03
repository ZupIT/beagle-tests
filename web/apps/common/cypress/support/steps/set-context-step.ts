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

import SetContextPage from '../page-objects/SetContextPage'

const setContextPage = new SetContextPage

Given("the Beagle application did launch with the SetContext screen url", () => {
    setContextPage.init()
})

When(/I press a SetContext button with the (.*) title/, (buttonText) => {
    setContextPage.clickButton(buttonText)
})

Then(/a text with the (.*) message should appear on the screen/, (text) => {
    setContextPage.checkSetContextAction(text)
})
