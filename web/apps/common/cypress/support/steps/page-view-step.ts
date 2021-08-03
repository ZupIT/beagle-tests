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

import PageViewPage from '../page-objects/PageViewPage'

const pageView = new PageViewPage

Given("that I'm on the pageview screen", () => {
  pageView.init()
})

When(/I click on Button with (.*) title/, (buttonText) => {
  pageView.clickButton(buttonText)
})

When("I click on Right arrow", () => {
  pageView.clickArrow()
})

When(/I press a navigation button (.*)/, (buttonText) => {
  pageView.clickButton(buttonText)
})

Then("checks that the component is on the screen", () => {
  pageView.checkArrows()
})

Then(/checks that the text Page (.*) is on the screen/, (text) => {
  pageView.checkPageContent(text)
})

Then(/checks if the context (.*) existing on the screen/, (pageViewContext) => {
  pageView.checkPageContent(pageViewContext)
})

