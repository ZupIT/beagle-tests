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

import ContainerPage from '../page-objects/ContainerPage'

const containerPage = new ContainerPage

Given("the Beagle application did launch with the container screen url", () => {
    containerPage.init()
})

Then(/the view that contains the texts with titles (.*) (.*) and (.*) must be displayed/, (item1, item2, item3) => {
    containerPage.checkItems(item1)
    containerPage.checkItems(item2)
    containerPage.checkItems(item3) 
})

Then(/the views that contains the texts (.*) (.*) set via context must be displayed/, (ContextValue1, ContextValue2) => {
    containerPage.checkContext(ContextValue1)
    containerPage.checkContext(ContextValue2)
})

