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

@file:Suppress("REDUNDANT_ELSE_IN_WHEN")

package br.com.zup.beagle.utils

import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileBy
import io.appium.java_client.MobileDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidTouchAction
import io.appium.java_client.functions.ExpectedCondition
import io.appium.java_client.ios.IOSTouchAction
import io.appium.java_client.touch.WaitOptions
import io.appium.java_client.touch.offset.PointOption
import org.openqa.selenium.*
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.FluentWait
import java.awt.image.BufferedImage
import java.io.File
import java.time.Duration
import javax.imageio.ImageIO


object AppiumUtil {

    @Synchronized
    fun scrollToElement(
        driver: MobileDriver<*>,
        elementLocator: By,
        swipeDirection: SwipeDirection,
        elementSearchTimeout: Long,
        overallSearchTimeout: Long
    ): MobileElement {

        if (overallSearchTimeout <= elementSearchTimeout)
            throw Exception("Swipe timeout must be greater than element search timeout!")

        val wait = FluentWait(driver)
        wait.withTimeout(Duration.ofMillis(overallSearchTimeout))
        return wait.until(ExpectedCondition<MobileElement> {
            if (elementExists(driver, elementLocator, elementSearchTimeout)) {
                return@ExpectedCondition driver.findElement(elementLocator) as MobileElement
            } else {
                swipeScreenTo(driver, swipeDirection)
                return@ExpectedCondition null
            }
        })
    }

    @Synchronized
    fun swipeScreenTo(driver: MobileDriver<*>, swipeDirection: SwipeDirection) {
        if (driver is AndroidDriver<*>)
            androidSwipeScreenTo(driver, swipeDirection)
        else
            iosSwipeScreenTo(driver, swipeDirection)
    }

    /**
     *
     * Performs swipe from the center of screen
     * Adapted from http://appium.io/docs/en/writing-running-appium/tutorial/swipe/ios-mobile-screen/
     */
    @Synchronized
    fun iosSwipeScreenTo(driver: MobileDriver<*>, swipeDirection: SwipeDirection) {

        val animationTime = 200 // ms
        val scrollObject = HashMap<String, String>()
        when (swipeDirection) {
            SwipeDirection.DOWN -> scrollObject["direction"] = "down"
            SwipeDirection.UP -> scrollObject["direction"] = "up"
            SwipeDirection.LEFT -> scrollObject["direction"] = "left"
            SwipeDirection.RIGHT -> scrollObject["direction"] = "right"
            else -> throw IllegalArgumentException("mobileSwipeScreenIOS(): dir: '$swipeDirection' NOT supported")
        }

        (driver as JavascriptExecutor).executeScript("mobile:swipe", scrollObject)

        Thread.sleep(animationTime.toLong())
    }


