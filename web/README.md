
# Structure
All tests are placed under the directory `apps`, which has the following structure:

- angular9: Angular application used to run the tests related to the angular components, in project common.
- react: React application used to run the tests related to the react components, in project common.
- common: Cypress tests.

# Tests

## Installation
First, install the dependencies:

```bash
yarn
cd apps/angular9
yarn
cd ../react
yarn
cd ../common
yarn
```

## Running

#### Backend
Access project ../bff-server to run the BFF server. It is required for all tests.

#### dos2unix tool

This tool helps to prevent the following error: "env: node\r: No such file or directory"    

brew install dos2unix

#### Applications

To test the Angular app after installing it, run the following command to start it:

```bash
# angular
cd apps/angular9
dos2unix -F node_modules/.bin/beagle
yarn start
```

To test the React app after installing it, run the following command to start it:

```bash
# react
cd apps/react
dos2unix -F node_modules/.bin/beagle
yarn start
```

#### Tests

```bash
# run all tests
yarn test
# run only tests for angular
yarn test:angular:9
# run only tests for react
yarn test:react
# run a specific feature
yarn test:angular:9 TAGS="@pageView"
```

When running the tests for the first time, it will take a while to check the installation of
Cypress.


