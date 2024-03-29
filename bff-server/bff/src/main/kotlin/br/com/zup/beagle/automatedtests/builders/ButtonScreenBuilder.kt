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


package br.com.zup.beagle.automatedtests.builders

import br.com.zup.beagle.automatedtests.constants.BUTTON_STYLE_APPEARANCE
import br.com.zup.beagle.automatedtests.constants.CYAN_BLUE
import br.com.zup.beagle.automatedtests.constants.SCREEN_ACTION_CLICK_ENDPOINT
import br.com.zup.beagle.core.CornerRadius
import br.com.zup.beagle.core.Style
import br.com.zup.beagle.ext.setStyle
import br.com.zup.beagle.widget.Widget
import br.com.zup.beagle.widget.action.Alert
import br.com.zup.beagle.widget.action.Navigate
import br.com.zup.beagle.widget.action.Route
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.context.constant
import br.com.zup.beagle.widget.context.expressionOf
import br.com.zup.beagle.widget.core.AlignContent
import br.com.zup.beagle.widget.core.EdgeValue
import br.com.zup.beagle.widget.core.Flex
import br.com.zup.beagle.widget.core.UnitValue
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.layout.NavigationBar
import br.com.zup.beagle.widget.layout.NavigationBarItem
import br.com.zup.beagle.widget.layout.Screen
import br.com.zup.beagle.widget.ui.Button

object ButtonScreenBuilder {

    private fun createButtonScreen(alignContent: AlignContent, marginTop: UnitValue): Screen {
        return Screen(
            context = ContextData(id = "enabled", value = false),
            navigationBar = NavigationBar(
                title = "Beagle Button",
                showBackButton = true,
                navigationBarItems = listOf(
                    NavigationBarItem(
                        text = "",
                        image = "informationImage",
                        onPress = listOf(
                            Alert(
                                title = "Button",
                                message = "This is a widget that will define a button natively using the server " +
                                    "driven information received through Beagle.",
                                labelOk = "OK"
                            )
                        )
                    )
                )
            ),
            child = createContainer(alignContent, marginTop)
        )
    }

    private fun createContainer(alignContent: AlignContent, marginTop: UnitValue) = Container(
        children = listOf(
            createButton(
                text = "Button",
                style = Style(
                    margin = EdgeValue(
                        top = UnitValue.real(15)
                    ),
                    flex = Flex(alignContent = alignContent)
                )
            ),

            createButton(
                text = "Button with style",
                styleId = "DesignSystem.Button.ScreenButton",
                style = Style(
                    margin = EdgeValue(
                        top = UnitValue.real(15)
                    )
                )
            ),

            buttonWithAppearanceAndStyle(text = "Button with Appearance").setStyle {
                this.margin = EdgeValue(
                    top = UnitValue.real(15)
                )
            },

            buttonWithAppearanceAndStyle(
                text = "Button with Appearance and style",
                styleId = BUTTON_STYLE_APPEARANCE
            ).setStyle {
                this.margin = EdgeValue(
                    top = marginTop
                )
            },
            Button(
                text = "Disabled Button",
                styleId = "DesignSystem.Button.ScreenButton",
                onPress = listOf(Alert(message = "This button must be disabled")),
                enabled = false
            ),
            Button(
                text = constant("Disabled Button by context"),
                styleId = "DesignSystem.Button.ScreenButton",
                onPress = listOf(Alert(message = "This button must be disabled")),
                enabled = expressionOf("@{enabled}")
            )
        )
    )

    fun buildButtonAlignCenter() = createButtonScreen(
        alignContent = AlignContent.CENTER,
        marginTop = UnitValue.real(10))

    fun buildButtonAlignLeft() = createButtonScreen(
        alignContent = AlignContent.FLEX_START,
        marginTop = UnitValue.real(20))

    private fun buttonWithAppearanceAndStyle(text: String, styleId: String? = null) = createButton(
        text = text,
        styleId = styleId,
        style = Style(
            margin = EdgeValue(
                left = UnitValue.real(25),
                right = UnitValue.real(25),
                top = UnitValue.real(15)
            )
        )
    ).setStyle {
        this.backgroundColor = constant(CYAN_BLUE)
        this.cornerRadius = CornerRadius(radius = constant(16.0))
    }


    private fun createButton(
        text: String,
        styleId: String? = null,
        style: Style? = null
    ): Widget {
        val button = Button(
            text = text,
            styleId = styleId,
            onPress = listOf(Navigate.PushView(Route.Remote(SCREEN_ACTION_CLICK_ENDPOINT, true))))

        @Suppress("UNUSED_EXPRESSION")
        if (style != null) {
            button.setStyle { style }
        }

        return button
    }
}