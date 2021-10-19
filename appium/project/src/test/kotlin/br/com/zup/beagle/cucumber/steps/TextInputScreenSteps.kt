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
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.nativekey.AndroidKey
import io.appium.java_client.android.nativekey.KeyEvent
import io.cucumber.datatable.DataTable
import io.cucumber.java.Before
import io.cucumber.java.en.Given
import io.cucumber.java.en.Then
import org.junit.Assert
import org.openqa.selenium.By


class TextInputScreenSteps : AbstractStep() {
    override var bffRelativeUrlPath = "/textinput"

    @Before("@textInput")
    fun setup() {
        loadBffScreen()
    }

    @Given("^the Beagle application did launch with the textInput on screen$")
    fun checkBaseScreen() {
        waitForElementWithValueToBeClickable("Beagle Text Input")
    }

    @Then("^validate placeholders:$")
    fun validatePlaceHoldersAndVisibility(dataTable: DataTable) {
        val rows = dataTable.asLists()
        for ((lineCount, columns) in rows.withIndex()) {

            if (lineCount == 0) // skip header
                continue

            try {
                hideKeyboard()
            } catch (e: Exception) {
            }

            val placeHolder = columns[0]!!
            val customTextValue = "$placeHolder-2"
            val enabled = columns[1]!!.toString().toBoolean()
            val readOnly = columns[2]!!.toString().toBoolean()

            val element = waitForElementWithValueToBePresent(placeHolder, nativeLocator = false)
            if (!enabled) {
                Assert.assertFalse(element.isEnabled)
            } else if (!readOnly) {
                element.clear()
                element.sendKeys(customTextValue)
                Assert.assertEquals(element.text, customTextValue)
                Assert.assertTrue(element.isEnabled)
            } else { // enabled and readOnly
                if (SuiteSetup.isAndroid())
                    element.clear()
                Assert.assertFalse(isKeyboardShowing())
                element.click()
                Assert.assertFalse(isKeyboardShowing())
            }
        }
    }

