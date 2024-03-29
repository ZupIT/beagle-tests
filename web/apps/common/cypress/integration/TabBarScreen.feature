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

@tabbar @regression
Feature: TabBar Component Validation

    As a Beagle developer/user
    I'd like to make sure my tabView component works as expected
    In order to guarantee that my application never fails

    Background:
        Given that I'm on the tabBar screen

    Scenario Outline: TabBar 01 - Checks that a TabBar component renders all its TabBarItems in the correct order
        Then I click on each <tabBarItem> and confirm its <currentPosition>
        
        Examples:
            |tabBarItem | currentPosition |
            |Tab1       | Tab position 0  |
            |Tab2       | Tab position 1  |
            |Tab3       | Tab position 2  |
            |Tab4       | Tab position 3  |
            |Tab5       | Tab position 4  |
            |Tab6       | Tab position 5  |
            |Tab7       | Tab position 6  |
            |Tab8       | Tab position 7  |
            |Tab9       | Tab position 8  |
            |Tab10      | Tab position 9  |
            
    Scenario Outline: TabBar 02 - Checks that a TabPosition is selected when the currentTab attribute is set HARDCODED and via EXPRESSION

        When I click on button <title>
        Then the tab with text <tab> must be selected

        Examples:
            | title                   | tab  |
            | Select tab 4 hardcoded  | Tab4 |
            | Select tab 9 expression | Tab9 |

    Scenario Outline: TabBar 03 - Checks that a list of actions is triggered when a tab is selected.
        When I click in a tab with text <title>
        Then the tab position should have its text changed to <position>

        Examples:
            | title | position       |
            | Tab4  | Tab position 3 |

    Scenario: TabBar 04 - Checks that a TabBarItem with a ICON is showing the ICON
        Then check tab with beagle icon is on screen

    Scenario: TabBar 05 - Checks that a TabBarItem with a ICON and a TITLE is showing both elements on screen
        Then check tab with text image and beagle icon are on screen

     Scenario: TabBar 06 - Checks that an ICON in a TabBarItem could be exchanged for another ICON via EXPRESSION
        Then check tab with beagle icon is on screen
        When I click on button ChangeTabIcon
    #   This senario only applies to mobile
    #   Then the tab with text image and beagle icon will change icon to delete icon
        Then the tab with text image and beagle image changed to other image with text image

        
