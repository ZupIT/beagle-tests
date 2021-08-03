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

import AddChildrenPage from '../page-objects/AddChildrenPage'

const addChildrenPage = new AddChildrenPage

Given("that I'm on the addChildren Screen", () => {
    addChildrenPage.init()
})

When(/I click on button (.*)/, (buttonText) => {
    addChildrenPage.clickButton(buttonText)
})

Then("A Text need to be added before the already exist one", () => {
    addChildrenPage.checkAddChildrenBefore()
})

Then('A Text need to be added after the already exist one', () => {
    addChildrenPage.checkAddChildrenAfter()
})

Then('Nothing should happen', () => {
    addChildrenPage.checkOnlyOneParagraph()
})

Then('A Text need to replace the already exist one', () => {
    addChildrenPage.checkOnlyOneParagraphAdded()
})
