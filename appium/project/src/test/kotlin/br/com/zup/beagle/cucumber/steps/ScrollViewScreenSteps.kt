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

import br.com.zup.beagle.setup.DEFAULT_ELEMENT_WAIT_TIME_IN_MILL
import br.com.zup.beagle.setup.SuiteSetup
import br.com.zup.beagle.utils.AppiumUtil
import br.com.zup.beagle.utils.SwipeDirection
import io.appium.java_client.MobileBy
import io.appium.java_client.MobileElement
import io.cucumber.java.Before
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import io.cucumber.java.en.When
import org.junit.Assert
import org.junit.runners.Suite
import org.openqa.selenium.By

class ScrollViewScreenSteps : AbstractStep() {

    private val scrollViewScreenHeader = "Beagle ScrollView"
    private val newTextPrefix = "Lorem ipsum diam luctus"
    private val newTextSufix = "proin iaculis orci gravida molestie."

    override var bffRelativeUrlPath = "/scrollview"

    // cache
    companion object {
        lateinit var scrollViewElement1: MobileElement
        lateinit var scrollViewElement2: MobileElement
        lateinit var scrollViewElement3: MobileElement
    }

    @Before("@scrollView")
    fun setup() {
        loadBffScreen()
    }

    @Given("^that I'm on the scrollview screen$")
    fun checkScrollViewScreen() {
        waitForElementWithTextToBeClickable(scrollViewScreenHeader)
    }

    @When("^I access ScrollView (.*)$")
    fun cacheScrollView(scrollViewElementNumber: Int) {
        when (scrollViewElementNumber) {
            1 -> {
                scrollViewElement1 = getScrollViewElement(1)
            }
            2 -> {
                scrollViewElement2 = getScrollViewElement(2)
            }
            else -> {
                scrollViewElement3 = getScrollViewElement(3)
            }
        }
    }

    @When("^I confirm that the ScrollView (.*) is not showing a button with text \"(.*)\" by default$")
    fun confirmScrollView1IsNotShowingButton(scrollViewElementNumber: Int, buttonText: String) {
        when (scrollViewElementNumber) {
            1 -> {
                Assert.assertFalse(isButtonShowing(buttonText))
            }
            2 -> {
                Assert.assertFalse(isButtonShowing(buttonText))
            }
            else -> {
                Assert.assertFalse(isButtonShowing(buttonText))
            }
        }
    }

    @When("^I confirm that the ScrollView (.*) is showing a button with text \"(.*)\" by default$")
    fun confirmScrollView2IsShowingButton(scrollViewElementNumber: Int, buttonText: String) {
        when (scrollViewElementNumber) {
            1 -> {
                Assert.assertTrue(isButtonShowing(buttonText))
            }
            2 -> {
                Assert.assertTrue(isButtonShowing(buttonText))
            }
            else -> {

                // ScrollView 3 is hidden in small screens
                if (SuiteSetup.isIos()) {
                    AppiumUtil.iosScrollInsideElement(getDriver(), scrollViewElement3, SwipeDirection.DOWN)
                } else {
                    AppiumUtil.androidScrollToElementByText(getDriver(), 0, buttonText, isHorizontalScroll = false)
                }
                Assert.assertTrue(isButtonShowing(buttonText))
            }
        }
    }

    @When("^I expand all the items of ScrollView (.*), checking their new values$")
    fun checkScrollViewNewTexts(scrollViewElementNumber: Int) {
        when (scrollViewElementNumber) {
            1 -> checkScrollView1NewTexts()
            2 -> checkScrollView2NewTexts()
            else -> checkScrollView3NewTexts()
        }
    }

    @Then("^I should view a button with text \"(.*)\" by scrolling ScrollView (.*) to the end$")
    fun checkButtonVisible(buttonText: String, scrollViewElementNumber: Int) {
        when (scrollViewElementNumber) {
            1 -> Assert.assertTrue(isButtonShowing(buttonText))
            2 -> Assert.assertTrue(isButtonShowing(buttonText))
            else -> Assert.assertTrue(isButtonShowing(buttonText))
        }
    }

