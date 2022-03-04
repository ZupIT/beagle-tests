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

import Foundation
import Beagle

class BeagleConfig {
    static func config() {
        var dependencies = BeagleDependencies()
        
        dependencies.networkClient = NetworkClientDefault()
        dependencies.urlBuilder = UrlBuilder(baseUrl: URL(string: "http://127.0.0.1:8080/"))
        dependencies.logger = BeagleLoggerDefault()
        
        dependencies.deepLinkHandler = DeepLinkScreenManager()
        dependencies.analyticsProvider = LocalAnalyticsProvider.shared
        
        dependencies.navigator.registerNavigationController(
            builder: CustomBeagleNavigationController.init,
            forId: "CustomBeagleNavigation"
        )
        dependencies.navigator.registerNavigationController(
            builder: OtherBeagleNavigationController.init,
            forId: "otherController"
        )
        
        BeagleConfigurator.setup(dependencies: dependencies)
    }
}
