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

import UIKit
import Beagle

class DeepLinkViewController: UIViewController {
    
    var bffUrl:String = ""
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        // calls the bff screen using the bffUrl
        let viewController = BeagleScreenViewController(ScreenType.Remote(url: bffUrl), controllerId: "CustomBeagleNavigation", config: BeagleConfig.config())
        navigationController?.pushViewController(viewController, animated: false)
    }
}
