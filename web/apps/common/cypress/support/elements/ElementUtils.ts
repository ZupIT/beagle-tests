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

export default class ElementUtils {

    static getAnyElementByText(text: string) {
        return cy.contains(text)
    }

    static getAnyElementByExactText(text: string) {
        return cy.contains(new RegExp("^\\s*" + text + "\\s*$"))
    }

    static getElementByText(elementTag: string, text: string) {
        return cy.contains(elementTag, text)
    }

    static getElementByExactText(elementTag: string, text: string) {
        return cy.contains(elementTag, new RegExp("^\\s*" + text + "\\s*$"))
    }

    static getElementById(id: string) {
        return cy.get('#' + id)
    }

    static getElementByAttributeValue(attribute: string, value: string) {
        return cy.get('[' + attribute + '="' + value + '"]')
    }

}