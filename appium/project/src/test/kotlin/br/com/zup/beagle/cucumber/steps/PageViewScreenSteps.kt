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

package br.com.zup.beagle.cucumber.steps


import br.com.zup.beagle.setup.SuiteSetup
import br.com.zup.beagle.utils.AppiumUtil
import br.com.zup.beagle.utils.SwipeDirection
import io.appium.java_client.touch.offset.PointOption
import io.cucumber.java.Before
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then

class PageViewScreenSteps : AbstractStep() {

    private val pageViewScreenHeader = "Beagle PageView"
    private val page1Text = "Page 1"
    private val page2Text = "Page 2"
    private val page3Text = "Page 3"

    override var bffRelativeUrlPath = "/pageview"

    @Before("@pageView")
    fun setup() {
        loadBffScreen()
    }

    @Given("^that I'm on the PageView screen$")
    fun checkPageViewScreen() {
        waitForElementWithTextToBeClickable(pageViewScreenHeader)
    }

    @Then("^my PageView components should render their respective pages attributes correctly when swiping left$")
    fun checkPageViewRendersTabs() {


        /**
         * Swipe screen sometimes fail due to the screen not being completely loaded, so there are some sleeps below
         */
        sleep(500)

        // state 1: shows only page1Text and Context0
        waitForElementWithTextToBeInvisible(page2Text)
        waitForElementWithTextToBeInvisible(page3Text)
        waitForElementWithTextToBeClickable("Context0")

        val anchorElementHeight = waitForElementWithTextToBeClickable(page1Text).location.y
        val screenWidth = getDriver().manage().window().size.width

        if (SuiteSetup.isAndroid()) {
            customAndroidSwipeLeft(anchorElementHeight, screenWidth)
        } else {
            swipeLeft()
        }
        sleep(500)

        // state 2: shows only page2Text and Context1
        waitForElementWithTextToBeInvisible(page1Text)
        waitForElementWithTextToBeInvisible(page3Text)
        waitForElementWithTextToBeClickable(page2Text)
        waitForElementWithTextToBeClickable("Context1")

        if (SuiteSetup.isAndroid()) {
            customAndroidSwipeLeft(anchorElementHeight, screenWidth)
        } else {
            swipeLeft()
        }
        sleep(500)

        // state 3: shows only page3Text and Context2
        waitForElementWithTextToBeInvisible(page1Text)
        waitForElementWithTextToBeInvisible(page2Text)
        waitForElementWithTextToBeClickable(page3Text)
        waitForElementWithTextToBeClickable("Context2")
    }

    private fun customAndroidSwipeLeft(anchorHeight: Int, screenWidth: Int) {
        AppiumUtil.androidSwipeScreenTo(
            getDriver(),
            SwipeDirection.LEFT,
            originPoint = PointOption.point(screenWidth - 10, anchorHeight)
        )

    }

}