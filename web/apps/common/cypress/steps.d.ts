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
