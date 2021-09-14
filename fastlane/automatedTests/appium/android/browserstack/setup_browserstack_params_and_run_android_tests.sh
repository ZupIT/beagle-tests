#!/bin/bash

#
# Copyright 2020 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
#
# Licensed under the Apache License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
#     http://www.apache.org/licenses/LICENSE-2.0
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

set -e

# setar platform e device, de acordo com a versao... verificar se faz isso aqui ou no actions

echo "Running Appium tests..."
if ./appium/project/gradlew --build-cache -p appium/project cucumber \
-Dplatform="android" \
-Dplatform_version="11.0" \
-Ddevice_name="Google Pixel 4" \
-Dbrowserstack_user="$BROWSERSTACK_USER" \
-Dbrowserstack_key="$BROWSERSTACK_KEY" \
-Dapp_file="$BROWSERSTACK_UPLOAD_ID" \
-Dbff_base_url="$BFF_URL"; then
  echo "Gradle task succeeded" >&2
else
  echo "Gradle task failed" >&2
  exit 1
fi