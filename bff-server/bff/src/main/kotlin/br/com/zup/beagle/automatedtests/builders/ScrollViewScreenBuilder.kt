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
import br.com.zup.beagle.ext.setStyle
import br.com.zup.beagle.widget.action.SetContext
import br.com.zup.beagle.widget.context.ContextData
import br.com.zup.beagle.widget.core.AlignSelf
import br.com.zup.beagle.widget.core.EdgeValue
import br.com.zup.beagle.widget.core.Flex
import br.com.zup.beagle.widget.core.ScrollAxis
import br.com.zup.beagle.widget.core.Size
import br.com.zup.beagle.widget.core.UnitValue
import br.com.zup.beagle.widget.layout.Container
import br.com.zup.beagle.widget.layout.Screen
import br.com.zup.beagle.widget.layout.ScrollView
import br.com.zup.beagle.widget.navigation.Touchable
import br.com.zup.beagle.widget.ui.Button
import br.com.zup.beagle.widget.ui.Text

const val PARAGRAPH = """Lorem ipsum diam luctus mattis arcu accumsan, at curabitur hac in dictum senectus neque,
    orci lorem aenean euismod leo. eu nunc tellus proin eget euismod lorem curabitur habitant nisi himenaeos 
    habitasse at quam, convallis potenti scelerisque aenean habitant viverra mollis fusce convallis dui
    urna aliquam. diam tristique etiam fermentum etiam nunc eget vel, ante nam eleifend habitant per senectus diam,
    bibendum lectus enim ultrices litora viverra. lorem fusce leo hendrerit himenaeos elementum aliquet nec,
    vestibulum luctus pretium diam tellus ligula conubia elit, a sodales torquent fusce massa euismod. et magna 
    imperdiet conubia sed netus vitae justo maecenas proin lorem, sapien nisi porttitor dolor facilisis pharetra 
    nam class. Morbi nullam odio accumsan quam urna sit tortor vulputate mi fames, elit molestie gravida ipsum
    dictumst aenean curabitur ultrices consectetur pharetra, auctor aenean diam pellentesque condimentum risus 
    diam scelerisque rutrum. conubia sem tincidunt cras venenatis tristique nisl duis rhoncus blandit, sed mattis 
    vulputate accumsan suscipit tristique imperdiet dui, ornare ipsum tempor viverra elementum consectetur euismod
    dapibus. ultricies in consectetur libero nam ultrices egestas quis volutpat ut nec sagittis eu, elementum
    malesuada ullamcorper dapibus donec aenean mattis odio mi nulla gravida. tellus metus imperdiet justo mattis 
    eros sodales potenti nibh nisl tincidunt, metus etiam cubilia amet donec primis sapien erat dictumst. Accumsan 
    etiam himenaeos tempor integer habitasse curae ac, tincidunt laoreet taciti nisl habitasse conubia, maecenas nec
    velit vitae amet varius. scelerisque vel fringilla consequat justo curabitur nam massa vitae, tempus tempor 
    sit torquent massa malesuada ullamcorper, laoreet elementum nam pharetra tempus nam mauris. sociosqu dictum 
    malesuada lectus suscipit ullamcorper aliquet pulvinar semper laoreet, vulputate aliquam nibh odio donec ligula
     bibendum suspendisse, facilisis ut lobortis lacus tortor hendrerit integer posuere. phasellus egestas dui hac 
    auctor faucibus purus accumsan arcu, sem vivamus rhoncus pharetra aliquam ornare curabitur rutrum, ut venenatis
     proin iaculis orci gravida molestie."""

object ScrollViewScreenBuilder {
    fun build() = Screen(
        child = ScrollView(
            scrollDirection = ScrollAxis.VERTICAL,
            children = listOf(
                Text("Beagle ScrollView").setStyle {
                    this.margin = EdgeValue(all = UnitValue.real(10))
                },
                getHorizontalScrollView(),
                getVerticalScrollView(),
                scrollviewWithinScrollview()
            )
        )
    )

