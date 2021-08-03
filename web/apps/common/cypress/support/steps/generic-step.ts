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

/* TODO: 
 1- Place here all default and gerenic steps, implementing them with cy object
 2- Refactor all .features \ steps files to use the generic steps 
 3- Refactor all Page classes removing gerenic and repetitive methods used by the now removed steps. 
    Page classes are meant to hold only page scope methods and not generic methods like 'clickOnButtonWithTittle'  

 All these improvements will be covered here: https://github.com/ZupIT/beagle-web-components/issues/54   

*/

//import { waitForDebugger } from 'inspector'
import ElementUtils from '../elements/ElementUtils'

When(/I click on a button with text \"(.*)\"/, (text) => {
    ElementUtils.getElementByText('button', text).should('be.visible').click()
})

When(/I click on a button with exact text \"(.*)\"/, (text) => {
    ElementUtils.getElementByExactText('button', text).should('be.visible').click().then(($el) => {
        cy.task('log', 'Button request: "' + text + '" Button clicked: "' + $el.text() + '"')
    })
})

Then(/the page should show an element with text \"(.*)\"/, (text) => {
    ElementUtils.getAnyElementByText(text).should('be.visible')
})

Then(/the page should show an element with exact text \"(.*)\"/, (text) => {
    // @ts-ignore
    ElementUtils.getAnyElementByExactText(text).should('be.visible')
})

Then(/the page should not show an element with text \"(.*)\"/, (text) => {
    ElementUtils.getAnyElementByText(text).should('not.exist')
})

Then(/the page should not show an element with exact text \"(.*)\"/, (text) => {
    // @ts-ignore
    ElementUtils.getAnyElementByExactText(text).should('not.exist')
})

Then(/the current page screenshot should be identical to image \"(.*)\"/, (imageName) => {
    compareScreenShot(imageName)
})

function compareScreenShot(imageName: string) {
    /*
     * Ensure the page is completely loaded before taking a screenshot 
     */
    cy.wait(2000)

    // @ts-ignore using untyped cypress extension
    cy.matchImageSnapshot(imageName)
}

