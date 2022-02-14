<p align="center">
  <img src="https://gblobscdn.gitbook.com/spaces%2F-M-Qy7jZbUpzGRP5GbCZ%2Favatar.png" width="256" height="256" />
</p>

<h1 align="center">
  <a href="https://usebeagle.io/">
    Beagle
  </a>
</h1>

### What is Beagle?

Beagle is a server-driven set of libraries that enable building screens for mobile and web using the same JSON.

Please visit Beagle's website https://usebeagle.io/ for more information

### About beagle-tests

This project contains tests that validate user interface of apps that use Beagle. The main folders are:

- [appium](./appium): Android and iOS tests. Refer to [Beagle's wiki](https://github.com/ZupIT/beagle/wiki/Mobile-tests-with-Appium)
  to get detailed info about Beagle's Appium tests implementation

- [bff-server](./bff-server): A Backend for Frontend (BFF) project that provides JSONs used by the mobile and web projects

- [web](./web): Angular and React tests

- beagle-android: A [GitHub Submodule](https://git-scm.com/book/en/v2/Git-Tools-Submodules), used as a dependency for mobile tests

- beagle-ios: A GitHub Submodule, used as a dependency for mobile tests

- beagle-backend-kotlin: A GitHub Submodule, used as a dependency for the bff-server project

- beagle-web-angular: A GitHub Submodule, used as a dependency for the web projects

### Cloning the main project and its submodules

Use the following command to automatically initialize and update each submodule in the repository, including nested 
submodules if any of the submodules in the repository have submodules themselves 
(src: https://git-scm.com/book/en/v2/Git-Tools-Submodules):

```git clone --recurse-submodules git@github.com:ZupIT/beagle-tests.git && git submodule update --remote```

If you already cloned the project and want only to update the submodules, execute the following command:

```git submodule update --remote```

### Contributing Guide

Read our [**Contributing Guide**][contribute] to learn about our development process, how to propose bugfixes and improvements, and how to build and test your changes to Beagle.

[contribute]: https://github.com/ZupIT/beagle/blob/main/CONTRIBUTING.md

### Code of Conduct

Beagle has adopted a Code of Conduct that we expect project participants to adhere to.
Please read the [code] so that you can understand what actions will and will not be tolerated.

[code]: https://github.com/ZupIT/beagle/blob/main/CODE_OF_CONDUCT.md

### 📄 License

Beagle is Apache 2.0 licensed, as found in the [LICENSE][l] file.

[l]: https://github.com/ZupIT/beagle/blob/main/LICENSE.txt