    private fun getHorizontalScrollView() = Container(
        context = ContextData(id = "testScrollHorizontal", value = "Click to see the new text in horizontal"),
        children = listOf(
            ScrollView(
                children = listOf(
                    Text("Horizontal")
                        .setStyle {
                            this.padding = EdgeValue(right = UnitValue.real(10))
                        },
                    Touchable(
                        child = Text("@{testScrollHorizontal}")
                            .setStyle {
                                this.padding = EdgeValue(right = UnitValue.real(10))
                            },
                        onPress = listOf(
                            SetContext(contextId = "testScrollHorizontal", value = PARAGRAPH)
                        )
                    ),
                    Container(
                        context = ContextData(id = "testScrollViewWithRotation",
                            value = "Click to see the text change, rotate and scroll horizontally"),
                        children = listOf(
                            ScrollView(
                                children = listOf(
                                    Touchable(
                                        child = Text("@{testScrollViewWithRotation}"),
                                        onPress = listOf(
                                            SetContext(contextId = "testScrollViewWithRotation", value = PARAGRAPH)
                                        )
                                    ),
                                    Button(text = "horizontal scroll")
                                        .setStyle {
                                            this.margin = EdgeValue(all = UnitValue.real(10))
                                            this.size = Size(width = UnitValue.real(100), height = UnitValue.real(100))
                                        }
                                        .setStyle {
                                            this.flex = Flex(alignSelf = AlignSelf.FLEX_START)
                                        },
                                ),
                                scrollDirection = ScrollAxis.HORIZONTAL
                            )
                        )
                    )
                ),
                scrollDirection = ScrollAxis.HORIZONTAL
            )
        )
    ).setStyle { this.padding = EdgeValue(left = UnitValue.real(10), right = UnitValue.real(10)) }

    private fun getVerticalScrollView() = Container(
        context = ContextData(id = "testScrollVertical", value = "Click to see the new text in vertical"),
        children = listOf(
            ScrollView(
                children = listOf(
                    Text("Vertical").setStyle { margin = EdgeValue(bottom = UnitValue.real(10)) },
                    Touchable(
                        child = Text("@{testScrollVertical}"),
                        onPress = listOf(
                            SetContext(contextId = "testScrollVertical", value = PARAGRAPH)
                        )
                    ),
                    Container(
                        context = ContextData(id = "testScrollRotation",
                            value = "Click to see the text change, rotate and scroll vertically"),
                        children = listOf(
                            ScrollView(
                                children = listOf(
                                    Touchable(
                                        child = Text("@{testScrollRotation}"),
                                        onPress = listOf(
                                            SetContext(contextId = "testScrollRotation", value = PARAGRAPH)
                                        )
                                    ),
                                    Button(
                                        text = "vertical scroll"
                                    )
                                ),
                                scrollDirection = ScrollAxis.VERTICAL
                            )
                        )
                    )
                ),
                scrollDirection = ScrollAxis.VERTICAL
            )
        )
    ).setStyle {
        this.margin = EdgeValue(left = UnitValue.real(10))
    }

    private fun scrollviewWithinScrollview() = Container(
        context = ContextData(id = "testScrollWithinScroll", value = "Click to see the new text"),
        children = listOf(
            ScrollView(
                scrollDirection = ScrollAxis.VERTICAL,
                children = listOf(
                    Text("Vertical scroll within scroll")
                        .setStyle { this.margin = EdgeValue(top = UnitValue.real(10)) },
                    Touchable(
                        onPress = listOf(SetContext(contextId = "testScrollWithinScroll", value = PARAGRAPH)),
                        child = Text("@{testScrollWithinScroll}")
                            .setStyle { this.margin = EdgeValue(bottom = UnitValue.real(10)) }),
                    ScrollView(
                        children = listOf(
                            Text("Horizontal scroll within scroll")
                                .setStyle { this.margin = EdgeValue(right = UnitValue.real(10)) },
                            Button(text = "step")
                                .setStyle {
                                    this.margin = EdgeValue(all = UnitValue.real(10))
                                    this.size = Size(width = UnitValue.real(100), height = UnitValue.real(100))
                                },
                            Text("horizontal $PARAGRAPH"),
                            Button(text = "horizontal direction")
                                .setStyle {
                                    this.margin = EdgeValue(all = UnitValue.real(10))
                                    this.size = Size(width = UnitValue.real(100), height = UnitValue.real(100))
                                }
                                .setFlex {
                                    this.alignSelf = AlignSelf.BASELINE
                                }
                        ),
                        scrollDirection = ScrollAxis.HORIZONTAL
                    ),
                    Button(text = "vertical direction")
                        .setStyle {
                            this.margin = EdgeValue(all = UnitValue.real(10))
                        }
                )
            )
        )
    ).setStyle {
        this.padding = EdgeValue(
            bottom = UnitValue.real(20), left = UnitValue.real(10), right = UnitValue.real(10)
        )
    }

}


