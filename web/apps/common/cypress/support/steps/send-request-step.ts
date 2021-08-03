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

import SendRequestPage from '../page-objects/SendRequestPage'

const sendRequestPage = new SendRequestPage

  Given("the Beagle application did launch with the send request screen url", () => {
    sendRequestPage.init()
  })

  When(/I press the (.*) button/, (text) => {
    sendRequestPage.clickButton(text)
  })

  Then(/the screen should show some alert with (.*) message/, (message) => {
    sendRequestPage.checkAlert(message)
  })

  Then(/the pressed button changes it's (.*) title to didFinish/, () => {
    sendRequestPage.checkButton()
  })

  Then(/the screen should show some alert with (.*) message/, (message) => {
    sendRequestPage.checkAlert(message)
  })
