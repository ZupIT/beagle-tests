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

import ListViewPage from '../page-objects/ListViewPage'
import ElementUtils from '../elements/ElementUtils'

const listViewPage = new ListViewPage


Given(/that I'm on the listView page/, () => {
  listViewPage.init()

  /**
   * The listview page is not loaded at once. Some elements are still being 
   * processed / loaded when Cypress returns from 'visit' method (check class BeaglePage) 
   * because they get content from end-points. Cypress doesn't wait for these content 
   * to finish loading. That's why the wait command is applied here.
   */
  cy.wait(2000)
})

When(/I scroll the listview with id \"(.*)\" to the end/, (elementId) => {
  ElementUtils.getElementByAttributeValue('data-beagle-id', elementId).scrollTo('right')
})

When(/I scroll to the bottom of the infinite scroll listview/, () => {
  // scrolls multiple times because the rest of the elements are only shown after the a second scroll
  cy.scrollTo('bottom', { duration: 1500 })
  cy.scrollTo('bottom', { duration: 1500 })
  cy.scrollTo('bottom', { duration: 1500 })
})