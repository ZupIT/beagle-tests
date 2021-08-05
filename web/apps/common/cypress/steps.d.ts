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

/* Code taken from the npm package @types/cypress-cucumber-preprocessor. They didn't declare the
library in a way it could be used in the global scope, so we had to copy the types and redeclare
them as globals. */

declare function Given(
  expression: RegExp | string,
  implementation: (...args: any[]) => void
): void;
declare function When(
  expression: RegExp | string,
  implementation: (...args: any[]) => void
): void;
declare function Then(
  expression: RegExp | string,
  implementation: (...args: any[]) => void
): void;
declare function And(
  expression: RegExp | string,
  implementation: (...args: any[]) => void
): void;
declare function But(
  expression: RegExp | string,
  implementation: (...args: any[]) => void
): void;
declare function Given(
  expression: RegExp | string,
  config: { timeout?: number },
  implementation: (...args: any[]) => void
): void;
declare function When(
  expression: RegExp | string,
  config: { timeout?: number },
  implementation: (...args: any[]) => void
): void;
declare function Then(
  expression: RegExp | string,
  config: { timeout?: number },
  implementation: (...args: any[]) => void
): void;
declare function And(
  expression: RegExp | string,
  config: { timeout?: number },
  implementation: (...args: any[]) => void
): void;
declare function But(
  expression: RegExp | string,
  config: { timeout?: number },
  implementation: (...args: any[]) => void
): void;
declare function Before(
  optionsOrImplementation: object | ((...args: any[]) => void),
  implementation?: (...args: any[]) => void
): void;
declare function After(
  optionsOrImplementation: object | ((...args: any[]) => void),
  implementation?: (...args: any[]) => void
): void;