    @Then("^validate clicks and input types:$")
    fun validateClicksAndInputTypes(dataTable: DataTable) {
        val rows = dataTable.asLists()
        for ((lineCount, columns) in rows.withIndex()) {

            if (lineCount == 0) // skip header
                continue

            try {
                hideKeyboard()
            } catch (e: Exception) {
            }

            val placeHolder = columns[0]!!

            when (val validationAction = columns[1]!!) {
                "placeholder reappears after clearing value" -> {

                    val element = waitForElementWithValueToBeClickable(placeHolder, nativeLocator = false)

                    if (SuiteSetup.isAndroid() && SuiteSetup.getPlatformVersion() == "4.4") {
                        element.click()
                        val androidDriver = (getDriver() as AndroidDriver)
                        androidDriver.pressKey(KeyEvent(AndroidKey.O))
                        androidDriver.pressKey(KeyEvent(AndroidKey.K))
                        waitForElementWithValueToBeClickable("ok").clear()
                        waitForElementWithValueToBeClickable(placeHolder) // placeholder restored

                    } else {
                        element.sendKeys("ok")
                        Assert.assertFalse(placeHolder == element.text)
                        element.clear()
                        Assert.assertTrue(placeHolder == element.text) // placeholder restored
                    }

                }
                "validate typed text" -> {
                    when {
                        placeHolder.contains("writing date") -> {

                            val element = waitForElementWithValueToBeClickable(placeHolder, nativeLocator = false)

                            if (SuiteSetup.isAndroid() && SuiteSetup.getPlatformVersion() == "4.4") {
                                element.click()
                                val androidDriver = (getDriver() as AndroidDriver)
                                androidDriver.pressKey(KeyEvent(AndroidKey.DIGIT_0))
                                androidDriver.pressKey(KeyEvent(AndroidKey.DIGIT_2))
                                androidDriver.pressKey(KeyEvent(AndroidKey.SLASH))
                                androidDriver.pressKey(KeyEvent(AndroidKey.DIGIT_0))
                                androidDriver.pressKey(KeyEvent(AndroidKey.DIGIT_4))
                                androidDriver.pressKey(KeyEvent(AndroidKey.SLASH))
                                androidDriver.pressKey(KeyEvent(AndroidKey.DIGIT_2))
                                androidDriver.pressKey(KeyEvent(AndroidKey.DIGIT_0))
                                androidDriver.pressKey(KeyEvent(AndroidKey.DIGIT_0))
                                androidDriver.pressKey(KeyEvent(AndroidKey.DIGIT_0))
                            } else {
                                element.sendKeys("02/04/2000")
                            }

                            waitForElementWithValueToBeClickable("02/04/2000", nativeLocator = false).clear()

                        }
                        placeHolder.contains("writing e-mail") -> {

                            val element = scrollUpToElementWithValue(placeHolder)
                            if (SuiteSetup.isAndroid() && SuiteSetup.getPlatformVersion() == "4.4") {
                                element.click()
                                val androidDriver = (getDriver() as AndroidDriver)
                                androidDriver.pressKey(KeyEvent(AndroidKey.A))
                                androidDriver.pressKey(KeyEvent(AndroidKey.B))
                                androidDriver.pressKey(KeyEvent(AndroidKey.C))
                                androidDriver.pressKey(KeyEvent(AndroidKey.AT))
                                androidDriver.pressKey(KeyEvent(AndroidKey.DIGIT_1))
                                androidDriver.pressKey(KeyEvent(AndroidKey.DIGIT_2))
                                androidDriver.pressKey(KeyEvent(AndroidKey.DIGIT_3))
                            } else {
                                element.sendKeys("abc@123")
                            }

                            waitForElementWithValueToBeClickable("abc@123", nativeLocator = false).clear()

                        }
                        placeHolder.contains("writing password") -> {

                            /**
                             * The text property of a password element is sometimes empty on Android 6.x,
                             * so it's difficult to look up for this element by its text.
                             * The approach then is to find the password element by its neighbor elements
                             */
                            if (SuiteSetup.isAndroid() && SuiteSetup.getPlatformVersion().toDouble() <= 6) {

                                // scrolls to an element that is below writing password, so writing password appears
                                AppiumUtil.androidScrollToElementByText(
                                    getDriver(),
                                    0,
                                    "writing number"
                                )

                                val passwordElement = if (placeHolder == "writing password") {
                                    waitForElementToBePresent(
                                        By.xpath(
                                            "//android.widget.EditText[@text='writing e-mail with expression']" +
                                                    "/following::android.widget.EditText[1]"
                                        )
                                    )
                                } else {
                                    waitForElementToBePresent(
                                        By.xpath(
                                            "//android.widget.EditText[@text='writing e-mail with expression']" +
                                                    "/following::android.widget.EditText[2]"
                                        )
                                    )
                                }

                                passwordElement.click()
                                val androidDriver = (getDriver() as AndroidDriver)
                                androidDriver.pressKey(KeyEvent(AndroidKey.DIGIT_1))
                                androidDriver.pressKey(KeyEvent(AndroidKey.DIGIT_2))
                                androidDriver.pressKey(KeyEvent(AndroidKey.DIGIT_3))
                                // validates that the value is encrypted in password style
                                Assert.assertFalse(elementExistsByValue("123"))

                            } else {

                                val element = scrollDownToElementWithValue(placeHolder)
                                element.sendKeys("1234")
                                Assert.assertTrue("1234" != element.text) // validates text is in password format
                                Assert.assertTrue(element.text.length == 4)
                                Assert.assertTrue(placeHolder != element.text)
                                element.clear()
                            }
                        }
                        placeHolder.contains("writing number") -> {

                            val element = scrollDownToElementWithValue(placeHolder)
                            if (SuiteSetup.isAndroid() && SuiteSetup.getPlatformVersion() == "4.4") {
                                element.click()
                                val androidDriver = (getDriver() as AndroidDriver)
                                androidDriver.pressKey(KeyEvent(AndroidKey.DIGIT_1))
                                androidDriver.pressKey(KeyEvent(AndroidKey.DIGIT_2))
                                androidDriver.pressKey(KeyEvent(AndroidKey.DIGIT_3))

                            } else {
                                element.sendKeys("123")
                            }
                            waitForElementWithValueToBeClickable("123", nativeLocator = false).clear()

                        }
                        placeHolder.contains("writing text") -> {

                            val element = scrollDownToElementWithValue(placeHolder)
                            if (SuiteSetup.isAndroid() && SuiteSetup.getPlatformVersion() == "4.4") {
                                element.click()
                                typeText()
                            } else {
                                element.sendKeys("text")
                            }

                            waitForElementWithValueToBeClickable("text", nativeLocator = false).clear()
                        }
                        else -> {
                            throw Exception("Wrong place holder: $placeHolder")
                        }
                    }
                }
                "validate is number only textInput" -> {

                    if (SuiteSetup.isAndroid() && SuiteSetup.getPlatformVersion() == "4.4") {

                        /**
                         *  On Android 4.4, an element sometimes lose its reference when its property (ex text) changes.
                         *  We took the following approach to look up an element without knowing any of its properties:
                         *  Step 1: grab its preceding sibling
                         *  Step 2: find the element by calling the following sibling of the element found in step 1
                         */
                        scrollDownToElementWithValue(placeHolder)
                        val targetElementPrecedingSiblingText = waitForElementToBePresent(
                            By.xpath(
                                "//android.widget.EditText[@text='$placeHolder']" +
                                        "/preceding::android.widget.EditText[1]"
                            )
                        ).text

                        // Changes the target element text by typing a digit.
                        try {
                            isTextFieldNumeric(placeHolder)
                        } catch (e: Exception) {
                        }

                        // It's necessary to find the element again
                        val targetElement =
                            waitForElementToBePresent(
                                By.xpath(
                                    "//android.widget.EditText[@text='${targetElementPrecedingSiblingText}']/following::android.widget.EditText[1]"
                                )
                            )

                        Assert.assertTrue(targetElement.text.all { Character.isDigit(it) })
                        targetElement.clear()
                    } else {
                        val element = scrollDownToElementWithValue(placeHolder)
                        Assert.assertTrue(isTextFieldNumeric(placeHolder))
                        element.clear()
                    }

                }
                else -> {
                    throw Exception("Wrong validation action: $validationAction")
                }
            }
        }
    }

