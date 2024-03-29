/*
 * Copyright 2021 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
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

package br.com.zup.beagle.cucumber.steps

import br.com.zup.beagle.setup.SuiteSetup
import br.com.zup.beagle.utils.SwipeDirection
import io.cucumber.java.Before
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.openqa.selenium.Dimension
import org.openqa.selenium.Point

class PullToRefreshScreenSteps : AbstractStep() {

    override var bffRelativeUrlPath = "/pull-to-refresh"

    @Before("@pullToRefresh")
    fun setup() {
        loadBffScreen()
    }

    @Given("^that I'm on the pull to refresh screen$")
    fun checkBaseScreen() {
        waitForElementWithTextToBeClickable("Beagle PullToRefresh screen")
    }

    @When("^I swipe (.*) from the center of the screen$")
    fun scrollDownOnElement(swipeDirection: String) {

        waitForElementWithTextToBeClickable("PullToRefresh text")

        /**
         * swipes in the center of the screen, touching the PushToRefresh element
         */
        if ("down".equals(swipeDirection, ignoreCase = true)) {
            if (SuiteSetup.isAndroid()) {
                swipeDown()
            } else {
                val screenSize: Dimension = getDriver().manage().window().size
                scrollFromOnePointToBorder(Point(screenSize.width / 2, screenSize.height / 2), SwipeDirection.DOWN)
            }
        } else {
            throw Exception("Wrong swipe direction")
        }
    }

    @Then("^the loading element should not be showing$")
    fun checkLoadingNotShowing() {
        waitForAllImagesToBeInvisible()
    }
}