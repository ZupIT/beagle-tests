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

@analytics2.0 @regression
Feature: Analytics validation
    As a Beagle developer/user
    I'd like to make sure my Analytics works as expected
    In order to guarantee that my application never fails

    Background:
        Given the Beagle application did launch with the Analytics screen url
        
    Scenario: Analytics 01 - Action with no remote analytics config and not declared in the local config (should not create record)
        When I press the button with title "Alert with no specific analytics configuration"
        Then an alert dialog should appear on the screen
        # When I press the dialog's OK button
        Then no analytics record should be created
    
    Scenario: Analytics 02 - Action with no remote analytics config and declared in the local config (should create record with params in config)
        Given that the analytics local storage is cleaned up
        When I press the button with title "Confirm with analytics local configuration"
        Then a confirm dialog should appear on the screen
        # When I press the dialog's OK button 
        Then an analytics record should be created with {"type":"screen","platform":"WEB Angular","timestamp":1609459200000,"screen":"analytics2"},{"type":"action","platform":"WEB Angular","event":"onPress","component":{"type":"beagle:button","id":"_beagle_5","xPath":"BODY/APP-ROOT[1]/BEAGLE[1]/BEAGLE-REMOTE-VIEW/BEAGLE-SCREEN[21]/BEAGLE-CONTAINER/BEAGLE-BUTTON[4]/","position":{"x":8,"y":26}},"beagleAction":"beagle:confirm","attributes":{"message":"Confirm Message"},"timestamp":1609459200000,"screen":"/analytics2"}

    Scenario: Analytics 03 - Action with remote analytics config and not declared in the local config (should create record with params from remote config)
        Given that the analytics local storage is cleaned up
        When I press the button with title "Alert with remote analytics configuration"
        Then an alert dialog should appear on the screen
        # When I press the dialog's OK button
        Then an analytics record should be created with {"type":"screen","platform":"WEB Angular","timestamp":1609459200000,"screen":"analytics2"},{"type":"action","platform":"WEB Angular","event":"onPress","component":{"type":"beagle:button","id":"_beagle_6","xPath":"BODY/APP-ROOT[1]/BEAGLE[1]/BEAGLE-REMOTE-VIEW/BEAGLE-SCREEN[21]/BEAGLE-CONTAINER/BEAGLE-BUTTON[12]/","position":{"x":8,"y":98}},"beagleAction":"beagle:alert","attributes":{"message":"AlertMessage"},"timestamp":1609459200000,"screen":"/analytics2"}

    Scenario: Analytics 04 - Action with analytics disabled in the remote config (should not create record)
        When I press the button with title "Confirm with disabled analytics configuration"
        Then a confirm dialog should appear on the screen
        # When I press the dialog's OK button
        Then no analytics record should be created
    
    Scenario: Analytics 05 - View loaded, screen analytics enabled in the config (should create record)
        Given that the analytics local storage is cleaned up
        When I press the button with title "navigateToPage"
        When I press the button with title "navigate to local screen"
        Then an analytics record should be created with {"type":"screen","platform":"WEB Angular","timestamp":1609459200000,"screen":"analytics2"}
        

