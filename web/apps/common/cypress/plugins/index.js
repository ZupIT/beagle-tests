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

/// <reference types="cypress" />
// ***********************************************************
// This example plugins/index.js can be used to load plugins
//
// You can change the location of this file or turn off loading
// the plugins file with the 'pluginsFile' configuration option.
//
// You can read more here:
// https://on.cypress.io/plugins-guide
// ***********************************************************

// This function is called when a project is opened or re-opened (e.g. due to
// the project's config changing)

const browserify = require('@cypress/browserify-preprocessor')
const cucumber = require('cypress-cucumber-preprocessor').default
const resolve = require('resolve')
const { addMatchImageSnapshotPlugin } = require('cypress-image-snapshot/plugin')

/**
 * @type {Cypress.PluginConfig}
 */
module.exports = (on, config) => {

  addMatchImageSnapshotPlugin(on, config);

  const options = {
    ...browserify.defaultOptions,
    typescript: resolve.sync('typescript', { baseDir: config.projectRoot }),
  }

  on('file:preprocessor', cucumber(options))
  on('task', {
    log(message) {
      console.log(message)
      return null
    }
  })

  on('before:browser:launch', (browser, launchOptions) => {
    if (browser.name === 'chrome' && browser.isHeadless) {
      launchOptions.args.push('--disable-extensions')

      // enforces the viewPort configured in cypress.json is applied in headless mode
      // src: https://docs.cypress.io/api/plugins/browser-launch-api#Set-screen-size-when-running-headless
      launchOptions.args.push('--force-device-scale-factor=1')
    }

    return launchOptions
  })
}

