
# **Beagle-tests**

## **Table of Contents**

### 1. [**About**](#about)
### 2. [**Usage**](#usage)
### 3. [**Documentation**](#documentation)
### 4. [**Contributing**](#contributing)
### 5. [**Code of Conduct**](#code-of-conduct)
### 6. [**License**](#license)
### 7. [**Community**](#community)

## **About**
This project contains tests that validate the user interface of apps that use Beagle. The main folders are:

- [**appium**](./appium): Android and iOS tests. For more information about Beagle's Appium tests implementation, check out [**Beagle's wiki**](https://github.com/ZupIT/beagle/wiki/Mobile-tests-with-Appium).

- [**bff-server**](./bff-server): A Backend for Frontend (BFF) project that provides JSONs used by the mobile and Web projects.

- [**web**](./web): Angular and React tests.

- **beagle-android**: A [**GitHub Submodule**](https://git-scm.com/book/en/v2/Git-Tools-Submodules) used as a dependency for mobile tests.

- **beagle-ios**: A GitHub Submodule used as a dependency for mobile tests.

- **beagle-backend-kotlin**: A GitHub Submodule used as a dependency for the BFF--Server project.

- **beagle-web-angular**: A GitHub Submodule used as a dependency for web projects.

## **Usage**
### **Cloning the main project and its submodules**

Use the following command to automatically initialize and update each submodule in the repository, including [**nested submodules**](https://git-scm.com/book/en/v2/Git-Tools-Submodules) if any of the submodules in the repository have submodules themselves:

```
git clone --recurse-submodules git@github.com:ZupIT/beagle-tests.git && git submodule update --remote
```

If you already cloned the project and want only to update the submodules, execute the following command:

```
git submodule update --remote
```


## **Documentation**

You can find Beagle's documentation on our [**website**][site].

Beagle's documentation discusses components, APIs, and topics that are specific to [**Beagle documentation**][b-docs].

[site]: https://usebeagle.io/
[b-docs]: https://docs.usebeagle.io/


## **Contributing**

If you want to contribute to this module, access our [**Contributing Guide**][contribute] to learn about our development process, how to propose bug fixes and improvements, and how to build and test your changes to Beagle.

[contribute]: https://github.com/ZupIT/beagle/blob/main/CONTRIBUTING.md

### **Developer Certificate of Origin - DCO**

 This is a security layer for the project and for the developers. It is mandatory.
 
 Follow one of these two methods to add DCO to your commits:
 
**1. Command line**
 Follow the steps: 
 **Step 1:** Configure your local git environment adding the same name and e-mail configured at your GitHub account. It helps to sign commits manually during reviews and suggestions.

 ```
git config --global user.name “Name”
git config --global user.email “email@domain.com.br”
```

**Step 2:** Add the Signed-off-by line with the `'-s'` flag in the git commit command:

```
$ git commit -s -m "This is my commit message"
```

**2. GitHub website**
You can also manually sign your commits during GitHub reviews and suggestions, follow the steps below: 

**Step 1:** When the commit changes box opens, manually type or paste your signature in the comment box, see the example:

```
Signed-off-by: Name < e-mail address >
```

For this method, your name and e-mail must be the same registered on your GitHub account.

## **Code of Conduct**

Please read the [**code of conduct**](https://github.com/ZupIT/beagle/blob/main/CODE_OF_CONDUCT.md).

## **License**

[**Apache License 2.0**](https://github.com/ZupIT/beagle/blob/main/LICENSE.txt).

## **Community**
Do you have any question about Beagle? Let's chat in our [**forum**](https://forum.zup.com.br/).