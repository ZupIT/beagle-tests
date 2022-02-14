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
chmod +x appium/project/gradlew
if appium/project/gradlew --build-cache -p appium/project cucumber \
-Dplatform="android" \
-Dplatform_version="$ANDROID_VERSION" \
-Ddevice_name="$DEVICE_NAME" \
-Dbff_base_url="$BFF_URL"; then
  echo "Gradle task succeeded" >&2
else
  echo "Gradle task failed" >&2
  exit 1
fi