    private fun checkScrollView1NewTexts() {

        val text1 = "Horizontal"
        val text2 = "Click to see the new text in horizontal"
        val text3 = "Click to see the text change, rotate and scroll horizontally"

        val textElementsTemp = getScrollViewChildTextElements(scrollViewElement1)
        val textElement1 = textElementsTemp.elementAt(0)
        var textElement2 = textElementsTemp.elementAt(1)
        var textElement3 = textElementsTemp.elementAt(2)

        // ScrollView 1 original state: three text elements showing
        Assert.assertEquals(text1, textElement1.text)
        Assert.assertEquals(text2, textElement2.text)
        Assert.assertEquals(text3, textElement3.text)

        // ScrollView 1 second state: new texts showing instead of text 2 and 3
        textElement2.click()
        if (SuiteSetup.isAndroid()) {
            waitForElementTextToContain(textElement2, newTextPrefix)
        } else {
            waitForScrollViewChildElementToDisappear(scrollViewElement1, text2)
        }
        textElement2 = getScrollViewChildrenTextElementByText(scrollViewElement1, newTextPrefix).last()
        Assert.assertTrue(textElement2.text.startsWith(newTextPrefix))
        Assert.assertTrue(textElement2.text.endsWith(newTextSufix))

        if (SuiteSetup.isIos()) {
            AppiumUtil.iosScrollInsideElement(getDriver(), scrollViewElement1, SwipeDirection.RIGHT)
        } else {
            AppiumUtil.androidScrollToElementByText(getDriver(), 1, text3, isHorizontalScroll = true)
        }

        // reloads element
        textElement3 = getScrollViewChildTextElement(scrollViewElement1, text3)
        textElement3.click()
        if (SuiteSetup.isAndroid()) {
            waitForElementTextToContain(textElement3, newTextPrefix)
        } else {
            waitForScrollViewChildElementToDisappear(scrollViewElement1, text3)
        }
        textElement3 = getScrollViewChildrenTextElementByText(scrollViewElement1, newTextPrefix).last()
        Assert.assertTrue(textElement3.text.startsWith(newTextPrefix))
        Assert.assertTrue(textElement3.text.endsWith(newTextSufix))

        // ScrollView 1 third state: scrolls until the end
        if (SuiteSetup.isIos()) {
            AppiumUtil.iosScrollInsideElement(getDriver(), scrollViewElement1, SwipeDirection.RIGHT)
        } else {
            AppiumUtil.androidScrollToElementByText(getDriver(), 1, "horizontal scroll", isHorizontalScroll = true)
        }

    }

    private fun checkScrollView2NewTexts() {

        val text1 = "Vertical"
        val text2 = "Click to see the new text in vertical"
        val text3 = "Click to see the text change, rotate and scroll vertically"

        // ScrollView 2 original state: three text elements showing
        getScrollViewChildTextElement(scrollViewElement2, text1)
        var textElement2 = getScrollViewChildTextElement(scrollViewElement2, text2)
        getScrollViewChildTextElement(scrollViewElement2, text3)

        // ScrollView 2 second state: new texts showing instead of text 2 and 3
        textElement2.click()
        if (SuiteSetup.isAndroid()) {
            waitForElementTextToContain(textElement2, newTextPrefix)
        } else {
            waitForScrollViewChildElementToDisappear(scrollViewElement2, text2)
        }
        textElement2 = getScrollViewChildrenTextElementByText(scrollViewElement2, newTextPrefix).last()
        Assert.assertTrue(textElement2.text.startsWith(newTextPrefix))
        Assert.assertTrue(textElement2.text.endsWith(newTextSufix))

        // Scrolls to text3
        if (SuiteSetup.isIos()) {
            AppiumUtil.iosScrollInsideElement(getDriver(), scrollViewElement2, SwipeDirection.DOWN)
        } else {
            AppiumUtil.androidScrollToElementByText(getDriver(), 0, text3, isHorizontalScroll = false)
        }

        // reloads element
        var textElement3 = getScrollViewChildTextElement(scrollViewElement2, text3)
        textElement3.click()
        if (SuiteSetup.isAndroid()) {
            waitForElementTextToContain(textElement3, newTextPrefix)
        } else {
            waitForScrollViewChildElementToDisappear(scrollViewElement2, text3)
        }
        textElement3 = getScrollViewChildrenTextElementByText(scrollViewElement2, newTextPrefix).last()
        Assert.assertTrue(textElement3.text.startsWith(newTextPrefix))
        Assert.assertTrue(textElement3.text.endsWith(newTextSufix))

        // ScrollView 2 third state: scrolls until the end
        if (SuiteSetup.isIos()) {
            AppiumUtil.iosScrollInsideElement(getDriver(), scrollViewElement2, SwipeDirection.DOWN)
        } else {
            AppiumUtil.androidScrollToElementByText(getDriver(), 0, "vertical scroll", isHorizontalScroll = false)
        }

    }

    private fun checkScrollView3NewTexts() {

        val text1 = "Vertical scroll within scroll"
        val text2 = "Click to see the new text"
        val text3 = "Horizontal scroll within scroll"

        // ScrollView 3 original state: three elements showing
        getScrollViewChildTextElement(scrollViewElement3, text1)
        var textElement2 = getScrollViewChildTextElement(scrollViewElement3, text2)
        getScrollViewChildTextElement(scrollViewElement3, text3)

        // ScrollView 3 second state: only the new text showing
        textElement2.click()
        if (SuiteSetup.isAndroid()) {
            waitForElementTextToContain(textElement2, newTextPrefix)
        } else {
            waitForScrollViewChildElementToDisappear(scrollViewElement3, text2)
        }
        textElement2 = getScrollViewChildrenTextElementByText(scrollViewElement3, newTextPrefix).last()
        Assert.assertTrue(textElement2.text.startsWith(newTextPrefix))
        Assert.assertTrue(textElement2.text.endsWith(newTextSufix))

        // Scrolls to the bottom
        if (SuiteSetup.isIos()) {
            // 2x to make sure it reached the bottom of the scroll element
            AppiumUtil.iosScrollInsideElement(getDriver(), scrollViewElement3, SwipeDirection.DOWN)
            AppiumUtil.iosScrollInsideElement(getDriver(), scrollViewElement3, SwipeDirection.DOWN)
        } else {
            AppiumUtil.androidScrollToElementByText(getDriver(), 0, "vertical direction", isHorizontalScroll = false)
        }

        // Scrolls to button "horizontal direction"
        if (SuiteSetup.isIos()) {
            val childScrollViewElement = waitForChildElementToBePresent(
                scrollViewElement3,
                MobileBy.iOSClassChain("**/XCUIElementTypeScrollView")
            )
            // 2x to make sure it reached the end of the scroll element
            AppiumUtil.iosScrollInsideElement(getDriver(), childScrollViewElement, SwipeDirection.RIGHT)
            AppiumUtil.iosScrollInsideElement(getDriver(), childScrollViewElement, SwipeDirection.RIGHT)
        } else {
            AppiumUtil.androidScrollToElementByText(getDriver(), 0, "horizontal direction", isHorizontalScroll = true)
        }
    }