    /**
     *
     * Performs a swipe from a given point to the border of the screen.
     * Adapted from http://appium.io/docs/en/writing-running-appium/tutorial/swipe/simple-screen/
     */
    @Synchronized
    fun androidSwipeScreenTo(
        driver: MobileDriver<*>,
        swipeDirection: SwipeDirection,
        originPoint: PointOption<*>? = null,
        animationTime: Int = 200,
        pressTime: Int = 200,
        edgeBorder: Int = 10
    ) {

        val pointOptionEndStart = originPoint ?: PointOption.point(
            driver.manage().window().size.width / 2,
            driver.manage().window().size.height / 2
        )

        val pointOptionEnd: PointOption<*>

        val dims: Dimension = driver.manage().window().getSize()
        pointOptionEnd = when (swipeDirection) {
            SwipeDirection.DOWN -> PointOption.point(dims.width / 2, dims.height - edgeBorder)
            SwipeDirection.UP -> PointOption.point(dims.width / 2, edgeBorder)
            SwipeDirection.LEFT -> PointOption.point(edgeBorder, dims.height / 2)
            SwipeDirection.RIGHT -> PointOption.point(dims.width - edgeBorder, dims.height / 2)
            else -> throw IllegalArgumentException("swipeScreen(): dir: '$swipeDirection' NOT supported")
        }

        AndroidTouchAction(driver)
            .press(pointOptionEndStart)
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(pressTime.toLong())))
            .moveTo(pointOptionEnd)
            .release().perform()

        Thread.sleep(animationTime.toLong())
    }

    /**
     * Scrolls from the position of originPoint to destinationPoint
     */
    @Synchronized
    fun androidScrollScreenFromOnePointToAnother(
        driver: MobileDriver<*>,
        originPoint: Point,
        destinationPoint: Point,
    ) {

        val animationTime = 200 // ms
        val pressTime = 200 // ms

        AndroidTouchAction(driver)
            .press(PointOption.point(originPoint.x, originPoint.y))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(pressTime.toLong())))
            .moveTo(PointOption.point(destinationPoint.x, destinationPoint.y)).perform()

        Thread.sleep(animationTime.toLong())

    }

    /**
     * Scrolls from the position of a given point to the border of the screen
     */
    @Synchronized
    fun androidScrollScreenFromOnePointToBorder(
        driver: MobileDriver<*>,
        originPoint: Point,
        swipeDirection: SwipeDirection
    ) {

        val animationTime = 200 // ms
        val pressTime = 200 // ms
        val borderEdge = 1
        val screenSize = driver.manage().window().size

        val destinationPoint = when (swipeDirection) {
            SwipeDirection.DOWN -> PointOption.point(originPoint.x, screenSize.height - borderEdge)
            SwipeDirection.UP -> PointOption.point(originPoint.x, borderEdge)
            SwipeDirection.LEFT -> PointOption.point(borderEdge, originPoint.y)
            SwipeDirection.RIGHT -> PointOption.point(screenSize.width - borderEdge, originPoint.y)
            else -> throw IllegalArgumentException("Diretion '$swipeDirection' not supported")
        }

        AndroidTouchAction(driver)
            .press(PointOption.point(originPoint.x, originPoint.y))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(pressTime.toLong())))
            .moveTo(destinationPoint).perform()

        Thread.sleep(animationTime.toLong())

    }

    /**
     * Scrolls from the position of a given point to the center of the screen
     */
    @Synchronized
    fun androidScrollScreenFromOnePointToCenterPoint(
        driver: MobileDriver<*>,
        originPoint: Point,
        horizontalScroll: Boolean
    ) {

        val animationTime = 200 // ms
        val pressTime = 200 // ms
        val screenSize = driver.manage().window().size
        var destinationPointX = originPoint.x
        var destinationPointY = originPoint.y

        if (horizontalScroll) {
            destinationPointX = screenSize.width / 2
        } else {
            destinationPointY = screenSize.height / 2
        }

        val destinationPoint = PointOption.point(destinationPointX, destinationPointY)

        AndroidTouchAction(driver)
            .press(PointOption.point(originPoint.x, originPoint.y))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(pressTime.toLong())))
            .moveTo(destinationPoint).perform()

        Thread.sleep(animationTime.toLong())

    }

    /**
     * Scrolls from the position of a given element to the border of the screen
     */
    @Synchronized
    fun iosScrollScreenFromOnePointToBorder(
        driver: MobileDriver<*>,
        originPoint: Point,
        swipeDirection: SwipeDirection
    ) {
        val horizontalBorderEdge = 100
        val verticalBorderEdge = 100
        val screenSize = driver.manage().window().size

        var timeout = 200L
        if (swipeDirection == SwipeDirection.UP || swipeDirection == SwipeDirection.DOWN) {
            timeout = 2000L
        }

        /**
         * Origin point should not be located before border limits
         */
        if (originPoint.x < horizontalBorderEdge)
            originPoint.x = horizontalBorderEdge
        if (originPoint.y < verticalBorderEdge)
            originPoint.y = verticalBorderEdge

        /**
         * Should not click outside the screen border. On iOS, clicks near the border sometimes won't work, so
         * the origin point is reworked
         */
        if (originPoint.x > (screenSize.width - horizontalBorderEdge))
            originPoint.x = screenSize.width - horizontalBorderEdge
        if (originPoint.y >= (screenSize.height - verticalBorderEdge))
            originPoint.y = screenSize.height - verticalBorderEdge


        val destinationPoint = when (swipeDirection) {
            SwipeDirection.DOWN -> Point(originPoint.x, screenSize.height - verticalBorderEdge)
            SwipeDirection.UP -> Point(originPoint.x, verticalBorderEdge)
            SwipeDirection.LEFT -> Point(horizontalBorderEdge, originPoint.y)
            SwipeDirection.RIGHT -> Point(screenSize.width - horizontalBorderEdge, originPoint.y)
            else -> throw IllegalArgumentException("Diretion '$swipeDirection' not supported")
        }

        IOSTouchAction(driver)
            .press(PointOption.point(originPoint.x, originPoint.y))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(timeout)))
            .moveTo(PointOption.point(destinationPoint)).perform()
    }

    /**
     * Scrolls from the position of a given point to the center of the screen
     */
    @Synchronized
    fun iosScrollScreenFromOnePointToCenterPoint(
        driver: MobileDriver<*>,
        originPoint: Point,
        horizontalScroll: Boolean
    ) {

        val horizontalBorderEdge = 100
        val verticalBorderEdge = 100
        val animationTime = 200 // ms
        val pressTime = 200 // ms
        val screenSize = driver.manage().window().size
        var destinationPointX = originPoint.x
        var destinationPointY = originPoint.y

        /**
         * Should not click outside the screen border. On iOS, clicks near the border sometimes won't work, so
         * the origin point is reworked
         */
        if (originPoint.x > (screenSize.width - horizontalBorderEdge))
            originPoint.x = screenSize.width - horizontalBorderEdge
        if (originPoint.y >= (screenSize.height - verticalBorderEdge))
            originPoint.y = screenSize.height - verticalBorderEdge

        if (horizontalScroll) {
            destinationPointX = screenSize.width / 2
        } else {
            destinationPointY = screenSize.height / 2
        }

        val destinationPoint = PointOption.point(destinationPointX, destinationPointY)

        IOSTouchAction(driver)
            .press(PointOption.point(originPoint.x, originPoint.y))
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(pressTime.toLong())))
            .moveTo(destinationPoint).perform()

        Thread.sleep(animationTime.toLong())

    }

    /**
     * Uses the visible part of the given element as the unit of  the scrolling. If the direction is up or
     * down, the display will be scrolled by the height of the element. If the direction is right or left the element
     * will be scrolled by its visible width.
     * source: https://developers.perfectomobile.com/pages/viewpage.action?pageId=25199704
     */
    @Synchronized
    fun iosScrollInsideElement(
        driver: MobileDriver<*>,
        element: MobileElement,
        swipeDirection: SwipeDirection
    ) {

        val scrollObject = HashMap<String, String>()
        when (swipeDirection) {
            SwipeDirection.DOWN -> scrollObject["direction"] = "down" // from down to up (! differs from mobile:swipe)
            SwipeDirection.UP -> scrollObject["direction"] = "up" // from up to down (! differs from mobile:swipe)
            SwipeDirection.LEFT -> scrollObject["direction"] =
                "left" // from left to right (! differs from mobile:swipe)
            SwipeDirection.RIGHT -> scrollObject["direction"] =
                "right" // from right to left (! differs from mobile:swipe)
            else -> throw IllegalArgumentException("mobileSwipeScreenIOS(): dir: '$swipeDirection' NOT supported")
        }
        scrollObject["element"] = element.id
        (driver as JavascriptExecutor).executeScript("mobile:scroll", scrollObject)
    }

    /**
     * Same as iosScrollInsideElement.
     * Adapted from http://appium.io/docs/en/writing-running-appium/tutorial/swipe/simple-element/
     */
    @Synchronized
    fun androidScrollInsideElement(
        driver: MobileDriver<*>,
        element: MobileElement,
        swipeDirection: SwipeDirection
    ) {

        val animationTime = 200 // ms
        val pressTime = 200 // ms

        val edgeBorder = 0
        val pointOptionStart: PointOption<*>
        val pointOptionEnd: PointOption<*>

        val rect: Rectangle = element.getRect()

        when (swipeDirection) {
            SwipeDirection.DOWN -> {
                pointOptionStart = PointOption.point(
                    rect.x + rect.width / 2,
                    rect.y + edgeBorder
                )
                pointOptionEnd = PointOption.point(
                    rect.x + rect.width / 2,
                    rect.y + rect.height - edgeBorder
                )
            }
            SwipeDirection.UP -> {
                pointOptionStart = PointOption.point(
                    rect.x + rect.width / 2,
                    rect.y + rect.height - edgeBorder
                )
                pointOptionEnd = PointOption.point(
                    rect.x + rect.width / 2,
                    rect.y + edgeBorder
                )
            }
            SwipeDirection.LEFT -> {
                pointOptionStart = PointOption.point(
                    rect.x + rect.width - edgeBorder,
                    rect.y + rect.height / 2
                )
                pointOptionEnd = PointOption.point(
                    rect.x + edgeBorder,
                    rect.y + rect.height / 2
                )
            }
            SwipeDirection.RIGHT -> {
                pointOptionStart = PointOption.point(
                    rect.x + edgeBorder,
                    rect.y + rect.height / 2
                )
                pointOptionEnd = PointOption.point(
                    rect.x + rect.width - edgeBorder,
                    rect.y + rect.height / 2
                )
            }
            else -> throw IllegalArgumentException("swipeElementAndroid(): dir: '" + swipeDirection.toString() + "' NOT supported")
        }

        AndroidTouchAction(driver)
            .press(pointOptionStart) // a bit more reliable when we add small wait
            .waitAction(WaitOptions.waitOptions(Duration.ofMillis(pressTime.toLong())))
            .moveTo(pointOptionEnd).perform()

        Thread.sleep(animationTime.toLong())
    }

    /**
     * src: http://appium.io/docs/en/writing-running-appium/tutorial/swipe/android-multiple/
     */
    @Synchronized
    fun androidScrollToElementByText(
        driver: MobileDriver<*>,
        scrollElementIndex: Int,
        elementText: String,
        isHorizontalScroll: Boolean = false,
        isLikeSearch: Boolean = false
    ) {
        val textSearchMethod = if (isLikeSearch) "textContains" else "text"
        if (isHorizontalScroll) {
            driver.findElement(
                MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)" +
                            ".instance($scrollElementIndex)).setAsHorizontalList()" +
                            ".scrollIntoView(new UiSelector().$textSearchMethod(\"$elementText\"))"
                )
            )
        } else {
            driver.findElement(
                MobileBy.AndroidUIAutomator(
                    "new UiScrollable(new UiSelector().scrollable(true)" +
                            ".instance($scrollElementIndex))" +
                            ".scrollIntoView(new UiSelector().$textSearchMethod(\"$elementText\"))"
                )
            )
        }
    }

    /**
     * Waits for an element to be found on the screen element tree. This does not
     * necessarily mean that the element is visible.
     */
    @Synchronized
    fun waitForElementToBePresent(driver: MobileDriver<*>, locator: By, timeoutInMilliseconds: Long): MobileElement {
        val wait: FluentWait<MobileDriver<*>> = FluentWait<MobileDriver<*>>(driver)
        wait.pollingEvery(Duration.ofMillis(200))
        wait.withTimeout(Duration.ofMillis(timeoutInMilliseconds))
        wait.ignoring(NoSuchElementException::class.java)
        wait.ignoring(StaleElementReferenceException::class.java)
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator)) as MobileElement
    }

    /**
     * Waits for an element, child of a given parent element, to be found on the screen element tree. This does not
     * necessarily mean that the element is visible.
     */
    @Synchronized
    fun waitForChildElementToBePresent(
        driver: MobileDriver<*>,
        parentElement: MobileElement,
        locator: By,
        timeoutInMilliseconds: Long
    ): MobileElement {
        val wait: FluentWait<MobileDriver<*>> = FluentWait<MobileDriver<*>>(driver)
        wait.pollingEvery(Duration.ofMillis(200))
        wait.withTimeout(Duration.ofMillis(timeoutInMilliseconds))
        wait.ignoring(NoSuchElementException::class.java)
        wait.ignoring(StaleElementReferenceException::class.java)
        return wait.until {
            parentElement.findElement(locator)
        } as MobileElement
    }

    /**
     * Waits for a list of elements, children of a given parent element, to be found on the screen element tree. This does not
     * necessarily mean that these elements are visible.
     */
    @Synchronized
    fun waitForChildrenElementsToBePresent(
        driver: MobileDriver<*>,
        parentElement: MobileElement,
        locator: By,
        timeoutInMilliseconds: Long
    ): List<MobileElement> {
        val wait: FluentWait<MobileDriver<*>> = FluentWait<MobileDriver<*>>(driver)
        wait.pollingEvery(Duration.ofMillis(200))
        wait.withTimeout(Duration.ofMillis(timeoutInMilliseconds))
        wait.ignoring(NoSuchElementException::class.java)
        wait.ignoring(StaleElementReferenceException::class.java)
        return wait.until {
            parentElement.findElements(locator)
        } as List<MobileElement>
    }

    /**
     * Waits for an element to be visible and enabled (clickable)
     */
    @Synchronized
    fun waitForElementToBeClickable(
        driver: MobileDriver<*>,
        locator: By,
        timeoutInMilliseconds: Long,
    ): MobileElement {
        val wait = FluentWait(driver)
        wait.pollingEvery(Duration.ofMillis(200))
        wait.withTimeout(Duration.ofMillis(timeoutInMilliseconds))
        wait.ignoring(NoSuchElementException::class.java)
        wait.ignoring(StaleElementReferenceException::class.java)
        wait.ignoring(ElementNotInteractableException::class.java)
        wait.ignoring(ElementNotVisibleException::class.java)
        return wait.until(ExpectedConditions.elementToBeClickable(locator)) as MobileElement // clickable = verifies enabled e visibility
    }

    /**
     * Waits for an element to be visible and enabled (clickable)
     */
    @Synchronized
    fun waitForElementToBeClickable(
        driver: MobileDriver<*>,
        element: MobileElement,
        timeoutInMilliseconds: Long
    ): MobileElement {
        val wait = FluentWait(driver)
        wait.pollingEvery(Duration.ofMillis(200))
        wait.withTimeout(Duration.ofMillis(timeoutInMilliseconds))
        wait.ignoring(NoSuchElementException::class.java)
        wait.ignoring(StaleElementReferenceException::class.java)
        wait.ignoring(ElementNotInteractableException::class.java)
        wait.ignoring(ElementNotVisibleException::class.java)
        return wait.until(ExpectedConditions.elementToBeClickable(element)) as MobileElement
    }

    /**
     * Waits for a set of elements to be visible and enabled (clickable)
     */
    @Synchronized
    fun waitForElementsToBeClickable(
        driver: MobileDriver<*>,
        elements: Array<MobileElement>,
        timeoutInMilliseconds: Long
    ) {
        for (element in elements) {
            waitForElementToBeClickable(driver, element, timeoutInMilliseconds)
        }
    }

    /**
     * Waits for an element to be hidden or nonexistent
     */
    @Synchronized
    fun waitForElementToBeInvisible(driver: MobileDriver<*>, locator: By, timeoutInMilliseconds: Long) {
        val wait = FluentWait(driver)
        wait.withTimeout(Duration.ofMillis(timeoutInMilliseconds))
        wait.pollingEvery(Duration.ofMillis(200))
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator))
    }

    /**
     * Waits for a child element to be hidden or nonexistent
     */
    @Synchronized
    fun waitForChildElementToBeInvisible(driver: MobileDriver<*>, parentElement: MobileElement, childLocator: By, timeoutInMilliseconds: Long) {
        val wait = FluentWait(driver)
        wait.withTimeout(Duration.ofMillis(timeoutInMilliseconds))
        wait.pollingEvery(Duration.ofMillis(200))
        wait.until {
            !childElementExists(driver, parentElement, childLocator, 1000)
        }
    }

    /**
     * Waits for an element to be hidden or nonexistent
     */
    @Synchronized
    fun waitForElementToBeInvisible(
        driver: MobileDriver<*>,
        element: MobileElement,
        timeoutInMilliseconds: Long
    ) {
        val wait = FluentWait(driver)
        wait.withTimeout(Duration.ofMillis(timeoutInMilliseconds))
        wait.pollingEvery(Duration.ofMillis(200))
        wait.until(ExpectedConditions.invisibilityOf(element))
    }

    /**
     * Waits for elements to be hidden or nonexistent
     */
    @Synchronized
    fun waitForElementsToBeInvisible(driver: MobileDriver<*>, locator: By, timeoutInMilliseconds: Long) {
        val wait = FluentWait(driver)
        wait.withTimeout(Duration.ofMillis(timeoutInMilliseconds))
        wait.pollingEvery(Duration.ofMillis(200))
        wait.until {
            driver.findElements(locator).size == 0
        }
    }

    /**
     * Waits for an element to be visible and disabled (not clickable)
     */
    @Synchronized
    fun waitForElementToBeDisabled(driver: MobileDriver<*>, locator: By, timeoutInMilliseconds: Long) {
        val mobileElement = waitForElementToBePresent(driver, locator, timeoutInMilliseconds)
        val wait = FluentWait(driver)
        wait.withTimeout(Duration.ofMillis(timeoutInMilliseconds))
        wait.pollingEvery(Duration.ofMillis(200))
        wait.until {
            mobileElement.isDisplayed && !mobileElement.isEnabled
        }
    }

    @Synchronized
    fun waitForElementTextToBe(
        driver: MobileDriver<*>,
        element: MobileElement,
        text: String,
        timeoutInMilliseconds: Long
    ) {
        val wait = FluentWait(driver)
        wait.withTimeout(Duration.ofMillis(timeoutInMilliseconds))
        wait.until {
            return@until element.text == text
        }
    }

    @Synchronized
    fun waitForElementTextToContain(
        driver: MobileDriver<*>,
        element: MobileElement,
        text: String,
        timeoutInMilliseconds: Long
    ) {
        val wait = FluentWait(driver)
        wait.withTimeout(Duration.ofMillis(timeoutInMilliseconds))
        wait.pollingEvery(Duration.ofMillis(200))
        wait.until {
            return@until element.text.contains(text)
        }
    }

    @Synchronized
    fun waitForElementAttributeToBe(
        driver: MobileDriver<*>,
        element: MobileElement,
        attribute: String,
        value: String,
        timeoutInMilliseconds: Long
    ): Boolean {
        val wait = FluentWait(driver)
        wait.withTimeout(Duration.ofMillis(timeoutInMilliseconds))
        wait.pollingEvery(Duration.ofMillis(200))
        return wait.until {
            element.getAttribute(attribute) != null && element.getAttribute(attribute) == value
        }
    }

    @Synchronized
    fun elementExists(driver: MobileDriver<*>, locator: By, timeoutInMilliseconds: Long): Boolean {
        try {
            waitForElementToBePresent(driver, locator, timeoutInMilliseconds)
            return true // element found
        } catch (e: Exception) {
        }
        return false
    }

    @Synchronized
    fun childElementExists(
        driver: MobileDriver<*>,
        parentElement: MobileElement,
        childLocator: By,
        timeoutInMilliseconds: Long
    ): Boolean {
        try {
            waitForChildElementToBePresent(driver, parentElement, childLocator, timeoutInMilliseconds)
            return true // element found
        } catch (e: Exception) {
        }
        return false
    }

    /**
     * @return true if element1 is above element2
     */
    @Synchronized
    fun isElementAboveElement(element1: MobileElement, element2: MobileElement): Boolean {
        var element1LocationY: Int = element1.location.y // + element1.size.height
        var element2LocationY: Int = element2.location.y

        if (element2LocationY > element1LocationY)
            return true

        return false
    }

    @Synchronized
    fun getXpathLocator(property: String, propertyValue: String, likeSearch: Boolean, ignoreCase: Boolean): By {
        var key = "@$property"
        var value = "\"$propertyValue\""

        if (ignoreCase) {
            key = "translate(@$property, 'ABCDEFGHIJKLMNOPQRSTUVWXYZ', 'abcdefghijklmnopqrstuvwxyz')"
            value = value.toLowerCase()
        }

        return if (likeSearch) {
            By.xpath("//*[contains($key, $value)]")
        } else {
            By.xpath("//*[$key=$value]")
        }
    }

    /**
     * Uses a native iOS locator strategy. Faster than XPath
     *
     * src:
     *  https://github.com/facebookarchive/WebDriverAgent/wiki/Predicate-Queries-Construction-Rules
     *  https://appium.io/docs/en/writing-running-appium/ios/ios-predicate/#ios-predicate
     */
    @Synchronized
    fun getIOSNsPredicateLocator(
        elementProperty: String,
        elementPropertyValue: String,
        likeSearch: Boolean,
        ignoreCase: Boolean
    ): By {

        return if (likeSearch && ignoreCase) {
            MobileBy.iOSNsPredicateString("$elementProperty CONTAINS[c] '${elementPropertyValue}'")
        } else if (likeSearch) {
            MobileBy.iOSNsPredicateString("$elementProperty CONTAINS '${elementPropertyValue}'")
        } else if (ignoreCase) {
            MobileBy.iOSNsPredicateString("$elementProperty ==[c] '${elementPropertyValue}'")
        } else {
            MobileBy.iOSNsPredicateString("$elementProperty == '${elementPropertyValue}'")
        }
    }

    /**
     * @return the current running app screenshot without the device's status and navigation bar, using
     * @param locator to crop the screenshot
     */
    @Synchronized
    fun getAppScreenshot(driver: AppiumDriver<*>, locator: By): File {

        // gets the base element's position
        val baseElement = driver.findElement(locator)
        val baseElementLocation = baseElement.location
        val baseElementWidth = baseElement.size.getWidth()
        val baseElementHeight = baseElement.size.getHeight()

        // takes a screenshot of the whole device screen.
        val screenshot = (driver as TakesScreenshot).getScreenshotAs(OutputType.FILE)

        // crops the image selection only the base element coordinates
        val bufferedImage = ImageIO.read(screenshot)
        val baseElementScreenshot: BufferedImage = bufferedImage.getSubimage(
            baseElementLocation.getX(),
            baseElementLocation.getY(),
            baseElementWidth,
            baseElementHeight
        )

        // save the cropped image overwriting the screenshot file
        ImageIO.write(baseElementScreenshot, "png", screenshot)

        return screenshot
    }

    /**
     * @return the current running app screenshot without the device's status and navigation bar
     * Device: iPhone 11
     * macOS resolution: 2560 x 1600
     */
    @Synchronized
    fun getIosAppScreenshot(driver: AppiumDriver<*>): File {

        // takes a screenshot of the whole device screen.
        val screenshot = (driver as TakesScreenshot).getScreenshotAs(OutputType.FILE)

        // crops the image selection only the base element coordinates
        val bufferedImage = ImageIO.read(screenshot)
        val baseElementScreenshot: BufferedImage = bufferedImage.getSubimage(
            0,
            65,
            828,
            1680
        )

        // save the cropped image overwriting the screenshot file
        ImageIO.write(baseElementScreenshot, "png", screenshot)

        return screenshot
    }

    @Synchronized
    fun iOStapOnCenterOfElement(driver: AppiumDriver<*>, element: MobileElement) {
        val x = element.center.x
        val y = element.center.y
        IOSTouchAction(driver).tap(PointOption.point(x, y)).perform()
    }

    @Synchronized
    fun androidLongPressElement(
        driver: AppiumDriver<*>,
        elementToBeLogPressed: MobileElement,
        milliseconds: Long = 200
    ) {
        val centerOfElement = PointOption.point(elementToBeLogPressed.center.x, elementToBeLogPressed.center.y)
        val waitOption = WaitOptions.waitOptions(Duration.ofMillis(milliseconds))
        AndroidTouchAction(driver)
            .press(centerOfElement)
            .waitAction(waitOption)
            .release().perform()
    }

    @Synchronized
    fun iosLongPressElement(driver: AppiumDriver<*>, elementToBeLogPressed: MobileElement, milliseconds: Long = 200) {
        val centerOfElement = PointOption.point(elementToBeLogPressed.center.x, elementToBeLogPressed.center.y)
        val waitOption = WaitOptions.waitOptions(Duration.ofMillis(milliseconds))
        IOSTouchAction(driver)
            .press(centerOfElement)
            .waitAction(waitOption)
            .release().perform()
    }
}