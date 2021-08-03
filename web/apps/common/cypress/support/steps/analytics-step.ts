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

import AnalyticsPage from '../page-objects/AnalyticsPage'

const analyticsPage = new AnalyticsPage

Given("the Beagle application did launch with the Analytics screen url", () => {
    cy.clock(Date.UTC(2021, 0, 1), ['Date']) //1609459200000
    analyticsPage.init()
})

Given("that the analytics local storage is cleaned up", () => {
    analyticsPage.verifyLocalStorage('Analytics_data')
})

When(/I press the button with title "(.*)"/, (buttonText) => {
    analyticsPage.clickButtonByText(buttonText)
})

Then("an alert dialog should appear on the screen", () => {
    analyticsPage.checkAlertAction()
})

Then("a confirm dialog should appear on the screen", () => {
    analyticsPage.checkConfirmAction()
})

Then("no analytics record should be created", () => {
    analyticsPage.verifyIfAnalyticsNotCreated()
})

Then(/an analytics record should be created with (.*)/, (analyticsRecord) => {
    analyticsPage.verifyIfAnalyticsIsCreated(analyticsRecord)
})