    @Then("^validate textInput events:$")
    fun validateEvents(dataTable: DataTable) {
        swipeUp()
        swipeUp()

        val rows = dataTable.asLists()

        for ((lineCount, columns) in rows.withIndex()) {

            if (lineCount == 0) // skip header
                continue

            when (val event = columns[0]!!) {
                "DidOnFocus" -> {
                    /**
                     * Asserts the text 'Unordered actions' changes to 'DidOnFocus'
                     * This verification is done by checking visibility and invisibility of elements by their text
                     * The same logic applies for the other cases
                     */
                    Assert.assertTrue(elementExistsByValue("Unordered actions"))
                    Assert.assertFalse(elementExistsByValue(event))
                    safeClickOnElement(waitForElementWithValueToBeClickable("action validation"))
                    if (SuiteSetup.isAndroid())
                        hideKeyboard()
                    Assert.assertFalse(elementExistsByValue("Unordered actions"))
                    Assert.assertTrue(elementExistsByValue(event))
                }
                "DidOnChange" -> {
                    Assert.assertTrue(elementExistsByValue("DidOnFocus"))
                    Assert.assertFalse(elementExistsByValue(event))
                    val element = waitForElementWithValueToBeClickable("action validation")
                    safeClickOnElement(element)
                    if (SuiteSetup.isAndroid() && SuiteSetup.getPlatformVersion() == "4.4") {
                        typeText()
                    } else {
                        element.sendKeys("text")
                    }
                    if (SuiteSetup.isAndroid())
                        hideKeyboard()
                    Assert.assertFalse(elementExistsByValue("DidOnFocus"))
                    Assert.assertTrue(elementExistsByValue(event))
                    waitForElementWithValueToBeClickable("text", nativeLocator = false).clear()
                }
                "DidOnBlur" -> {
                    Assert.assertTrue(elementExistsByValue("DidOnChange"))
                    Assert.assertFalse(elementExistsByValue(event))
                    safeClickOnElement(waitForElementWithValueToBeClickable("action validation"))
                    safeClickOnElement(waitForElementWithValueToBeClickable("is textInput type number"))
                    if (SuiteSetup.isIos()) {
                        safeClickOnElement(waitForElementWithTextToBeClickable("Done"))
                    } else {
                        hideKeyboard()
                    }
                    Assert.assertFalse(elementExistsByValue("DidOnChange"))
                    Assert.assertTrue(elementExistsByValue(event))
                }
                "DidOnFocusDidOnChangeDidOnBlur" -> {

                    Assert.assertTrue(elementExistsByValue("Ordered actions"))
                    Assert.assertFalse(elementExistsByValue(event))
                    val element = waitForElementWithValueToBeClickable("action order")
                    safeClickOnElement(element)
                    if (SuiteSetup.isAndroid() && SuiteSetup.getPlatformVersion() == "4.4") {
                        typeText()
                    } else {
                        element.sendKeys("text")
                    }

                    safeClickOnElement(waitForElementWithValueToBeClickable("is textInput type number"))

                    if (SuiteSetup.isIos()) {
                        safeClickOnElement(waitForElementWithTextToBeClickable("Done"))
                    } else {
                        hideKeyboard()
                    }
                    Assert.assertFalse(elementExistsByValue("Ordered actions"))
                    Assert.assertTrue(elementExistsByValue(event))
                }
                else -> {
                    throw Exception("Wrong event: $event")
                }
            }
        }
    }

    private fun typeText() {
        val androidDriver = (getDriver() as AndroidDriver)
        androidDriver.pressKey(KeyEvent(AndroidKey.T))
        androidDriver.pressKey(KeyEvent(AndroidKey.E))
        androidDriver.pressKey(KeyEvent(AndroidKey.X))
        androidDriver.pressKey(KeyEvent(AndroidKey.T))
    }
}


