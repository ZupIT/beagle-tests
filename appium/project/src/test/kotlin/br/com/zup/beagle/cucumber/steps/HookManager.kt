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
import io.cucumber.java.After
import io.cucumber.java.Scenario
import org.apache.commons.io.FileUtils
import org.openqa.selenium.OutputType
import org.openqa.selenium.TakesScreenshot
import java.io.File

/**
 * Must be in the same package of cucumber steps
 */
class HookManager {

    /**
     * After each Scenario and its Examples
     */
    @After
    fun tearDownAfterScenario(scenario: Scenario) {

        // takes a screenshot of the screen on error
        if (scenario.isFailed) {
            try {
                val scrFile: File = (SuiteSetup.getDriver() as TakesScreenshot).getScreenshotAs(OutputType.FILE)
                val scenarioName = scenario.name.replace("[^A-Za-z0-9]".toRegex(), " ")
                val destFile =
                    File("${SuiteSetup.ERROR_SCREENSHOTS_ROOT_DIR}/${SuiteSetup.getPlatformDetails()}ERROR-${scenarioName}-${System.currentTimeMillis()}.png")

                if (destFile.exists())
                    destFile.delete()

                FileUtils.moveFile(
                    scrFile,
                    destFile
                )
            } catch (exception: Exception) {
                println("ERROR taking a screenshot on error: ${exception.message}")
            }

            SuiteSetup.resetApp()
        } else {

            /**
             * Android tests by default won't restart app anymore because they use deep
             * links to load BFF screens.
             * Refer to method loadBffScreen() in AbstractStep class for more details
             */
            if (SuiteSetup.isIos()) {
                SuiteSetup.resetApp()
            } else if (SuiteSetup.getPlatformVersion() == "4.4") { // deep links on Android is only supported in v 5.0+
                SuiteSetup.restartApp()
            }
        }


    }
}