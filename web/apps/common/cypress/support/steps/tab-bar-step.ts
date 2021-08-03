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

import TabBarPage from '../page-objects/TabBarPage'

const tabBarPage = new TabBarPage

Given("that I'm on the tabBar screen", () => {
    tabBarPage.init()
  })

Then(/I click on each (.*) and confirm its (.*)/, (tabTitle,currentPosition) => {
  tabBarPage.ClickOnTab(tabTitle),
  tabBarPage.CheckPosition(currentPosition)
})

When(/I click on button (.*)/, (buttonText) => {
  tabBarPage.ClickOnButton(buttonText)
})

Then(/the tab with text (.*) must be selected/, (tab) => {
  tabBarPage.CheckIfTabIsSelected(tab)
})

When(/I click in a tab with text (.*)/, (tabtitle) => {
  tabBarPage.ClickOnTab(tabtitle)
})

Then(/the tab position should have its text changed to (.*)/, (currentPosition) => {
  tabBarPage.CheckPosition(currentPosition)
})  

Then("check tab with beagle icon is on screen", () => {
  tabBarPage.CheckIfImagesExists()
})  

Then(/check tab with text (.*) and beagle icon are on screen/, (tabText) => {
  tabBarPage.CheckImageByIndex()
  tabBarPage.CheckIfImagesWithText(tabText)
}) 

Then("the tab with text image and beagle image changed to other image with text image", () => {
  tabBarPage.CheckImageSource()
}) 