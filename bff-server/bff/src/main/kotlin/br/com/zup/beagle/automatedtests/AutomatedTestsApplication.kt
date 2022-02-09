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
package br.com.zup.beagle.automatedtests

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import java.util.*


@SpringBootApplication
open class AutomatedTestsApplication

fun main(args: Array<String>) {

    // Heroku support
    val port = if (System.getenv("PORT") != null) System.getenv("PORT").toInt() else 8080
    println("setting port to $port")

    val app = SpringApplication(AutomatedTestsApplication::class.java)
    app.setDefaultProperties(Collections
        .singletonMap<String, Any>("server.port", port))
    app.run(*args)
}