    private fun getScrollViewElement(scrollViewElementNumber: Int): MobileElement {
        val locator: By?

        when (scrollViewElementNumber) {
            1 -> {
                locator = if (SuiteSetup.isIos()) {
                    MobileBy.iOSClassChain("**/XCUIElementTypeScrollView/**/XCUIElementTypeScrollView[\$type == 'XCUIElementTypeTextView' AND value == 'Horizontal'\$]")
                } else {
                    By.xpath("//android.widget.ScrollView//android.widget.HorizontalScrollView[.//android.widget.TextView[@text='Horizontal']]")
                }
            }
            2 -> {
                locator = if (SuiteSetup.isIos()) {
                    MobileBy.iOSClassChain("**/XCUIElementTypeScrollView/**/XCUIElementTypeScrollView[\$type == 'XCUIElementTypeStaticText' AND value == 'Vertical'\$]")
                } else {
                    By.xpath("//android.widget.ScrollView//android.widget.ScrollView[.//android.widget.TextView[@text='Vertical']]")
                }
            }
            3 -> {
                locator = if (SuiteSetup.isIos()) {
                    MobileBy.iOSClassChain("**/XCUIElementTypeScrollView/**/XCUIElementTypeScrollView[\$type == 'XCUIElementTypeTextView' AND value == 'Vertical scroll within scroll'\$]")
                } else {
                    By.xpath("//android.widget.ScrollView//android.widget.ScrollView[.//android.widget.TextView[@text='Vertical scroll within scroll']]")
                }
            }
            else -> {
                throw Exception("Invalid ScrollView number")
            }
        }

        return waitForElementToBePresent(locator)

    }

    @Suppress("SameParameterValue")
    private fun getScrollViewChildrenTextElementByText(
        scrollViewElement: MobileElement,
        elementTextQuery: String
    ): Collection<MobileElement> {

        val locator: By = if (SuiteSetup.isIos()) {
            MobileBy.iOSClassChain("**/XCUIElementTypeTextView[`value BEGINSWITH \"$elementTextQuery\"`]")
        } else {
            By.xpath(".//android.widget.TextView[starts-with(@text,'$elementTextQuery')]")
        }

        return waitForChildrenElementsToBePresent(scrollViewElement, locator)
    }

    private fun getScrollViewChildTextElements(
        scrollViewElement: MobileElement
    ): Collection<MobileElement> {

        val locator: By = if (SuiteSetup.isIos()) {
            MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText'")

        } else {
            By.xpath(".//android.widget.TextView")
        }

        return waitForChildrenElementsToBePresent(scrollViewElement, locator)
    }

    private fun getScrollViewChildTextElement(scrollViewElement: MobileElement, text: String): MobileElement {

        val locator: By = if (SuiteSetup.isIos()) {
            MobileBy.iOSNsPredicateString("type == 'XCUIElementTypeStaticText' AND value == '$text'")

        } else {
            By.xpath(".//android.widget.TextView[@text = '$text']")
        }

        return waitForChildElementToBePresent(scrollViewElement, locator)
    }

    private fun waitForScrollViewChildElementToDisappear(
        scrollViewElement: MobileElement,
        childElementTextQuery: String
    ) {

        val locator: By = if (SuiteSetup.isIos()) {
            MobileBy.iOSClassChain("**/XCUIElementTypeTextView[`value == \"$childElementTextQuery\"`]")
        } else {
            By.xpath(".//android.widget.TextView[@text='$childElementTextQuery']")
        }

        AppiumUtil.waitForChildElementToBeInvisible(
            getDriver(),
            scrollViewElement, locator, DEFAULT_ELEMENT_WAIT_TIME_IN_MILL
        )
    }

    private fun isButtonShowing(buttonText: String): Boolean {

        val locator: By = if (SuiteSetup.isIos()) {
            MobileBy.iOSClassChain("**/XCUIElementTypeButton[`name == \"$buttonText\"`]")
        } else {
            By.xpath("//android.widget.Button[@text='$buttonText']")
        }

        if (elementExists(locator)) {
            return waitForElementToBePresent(locator).isDisplayed
        }

        return false
    }


}
