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

APP_FILE=$GITHUB_WORKSPACE/appium/app-android/app/build/outputs/apk/debug/app-debug.apk

echo "Uploading .apk file in BrowserStack..."
APP_UPLOAD_RESPONSE=$(curl -u "$BROWSERSTACK_USER:$BROWSERSTACK_KEY" \
-X POST https://api-cloud.browserstack.com/app-automate/upload \
-F "file=@$APP_FILE")

APP_ID=$(echo $APP_UPLOAD_RESPONSE | jq -r ".app_url")

if [ $APP_ID != null ]; then
  echo "Apk uploaded to BrowserStack!"
else
  UPLOAD_ERROR_MESSAGE=$(echo $APP_UPLOAD_RESPONSE | jq -r ".error")
  echo "App upload failed, reason : ",$UPLOAD_ERROR_MESSAGE
  exit 1;
fi

echo "$APP_ID" > browserstack_app_id.txt

