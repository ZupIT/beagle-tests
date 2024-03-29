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

import br.com.zup.beagle.ext.setFlex
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.context.constant
import br.com.zup.beagle.widget.core.AlignSelf
import br.com.zup.beagle.widget.core.JustifyContent
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.layout.NavigationBar
import br.com.zup.beagle.widget.layout.Screen
import br.com.zup.beagle.widget.ui.Button
import br.com.zup.beagle.widget.ui.Text

object BuilderKotlinDslScreenBuilder {
    fun build(): Screen {
        return Screen(
            navigationBar = NavigationBar(
                title = "Navigation Bar Title",
                showBackButton = true),
            child = Container(
                children = listOf(
                    Text(
                        text = constant("@{kotlinDSL}")
                    ),
                    Text("@{kotlinDSL}").setFlex {
                        this.alignSelf = AlignSelf.FLEX_START
                    },
                    Button("Beagle Button")
                ),
                context = ContextData(id = "kotlinDSL", value = "Hello There")
            ).setFlex {
                this.grow = 1.0
                this.justifyContent = JustifyContent.CENTER
            })
    }
}
