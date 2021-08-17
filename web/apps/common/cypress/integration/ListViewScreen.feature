#
# Copyright 2020 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#


@listView @regression
Feature: ListView Component Validation
    As a Beagle developer/user
    I'd like to make sure my listView component works as expected


    Background:
        Given that I'm on the listView page

    Scenario: ListView 01 - whole page validation
        Then the current page screenshot should be identical to image "ListViewWholePage"

    Scenario: ListView 02 - characters listview 1 scroll validation
        When I scroll the listview with id "charactersList" to the end
        Then the current page screenshot should be identical to image "ListViewCharacters1Scrolled"

    Scenario: ListView 03 - characters listview 2 validation
        When I click on a button with exact text "next"
        Then the current page screenshot should be identical to image "ListViewCharacters2"

    Scenario: ListView 04 - characters listview 2 scroll validation
        When I scroll the listview with id "charactersList" to the end
        Then the current page screenshot should be identical to image "ListViewCharacters2Scrolled"

    Scenario: ListView 05 - infinite scroll listview
        When I scroll to the bottom of the infinite scroll listview
        Then the current page screenshot should be identical to image "ListViewInfiniteScroll"